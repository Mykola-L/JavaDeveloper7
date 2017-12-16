package com.system.management.project.controllers;

import com.system.management.project.dao.IGenericDAO;
import com.system.management.project.models.Skill;

import java.util.Scanner;

public class SkillController extends AbstractModelController<Skill> {

    public SkillController(IGenericDAO<Skill, Long> dao) {
        super(dao);
    }

    @Override
    protected Skill getNevModel() {
        System.out.println("Input skill name: ");
        String skillName = new Scanner(System.in).nextLine();
        return new Skill(-100, skillName);
    }
}