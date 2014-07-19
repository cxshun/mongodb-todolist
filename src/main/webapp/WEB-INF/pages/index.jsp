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
<%@ taglib uri="/dateTags" prefix="date" %>
<html>
  <head>
    <link rel="stylesheet" href='<c:url value="/assets/bootstrap/css/bootstrap-theme.min.css" />' />
    <link rel="stylesheet" href='<c:url value="/assets/bootstrap/css/bootstrap.min.css" /> '/>
    <script type="text/javascript" >
        days = ${days},finished = ${finished};
    </script>
    <script type="text/javascript" src='<c:url value="/assets/js/jquery-1.8.2.min.js" />' ></script>
    <script type="text/javascript" src='<c:url value="/assets/js/jquery.form.js" />' ></script>
    <script type="text/javascript" src='<c:url value="/assets/bootstrap/js/bootstrap.min.js" />' ></script>
    <script type="text/javascript" src='<c:url value="/assets/js/main.js" />' ></script>
    <meta name="viewport" content="width=device-width,initial-scale=1" >
    <title>Tiny todolist</title>
  </head>
  <body>
    <div class="container-fluid">
        <ul class="nav nav-tabs" role="tablist" id="todo-tab">
            <li><a href='<c:url value="/todoItem?action=list&days=1&finished=0" />' id="today">Today</a></li>
            <li><a href='<c:url value="/todoItem?action=list&days=3&finished=0" />' id="3day">3 days</a></li>
            <li><a href='<c:url value="/todoItem?action=list&days=7&finished=0" />' id="7day">7 days</a></li>
        </ul>
    </div>

    <div class="tab-content">
        <div class="tab-pane active" >
            <table class="table table-striped" id="todo-table">
                <c:forEach items="${todoItemList}" var="todoItem">
                    <tr>
                        <td><input type="checkbox" onclick="click2Finish('${todoItem.id}', ${days}, ${finished})"/></td>
                        <td>${todoItem.title}</td>
                        <td><date:timeStamp2Date value="${todoItem.predictFinishTime}" pattern="yyyy-MM-dd" /></td>
                        <td><span class="glyphicon glyphicon-remove" onclick="click2Delete('${todoItem.id}', ${days}, ${finished})"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <button type="button" class="btn btn-lg btn-primary" data-toggle="modal" data-target="#createDialog">Add task</button>

    <div class="modal fade" role="dialog" id="createDialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Add Task</h4>
                </div>
                <div class="modal-body">
                    <form role="form" id="createForm" name="createForm" action='<c:url value="/todoItem?action=create" />' />
                        <div class="form-group">
                            <label for="title">Title</label>
                            <input type="text" class="form-control" name="title" id="title" placeholder="Input title" />
                        </div>
                        <div class="form-group">
                            <label for="content">Content</label>
                            <textarea class="form-control" name="content" id="content" placeholder="Input content" ></textarea>
                        </div>
                        <div class="form-group">
                            <label for="finish_time">Predict Finish Date</label>
                            <input class="form-control" name="finish_time" id="finish_time" placeholder="Input finish_time" ></textarea>
                        </div>
                        <input type="hidden" value="${days}" name="days" />
                        <input type="hidden" value="${finished}" name="finished" />
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" onclick="$('#createForm').submit()">Save</button>
                </div>
            </div>
        </div>
    </div>
  </body>
</html>