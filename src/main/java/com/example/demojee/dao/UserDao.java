package com.example.demojee.dao;

import com.example.demojee.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/gestion_user?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "chiheb2002";
    private static final String INSERT_USERS_SQL = "INSERT INTO  user" + "  (name, email, adresse) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_ETUDIANT_BY_ID = "select id,name,email,adresse from user where id =?";
    private static final String SELECT_ALL_ETUDIANT = "select * from user";
    private static final String DELETE_ETUDIANT_SQL = "delete from user where id = ?;";
    private static final String UPDATE_ETUDIANT_SQL = "update user set name = ?,email= ?, adresse =? where id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    public void insertUser(User e) throws SQLException{
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)
        ){
            preparedStatement.setString(1,e.getName());
            preparedStatement.setString(2,e.getEmail());
            preparedStatement.setString(3,e.getAdresse());
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            printSQLException(exception);
        }
    }
    public User selectUser(int id){
        User user = null;

         try (Connection connection = getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ETUDIANT_BY_ID)
         ){
             preparedStatement.setInt(1,id);
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()){
                  user = new User(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"),resultSet.getString("adresse"));
             }
         }catch (SQLException exception){
            printSQLException(exception);
         }
         return user;
    }
    public List<User> getAllEtudiants() throws SQLException {
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ETUDIANT)
         ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               User user = new User(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"),resultSet.getString("adresse"));
                list.add(user);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return list;
    }
    public boolean deleteEtudiant(int id){
        boolean rowdeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ETUDIANT_SQL)
        ){
            preparedStatement.setInt(1,id);
            rowdeleted = preparedStatement.executeUpdate()>0;
        }catch (SQLException e){
            printSQLException(e);
        }
        return  rowdeleted;
    }
    public boolean updateRtudiant(User user){
        boolean rowUpdated = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_ETUDIANT_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getAdresse());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

}
