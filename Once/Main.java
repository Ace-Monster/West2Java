public class Main {
    public static void main(String[] args){
        for(int i = 1;i <= 9;i++){
            for(int j = 1;j <= i;j++) {
                int sum = i*j;
                System.out.printf("%d * %d = %d%c", j, i, sum, (j == i) ? '\n' : ' ');
                if(sum/10 == 0 && i != j)
                    System.out.print(" ");
            }
        }
    }
}
