package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeManagerTest {

    @Spy
    private Employee john=new Employee(
            "John","VP, Sales",13400);

    @Spy
    private Employee emily=new Employee(
         "Emily","VP, Engineering",150000);

    @Test
    public void testAddEmployee(){

        EmployeeManager employeeManager = new EmployeeManager("database_conn_string");

        when(john.saveToDatabase(anyString())).thenReturn(true);

        employeeManager.addEmployee(john);

        verify(john, only()).saveToDatabase("database_conn_string");

        assertTrue(employeeManager.employeeExists("John"));
        assertFalse(employeeManager.employeeExists("Emily"));
    }

    @Test
    public void testRemoveEmployee(){

        EmployeeManager employeeManager=new EmployeeManager("database_conn_string");

        employeeManager.addEmployee(john);
        employeeManager.addEmployee(emily);

        when(john.deleteFromDatabase(anyString())).thenReturn(true);
        when(emily.deleteFromDatabase(anyString())).thenReturn(true);

        employeeManager.removeEmployeeByName("John");
        employeeManager.removeEmployeeByName("Emily");

        verify(john,times(1)).
                deleteFromDatabase("database_conn_string");
        verify(emily,times(1)).
                deleteFromDatabase("database_conn_string");

        assertFalse(employeeManager.employeeExists("John"));
        assertFalse(employeeManager.employeeExists("Emily"));

    }

}
