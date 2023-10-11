/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Major;
import model.Student;

/**
 *
 * @author toanl
 */
public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {
            // Edit URL, username, password to authenticate with your MS SQL Server 
            //String url = "jdbc:sqlserver://localhost:1433;databaseName=Laptop";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Students;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "toanluon12";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Student> getStudent() {
        List<Student> students = new ArrayList<>();
        try {
            String sql = "select s.RollCode,s.FullName,s.DoB,s.Gender,m.Major\n"
                    + "from Student s, Major m\n"
                    + "where s.RollCode=m.RollCode";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setRollCode(rs.getInt("RollCode"));
                s.setName(rs.getString("FullName"));
                s.setDoB(rs.getDate("DoB"));
                s.setGender(rs.getString("Gender"));

                Major m = new Major();
                m.setMajorRollCode(rs.getInt("RollCode"));
                m.setMajor(rs.getString("Major"));
                s.setMajor(m);
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }

    public List<Major> getMajor() {
        List<Major> major = new ArrayList<>();
        try {
            String sql = "select m.RollCode,m.Major from Major m";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Major m = new Major();
                m.setMajorRollCode(rs.getInt("RollCode"));
                m.setMajor(rs.getString("Major"));
                major.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return major;
    }

    public Major getMajorByRollCode(String code) {
        Major ht = null;
        try {
            String sql = "SELECT [RollCode], [Major] "
                    + "FROM [Major]"
                    + "WHERE [RollCode] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ht = new Major();
                ht.setMajorRollCode(rs.getInt("RollCode"));
                ht.setMajor(rs.getString("Major"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ht;
    }

 public int getNextStudentId() throws SQLException {
        String sql = "SELECT MAX(rollcode) FROM student";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }
    
    //insert controller

    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO [dbo].[Student] ([rollcode], [fullname], [dob], [gender]) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, student.getRollCode());
        statement.setString(2, student.getName());
        statement.setDate(3, student.getDoB());
        statement.setString(4, student.getGender());

        statement.executeUpdate();

    }

    public void insertMajor(Major major) throws SQLException {
        String sql = "INSERT INTO [dbo].[Major] ([rollcode], [major]) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, major.getMajorRollCode());
        statement.setString(2, major.getMajor());

        statement.executeUpdate();
    }




public List<Student> sortStudentsByField(String field) {
    List<Student> sortedStudents = new ArrayList<>();
    try {
        // Xây dựng truy vấn SQL sắp xếp theo trường được chọn
        String sql = "";
        if (field.equals("Major")) {
            sql = "SELECT s.RollCode, s.FullName, s.DoB, s.Gender, m.Major " +
                  "FROM Student s INNER JOIN Major m ON s.RollCode = m.RollCode " +
                  "ORDER BY m.Major";
        } else {
            sql = "SELECT s.RollCode, s.FullName, s.DoB, s.Gender, m.Major " +
                  "FROM Student s INNER JOIN Major m ON s.RollCode = m.RollCode " +
                  "ORDER BY " + field;
        }
        
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        
        // Thêm Sinh viên vào danh sách đã sắp xếp
        while (rs.next()) {
            Student student = new Student();
            student.setRollCode(rs.getInt("RollCode"));
            student.setName(rs.getString("FullName"));
            student.setDoB(rs.getDate("DoB"));
            student.setGender(rs.getString("Gender"));        
                Major major = new Major();
                major.setMajor(rs.getString("Major"));
                student.setMajor(major);
            
            sortedStudents.add(student);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return sortedStudents;
}
public Student getStudentByRollCode(int rollCode) {
    Student student = null;
    try {
        String sql = "SELECT s.RollCode, s.FullName, s.DoB, s.Gender, m.Major "
                   + "FROM Student s "
                   + "INNER JOIN Major m ON s.RollCode = m.RollCode "
                   + "WHERE s.RollCode = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, rollCode);
        ResultSet rs = statement.executeQuery();
        
        if (rs.next()) {
            student = new Student();
            student.setRollCode(rs.getInt("RollCode"));
            student.setName(rs.getString("FullName"));
            student.setDoB(rs.getDate("DoB"));
            student.setGender(rs.getString("Gender"));
            
            // Lấy thông tin về ngành học
            Major major = new Major();
            major.setMajor(rs.getString("Major"));
            student.setMajor(major);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return student;
}
 public List<Student> sortStudentByGender() {
        List<Student> students = getStudent();
        students.sort((s1, s2) -> s1.getGender().compareTo(s2.getGender()));
        return students;
    }

    public List<Student> sortStudentByMajor() {
        List<Student> students = getStudent();
        students.sort((s1, s2) -> s1.getMajor().getMajor().compareTo(s2.getMajor().getMajor()));
        return students;
    }

    public List<Student> sortStudentByRollCode() {
        List<Student> students = getStudent();
        students.sort((s1, s2) -> s1.getRollCode()- s2.getRollCode());
        return students;
    }

}
