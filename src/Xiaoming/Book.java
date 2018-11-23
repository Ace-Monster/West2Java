package Xiaoming;

import java.util.Scanner;

public class Book extends Commodity {
    private int pages;

    public Book(){ }
    public Book(String n,int p){
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
