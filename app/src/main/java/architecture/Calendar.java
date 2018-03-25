package architecture;

import android.support.annotation.NonNull;
import android.util.Log;


/**
 * Created by User on 2017-11-19.
 */

public class Calendar implements Comparable<Calendar>{
    public int rok;
    public int miesiac;
    public int dzien;
    public String imie;

    public Calendar(int rok, int miesiac, int dzien, String imie){
        this.rok = rok;
        this.miesiac=miesiac;
        this.dzien=dzien;
        this.imie=imie;
    }

    @Override
    public int compareTo(@NonNull Calendar calendar) {

        Log.i("korzysta","cos");
        if (calendar==null)
            throw new NullPointerException();

        if (this.miesiac < calendar.miesiac)
            return -1;

        if(this.miesiac>calendar.miesiac)
            return 1;

        if(this.dzien<calendar.dzien)
            return -1;

        if (this.dzien>calendar.dzien)
            return 1;

        return 0;
    }
}

