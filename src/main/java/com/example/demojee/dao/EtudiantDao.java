package com.example.demojee.dao;

import com.example.demojee.model.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/etudiant?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "chiheb2002";
    private static final String INSERT_USERS_SQL = "INSERT INTO etudiant" + "  (name, email, note) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_ETUDIANT_BY_ID = "select id,name,email,note from etudiant where id =?";
    private static final String SELECT_ALL_ETUDIANT = "select * from etudiant";
    private static final String DELETE_ETUDIANT_SQL = "delete from etudiant where id = ?;";
    private static final String UPDATE_ETUDIANT_SQL = "update etudiant set name = ?,email= ?, note =? where id = ?;";

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
    public void insertEtudiant(Etudiant e) throws SQLException{
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)
        ){
            preparedStatement.setString(1,e.getName());
            preparedStatement.setString(2,e.getEmail());
            preparedStatement.setDouble(3,e.getNote());
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            printSQLException(exception);
        }
    }
    public Etudiant selectEtudaint(int id){
        Etudiant etudiant = null;

         try (Connection connection = getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ETUDIANT_BY_ID)
         ){
             preparedStatement.setInt(1,id);
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()){
                  etudiant = new Etudiant(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"),resultSet.getDouble("note"));
             }
         }catch (SQLException exception){
            printSQLException(exception);
         }
         return etudiant;
    }
    public List<Etudiant> getAllEtudiants() throws SQLException {
        List<Etudiant> list = new ArrayList<>();
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ETUDIANT)
         ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               Etudiant etudiant = new Etudiant(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"),resultSet.getDouble("note"));
                list.add(etudiant);
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
    public boolean updateRtudiant(Etudiant etudiant){
        boolean rowUpdated = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_ETUDIANT_SQL);) {
            statement.setString(1, etudiant.getName());
            statement.setString(2, etudiant.getEmail());
            statement.setDouble(3, etudiant.getNote());
            statement.setInt(4, etudiant.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

}
