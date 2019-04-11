package bilibili;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectClass {

    static String getAccount() throws FileNotFoundException {
        Scanner ACC = new Scanner(new File("Account.txt"));
        return ACC.next();
    }

    static String getPassword() throws FileNotFoundException{
        Scanner PWD = new Scanner(new File("Password.txt"));
        return PWD.next();
    }

    Connection conn;

    public Connection getConn(){
        return  conn;
    }

    public ConnectClass() throws ClassNotFoundException, SQLException {
        String sqlurl = "jdbc:mysql://59.77.134.33:3306/User?characterEncoding=utf8&useSSL=false";
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
    }
}
