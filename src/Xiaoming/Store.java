package Xiaoming;

import java.util.*;

public class Store {
    Map<String, ArrayList<Book> > book_shelf = new HashMap<String, ArrayList<Book> >();
    Map<String, ArrayList<Food> > food_shelf = new HashMap<String, ArrayList<Food> >();

    public void add(Commodity t){
        if(t instanceof Food)
            add((Food) t);
        else
            add((Book) t);
    }

    void add(Food t){
        ArrayList<Food> tfood;
        if(food_shelf.get(t.get_name()) != null){
            tfood = food_shelf.get(t.get_name());

        }
        else{
            tfood = new ArrayList<Food>();
        }
        tfood.add(t);
        food_shelf.put(t.get_name(), tfood);
    }

    void add(Book t){
        ArrayList<Book> tbook;
        if(book_shelf.get(t.get_name()) != null){
            tbook = book_shelf.get(t.get_name());
        }
        else{
            tbook = new ArrayList<Book>();
        }
        tbook.add(t);
        book_shelf.put(t.get_name(), tbook);
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
            String selct = IN.next();
            if(selct.equals("y")|| selct.equals("yes")){
                tbook.remove(thbook);
                book_shelf.put(s, tbook);
                return true;
            }
            else{
                throw new BuyException("Termination of transaction");
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
                String selct = IN.next();
                if(selct.equals("y")|| selct.equals("yes")){
                    tfood.remove(thfood);
                    food_shelf.put(s, tfood);
                    return true;
                }
                else{
                    throw new BuyException("Termination of transaction");
                }
            }
            else {
                tfood.remove(thfood);
            }
        }
        throw new BuyBookException("This food is sold out");
    }
}
