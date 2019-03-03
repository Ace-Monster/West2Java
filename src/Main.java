import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    }

    static public int getUrlID(String url){
        Pattern pattern  = Pattern.compile("\\d+");
        Matcher matcher  = pattern.matcher(url);
        String sid = "Null";
        while (matcher.find())
            sid = matcher.group();
        return Integer.parseInt(sid);
    }

    static void getBook(int bookid){
        ResultSet rs;
        while(true) {
            try {
                rs = st.executeQuery("select * from wait_que where url is not null");
                if(!rs.isBeforeFirst()) //结果集为空，获取结束
                    return;
                rs.next();
            } catch (SQLException ex) {
                System.out.println("rs2");
                return;
            }
            ArrayList<toRun> q = new ArrayList<toRun>();
            ArrayList<Thread> p = new ArrayList<Thread>();
            for (int i = 1; i <= 8; i++) {
                try {
                    toRun tt = new toRun(rs, conn.createStatement(), bookid);
                    Thread t = new Thread(tt);
                    t.start();
                    q.add(tt);
                    p.add(t);
                }catch (SQLException ex){
                    System.out.println("建立进程:"+i);
                    System.out.println(ex.getMessage());
                }
            }
            boolean flag = false;
            while (!flag) {
                flag = true;
                for (int i = 0; i < 8; i++) {
                    flag = (flag & q.get(i).getFlag());
                }
            }
        }
    }

    static void getUrl(Document doc, int bookid){
        Elements title = doc.getElementById("list").getElementsByTag("dd");
        for (int i = 0; i < title.size(); i++) {
            String url = title.get(i).select("a").attr("abs:href");//章节链接
            String name = title.get(i).text();
            int id = getUrlID(url);
            String sql = "insert into wait_que (id, url) value ("+id+", \""+url+"\") on duplicate key update id=values(id)";
            try {
                st.execute(sql);
            }catch (SQLException ex){
                System.out.println("inst url" + ex.getMessage());
            }
        }
    }

    static void updBook(int bookid, int isurl){
        try {
            st.execute("update wait_que set book_id=" + (bookid+1) + " is_url=" + 0 + "where book_id is not null");
        }catch (SQLException ex){
            System.out.println("upd " + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        String url = "https://www.qu.la/book/";
        Integer bookid = 0;
        Integer isurl = 0;
        ResultSet rs;
        try{
            Con();
        }catch (ClassNotFoundException ex1){
            System.out.println("ClassError");
            return;
        }catch (SQLException ex2){
            System.out.println(ex2.getMessage());
            return;
        }//连接数据库

        try {
            //获取当前书籍进度
            st.execute("use Book");
            rs = st.executeQuery("select * from wait_que where book_id is not null");
            rs.next();
            bookid = rs.getInt("book_id");
            isurl = rs.getInt("is_url");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return;
        }

        while(true) {
            if(bookid > 167020)//不要问我这个数怎么来的，问就开花
                break;

            //询问url是否取完，是直接进入getBook方法
            if(isurl == 1) {
                getBook(bookid);
                updBook(bookid, isurl);
                bookid++;
                isurl = 0;
            }

            String turl = url + bookid.toString();
            Document document;
            try {
                document = Jsoup.connect(turl).timeout(100000).get();
            }catch (IOException ex){
                System.out.println(ex.getMessage());
                break;
            }
            Element info = document.getElementById("maininfo");//主要信息
            if(info==null){ //不存在主要信息标签，判断此ID对应书籍不存在
                updBook(bookid, isurl);
                bookid++;
                isurl = 0;
                continue;
            }
            //标题
            String name = info.getElementById("info").getElementsByTag("h1").first().text();
            //作者
            String author = info.getElementById("info").getElementsByTag("p").first().text();
            //图片url
            String img = document.getElementById("fmimg").select("img").attr("abs:src");
            author = author.replaceAll("作 者：", "");
            //简介
            String intro = info.getElementById("intro").text();

            //插入信息
            String sql = "insert into Bookshelf (id, img, `name`, author, intro) value (" + bookid + ", \"" + img +
                    "\", \"" + name + "\", \"" + author + "\", \"" + intro+"\")" + "on duplicate key update id=values(id)";
            try {
                st.execute(sql);
            } catch (SQLException ex){
                System.out.println("inst info " + ex.getMessage());
            }

            try{
                st.execute("create table bookid_"+bookid+" (id int not null, url text, `name` char(255), content text, " +
                        "primary key (id))");
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }

            //章节名称、url获取
            getUrl(document, bookid);
            try {
                st.execute("update wait_que set is_url=1 where book_id is not null");
            }catch (SQLException ex){
                System.out.println("up2 "+ex.getMessage());
            }
            isurl = 1;

            //调用getBook方法
            getBook(bookid);

            //更新断点信息
            updBook(bookid, isurl);
            bookid++;
            isurl = 0;
        }
    }
}
