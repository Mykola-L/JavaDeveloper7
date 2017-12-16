package com.system.management.project.controllers;

import com.system.management.project.dao.ICompanyDAO;
import com.system.management.project.dao.IGenericDAO;
import com.system.management.project.dao.IProjectDAO;
import com.system.management.project.dao.ISkillDAO;
import com.system.management.project.models.Company;
import com.system.management.project.models.Developer;
import com.system.management.project.models.Project;
import com.system.management.project.models.Skill;

import java.util.HashSet;
import java.util.Scanner;

public class DeveloperController extends AbstractModelController<Developer> {

    protected ISkillDAO skillDAO;
    protected ICompanyDAO companyDAO;
    protected IProjectDAO projectDAO;

    public DeveloperController(IGenericDAO<Developer, Long> dao, ISkillDAO skillDAO,
                               ICompanyDAO companyDAO, IProjectDAO projectDAO) {
        super(dao);
        this.skillDAO = skillDAO;
        this.companyDAO = companyDAO;
        this.projectDAO = projectDAO;
    }

    @Override
    protected Developer getNevModel() {
        System.out.println("Input developer name: ");
        String developerName = new Scanner(System.in).nextLine();
        System.out.println("Input developer salary: ");
        int salary = new Scanner(System.in).nextInt();
        System.out.println("Input company id: ");
        long companyId = new Scanner(System.in).nextLong();
        System.out.println("Input project id: ");
        long projectId = new Scanner(System.in).nextLong();
        System.out.println("Input skill ids (example: 1,2,3... ): ");
        String skillIdLine = new Scanner(System.in).nextLine().replaceAll(" ", "");
        HashSet<Skill> skillsSet = new HashSet<>();
        for (String skillId : skillIdLine.split(",")) {
            Skill skill = skillDAO.findById(Long.parseLong(skillId));
            if (skill != null) {
                skillsSet.add(skill);
            }
        }
        Company company = companyDAO.findById(companyId);
        Project project = projectDAO.findById(projectId);
        return new Developer(-100, developerName, company, project, salary, skillsSet);
    }
}