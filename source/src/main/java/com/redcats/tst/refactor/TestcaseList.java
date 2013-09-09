/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcats.tst.refactor;


import com.redcats.tst.database.DatabaseSpring;
import com.redcats.tst.log.MyLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author bcivel
 */
@WebServlet(name = "TestcaseList", urlPatterns = {"/TestcaseList"})
public class TestcaseList extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DatabaseSpring db = new DatabaseSpring();
        Connection conn = db.connect();
        PreparedStatement stmt_testlist = null;
        try {

            String application = request.getParameter("application");
            String app = "";
            String test = request.getParameter("test");
            String tes = "";
            String url = request.getParameter("url");

            if ((StringUtils.isNotBlank(application)) && !(application.equals("all"))) {
                app = " and application = '" + application + "'";
            } else {
                app = "";
            }

            if ((StringUtils.isNotBlank(test)) && !(test.equals("all"))) {
                tes = " and test = '" + test + "'";
            } else {
                tes = "";
            }

            if (StringUtils.isNotBlank(url)) {
                stmt_testlist = conn.prepareStatement("SELECT concat(?) AS list FROM testcase "
                        + " WHERE TcActive = 'Y'  AND `Group` = 'Interactive' ? ? ORDER BY test,testcase");
                stmt_testlist.setString(1, url);
                stmt_testlist.setString(2, app);
                stmt_testlist.setString(3, tes);
                ResultSet rs_testlist = stmt_testlist.executeQuery();
                int id = 0;

                if (rs_testlist.first()) {
                    do {

                        out.println(rs_testlist.getString("list"));

                    } while (rs_testlist.next());

                }
                rs_testlist.close();
                stmt_testlist.close();
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            out.close();
            try {
                conn.close();
            } catch (Exception ex) {
                MyLogger.log(TestcaseList.class.getName(), Level.INFO, "Exception closing ResultSet: " + ex.toString());
            }
            try {
                if (stmt_testlist != null) {
                    stmt_testlist.close();
                }
            } catch (SQLException ex) {
                MyLogger.log(TestcaseList.class.getName(), Level.INFO, "Exception closing PreparedStatement: " + ex.toString());
            }
        }

    }

    // <editor-fold defaultstate="collapsed"
    // desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}