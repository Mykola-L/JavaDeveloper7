package com.system.management.project.controllers;

import com.system.management.project.dao.IGenericDAO;
import com.system.management.project.models.IModel;

import java.util.Scanner;

public abstract class AbstractModelController<T extends IModel> extends AbstractController {

    protected final IGenericDAO<T, Long> dao;

    public AbstractModelController(IGenericDAO<T, Long> dao) {
        this.dao = dao;
    }

    @Override
    protected void printMenu() {
        System.out.println("1 - create");
        System.out.println("2 - read");
        System.out.println("3 - update");
        System.out.println("4 - delete");
        System.out.println("0 - go to main menu");

    }

    @Override
    protected void action(int choice) {
        switch (choice) {
            case 1:
                create();
                break;
            case 2:
                read();
                break;
            case 3:
                update();
                break;
            case 4:
                deleteById();
                break;
        }
    }

    protected void create() {
        T model = getNevModel();
        System.out.println("Id of new model: " + dao.create(model));
    }

    protected void update() {
        System.out.println("Update. Input id: ");
        try {
            long id = new Scanner(System.in).nextLong();
            T oldModel = dao.findById(id);
            if (oldModel == null) {
                System.out.println("Model with this id is not found");
                return;
            }
            System.out.println("Input new information for " + oldModel);
            T model = getNevModel();
            model.setId(id);
            System.out.println("New model: ");
            System.out.println(model);
            dao.update(model);
        } catch (Exception e) {
            System.out.println("Error! Enter number(example: 1,2,3... )");
            update();
        }
    }

    protected void deleteById() {
        System.out.println("Delete by id. Input id: ");
        try {
            long id = new Scanner(System.in).nextLong();
            T model = dao.findById(id);
            if (model == null) {
                System.out.println("Model with this id is not found");
                return;
            }
            System.out.println("Delete: " + model);
            dao.delete(model);
        } catch (Exception e) {
            System.out.println("Error! Enter number(example: 1,2,3... )");
            deleteById();
        }
    }

    protected void read() {
        dao.read().forEach(System.out::println);
    }

    protected abstract T getNevModel();
}