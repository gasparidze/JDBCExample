package org.example;

import org.example.util.ConnectionManager;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws SQLException {
        try (var connection = ConnectionManager.open()){
            System.out.println(connection.getTransactionIsolation());
        }
    }
}
