package com.komaf.server;

import javax.servlet.http.HttpSession;

public class SessionService {

    public static void sessionFactory(HttpSession session)
    {
        session.setAttribute("playerId",null);
        session.setAttribute("gameId",null);
    }

}
