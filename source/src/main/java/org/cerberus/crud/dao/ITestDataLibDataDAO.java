/* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
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
package org.cerberus.crud.dao;

import java.util.ArrayList;
import java.util.List;
import org.cerberus.crud.entity.TestDataLib;
import org.cerberus.crud.entity.TestDataLibData;
import org.cerberus.util.answer.Answer;
import org.cerberus.util.answer.AnswerItem;
import org.cerberus.util.answer.AnswerList;

/**
 *
 * @author vertigo17
 * @author FNogueira
 */
public interface ITestDataLibDataDAO {

    /**
     *
     * @param testDataLibID
     * @param subData
     * @return
     */
    AnswerItem readByKey(Integer testDataLibID, String subData);

    /**
     *
     * @param testDataLibDataID
     * @return
     */
    AnswerItem readByKeyTech(Integer testDataLibDataID);

    /**
     *
     * @param testDataLibID resultSet
     * @return
     */
    AnswerList readByVarious(Integer testDataLibID, String columnEmpty, String parsingAnswerEmpty);

    /**
     *
     * @return All TestData
     */
    AnswerList readAll();

    /**
     *
     * @param start
     * @param amount
     * @param colName
     * @param dir
     * @param searchTerm
     * @param individualSearch
     * @return
     */
    AnswerList readByCriteria(int start, int amount, String colName, String dir, String searchTerm, String individualSearch);

    /**
     * Finds all subdata entries (testdatalibdata) that are associated with an
     * entry name (testdatalib).
     *
     * @param testDataLibName - entry name used to filter the subdata entries
     * @return Answer indicating the status of the operation
     */
    AnswerList readByName(String testDataLibName);

    /**
     *
     * @param testDataLibData
     * @return
     */
    Answer create(TestDataLibData testDataLibData);

    /**
     * Batch that inserts several records in the table TestDataLibData
     *
     * @param subdataSet - entries to insert
     * @return Answer indicating the status of the operation
     */
    Answer create(List<TestDataLibData> subdataSet);

    /**
     *
     * @param testDataLibData
     * @return
     */
    Answer update(TestDataLibData testDataLibData);

    /**
     * Batch that updates several records in the table TestDataLibData.
     *
     * @param entriesToUpdate - entries to update
     * @return Answer indicating the status of the operation
     */
    Answer update(ArrayList<TestDataLibData> entriesToUpdate);

    /**
     *
     * @param testDataLibData
     * @return
     */
    Answer delete(TestDataLibData testDataLibData);

    /**
     * Deletes all testdatalibdata records that belong to a testdatalib.
     *
     * @param testDataLibID - remove all entries that belong to a testdatalib
     * entry.
     * @return Answer indicating the status of the operation
     */
    Answer delete(TestDataLib testDataLibID);

    /**
     * Batch that deletes several records in the table TestDataLibData.
     *
     * @param subdataSet
     * @return
     */
    Answer delete(List<TestDataLibData> subdataSet);

}
