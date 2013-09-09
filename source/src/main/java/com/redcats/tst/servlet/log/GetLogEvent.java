/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcats.tst.servlet.log;

import com.redcats.tst.entity.LogEvent;
import com.redcats.tst.exception.CerberusException;
import com.redcats.tst.log.MyLogger;
import com.redcats.tst.service.ILogEventService;
import com.redcats.tst.service.impl.LogEventService;
import com.redcats.tst.servlet.user.GetUsers;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author vertigo
 */
public class GetLogEvent extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String echo = request.getParameter("sEcho");
        String sStart = request.getParameter("iDisplayStart");
        String sAmount = request.getParameter("iDisplayLength");
        String sCol = request.getParameter("iSortCol_0");
        String sdir = request.getParameter("sSortDir_0");
        String dir = "asc";
        String[] cols = {"Time", "login", "Page", "Action", "log"};

        int start = 0;
        int amount = 0;
        int col = 0;

        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0) {
                start = 0;
            }
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10 || amount > 100) {
                amount = 10;
            }
        }
        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0 || col > 5) {
                col = 0;
            }
        }
        if (sdir != null) {
            if (!sdir.equals("asc")) {
                dir = "desc";
            }
        }
        String colName = cols[col];

        JSONArray data = new JSONArray(); //data that will be shown in the table

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        ILogEventService logEventService = appContext.getBean(LogEventService.class);
        try {
            JSONObject jsonResponse = new JSONObject();
            try {
                for (LogEvent myLogEvent : logEventService.findAllLogEvent(start, amount, colName, dir)) {
                    JSONObject u = new JSONObject();
                    u.put("login", myLogEvent.getLogin());
                    u.put("time", myLogEvent.getTime());
                    u.put("page", myLogEvent.getPage());
                    u.put("action", myLogEvent.getAction());
                    u.put("log", myLogEvent.getLog());
                    data.put(u);
                }
                Integer nbLog = logEventService.getNumberOfLogEvent();
                jsonResponse.put("aaData", data);
                jsonResponse.put("sEcho", echo);
                jsonResponse.put("iDisplayLength", data.length());
                jsonResponse.put("iTotalDisplayRecords", nbLog);
                response.setContentType("application/json");
                response.getWriter().print(jsonResponse.toString());
            } catch (CerberusException ex) {
                response.setContentType("text/html");
                response.getWriter().print(ex.getMessageError().getDescription());

            }
        } catch (JSONException e) {
            MyLogger.log(GetUsers.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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