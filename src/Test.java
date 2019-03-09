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
    public ArrayList<Replies> replies;
}

class Replies{
    Replies() { }
    public Content content;
}

class Content{
    Content() { }
    public String message;
}

public class Test {

    public static void main(String[] args) throws IOException{
        String url = "https://api.bilibili.com/x/v1/dm/list.so?oid=19259624";
        Document doc = Jsoup.connect(url).get();
        Elements els = doc.getElementsByTag("d");
        for(int i = 0;i < els.size();i++){
            System.out.println(els.get(i).text());
        }
        //String url = "";
        //Jsoup.connect(url).get();
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
    }
}
