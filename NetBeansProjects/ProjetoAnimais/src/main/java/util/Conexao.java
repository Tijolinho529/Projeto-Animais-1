package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/animais_db", "root", "sua_senha"
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
