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

<h2>Employee List</h2>
<br> <a href="/trinetix/employee/editXml">Add new employee</a>


<table class="gridtable">
    <tr>
        <th><a href="list?orderBy=ID">Id</a></th>
        <th><a href="list?orderBy=FIRST_NAME">First Name</a></th>
        <th><a href="list?orderBy=LAST_NAME">Last Name</a></th>
        <th><a href="list?orderBy=TITLE">Title</a></th>
        <th><a href="list?orderBy=DATE_OF_BIRTH">Date of Birth</a></th>
        <th><a href="list?orderBy=START_DATE">Start Date</a></th>
        <th>Manager</th>
        <th>Action</th>

    </tr>
    <c:forEach items="${employeeList}" var="employee" >
        <tr>
            <td>${employee.id}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.title}</td>
            <td>${employee.dateOfBirth}</td>
            <td>${employee.startDate}</td>
            <td>${employee.manager}</td>
            <td><a href="edit/${employee.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
