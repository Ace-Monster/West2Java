import java.util.Scanner;
public class Book {
    String name;
    boolean borrowed;
    Book() { }
    Book(String t_name) { name = t_name; }

    public String get_name(){
        return name;
    }

    public void set_name(String t_name){
        name = t_name;
    }

    public boolean get_borrowed(){
        return borrowed;
    }

    public void set_borroeed(boolean t_status){
        if(t_status == borrowed) {
            System.err.println("This book is " + (borrowed ? "borrowed" : "returned") + " already");
            return;
        }
        if(t_status == true) {
            System.out.println("Borrowed success!");
        }
        else{
            System.out.println("Returned success!");
        }
        borrowed = t_status;
    }

    @Override
    public String toString() {
        String t = "Name: " + get_name() + "\nStatus: " + get_borrowed();
        return t;
    }

    public static void printInfo(Book book){
        System.out.println("Name: " + book.get_name());
        System.out.println("Status: " + book.get_borrowed());
    }
    public static void main(String[] args){
        Book book = new Book("The man who changed China");
        System.out.println(book.toString());
        while(true) {
            System.out.println("\nPlease select the operator:");
            System.out.println("1.Book info\n2.Borrow this book\n3.Return this book\n4.Exit");
            Scanner IN = new Scanner(System.in);
            int op = IN.nextInt();
            if(op == 1){
                Book.printInfo(book);
            }
            if(op == 2){
                book.set_borroeed(true);
            }
            if(op == 3){
                book.set_borroeed(false);
            }
            if(op == 4){
                break;
            }
        }
    }
}
