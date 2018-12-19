package com.komaf.client.utils;

public class CookieData {

    private static Long playerId;

    public static void parseCookie(String cookieString)
    {
        playerId = Long.valueOf(cookieString.replace("playerId=",""));
    }


    public static Long getPlayerId()
    {
        return playerId;
    }

}
