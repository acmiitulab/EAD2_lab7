package com.example.lab7.Collections;

import com.example.lab7.Domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Token {

    private static String url = "jdbc:mysql://localhost:3306/EAD2lab7?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "";
    private Connection conn;

    public Token() {
        System.out.println("Init databasecon");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Integer getTokens() {
        Integer num = 0;
        String sql = "SELECT * FROM tokens";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                num++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return num;

    }

    public String getToken(int token) {
        String usernm = null;
        String sql = "SELECT * FROM tokens WHERE token=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                usernm = resultSet.getString(2);
                return usernm;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return usernm;

    }


    public int insert(Integer token, String username ) {

        String sql = "INSERT INTO tokens (token, username) Values (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, token);
            preparedStatement.setString(2, username);
            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;

    }



}