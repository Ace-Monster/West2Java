import java.security.MessageDigest;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

class A{
    A(){ }
    A(ResultSet rs) {
        try {
            if(!rs.isAfterLast()){
                System.out.println(rs.getString("name"));
                rs.next();
            }else{
                System.out.println("no");
            }
        }catch (SQLException ex){
            System.out.println("1");
        }
    }
}
public class Test {
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

    public static void main(String[] args) {
        try {
            Con();
            ResultSet rs = st.executeQuery("select * from user");
            rs.next();
            A a = new A(rs);
            A b = new A(rs);
            A c = new A(rs);
        }catch (ClassNotFoundException e){
            System.out.println("2");
        }catch (SQLException x){
            System.out.println("3");
        }

        return;
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
