<%-- 
    Document   : buildcontent
    Created on : Dec 2, 2011, 10:16:49 PM
    Author     : vertigo
    Description: This page display the content of a build/revision.
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="com.mysql.jdbc.StringUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>

        <!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sprint Content</title>
        <link rel="stylesheet" 
              type="text/css" href="css/crb_style.css"
              />
        <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">
    </head>
    <body>
        <%@ include file="include/function.jsp" %>
        <%@ include file="include/header.jsp" %>
<%        
            Date DatePageStart = new Date() ;
                
                Connection conn = db.connect();

            try {
                
                Statement stmtBuild = conn.createStatement();
                Statement stmtApp = conn.createStatement();

                /* Parameter Setup */

                String build = "NONE";
                if (request.getParameter("build") != null && request.getParameter("build").compareTo("") != 0) {
                    build = request.getParameter("build");;
                } else {
                    ResultSet rsBR = stmtBuild.executeQuery("SELECT max(Build) mb FROM buildrevisionparameters where build!='NONE'");
                    if (rsBR.first()) {
                        build = rsBR.getString("mb");
                    }
                }
                String revision = "NONE";
                if (request.getParameter("revision") != null && request.getParameter("revision").compareTo("") != 0) {
                    revision = request.getParameter("revision");;
                } else {
                    ResultSet rsREV = stmtBuild.executeQuery("SELECT max(Revision) mr FROM buildrevisionparameters "
                            + " where build = '" + build + "'");
                    if (rsREV.first()) {
                        revision = rsREV.getString("mr");
                    }
                }

                Statement stmtRev = conn.createStatement();
                
        %>                    <form method="GET" name="SprintContent" id="buildcontent">
<table class="tablef"> <tr> <td><a href="?build=NONE&revision=NONE">Pending Release</a></td><td> 
                        <ftxt><%=dbDocS(conn,"invariant","build","")%></ftxt> <select id="build" name="build" style="width: 100px" OnChange ="document.buildcontent.submit()">
                            <option style="width: 100px" value="NONE" <%=build.compareTo("NONE") == 0 ? " SELECTED " : ""%>>-- NONE --</option>
                            <%ResultSet rsBuild = stmtBuild.executeQuery("SELECT value, description "
                                        + "FROM invariant "
                                        + "WHERE id = 8 "
                                        + "ORDER BY sort ASC");
                                while (rsBuild.next()) {
                            %><option style="width: 100px" value="<%= rsBuild.getString(1)%>" <%=build.compareTo(rsBuild.getString(1)) == 0 ? " SELECTED " : ""%>><%= rsBuild.getString(1)%></option>
                            <% }
                            %></select>
                        <ftxt><%=dbDocS(conn,"invariant","revision","")%></ftxt> <select id="revision" name="revision" style="width: 100px" OnChange ="document.buildcontent.submit()">
                            <option style="width: 100px" value="ALL" <%=revision.compareTo("ALL") == 0 ? " SELECTED " : ""%>>-- ALL --</option>
                            <option style="width: 100px" value="NONE" <%=revision.compareTo("NONE") == 0 ? " SELECTED " : ""%>>-- NONE --</option>
                            <%ResultSet rsRev = stmtRev.executeQuery("SELECT value, description "
                                        + "FROM invariant "
                                        + "WHERE id = 9 "
                                        + "ORDER BY sort ASC");
                                while (rsRev.next()) {
                            %><option style="width: 100px" value="<%= rsRev.getString(1)%>" <%=revision.compareTo(rsRev.getString(1)) == 0 ? " SELECTED " : ""%>><%= rsRev.getString(1)%></option>
                            <% }
                            %></select>
                        <input type="submit" name="FilterApply" value="Apply">
                </td></tr></table>
                    </form><br>
        <%
            stmtBuild.close();
            stmtRev.close();
            
            Statement stmtBR = conn.createStatement();
            String BR;


                BR = "SELECT DISTINCT b.ID, b.Build, b.Revision, b.Application, b.Release, b.Link, b.ReleaseOwner, b.Project,b.TicketIDFixed, b.BugIDFixed,b.Subject "
                        + "FROM `buildrevisionparameters` b "
                        + "WHERE 1=1 ";
                if (!build.trim().equalsIgnoreCase("ALL")) {
                    BR += " and Build='" + build + "' ";
                }
                if (!revision.trim().equalsIgnoreCase("ALL")) {
                    BR += " and Revision='" + revision + "' ";
                }
                BR += " ORDER by Build desc, Revision desc, Application ASC, `Release` ASC";
//                out.print(BR);
                               ResultSet rsBR = stmtBR.executeQuery(BR);
        %>   
        <form method="post" name="UpdateBuildContent" action="UpdateBuildRevisionParameter">
            <input style="display:none" name="ubcBuildFilter" value="<%=build%>"></input>
            <input style="display:none" name="ubcRevisionFilter" value="<%=revision%>"></input>
        <table  id="buildcontenttable"  style="text-align: left; border-collapse:collapse ; border-color: gainsboro" border="1">
                        <tr id="header">
                <td><%=dbDocS(conn,"page_buildcontent","delete","")%></td>
                <td><%=dbDocS(conn,"invariant","build","")%></td>
                <td><%=dbDocS(conn,"invariant","revision","")%></td>
                <td><%=dbDocS(conn,"application","Application","")%></td>
                <td><%=dbDocS(conn,"buildrevisionparameters","Release","")%></td>
                <td></img>Project</td>
                <td></img>Ticket</td>
                <td></img>Bug</td>
                <td>Subject</td>
                <td><%=dbDocS(conn,"buildrevisionparameters","ReleaseOwner","")%></td>
                <td colspan="2"><%=dbDocS(conn,"buildrevisionparameters","Link","")%></td>
                
                
<!--                <td><%=dbDocS(conn,"buildrevisionparameters","Link","")%></td>-->
            </tr>
<%
            int a = 1;
            String backColor = "white";
            if (rsBR.first()){
        do {
                                                            //Background color Management
                                                                        a++;
                                                                        int b;
                                                                          b = a%2;
                                                                          if (b == 1) 
                                                                          {backColor = "#f3f6fa";
                                                                          } 
                                                                          else 
                                                                          {backColor = "White";
                                                                          } 

                                                              // 
                                                           String[] ticketLinks = new String[0];
                                                           String linkToTickets = "";
                                                           if (!StringUtils.isNullOrEmpty(rsBR.getString("b.subject"))){
                                                           ticketLinks = rsBR.getString("b.subject").split(", ");
                                                           for ( int i = 0 ; i < ticketLinks.length ; i++){
                                                           linkToTickets = linkToTickets + "<a href=\"http://192.168.134.45/Cerberus/TestCase.jsp?ScTicket=" + ticketLinks[i] + "&Search=Y&SearchTc=Y\">"+ticketLinks[i]+" </a>";
                                                           }
                                                           }   
                                                           String[] bugLinks = new String[0];
                                                           String linkToBugs = "";
                                                           if (!StringUtils.isNullOrEmpty(rsBR.getString("b.subject"))){
                                                           bugLinks = rsBR.getString("b.subject").split(", ");
                                                           for ( int i = 0 ; i < bugLinks.length ; i++){
                                                           linkToBugs = linkToBugs + "<a href=\"http://192.168.134.35/Cerberus/TestCase.jsp?ScBugID=" + bugLinks[i] + "&Search=Y&SearchTc=Y\">"+bugLinks[i]+" </a>";
                                                           }
                                                           }
                                                           Statement stmtProj = conn.createStatement();
            %>
            <tr>
                <td class="wob" style="background-color:<%=backColor%>"><input name="ubcDelete" type="checkbox" style="width:10px ; background-color:<%=backColor%>" 
                                                                               value="<%=rsBR.getString("b.ID")%>"></td>
                <td class="wob" style="background-color:<%=backColor%>"><%=ComboInvariant(conn,"ubcBuild","width:60px ; background-color:"+backColor+"; font-size:x-small;border:0px","ubcBuild","","8",rsBR.getString("b.build"),"","NONE")%></td>
                <td class="wob" style="background-color:<%=backColor%>"><%=ComboInvariant(conn,"ubcRevision","width:40px ; background-color:"+backColor+"; font-size:x-small; border:0px","ubcRevision","","9",rsBR.getString("b.revision"),"","NONE")%></td>
                <td class="wob" style="background-color:<%=backColor%>"><select id="ubcApplication" name="ubcApplication" class="wob" style="width:100px; font-size:x-small;background-color:<%=backColor%>"><%
            ResultSet rsApp = stmtProj.executeQuery(" SELECT distinct application from application where application != '' and internal='Y' order by sort ");
              rsApp.first();
            do {
                    %><option value="<%=rsApp.getString("application")%>" <%=rsApp.getString("application").compareTo(rsBR.getString("b.Application")) == 0 ? " SELECTED " : ""%>><%=rsApp.getString("application")%></option><%
				} while (rsApp.next());
	%></select>
                    <input style="display:none" name="ubcReleaseID" value="<%=rsBR.getString("b.ID")%>"></td>
                <td class="wob" style="background-color:<%=backColor%>"><input class="wob" name="ubcRelease" style="width:100px ; background-color:<%=backColor%>; font-size:x-small" value="<%=rsBR.getString("b.Release")%>"></td>
                <td class="wob" style="background-color:<%=backColor%>"><select class="wob" name="ubcProject" value="<%=rsBR.getString("b.Project")%>" style="width: 50px; background-color:<%=backColor%>; font-size:x-small"><%
									ResultSet rsProj = stmtProj.executeQuery(" SELECT idproject, VCCode, Description from project order by idproject ");
												while (rsProj.next()) {
								%><option value="<%=rsProj.getString("idproject")%>"<%=rsBR.getString("b.Project").compareTo(rsProj.getString("idproject")) == 0 ? " SELECTED " : ""%>><%=rsProj.getString("idproject")%> [<%=rsProj.getString("VCCode")%>] <%=rsProj.getString("Description")%></option><%
									}
								%></select></td>
                <td class="wob" style="background-color:<%=backColor%>"><input class="wob" name="ubcTicketIDFixed" value="<%=rsBR.getString("b.TicketIDFixed")%>" style="width: 50px; background-color:<%=backColor%>; font-size:x-small"></td>
                <td class="wob" style="background-color:<%=backColor%>"><input class="wob" name="ubcBugIDFixed" value="<%=rsBR.getString("b.BugIDFixed")%>" style="width: 50px; background-color:<%=backColor%>; font-size:x-small"></td>
                <td class="wob" style="background-color:<%=backColor%>"><textarea class="wob" name="ubcSubject" value="<%=rsBR.getString("b.Subject")%>" rows="1" style="width: 300px; background-color:<%=backColor%>; font-size:x-small"><%=rsBR.getString("b.Subject")%></textarea></td>
                <td class="wob" style="background-color:<%=backColor%>"><select class="wob" name="ubcReleaseOwner" style="width: 100px; background-color:<%=backColor%>; font-size:x-small">
                <option value="" ></option><%
									ResultSet rsOwner = stmtProj.executeQuery(" SELECT Login, Name FROM user where name like '%(CDI)';");
												while (rsOwner.next()) {
								%><option value="<%=rsOwner.getString("Login")%>"<%=rsBR.getString("b.ReleaseOwner").compareTo(rsOwner.getString("Login")) == 0 ? " SELECTED " : ""%>><%=rsOwner.getString("Name")%></option><%
									}
								%>
        </select></td>
                <td class="wob" style="width:22px; background-color:<%=backColor%>">
                    <input style="display:inline; height:20px; width:20px; background-color: <%=backColor%>; color:blue; font-weight:bolder" title="Link" class="smallbutton" type="button" value="L" onclick="popup('<%=rsBR.getString("b.Link")%>')">
                </td>
                <td class="wob" style="background-color:<%=backColor%>"><textarea class="wob" name="ubcLink" value="<%=rsBR.getString("b.Link")%>" rows="1" style="width: 300px; background-color:<%=backColor%>; font-size:x-small" maxlength="<%=rsBR.getMetaData().getColumnDisplaySize(5) %>"><%=rsBR.getString("b.Link")%></textarea></td>
                <!--                <td class="wob" style="width: 200px; background-color:<%=backColor%>"><%=linkToTickets%><%=linkToBugs%></td>-->
                </tr><% 
            stmtProj.close();
                       } while (rsBR.next());
               }%></table>
            <input type="button" value="New Line" onclick="addBuildContent('buildcontenttable' )"></td></tr>


	<%=ComboInvariant(conn,"buildcontent_build_","visibility:hidden","buildcontent_build_","","8",build,"","NONE")%>
	<%=ComboInvariant(conn,"buildcontent_revision_","visibility:hidden","buildcontent_revision_","","9",revision,"","NONE")%>
        <select id="buildcontent_application_" name="buildcontent_application_" style="visibility:hidden"><%
            ResultSet rsApp = stmtApp.executeQuery(" SELECT distinct application from application where application != '' and internal = 'Y' order by sort ");
                while (rsApp.next()) {
                    %><option value="<%=rsApp.getString("application")%>" <%=rsApp.getString("application").compareTo("AS400") == 0 ? " SELECTED " : ""%>><%=rsApp.getString("application")%></option><%
				}
	%></select>
        <select id="ubcReleaseOwner_" name="ubcReleaseOwner_" style="visibility:hidden">
                <option value="" ></option><%
                Statement stmtProj = conn.createStatement();
		ResultSet rsOwner = stmtProj.executeQuery(" SELECT Login, Name FROM user where name like '%(CDI)';");
			while (rsOwner.next()) {
			%><option value="<%=rsOwner.getString("Login")%>"><%=rsOwner.getString("Name")%></option><%
			}%>
        </select>
        <select id="ubcProject_" name="ubcProject_" style="visibility:hidden"><%
                ResultSet rsProj = stmtProj.executeQuery(" SELECT idproject, VCCode, Description from project order by idproject ");
			while (rsProj.next()) {
			%><option value="<%=rsProj.getString("idproject")%>"><%=rsProj.getString("idproject")%> [<%=rsProj.getString("VCCode")%>] <%=rsProj.getString("Description")%></option><%
                        }
                        stmtProj.close();%>
        </select>

            <br><input type="submit" name="Save" value="Save">
            </form>
<%
            rsApp.close();
            rsBR.close();
            rsBuild.close();
            rsOwner.close();
            rsProj.close();
            rsRev.close();
            
            stmtApp.close();
            stmtBR.close();
            stmtBuild.close();
            stmtProj.close();
            stmtRev.close();
        
                        
        } catch (Exception e) {
            Logger.getLogger("buildcontent.jsp").log(Level.SEVERE, Version.PROJECT_NAME_VERSION + " - Exception catched.", e);
            out.println("<br> error message : " + e.getMessage() + " " + e.toString() + "<br>");
        } finally {
                                    try {
                                        conn.close();
                                    } catch (Exception ex) {
            Logger.getLogger("buildcontent.jsp").log(Level.SEVERE, Version.PROJECT_NAME_VERSION + " - Exception catched.", ex);
                                    }
                                }



    %>

<br><% out.print(display_footer(DatePageStart)); %>
    </body>
</html>