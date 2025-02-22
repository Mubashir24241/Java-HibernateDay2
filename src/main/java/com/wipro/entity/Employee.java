package com.wipro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

//@Table(name = "employee")  // Optional if you want to specify the table name


public class Employee {

    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)  // this line will Auto-generate and increment the ID value in MySQL
    
   
    
    
    private Integer stid;
    private String stname;
    private String address;  // New address field

   
    public Employee() {
    }

    
    public Employee(Integer stid, String stname, String address) {
        this.stid = stid;
        this.stname = stname;
        this.address = address;  // new address in constructor
    }

   
    public Integer getStid() {
        return stid;
    }

    public void setStid(Integer stid) {
        this.stid = stid;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    

    @Override
    public String toString() {
        return stid + " " + stname + " " + address;
    }
}
