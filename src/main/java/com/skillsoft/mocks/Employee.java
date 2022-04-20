package com.skillsoft.mocks;

public class Employee {

    private String name;
    private String designation;
    private int salary;

    public Employee(String name,String designation,int salary){
        this.name=name;
        this.designation=designation;
        this.salary=salary;
    }

    public String getName(){
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public int getSalary() {
        return salary;
    }

    public boolean saveToDatabase(String connectionString){
        return true;
    }

    public boolean deleteFromDatabase(String connectionString){
        return true;
    }
}
