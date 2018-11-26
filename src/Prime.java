import java.util.ArrayList;

class toRun implements Runnable{
    /*
    * get the prime from a to b
    * */
    int a,b;
    long sum = 0;
    boolean flag = false;

    public toRun() {

    }

    public toRun(int a,int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = a;i<b;i++){
            if(Prime.isPrime(i)){
                sum += (long)i;
            }
        }
        flag = true;
    }

    public long getSum(){
        return sum;
    }

    public boolean getFlag(){
        return flag;
    }
}
public class Prime {
    public static final int NUM = 50000000;
    public static final int x = 8;

    public static ArrayList<toRun> q = new ArrayList<toRun>();
    public static ArrayList<Thread> p = new ArrayList<Thread>();

    public static void main(String[] args) {
        //long startTime = System.currentTimeMillis();

        long sum = 0L;
        int a = 1, b = NUM / x + 1, NUMB = NUM / x;
        for (int i = 1; i <= x; i++, a += NUMB, b += NUMB) {
            //System.out.println(a +" "+ b);
            toRun tt = new toRun(a, b);
            Thread t = new Thread(tt);
            t.start();
            p.add(t);
            q.add(tt);
        }
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 0; i < x; i++) {
                flag = (flag & q.get(i).getFlag());
            }
        }
        for (int i = 0; i < x; i++) {
            sum += q.get(i).getSum();
        }
        System.out.println("1 到" + NUM + "内所有素数的和为:" + sum);

        //long endTime = System.currentTimeMillis();

        //System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

    public static boolean isPrime(int num) {
        if (num <= 0) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;

            }
        }
        return true;
    }
}