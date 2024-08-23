package org.ecommerce.models;

public class Manager extends User {
    int employeeNumber;

    public Manager(Long id, String firstName, String lastName, String email, String password, int employeeNumber) {
        super(id, firstName, lastName, email, password);
        this.employeeNumber = employeeNumber;
    }
}
