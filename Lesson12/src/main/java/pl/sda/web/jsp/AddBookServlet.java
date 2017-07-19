/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sda.web.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grzegorz
 */
public class AddBookServlet extends HttpServlet {

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
            
            out.println("<form action=\"/Lesson12/AddBookServlet\" method=\"post\">");
            out.println("Tytuł:<br>");
            out.println("<input type=\"text\" name=\"title\"><br>");
            out.println("ISBN:<br>");
            out.println("<input type=\"text\" name=\"isbn\" value='xxx-x-xx-xxxxxx-x'><br>");
             out.println("Data wydania:<br>");
            out.println("<input type=\"text\" name=\"date\" value='xxxx-xx-xx'><br>");
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
        //processRequest(request, response);
        try {
            String url = "jdbc:mysql://localhost/books_db?serverTimezone=CET&useSSL=false";
            String userName = "root";
            String password = "root";
            
            
            DBConnectionManager dbConnectionManager = new DBConnectionManager(url, userName, password);
            Connection connection = dbConnectionManager.getConnection();
            //ctx.setAttribute("DBConnection", connectionManager.getConnection());
            //Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement("insert into books(title, isbn, release_date) values (?,?,?)");               
                preparedStatement.setString(1, request.getParameter("title"));
                preparedStatement.setString(2, request.getParameter("isbn")); 
                preparedStatement.setString(3, request.getParameter("date")); 
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet AddBookServlet</title>");
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
