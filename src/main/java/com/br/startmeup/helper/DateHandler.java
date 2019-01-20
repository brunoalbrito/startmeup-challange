package com.br.startmeup.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {

    public static Date parseStringtoDate(String data){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
