package Xiaoming;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Food extends Commodity {
    Time in;
    Time out;

    public Food(){ }
    public Food(String n,int p){
        super(n,p);

        while (true){
            System.out.println("Production Date:");
            in = new Time(true);
            Time nowTime = get_nowTime();
            if(Time.Compare(in, nowTime) != -1)
                break;
            else
                System.err.println("The Time is invalid, please check again.\n");
        }
        while (true) {
            System.out.println("Fresh Date:");
            out = new Time(true);
            Time nowTime = get_nowTime();
            if (Time.Compare(out, nowTime) != 1)
                break;
            else
                System.err.println("The Time is invalid, please check again.\n");
        }
    }

    Time get_nowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(new Date());
        int yy = Time.StringtoInt(nowTime, 0, 3),
                mm = Time.StringtoInt(nowTime, 5, 6),
                dd = Time.StringtoInt(nowTime, 8, 9);
        return new Time(yy,mm,dd);
    }

    @Override
    public String toString(){
        String t = "Food name:" + get_name() + "\nPrice:" + get_price() +
                "\nProduction Date:" + in + "\nFresh Date:" + out;
        return t;
    }
}
