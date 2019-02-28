import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Cmd {

    static Connection conn;

    static String getAccount() throws FileNotFoundException {
        Scanner ACC = new Scanner(new File("Account.txt"));
        return ACC.next();
    }

    static String getPassword() throws FileNotFoundException{
        Scanner PWD = new Scanner(new File("Password.txt"));
        return PWD.next();
    }

    static Profile Login(Statement st) throws java.sql.SQLException{
        Scanner IN = new Scanner(System.in);
        System.out.print("Username:");
        String user = IN.next();
        System.out.print("Password:");
        String pswd = IN.next();
        ResultSet rs = st.executeQuery("select * from user where username='" + user + "'");
        if(!rs.isBeforeFirst())
            throw new LoginException("Account does not exist.");
        rs.next();
        if(!pswd.equals(rs.getString("password")))
            throw new LoginException("Wrong password");
        return new Profile(rs);
    }

    //static

    static void PeoFace(Profile peo) throws SQLException{
        Scanner IN = new Scanner(System.in);
        while(true){
            System.out.println("1.Print Info");
            System.out.println("2.Setting");
            System.out.println("3.Change Password");
            System.out.println("4.Logout");
            int op = IN.nextInt();
            if(op == 1){
                peo.PrintInfo();
            }else if(op == 2){
                Statement st = conn.createStatement();
                Profile tPeo;
                try{
                    tPeo = Profile.Setting(peo);
                } catch (SettingException tExc){
                    System.out.println(tExc.getMessage());
                    continue;
                }
                String sql = "select password from user where id=" + peo.getId();
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                String pswd = rs.getString("password");
                System.out.print("Please input your password to change your setting:");
                String Pwd = IN.next();
                if(pswd.equals(Pwd)){
                    peo = tPeo;
                    sql = "update user set name='" + peo.getName() +
                            "',gender='" + peo.getGender() +
                            "',phone=" + peo.getPhone() +
                            " where id=" + peo.getId();
                    st.executeUpdate(sql);
                    System.out.println("Change success.");
                }else{
                    System.out.println("Wrong password.");
                    continue;
                }
            }else if (op == 3) {
                Statement st = conn.createStatement();
                System.out.print("Please input your old password:");
                String Pwd = IN.next();
                String sql = "select password from user where id=" + peo.getId();
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                if(!Pwd.equals(rs.getString("password"))){
                    System.out.println("Wrong password.");
                    continue;
                }
                System.out.print("Please input your new password:");
                Pwd = IN.next();
                System.out.print("Once again:");
                if(!Pwd.equals(IN.next())){
                    System.out.println("Is incorrect of twice input.");
                    continue;
                }
                sql = "update user set password='" + Pwd + "' where id=" + peo.getId();
                st.executeUpdate(sql);
                System.out.println("Change success.");
            }else if(op == 4){
                System.out.println("Logouted.");
                break;
            }else{
                System.out.println("Wrong operate.");
            }
        }
    }

    static void Register(Statement st) throws java.sql.SQLException{
        Scanner IN = new Scanner(System.in);
        Profile peo = new Profile();
        while(true){
            System.out.print("Please input your username:");
            peo.setUsername(IN.next());
            ResultSet rs = st.executeQuery("select * from user where username='" + peo.getUsername() + "'");
            if(!rs.isBeforeFirst())
                break;
            System.out.println("This username is exist, please change another.");
        }
        System.out.println("Congratulations!This username is Available.");
        String Pwd;
        while (true){
            System.out.print("Please input your new password:");
            Pwd = IN.next();
            System.out.print("Once again:");
            if(!Pwd.equals(IN.next())){
                System.out.println("Is incorrect of twice input.");
                continue;
            }
            break;
        }
        ResultSet rs = st.executeQuery("select max(id) from user");
        rs.next();
        peo.setId(rs.getInt("max(id)") + 1);
        String sql = "insert into user (id,username,password) values (" + peo.getId()
                                            + ",'" + peo.getUsername() + "','" + Pwd + "')";
        st.execute(sql);
        System.out.println("Register success.");
    }

    public static void main(String[] argc) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/User?characterEncoding=utf8&useSSL=false";
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
        conn = DriverManager.getConnection(url, Account, Password);
        Statement st = conn.createStatement();
        while(true){
            System.out.println("Welcome!Please login or register.");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Exit");
            Scanner IN = new Scanner(System.in);
            int op = IN.nextInt();
            if(op == 1){
                Profile peo;
                try{
                    peo = Login(st);
                } catch (java.sql.SQLException tExc){
                    System.out.println("Account dose not exist");
                    continue;
                } catch (LoginException tExc){
                    System.out.println(tExc.getMessage());
                    continue;
                }
                System.out.println("Login success!");
                PeoFace(peo);
            }else if(op == 2){
                Register(st);
            }else if(op == 3){
                break;
            }else{
                System.out.println("Wrong operate.");
            }
        }
        st.close();
        conn.close();
    }
}
