package com.example.lab7.Collections;


import com.example.lab7.Domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersCollection {

    private static String url = "jdbc:mysql://localhost:3306/EAD2lab7?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "";
    private Connection conn;

    public UsersCollection() {
        System.out.println("Init databasecon");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public User getUser(String username) {

        User account = null;

        String sql = "SELECT * FROM users WHERE username=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                username = resultSet.getString(1);
                String password = resultSet.getString(2);
                Boolean isAdmin = resultSet.getBoolean(3);
                account = new User(username, password, isAdmin);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return account;

    }


    public int insert(User account) {

        String sql = "INSERT INTO users (username, password, isAdmin) Values (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setBoolean(3, account.isAdmin());
            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;

    }

    public int update(User account) {

        String sql = "UPDATE users SET password = ?, isAdmin = ?   WHERE username = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setBoolean(1, account.isAdmin());
            preparedStatement.setString(2, account.getUsername());
            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public Connection getConn() {
        return conn;
    }
}