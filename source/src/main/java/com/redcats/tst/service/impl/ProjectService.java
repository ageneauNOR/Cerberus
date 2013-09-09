/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcats.tst.service.impl;

import com.redcats.tst.dao.IProjectDAO;
import com.redcats.tst.entity.Project;
import com.redcats.tst.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bcivel
 */
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectDAO projectDao;

    @Override
    public List<String> findListOfProjectDescription() {
        List<String> result = new ArrayList<String>();
        List<Project> listOfProject = this.projectDao.findAllProject();
        for (Project project : listOfProject) {
            result.add(project.getIdProject().concat(project.getCode()).concat(project.getDescription()));
        }

        return result;
    }

}