package com.chi.chwitter.util;

import com.chi.chwitter.entity.Token;

import java.util.Calendar;
import java.util.Date;

public class TokenUtils {
    public static Date calculateExpiryDate(final int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public static boolean isTokenExpired(Token token) {
        return (new Date()).compareTo(token.getExpiryDate()) >= 0;
    }
}
