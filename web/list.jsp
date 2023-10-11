
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Student"%>
<%@page import="model.Major"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Student</title>
    </head>
    <body>
    <center>
        <h1>List of Student</h1>
        <table border="1px">
            <thead>
            <th>RollCode</th>
            <th>FullName</th>
            <th>DoB</th>
            <th>Gender</th>
            <th>Major</th>
            <th></th>
        </thead>
        
        <tbody>
            
            <%
                List<Student> student = (List<Student>)request.getAttribute("students");
                for (Student st : student) {
                
            %>
            <tr>
                
                <td><%=st.getRollCode()%></td>
                <td><%=st.getName()%></td>
                <td><%=st.getDoB()%></td>
                <td><%=st.getGender()%></td>
                <td><%=st.getMajor().getMajor()%></td>
                 <td>
                    <form action="detail" method="post">
                        <input type="hidden" name="id" value="<%=st.getRollCode()%>">
                        <input type="submit" value="detail">
                    </form>        
                </td>
                            </tr>
                            
            <%
                }
            %>
            
        
            
        </tbody>
        <tfoot>
            <tr>
                
                <td colspan="5">
                    <form action="create">
                        <input type="submit" value="Create">
                    </form>
                </td>
            </tr>
            
        </tfoot>
        </table>
            <form action="list" method="post">
    Sort by:
    <select name="sort">
        <option value="RollCode">Roll Code</option>
        <option value="Gender">Gender</option>
        <option value="Major">Major</option>
    </select>
    <input type="submit" value="Sort">
</form>
            

        </center>
           
</body>
</html>
