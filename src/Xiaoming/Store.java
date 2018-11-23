package Xiaoming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Store {
    ArrayList[] book_shelf = new ArrayList[100];
    ArrayList[] food_shelf = new ArrayList[100];
    Map<String, Integer> hash_book = new HashMap<String, Integer>();
    Map<String, Integer> hash_food = new HashMap<String, Integer>();

    public void add(Commodity t){
        if(t instanceof Food)
            add((Food) t);
        else
            add((Book) t);//ok
    }

    void add(Food t){
        if(hash_food.get(t.get_name()) != null) {
            food_shelf[hash_food.get(t.get_name())].add(t);
        }
        else {
            for (int i = 0; i < 100; i++) {
                if (food_shelf[i].isEmpty()) {
                    hash_food.put(t.get_name(), i);
                    food_shelf[i].add(t);
                    break;
                }
            }
        }
    }

    void add(Book t){
        if(hash_book.get(t.get_name()) != null) {
            book_shelf[hash_book.get(t.get_name())].add(t);
        }
        else {
            for (int i = 0; i < 100; i++) {
                if (book_shelf[i].isEmpty()) {
                    hash_book.put(t.get_name(), i);
                    book_shelf[i].add(t);
                    break;
                }
            }
        }
    }

    public void buy(String s){
        try{

        }catch (){

        }
    }

    void buy_book(String s){

    }

    void buy_food(String s){

    }
}
