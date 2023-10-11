
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="model.Major"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
    </head>
    <body>
    <center>
        <table border="1px">
            <thead>
            <th>RollCode</th>
            <th>FullName</th>
            <th>DoB</th>
            <th>Gender</th>
            <th>Major</th>
        </thead>
        <tbody>
            <%
                Student student = (Student)request.getAttribute("student");
            %>
            <tr>
                <td><%=student.getRollCode()%></td>
                <td><%=student.getName()%></td>
                <td><%=student.getDoB()%></td>
                <td><%=student.getGender()%></td>
                <td><%=student.getMajor().getMajor()%></td>

            </tr>
        </tbody>    
        <tfoot>
            <tr>
                <td colspan="6">
                    <form action="list">
                        <input type="submit" value="Back to list">
                    </form>
                </td>
            </tr>
        </tfoot>
    </table>
                </center>
</body>
</html>