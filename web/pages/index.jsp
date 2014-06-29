<%--
  Created by IntelliJ IDEA.
  User: chenxiaoshun
  Date: 14-6-28
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css" />
    <script type="text/javascript" src="../assets/bootstrap/js/bootstrap.min.js" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <title>Tiny todolist</title>
  </head>
  <body>
    <div class="container-fluid">
        <ul class="nav nav-tabs" id="content">
            <li class="active">
                <table class="table table-striped">
                    <c:forEach items="todoItemList" var="todoItem">
                        <tr>
                            <td>${todoItem.title}</td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${todoItem.finishedTime}" /></td>
                        </tr>
                    </c:forEach>
                </table>
            </li>
        </ul>
    </div>
  </body>
</html>