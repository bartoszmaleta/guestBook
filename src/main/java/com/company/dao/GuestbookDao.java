package com.company.dao;

import com.company.model.Entry;
import com.company.model.Guestbook;

public interface GuestbookDao {
    Guestbook loadGuestbook();
    boolean addEntry (Entry entry);

}

