package com.design.pattern.structural;

// It is a Structural pattern
// Provides a class with will limit access to another class
// You may do this for security reasons
// In this example, ATMProxy doesn't give us access to setter methods

// Subject Interface
interface EmployeeService {
    void create(String data);
    void delete(int id);
    String get(int id);
}

// Real Object
class EmployeeServiceImpl implements EmployeeService {
    @Override
    public void create(String data) {
        System.out.println("Creating employee: " + data);
    }

    @Override
    public void delete(int id) {
        System.out.println("Deleting employee with ID: " + id);
    }

    @Override
    public String get(int id) {
        return "Employee data for ID: " + id;
    }
}

// Proxy Object
class EmployeeServiceProxy implements EmployeeService {
    private EmployeeServiceImpl realService;
    private boolean isAdmin;

    public EmployeeServiceProxy(boolean isAdmin) {
        this.realService = new EmployeeServiceImpl();
        this.isAdmin = isAdmin;
    }

    @Override
    public void create(String data) {
        if (isAdmin) {
            realService.create(data);
        } else {
            System.out.println("Access Denied: Only Admins can create employees.");
        }
    }

    @Override
    public void delete(int id) {
        if (isAdmin) {
            realService.delete(id);
        } else {
            System.out.println("Access Denied: Only Admins can delete employees.");
        }
    }

    @Override
    public String get(int id) {
        System.out.println("Fetching employee data...");
        return realService.get(id);
    }
}

// Client Code
public class ProxyClient {
    public static void main(String[] args) {
        EmployeeService adminProxy = new EmployeeServiceProxy(true);
        adminProxy.create("John Doe");
        adminProxy.delete(101);

        EmployeeService userProxy = new EmployeeServiceProxy(false);
        userProxy.create("Jane Doe");
        userProxy.get(102);
    }
}
