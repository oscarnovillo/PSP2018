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


        </h2>
        <c:if test="${param.red != null}" >
            <h1 style="color: red"><c:out value="${param.red}" /></h1>
        </c:if>

    </body>
</html>
