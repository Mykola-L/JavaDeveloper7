package com.system.management.project.controllers;

import com.system.management.project.dao.IGenericDAO;
import com.system.management.project.models.Customer;

import java.util.Scanner;

public class CustomerController extends AbstractModelController<Customer> {

    public CustomerController(IGenericDAO<Customer, Long> dao) {
        super(dao);
    }

    @Override
    protected Customer getNevModel() {
        System.out.println("Input customer name: ");
        String customerName = new Scanner(System.in).nextLine();
        return new Customer(-100, customerName);
    }
}