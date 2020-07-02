package com.company.dao;

import com.company.model.Entry;
import com.company.model.Guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class GuestbookDaoDb implements GuestbookDao {

    @Override
    public Guestbook loadGuestbook() {

        Guestbook guestbook = new Guestbook();
        String query = "SELECT content, name, date_trunc('second', date_time) AS time FROM guestbook";

        try {
            Connection connection = new DataSourceConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                guestbook.addEntry(getEntry(rs));
            }
            ps.close();
            connection.close();
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return guestbook;
    }

    private Entry getEntry(ResultSet rs) throws SQLException {
        String content = rs.getString("content");
        String name = rs.getString("name");

        long date = rs.getTimestamp("time").getTime();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());

        return new Entry(content, name, localDateTime);
    }


    @Override
    public boolean addEntry(Entry entry) {
        String query = "INSERT INTO guestbook " +
                "(content, name, date_time) " +
                "VALUES (" +
                "'" + entry.getContent() + "', " +
                "'" + entry.getName() + "', " +
                "now())";

        PreparedStatement ps = null;
        boolean addEntrySuccess = false;

        try {
            Connection connection = new DataSourceConnection().getConnection();
            ps = connection.prepareStatement(query);
            ps.execute();
            ps.close();;
            connection.close();;
            addEntrySuccess = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return addEntrySuccess;
    }
}
