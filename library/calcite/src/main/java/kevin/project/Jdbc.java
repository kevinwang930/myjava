package kevin.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc {
    public static void main(String[] args) {
        Properties info = new Properties();
        info.setProperty("lex", "MYSQL");
        info.setProperty("model",Jdbc.class.getResource("/mariadb.json").getPath());

        try (Connection connection = DriverManager.getConnection("jdbc:calcite:", info)) {
            // use connection
            System.out.println(connection.getCatalog());
            System.out.println(connection.getSchema());
            connection.setSchema("blog_db");
            System.out.println(connection.getSchema());
            Statement statement = connection.createStatement();
            statement.executeQuery("select count(*) from blog");
            while (statement.getResultSet().next()) {
                System.out.println(statement.getResultSet().getString(1));
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
