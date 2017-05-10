package com.bootcamp.rms.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Kholishul_A on 27/04/2017.
 */
public class Utils {

    public static Date parseStrToDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(strDate);

        java.sql.Date sqlDate = new Date(date.getTime());
        return sqlDate;
    }

    public static String jsonToString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OAuthToken {
        @JsonProperty("access_token")
        public String accessToken;
    }

}


