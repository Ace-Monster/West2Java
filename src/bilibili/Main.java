package bilibili;

import com.google.gson.Gson;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static ConnectClass MySql;
    static boolean getinfo(JsonBean js){
        try {
            Statement st = MySql.conn.createStatement();
            st.execute("insert into VideoSet (" +
                    "aid, title, img, intro, pubdate, `view`, danmaku, reply, `like`, favorite, coin, share, uid, `name`" +
                    ") value ("+
                    js.getData().getAid()+", \"" +
                    js.getData().getTitle()+"\", \"" +
                    js.getData().getImg()+"\", \"" +
                    js.getData().getIntro()+"\", " +
                    js.getData().getPubdate()+", " +
                    js.getData().getStat().getView()+", " +
                    js.getData().getStat().getDanmaku()+", " +
                    js.getData().getStat().getReply()+", " +
                    js.getData().getStat().getLike()+", " +
                    js.getData().getStat().getFavorite()+", " +
                    js.getData().getStat().getCoin()+", " +
                    js.getData().getStat().getShare()+", " +
                    js.getData().getOwner().getMid()+", \"" +
                    js.getData().getOwner().getName()+"\") on duplicate key update aid=values(aid)");
            int id = 0;
            ResultSet rs = st.executeQuery("select max(id) from PageSet");
            if(rs.next())
                id = rs.getInt("max(id)");
            id++;
            for(int i = 0;i < js.getData().getPages().size();i++){
                st.execute("insert into PageSet (id, aid, page, cid) value (" +
                        (id++)+", " +
                        js.getData().getAid()+", " +
                        js.getData().getPages().get(i).getPage()+", " +
                        js.getData().getPages().get(i).getCid()+")on duplicate key update aid=values(aid)");
            }
            return true;
        }catch (SQLException ex){
            System.out.println("getinfo SQL_error, js_id="+js.getData().getAid());
            System.out.println(ex.getMessage());
            return false;
        }
    }

    static public void main(String argc[]) throws SQLException, ClassNotFoundException {
        MySql = new ConnectClass();
        Statement st = MySql.conn.createStatement();
        st.execute("use Bilibili");

        int id = 0;
        ResultSet rs = st.executeQuery("select max(aid) from VideoSet");
        if(rs.next())
            id = rs.getInt("max(aid)");
        id++;
        while(true) {
            String url = "https://api.bilibili.com/x/web-interface/view?aid="+id;
            String doc;
            try {
                doc = Jsoup.connect(url).ignoreContentType(true).get().body().text();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return;
            }

            Gson gson = new Gson();
            JsonBean js = gson.fromJson(doc, JsonBean.class);
            if(js.getCode() != 0) {
                System.out.println(js.getMessage());
            }
            else{
                if(!getinfo(js)){
                    continue;
                }
            }
            id++;
        }
    }
}
