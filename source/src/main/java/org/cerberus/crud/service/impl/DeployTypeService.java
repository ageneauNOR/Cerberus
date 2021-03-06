/*
 * Cerberus  Copyright (C) 2013  vertigo17
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This file is part of Cerberus.
 *
 * Cerberus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cerberus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cerberus.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cerberus.crud.service.impl;

import org.cerberus.crud.dao.IDeployTypeDAO;
import org.cerberus.crud.entity.DeployType;
import org.cerberus.crud.service.IDeployTypeService;
import org.cerberus.util.answer.Answer;
import org.cerberus.util.answer.AnswerItem;
import org.cerberus.util.answer.AnswerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeployTypeService implements IDeployTypeService {

    @Autowired
    private IDeployTypeDAO deployTypeDAO;

    @Override
    public AnswerItem readByKey(String deployType) {
        return deployTypeDAO.readByKey(deployType);
    }

    @Override
    public AnswerList readAll() {
        return deployTypeDAO.readAll();
    }

    @Override
    public AnswerList readByCriteria(int startPosition, int length, String columnName, String sort, String searchParameter, String string) {
        return deployTypeDAO.readByCriteria(startPosition, length, columnName, sort, searchParameter, string);
    }

    @Override
    public Answer create(DeployType deployType) {
        return deployTypeDAO.create(deployType);
    }

    @Override
    public Answer delete(DeployType deployType) {
        return deployTypeDAO.delete(deployType);
    }

    @Override
    public Answer update(DeployType deployType) {
        return deployTypeDAO.update(deployType);
    }

}
