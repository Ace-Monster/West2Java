package qu.la;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class toRun implements Runnable{
    ResultSet rs;
    Statement st;
    boolean flag;
    int bookid;
    public toRun(ResultSet a, Statement b, int c){
        rs = a;st = b;bookid = c;
    }

    public boolean getFlag(){
        return flag;
    }

    @Override
    public void run(){
        flag = false;
        while(true) {
            String url = null;
            try{
                url = rs.getString("url");//获取url
            }catch (SQLException ex1){
                System.out.println(ex1.getMessage());
                break;//获取失败，从发生的可能上只有rs结果集指针为末尾
            }
            try {
                rs.next();
                int id = 0;
                id = Main.getUrlID(url);
                if (id != 0) {
                    if(st.executeQuery("select * from bookid_"+bookid+" where id="+id).next()) {
                        st.execute("delete from bookid_"+bookid+" where id="+id);
                        continue;//判重
                    }
                    Document document = Jsoup.connect(url).timeout(100000).get();//获取html
                    String name = document.getElementsByTag("h1").get(0).text();//章节名称

                    Element content = document.getElementById("content");
                    String str = content.toString();//获取章节文本
                    {
                        str = str.replaceAll("&nbsp;", "");
                        str = str.replaceAll("<div id=\"content\">", "");
                        str = str.replaceAll("　", " ");
                        str = str.replaceAll("\\r|\\s|\\n|\\t*", "");
                        str = str.replaceAll("<br>", "\n");
                        str = str.replaceAll("<script>chaptererror\\(\\);</script></div>", "\n");
                        str = str.replaceAll("\\n+", "\n");
                    }//章节内容处理

                    String sql = "insert into bookid_"+bookid+" (id, `name`, url, content) value " +
                            "("+id+", \""+name+"\", \""+url+"\", \""+str+"\") " +
                            "on duplicate key update id=values(id);";//判重后依旧可能发生时间片的切换
                    st.execute(sql);
                    st.execute("delete from wait_que where id="+id);
                }
            } catch (SQLException ex1) {
                System.out.println(ex1.getMessage());
            } catch (IOException ex2) {
                System.out.println(ex2.getMessage());
            }
        }
        flag = true;
    }
}