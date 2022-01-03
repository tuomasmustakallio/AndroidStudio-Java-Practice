package com.example.viikko9;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Theater {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    LocalDate localDate = LocalDate.now();

    private String id;
    private String theaterName;
    private String date = dtf.format(localDate);

    public Theater(String newID, String newTheaterName){
        id = newID;
        theaterName = newTheaterName;
    }

    public String getId(){
        return id;
    }
    public String getTheaterName(){
        return theaterName;
    }

    public String getDate(){
        return date;
    }

    @Override
    public String toString() {
        return theaterName;
    }
}
