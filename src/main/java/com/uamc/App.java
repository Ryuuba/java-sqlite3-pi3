package com.uamc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main( String[] args ) {
        System.out.println("Starting...\n");
        Connection connection = null;
        String dbName = "jdbc:sqlite:/home/geo/dbdemo/datastudent.db";
        try
        {
            connection = DriverManager.getConnection(dbName);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("select * from student");
            while(rs.next()) {
                System.out.println("first name = " + rs.getString("firstname"));
                System.out.println("last name = " + rs.getString("lastname"));
                System.out.println("campus = " + rs.getString("campus"));
            }
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            try {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
