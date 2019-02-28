import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.annotation.Documented;
import java.sql.*;
import java.util.Scanner;

class toRun implements Runnable{

    public toRun(){

    }

    @Override
    public void run(){

    }
}

public class Main {

    static String getAccount() throws FileNotFoundException {
        Scanner ACC = new Scanner(new File("Account.txt"));
        return ACC.next();
    }

    static String getPassword() throws FileNotFoundException{
        Scanner PWD = new Scanner(new File("Password.txt"));
        return PWD.next();
    }

    static Connection conn;
    static Statement st;

    static void Con() throws ClassNotFoundException, SQLException {
        String sqlurl = "jdbc:mysql://127.0.0.1:3306/User?characterEncoding=utf8&useSSL=false";
        String Account;
        try{
            Account = getAccount();
        }catch (FileNotFoundException tExc){
            System.out.println("Account.txt is not found");
            return;
        }
        String Password;
        try{
            Password = getPassword();
        } catch (FileNotFoundException tExc){
            System.out.println("Password.txt is not found");
            return;
        }
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(sqlurl, Account, Password);
        st = conn.createStatement();
        return;
    }

    static void getBook(String booktab){
        ResultSet rs;
        try {
            rs = st.executeQuery("select * from wait_que where book_id is null");
            if(!rs.isBeforeFirst()) return;
            while(rs.next()){

            }
        }catch (SQLException ex){
            System.out.println("rs2");
            return;
        }
    }

    public static void main(String args[]) {
        String url = "https://www.qu.la/book/";
        Integer bookid = 0;
        ResultSet rs;
        try{
            Con();
            //获取当前书籍进度
            rs = st.executeQuery("select * from wait_que where book_id is not null");
            rs.next();
            bookid = rs.getInt("book_id");
        }catch (ClassNotFoundException ex1){
            System.out.println("ClassError");
            return;
        }catch (SQLException ex2){
            System.out.println("SQLError");
            return;
        }//连接数据库

        try {
            while(true) {
                //询问章节队列是否为空，否进入getBook方法

                String turl = url + bookid.toString();
                Document document = Jsoup.connect(turl).get();
                //File a = new File("a.txt");
                //System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(a)), true));

                //图片url
                String img = document.getElementById("fmimg").select("img").attr("abs:src");
                System.out.println("img:" + img);

                //主要信息
                Element info = document.getElementById("maininfo");
                String name = info.getElementById("info").getElementsByTag("h1").first().text();
                System.out.println("name:" + name);
                String author = info.getElementById("info").getElementsByTag("p").first().text();
                author = author.replaceAll("作 者：", "");
                System.out.println("author:" + author);

                //简介
                String intro = info.getElementById("intro").text();
                System.out.println("intro:" + intro);

                //章节名称获取
                Elements title = document.getElementById("list").getElementsByTag("dd");
                for (int i = 0; i < title.size(); i++) {
                    System.out.println(title.get(i).select("a").attr("abs:href"));//章节链接
                    System.out.println(title.get(i).text());
                }

                //调用getBook方法

                //更新断点信
            }
        } catch (IOException ex){
            System.out.println("解析出错/解析结束");
            ex.printStackTrace();
        }
    }
}
