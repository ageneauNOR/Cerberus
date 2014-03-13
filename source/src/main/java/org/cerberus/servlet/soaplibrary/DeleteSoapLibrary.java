/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cerberus.servlet.soaplibrary;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cerberus.entity.SoapLibrary;
import org.cerberus.entity.SqlLibrary;
import org.cerberus.exception.CerberusException;
import org.cerberus.factory.IFactorySoapLibrary;
import org.cerberus.factory.IFactorySqlLibrary;
import org.cerberus.service.ISoapLibraryService;
import org.cerberus.service.ISqlLibraryService;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author cte
 */
public class DeleteSoapLibrary extends HttpServlet {
    
     /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CerberusException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

        try {
            String name = policy.sanitize(request.getParameter("id"));
            
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            ISoapLibraryService soapLibraryService = appContext.getBean(ISoapLibraryService.class);
            IFactorySoapLibrary factorySoapLibrary = appContext.getBean(IFactorySoapLibrary.class);

            SoapLibrary soapLib = factorySoapLibrary.create(null, name, null,  null, null, null, null);
            soapLibraryService.deleteSoapLibrary(soapLib);
            
        } finally {
            out.close();
        }
    }
}