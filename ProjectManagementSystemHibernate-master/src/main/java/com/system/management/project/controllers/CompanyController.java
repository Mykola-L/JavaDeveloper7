package com.system.management.project.controllers;

import com.system.management.project.dao.IGenericDAO;
import com.system.management.project.models.Company;

import java.util.Scanner;

public class CompanyController extends AbstractModelController<Company> {

    public CompanyController(IGenericDAO<Company, Long> dao) {
        super(dao);
    }

    @Override
    protected Company getNevModel() {
        System.out.println("Input company name: ");
        String companyName = new Scanner(System.in).nextLine();
        return new Company(-100, companyName);
    }
}