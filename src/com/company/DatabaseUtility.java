package com.company;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseUtility {
    private Connection connection = null;
    private Statement statement = null;

    public DatabaseUtility() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/users?","root", "password");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTable(){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            writeResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username, String password){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (?,?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE username= ? ; ");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String user = resultSet.getString("username");
            String password = resultSet.getString("password_hash");
            System.out.println("User: " + user);
            System.out.println("Password: " + password);
        }
    }

}
