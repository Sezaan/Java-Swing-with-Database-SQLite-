/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Sezan Ahmed
 */
public class Driver {
  
  public String driverID;
  public String driverName;
  public String contactNo;
  public String address;
  
  public Driver(String driverID, String driverName, String contactNo, String address) {
    this.driverID = driverID;
    this.driverName = driverName;
    this.contactNo = contactNo;
    this.address = address;
  }
  
  public void setDriverID(String driverID) {
    this.driverID = driverID;
  }
  
  public void setDriverName(String driverName) {
    this.driverName= driverName;
  }
  
  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getDriverID() {
    return driverID;
  }
  
  public String getDriverName() {
    return driverName;
  }
  
  public String getContactNo() {
    return contactNo;
  }
  
  public String getAddress() {
    return address;
  }
}
