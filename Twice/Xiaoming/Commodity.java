package Xiaoming;

public class Commodity {
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
