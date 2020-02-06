package com.chi.chwitter.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {
    private static final String PAGE_SIZE = "pageSize";
    private static final int DEFAULT_PAGE_SIZE = 5;

    public int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    public int getOrCreateSessionPageSize(HttpSession session) {
        if (session.getAttribute(PAGE_SIZE) == null) {
            session.setAttribute(PAGE_SIZE, getDefaultPageSize());
        }
        return (Integer) session.getAttribute(PAGE_SIZE);
    }

    public void incrementSessionPageSize(HttpSession session) {
        session.setAttribute(PAGE_SIZE, getOrCreateSessionPageSize(session) + getDefaultPageSize());
    }

    public int getOrCreateSessioUserPageSize(HttpSession session, String username) {
        String key = username + "." + PAGE_SIZE;
        if (session.getAttribute(key) == null) {
            session.setAttribute(key, getDefaultPageSize());
        }
        return (Integer) session.getAttribute(key);
    }

    public void incrementSessionUserPageSize(HttpSession session, String username) {
        session.setAttribute(PAGE_SIZE, getOrCreateSessioUserPageSize(session, username) + getDefaultPageSize());
    }
}
