package com.peaksoft.unit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Unit {
    // реализуйте настройку соеденения с БД
    private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "postgres";

    public static Connection connection (){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Successfully connected");
        }catch (SQLException s ){
            s.printStackTrace();
        }
        return conn;
    }
}
