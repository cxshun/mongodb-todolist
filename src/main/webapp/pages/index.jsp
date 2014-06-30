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
            <li class="active" id="today">
                <table class="table table-striped">
                    <c:forEach items="todoItemList" var="todoItem">
                        <tr>
                            <td><input type="checkbox" onclick="click2Finish(${todoItem.id})"/></td>
                            <td>${todoItem.title}</td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${todoItem.finishedTime}" /></td>
                            <td><span class="glyphicon glyphicon-remove" onclick="click2Delete(${todoItem.id})"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </li>
            <li id="three_days">
               <table class="table table-striped">

               </table>
            </li>
            <li id="seven_days">
                <table class="table table-striped">

                </table>
            </li>
        </ul>
    </div>
  </body>
</html>