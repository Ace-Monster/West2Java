package Xiaoming;

import java.util.Comparator;

public class ComparatorTime implements Comparator<Time> {
    @Override
    public int compare(Time x, Time y){
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
        if(x.day <= y.day)
            return 1;
        return -1;
    }
}
