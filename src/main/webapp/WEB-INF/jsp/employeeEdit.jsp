<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Employee Manager</title>
	<style type="text/css">
        body {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
        }


        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
            margin-top: 10px;
            margin-bottom: 10px;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
	</style>
</head>
<body>

<h2>Employee Edit</h2>

<form:form modelAttribute="employeeAttribute" method="POST" action="/trinetix/employee/save">
<table class="gridtable">
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Title</th>
        <th>Date of Birth</th>
        <th>Start Date</th>
        <th>Manager</th>
    </tr>

        <tr>
            <td>${employee.id}  </td>
            <td>${employee.firstName}"</td>
            <td>${employee.lastName}"</td>
            <td>
                <select name="title">
                    <c:forEach items="${titles}" var='title'>
                        <c:choose>
                            <c:when test="${employee.title == title}">
                                <option value="${title}" selected="true">${title}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${title}">${title}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>${employee.dateOfBirth}"</td>
            <td>${employee.startDate}"</td>
            <td>
                <select name="managerId">
                    <c:choose>
                        <c:when test="${employee.managerId == null || employee.managerId ==-1}">
                            <option value="-1"  selected="true">No Manager</option>
                        </c:when>
                        <c:otherwise>
                            <option value="-1">No Manager</option>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach items="${managers}" var='manager'>
                        <c:choose>
                            <c:when test="${employee.managerId == manager.id}">
                                <option value="${manager.id}" selected="true">${manager.firstName}&nbsp;${manager.lastName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${manager.id}">${manager.firstName}&nbsp;${manager.lastName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="7"><p>Comments:</p> ${employee.comments}</td>
        </tr>
</table>
<input type="hidden" name="id" value="${employee.id}">
<input type="hidden" name="dateOfBirth" value="${employee.dateOfBirth}">
<input type="hidden" name="startDate" value="${employee.startDate}">
<input type="hidden" name="firstName" value="${employee.firstName}">
<input type="hidden" name="lastName" value="${employee.lastName}">
<input type="hidden" name="comments" value="${employee.comments}">
<a href="#" onclick="document.getElementById('employeeAttribute').submit();">Save</a>
<a href="/trinetix/employee/list" >Cancel</a>
</form:form>

</body>
</html>
