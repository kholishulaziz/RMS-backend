package com.aziz.rms.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * Created by Kholishul_A on 27/04/2017.
 */
public class util {

    public static Date parseStrToDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(strDate);

        java.sql.Date sqlDate = new Date(date.getTime());
        return sqlDate;
    }
}
