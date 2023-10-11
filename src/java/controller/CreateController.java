package controller;

import dal.DBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;
import model.Major;

/**
 *
 * @author HP
 */
@WebServlet(name = "InsertController", urlPatterns = {"/create"})
public class CreateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị trang create.jsp khi yêu cầu GET được gửi đến servlet
        DBContext db = new DBContext();
        List<Major> majorList = db.getMajor();
        request.setAttribute("major", majorList);
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nhận thông tin từ form
        int rollCode = Integer.parseInt(request.getParameter("code"));
        String name = request.getParameter("name");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String gender = request.getParameter("gender");
        String majorName = request.getParameter("major");

        // Tạo đối tượng Student và Major
        Student student = new Student();
        student.setRollCode(rollCode);
        student.setName(name);
        student.setDoB(dob);
        student.setGender(gender);

        Major major = new Major();
        major.setMajorRollCode(rollCode);
        major.setMajor(majorName);

        // Đưa thông tin Student vào Database (sử dụng phương thức insertStudentWithMajor trong DBContext)
        DBContext db = new DBContext();
        try {
            db.insertStudent(student);
            db.insertMajor(major);
        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }
        List<Student> students = db.getStudent();

        // Set the students attribute in the request
        request.setAttribute("students", students);

        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
