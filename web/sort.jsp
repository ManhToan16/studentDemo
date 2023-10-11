<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Student"%>
<%@page import="model.Major"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sorted Student List</title>
</head>
<body>
<center>
    <h2>Sorted Student List</h2>
    <table border="1">
        <tr>
            <th>Roll Code</th>
            <th>Full Name</th>
            <th>Date of Birth</th>
            <th>Gender</th>
            <th>Major</th>
        </tr>
       <tbody>
            <%
                List<Student> student = (List<Student>)request.getAttribute("sortedStudents");
                for (Student st : student) {
            %>
            <tr>
                <td><%=st.getRollCode()%></td>
                <td><%=st.getName()%></td>
                <td><%=st.getDoB()%></td>
                <td><%=st.getGender()%></td>
                <td><%=st.getMajor().getMajor()%></td>
            </tr>
            <%
                }
            %>
        </tbody>    
    </table>
        </center>
</body>
</html>

