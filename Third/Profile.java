import java.sql.ResultSet;
import java.util.Scanner;

public class Profile implements Cloneable{
    int id;
    String username;
    String name;
    String gender;
    int phone;

    Profile() { }

    Profile(ResultSet rs) throws java.sql.SQLException{
        id = rs.getInt("id");
        username = rs.getString("username");
        name = rs.getString("name");
        gender = rs.getString("gender");
        phone = rs.getInt("phone");
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void PrintInfo(){
        System.out.println("Username:"+username);
        System.out.println("Name:"+name);
        System.out.println("Gender:"+gender);
        System.out.println("Phone:"+phone);
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    @Override
    public Object clone(){
        Profile tPeo = null;
        try{
        tPeo = (Profile)super.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return tPeo;
    }

    public static Profile Setting(Profile Peo){
        Profile tPeo = (Profile) Peo.clone();
        Scanner IN = new Scanner(System.in);
        int op;
        while(true) {
            System.out.println("Setting:");
            System.out.println("1.Name");
            System.out.println("2.Gender");
            System.out.println("3.Phone");
            System.out.println("4.Confirm");
            System.out.println("5.Cancel");
            op = IN.nextInt();
            if(op == 1){
                System.out.print("Please input your new name:");
                tPeo.setName(IN.next());
            }else if(op == 2){
                System.out.println("Please change your gender:");
                System.out.println("1.Male");
                System.out.println("2.Female");
                op = IN.nextInt();
                if(op == 1){
                    tPeo.setGender("Male");
                }else if(op == 2) {
                    tPeo.setGender("Female");
                }else{
                    System.out.println("Wrong input.");
                }
            }else if(op == 3){
                System.out.print("Please input your new phone number:");
                tPeo.setPhone(IN.nextInt());
            }else if(op == 4){
                System.out.println("Your new Info:");
                tPeo.PrintInfo();
                System.out.println("Confirm?Y/N");
                String se = IN.next();
                se = se.toUpperCase();
                if(se.equals("Y") || se.equals("YES")){
                    return tPeo;
                }
            }else if(op == 5){
                System.out.println("Cancel?Y/N");
                String se = IN.next();
                se = se.toUpperCase();
                if(se.equals("Y") || se.equals("YES")){
                    throw new SettingException("Cancel change.");
                }
            }else{
                System.out.println("Wrong operator.");
            }
        }
    }
}
