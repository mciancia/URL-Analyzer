package sample;

import java.sql.*;

/**
 * Created by mateusz on 28.01.2016.
 */
public class FetchHistory {
    private String dbName = "jdbc:sqlite:history.db";

    public FetchHistory() {
        try {
            Connection connection = this.connect();
            this.createTable(connection);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbName);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        return c;
    }
    private void createTable(Connection connection) {
        boolean flag = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery( "SELECT name FROM sqlite_master WHERE type='table' AND name='HISTORY';");
            flag = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!flag) {
            try {
                Statement statement = connection.createStatement();
                String sql = "CREATE TABLE HISTORY(ADDRESS       VARCHAR(200))";
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addUrl(String url) {
        try {
            Connection connection = this.connect();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO HISTORY(ADDRESS) VALUES('" + url +"');";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
