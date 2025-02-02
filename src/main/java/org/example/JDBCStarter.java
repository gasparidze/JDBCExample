package org.example;

import org.example.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStarter {
    private static String CREATE_SQL = """
            CREATE TABLE IF NOT EXISTS stuff
            (
                id           SERIAL PRIMARY KEY,
                type         VARCHAR(50),
                fio          VARCHAR(50),
                birthday     DATE,
                passportInfo VARCHAR(50),
                bankInfo     VARCHAR(100)
            );
            
            CREATE TABLE IF NOT EXISTS partner
            (
                id           SERIAL PRIMARY KEY,
                type         VARCHAR(50),
                name         VARCHAR(50),
                legalAddress VARCHAR(100),
                inn          VARCHAR(20),
                fio          VARCHAR(50),
                phone        VARCHAR(20),
                email        VARCHAR(50),
                username     VARCHAR(50),
                password     varchar(50),
                rating       FLOAT,
                manager      INT REFERENCES stuff (id),
                discount     DOUBLE PRECISION
            );
            
            CREATE TABLE IF NOT EXISTS product
            (
                id             SERIAL PRIMARY KEY,
                article        VARCHAR(50),
                type           VARCHAR(50),
                title          VARCHAR(100),
                description    TEXT,
                cost           INT,
                productionDate DATE,
                request_id INT REFERENCES partner(id)
            );
            
            CREATE TABLE IF NOT EXISTS request
            (
                id               SERIAL PRIMARY KEY,
                status           VARCHAR(50),
                deliveryType     VARCHAR(50),
                realizationPrice DOUBLE PRECISION,
                partner          INT REFERENCES partner (id)
            );
            """;

    public static void prepareDatabase(){
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()){

            statement.execute(CREATE_SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
