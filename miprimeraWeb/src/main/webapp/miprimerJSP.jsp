<%-- 
    Document   : miprimerJSP
    Created on : 24-sep-2018, 9:42:49
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>
            <c:out value="${param.k}" />
            <%=request.getParameter("k")%>
        <%
            int x = 3;

        %>
        </h2>
        <h1 style="color: red">Hello World!</h1>

    </body>
</html>
