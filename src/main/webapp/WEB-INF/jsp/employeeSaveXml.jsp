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

<h2>Employee Save</h2>

<form id="saveEmployee" action="/trinetix/employee/saveXml" method="POST">

    <table class="gridtable">
        <tr>
            <td><textarea name="employeeData" rows="10" cols="50"></textarea></td>
            <td><b>Format For Incoming Data</b> <br>
                &ltorg.trinetix.dto.Employee&gt<br>
                &ltId&gt35&lt/Id&gt	<br>
                &ltfirstName&gtAlexander&lt/firstName&gt <br>
                &ltlastName&gtTarasenko&lt/lastName&gt <br>
                &ltdateOfBirth&gt1966-09-09&lt/dateOfBirth&gt <br>
                &ltstartDate&gt2014-02-12&lt/startDate&gt <br>
                &lttitle&gtManager&lt/title&gt <br>
                &lt/org.trinetix.dto.Employee&gt</td>

        </tr>
    </table>
    <a href="#" onclick="document.getElementById('saveEmployee').submit();">Save</a>
    <a href="/trinetix/employee/list" >Cancel</a>
</form>

</body>
</html>

