/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sda.web.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adrian
 */
@WebServlet(name = "DbServlet", urlPatterns = {"/DbServlet"})
public class DbServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DbServlet</title>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<form action='/servlet/DbServlet' method='post'><input type='text'/><input type='submit' value='Ok'/></form>");
            
            out.println("<form action=\"/Lesson12/DbServlet\" method=\"post\">");
            out.println("First name:<br>");
            out.println("<input type=\"text\" name=\"firstname\"><br>");
            out.println("Last name:<br>");
            out.println("<input type=\"text\" name=\"lastname\"></p>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        try {
            String url = "jdbc:mysql://localhost/books_db?serverTimezone=CET&useSSL=false";
            String userName = "root";
            String password = "root";
            
            
            DBConnectionManager dbConnectionManager = new DBConnectionManager(url, userName, password);
            Connection connection = dbConnectionManager.getConnection();
            
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement("insert into users_servlets(first_name, last_name, add_date) values (?,?,now())");               
                preparedStatement.setString(1, request.getParameter("firstname"));
                preparedStatement.setString(2, request.getParameter("lastname")); 
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet DbServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h4><a href='/Lesson12/MyServlet'>Powrót</a></h4>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (ClassNotFoundException ex) {            
            Logger.getLogger(DbServlet.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            Logger.getLogger(DbServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
