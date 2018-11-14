import java.util.Scanner;

class Commodity{

    private String name;
    private int price;

    public Commodity(){ }
    public Commodity(String n, int p){
        name = n;
        price = p;
    }

    public String get_name(){
        return name;
    }
    public void set_name(String n){
        name = n;
    }

    public int get_price(){
        return price;
    }
    public void set_price(int p){
        price = p;
    }
}

class Books extends Commodity{

    private int pages;

    public Books(){ }
    public Books(String n,int p){
        super(n,p);
        Scanner IN = new Scanner(System.in);
        System.out.print("Number of pages:");
        pages = IN.nextInt();
    }

    public int get_pages(){
        return pages;
    }
    public void set_pages(int p){
        pages = p;
    }

    @Override
    public String toString() {
        String t = "Book name:" + get_name() + "\nPrice:" +
                get_price() + "\nNumber of pages:" + get_pages() + "\n";
        return super.toString();
    }
}

class Time{
    public int year;
    public int month;
    public int day;

    public Time() { }
    public Time(int y,int m,int d) {
        year = y;
        month = m;
        day = d;
    }
    public Time(boolean flag){
        Scanner IN = new Scanner(System.in);
        System.out.print("Year:");
        year = IN.nextInt();
        System.out.print("Month:");
        month = IN.nextInt();
        System.out.print("Day:");
        day = IN.nextInt();
    }



    @Override
    public String toString(){
        String t = year + "." + month + "." + day;
        return t;
    }
}

class Food extends Commodity{
    Time in;
    Time out;

    Food(){ }
    Food(String n,int p){
        super(n,p);
        System.out.println("Production Date:");
        in = new Time(true);
        System.out.println("Fresh Date:");
        out = new Time(true);
    }

    @Override
    public String toString(){
        String t = "Food name:" + get_name() + "\nPrice:" + get_price() +
                "\nProduction Date:" + in + "\nFresh Date:" + out;
        return t;
    }
}

public class Shop {
    public static void main(String[] args){

    }
}
