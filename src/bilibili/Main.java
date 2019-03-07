package bilibili;

import com.google.gson.Gson;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static public void main(String argc[]) throws SQLException, ClassNotFoundException {
        ConnectClass MySql = new ConnectClass();
        Statement st = MySql.conn.createStatement();

        //String turl = "https://www.bilibili.com/video/av";
        //int id = st.executeQuery("select * from wait_que where video_id is not null").getInt("video_id");
        //for(int i = id;id < )

        String url = "https://api.bilibili.com/x/web-interface/view?aid=11657629";
        String doc;
        try {
            doc = Jsoup.connect(url).ignoreContentType(true).get().body().text();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
            return;
        }
        Gson gson = new Gson();
        JasonBean js = gson.fromJson(doc, JasonBean.class);
        System.out.println(js.getData().getAid());
    }
}
