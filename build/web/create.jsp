
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Major"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert</title>
    </head>
    <body>
        <form action="create" method="post">
            RollCode: <input type="text" name="code">
            <br>
            Name: <input type="text" name="name">
            <br>
            DOB: <input type="date" name="dob">
            <br>
            Gender:
            <label>
                <input type="radio" name="gender" value="Male">Male
            </label>
            <label>
                <input type="radio" name="gender" value="Female">Female
            </label>  
            <br>
            Major:
            <select name="major">
                <%
                    List<Major> majorList = (List<Major>)request.getAttribute("major");
                    for (Major m : majorList) {
                %>
                <option value="<%=m.getMajor()%>"><%=m.getMajor()%></option>
                <%
                    }
                %>
            </select>
            <br>
            
            <input type="submit" value="Create">
        </form>
    </body>
</html>
