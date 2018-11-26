import Xiaoming.*;

import java.util.Scanner;

public class Shop {
    static String get_name(){
        String name;
        System.out.println("Input the Commodity name");
        Scanner IN = new Scanner(System.in);
        name = IN.next();
        return name;
    }

    static int get_price(){
        int price;
        System.out.println("Input the Commodity price");
        Scanner IN = new Scanner(System.in);
        price = IN.nextInt();
        return price;
    }

    public static void main(String[] args){
        Store store = new Store();
        while (true){
            System.out.println("Welcome to Xiaoming Shop, selct operator:");
            System.out.println("1:Add Cpmmodity");
            System.out.println("2:Buy Commodity");
            System.out.println("3:Exit");
            Scanner IN = new Scanner(System.in);

            int op = IN.nextInt();
            if(op == 1){
                while(true){
                    String name = get_name();
                    int price = get_price();
                    System.out.println("Select the kind of commodity:");
                    System.out.println("1:Food");
                    System.out.println("2:Book");
                    System.out.println("3:Black");
                    op = IN.nextInt();
                    if(op == 1){
                        Food newfood = new Food(name, price);
                        store.add(newfood);
                        break;
                    }else if(op == 2){
                        Book newbook = new Book(name, price);
                        store.add(newbook);
                        break;
                    }else if(op == 3)
                        break;
                    else
                        System.out.println("Woring kind");
                }
            }else if(op == 2){
                String name = get_name();
                try {
                    if(store.buy(name))
                        System.out.println("Transaction success");
                }catch (BuyException ex){
                    System.out.println("Transaction failed");
                    System.out.println(ex.getMessage());
                }
            }else if(op == 3)
                break;
            else
                System.out.println("Wrong operator");

        }
    }
}
