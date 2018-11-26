package Xiaoming;

import java.util.Scanner;

public class Time{
    public int year;
    public int month;
    public int day;

    public Time() { }
    public Time(int y,int m,int d) {
        year = y;
        month = m;
        day = d;
    }
    public Time(boolean flag){
        Scanner IN = new Scanner(System.in);
        System.out.print("Year:");
        year = IN.nextInt();
        System.out.print("Month:");
        month = IN.nextInt();
        System.out.print("Day:");
        day = IN.nextInt();
    }

    public static int StringtoInt(String a, int i, int n){
        int t = 0;
        for(;i <= n;i++)
            t = t*10+(a.charAt(i) - '0');
        return t;
    }

    public static int Compare(Time x, Time y){
        /*
        * x < y return 1;
        * x > y return -1;
        * x == y return 0;
        * */
        if(x.year != y.year){
            if(x.year < y.year)
                return 1;
            return -1;
        }
        if(x.month != y.month) {
            if (x.month < y.month)
                return 1;
            return -1;
        }
        if(x.day < y.day)
            return 1;
        if(x.day > y.day)
            return -1;
        return 0;
    }

    @Override
    public String toString(){
        String t = year + "." + month + "." + day;
        return t;
    }
}
