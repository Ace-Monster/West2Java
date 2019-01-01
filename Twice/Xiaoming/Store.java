package Xiaoming;

import javax.swing.*;
import java.util.*;

public class Store {
    Map<String, ArrayList<Book> > book_shelf = new HashMap<String, ArrayList<Book> >();
    Map<String, ArrayList<Food> > food_shelf = new HashMap<String, ArrayList<Food> >();

    private int sel(String select){
        if(select.equals("y")|| select.equals("yes"))
            return 1;
        else if (select.equals("n")|| select.equals("no"))
            return -1;
        return 0;
    }

    private boolean addsel(String kind, String dif){
        System.out.println("The same " + kind + " exists, but the " + dif +" is different.");
        System.out.println("Do you want to add it? y/n");
        Scanner IN = new Scanner(System.in);
        String select = IN.next();
        int se = sel(select);
        if(se == -1){
            return false;
        }else if(se == 0){
            System.err.println("Wrong operator.");
            return false;
        }
        return true;
    }

    public void add(Commodity t){
        boolean flag = false;
        if(t instanceof Food)
            flag = add((Food) t);
        else
            flag = add((Book) t);
        if(flag)
            System.out.println("Add success");
        else
            System.err.println("Add failed.");
    }

    boolean add(Food t){
        ArrayList<Food> tfood;
        if(food_shelf.get(t.get_name()) != null){
            tfood = food_shelf.get(t.get_name());
            if(!tfood.isEmpty()){
                if(tfood.get(0).get_price() != t.get_price()){
                    if(!addsel("food", "price"))
                        return false;
                }
            }
        }
        else{
            tfood = new ArrayList<Food>();
        }
        tfood.add(t);
        food_shelf.put(t.get_name(), tfood);
        return true;
    }

    boolean add(Book t){
        ArrayList<Book> tbook;
        if(book_shelf.get(t.get_name()) != null){
            tbook = book_shelf.get(t.get_name());
            if(!tbook.isEmpty()){
                if(tbook.get(0).get_price() != t.get_price())
                    if(!addsel("book", "price"))
                        return false;
                if(tbook.get(0).get_pages() != t.get_pages()){
                    if(!addsel("book", "pages"))
                        return false;
                }
            }
        }
        else{
            tbook = new ArrayList<Book>();
        }
        tbook.add(t);
        book_shelf.put(t.get_name(), tbook);
        return true;
    }

    public boolean buy(String s){
        try{
            return buy_book(s);
        }catch (BuyBookException ex1){
            try {
                return buy_food(s);
            }catch (BuyFoodException ex2){
                throw new BuyException("No exist this commodity");
            }
        }
    }

    boolean buy_book(String s){
        if(book_shelf.get(s) == null) throw new BuyBookException("No exist this book");
        ArrayList<Book> tbook = book_shelf.get(s);
        if(tbook.size() != 0){
            Book thbook = tbook.get(0);
            System.out.println(thbook);
            System.out.println("This book is price "+thbook.get_price()+",do you want to buy? y/n");
            Scanner IN = new Scanner(System.in);
            String select = IN.next();
            int se = sel(select);
            if(se == 1){
                tbook.remove(thbook);
                book_shelf.put(s, tbook);
                return true;
            }
            else if (se == -1) {
                throw new BuyException("Termination of transaction");
            }else{
                throw new BuyException("Wrong operator");
            }
        }
        else {
            throw new BuyBookException("This book is sold out");
        }
    }

    boolean buy_food(String s) {
        if(food_shelf.get(s) == null) throw new BuyBookException("No exist this book");
        ArrayList<Food> tfood = food_shelf.get(s);
        for(int i = 0;i < tfood.size();){
            Food thfood = tfood.get(i);
            if(thfood.isValid()){
                System.out.println(thfood);
                System.out.println("This food is price "+thfood.get_price()+",do you want to buy? y/n");
                Scanner IN = new Scanner(System.in);
                String select = IN.next();
                int se = sel(select);
                if(se == 1){
                    tfood.remove(thfood);
                    food_shelf.put(s, tfood);
                    return true;
                }
                else if (se == -1) {
                    throw new BuyException("Termination of transaction");
                }else{
                    throw new BuyException("Wrong operator");
                }
            }
            else {
                tfood.remove(thfood);
            }
        }
        throw new BuyBookException("This food is sold out");
    }
}
