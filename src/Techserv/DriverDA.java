/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Techserv;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Domain.Driver;

/**
 *
 * @author Sezan Ahmed
 */
public class DriverDA {
  
  public ArrayList<Driver> driverList;  
  private int recordCursor;
  private Connection connection;
  
  private final String dbDir = "test.db";
  
  public DriverDA() {
    //setRecordCursorAtStart();
    setConnection();
    populateDriverList();
  }
  
  public void setRecordCursorAtStart() {
    recordCursor = 0;
  }
  
  public void setRecordCursorAtEnd() {
    recordCursor = driverList.size() - 1;
  }
  
  public void setConnection() {
    
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbDir);
    } catch(ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    
  }
  
  public void populateDriverList() {
    
    driverList = new ArrayList<Driver>();
    
    try {
      String query = "SELECT * FROM driver";
      PreparedStatement ps = connection.prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      Driver current;
      while(rs.next()) {
        if(rs.getString("stamp").contains("inactive")) {
          continue;
        }
        current = new Driver(rs.getString("id"),
                             rs.getString("name"),
                             rs.getString("contactno"),
                             rs.getString("address")
                            );
        driverList.add(current);
      }
    } catch(SQLException e) {
        e.printStackTrace();
      }
  }
  
  public void commitStatement(String query) {
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.executeUpdate();
    } catch(SQLException e) {
        JOptionPane.showMessageDialog(null, "Oops! Something is wrong!", "", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      }
    populateDriverList();
  }
  
  public void addDriver(Driver current) {
    String query = "INSERT INTO driver VALUES('%s', '%s', '%s', '%s', 'active')";
    commitStatement(String.format(query, current.getDriverName(),
                                         current.getDriverID(),
                                         current.getContactNo(),
                                         current.getAddress()
                   ));
    setRecordCursorAtEnd();
  }
  
  public void updateDriver(String oldID, Driver current) {
    String query = "UPDATE driver SET name = '%s', id = '%s', contactno = '%s', address = '%s' WHERE id = '%s'";
    commitStatement(String.format(query, current.getDriverName(),
                                         current.getDriverID(),
                                         current.getContactNo(),
                                         current.getAddress(),
                                         oldID
                   ));
  }
  
  public void deleteDriver(String ID) {
    String query = "UPDATE driver SET stamp = 'inactive' WHERE id = '%s'";
    commitStatement(String.format(query, ID));
    setRecordCursorAtStart();
  }
  
  public Driver getNextDriver() {  
    return driverList.get(  
    recordCursor = recordCursor >= driverList.size()-1? driverList.size() - 1: ++recordCursor);   
  }  
        
  public Driver getPreviousDriver() {  
    return driverList.get(  
    recordCursor = recordCursor <= 0? 0: --recordCursor);   
  }

  public Driver getFirstDriver() {  
    return driverList.get(recordCursor = 0);   
  }  
        
  public Driver getLastDriver() {  
    return driverList.get(recordCursor = driverList.size()-1);   
  }

  public Driver getCurrentDriver() {
    return driverList.get(recordCursor);
  }
        
  public ArrayList<Driver> getDriverList() {  
    return driverList;  
  }
}
