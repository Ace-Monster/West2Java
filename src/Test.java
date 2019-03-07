import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import bilibili.ConnectClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.net.www.http.HttpClient;


import java.security.MessageDigest;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JsonBean{
    JsonBean() { }
    public Data data;
}

class Data{
    Data() { }
    public int aid;
    public int view;
}

public class Test {

    public static void main(String[] args) {
            String doc = "{\n" +
                    "    \"code\": 0,\n" +
                    "    \"message\": \"0\",\n" +
                    "    \"ttl\": 1,\n" +
                    "    \"data\": {\n" +
                    "        \"aid\": 2,\n" +
                    "        \"view\": 728590,\n" +
                    "        \"danmaku\": 38769,\n" +
                    "        \"reply\": 36434,\n" +
                    "        \"favorite\": 20872,\n" +
                    "        \"coin\": 7764,\n" +
                    "        \"share\": 3726,\n" +
                    "        \"like\": 18633,\n" +
                    "        \"now_rank\": 0,\n" +
                    "        \"his_rank\": 0,\n" +
                    "        \"no_reprint\": 0,\n" +
                    "        \"copyright\": 2,\n" +
                    "        \"argue_msg\": \"\"\n" +
                    "    }\n" +
                    "}";
            //String doc = Jsoup.connect(url).ignoreContentType(true).get().body().text();
            Gson gson = new Gson();
            JsonBean a = gson.fromJson(doc, JsonBean.class);
            System.out.println("aid="+a.data.aid);
            System.out.println("view"+a.data.view);
            /*
        try {
            String x = "https://www.qu.la/book/1/";
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(x.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
            }
            System.out.println(result);

        } catch (UnsupportedEncodingException ex) {
            System.out.println("1");
        }catch (NoSuchAlgorithmException e){
            System.out.println("2");
        }*/




        //String url = "https://api.bilibili.com/x/v2/reply?callback=jQuery17209674413220854512_1551807914163&jsonp=" +
        //        "jsonp&pn=1&type=1&oid=2&sort=0&_=1551807925940";//评论接口
        //String url = "http://interface.bilibili.com/player?id=cid:62131&aid=2";//没搞懂但是好像有用接口
        //String url = "http://api.bilibili.com/x/tag/archive/tags?aid=2";//标签集合接口
        //String url = "https://api.bilibili.com/x/web-interface/archive/stat?aid=2";//基础信息接口
        String url = "https://api.bilibili.com/x/web-interface/view?aid=2";//视频信息接口
        //String url = "https://api.bilibili.com/x/v1/dm/list.so?oid=62131";//弹幕接口
        //String doc = Jsoup.connect(url).ignoreContentType(true).execute().body();
    }
}
