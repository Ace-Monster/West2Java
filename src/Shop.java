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
        System.out.println("いらっしゃい!");
        while (true){
            System.out.println("\nWelcome to Xiaoming Shop, select operator:");
            System.out.println("1:Add Commodity");
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
                        System.err.println("Wrong kind");
                }
            }else if(op == 2){
                String name = get_name();
                try {
                    if(store.buy(name))
                        System.out.println("Transaction success");
                }catch (BuyException ex){
                    System.err.println("Transaction failed");
                    System.err.println(ex.getMessage());
                }
            }else if(op == 3) {
                System.out.println("ありがとうございました、またおこしください!");
                //混进了两句奇怪的东西？
                break;
            }
            else
                System.err.println("Wrong operator");

        }
    }
}
