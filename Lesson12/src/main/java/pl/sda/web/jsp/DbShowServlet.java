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
import java.sql.ResultSet;
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
public class DbShowServlet extends HttpServlet {

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
            out.println("<title>Servlet DbShowServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DbShowServlet at " + request.getContextPath() + "</h1>");
            
            try {
            String url = "jdbc:mysql://localhost/books_db?serverTimezone=CET&useSSL=false";
            String userName = "root";
            String password = "root";
            
            
            DBConnectionManager dbConnectionManager = new DBConnectionManager(url, userName, password);
            Connection connection = dbConnectionManager.getConnection();
            //ctx.setAttribute("DBConnection", connectionManager.getConnection());
            //Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = connection.prepareStatement("SELECT * FROM users_servlets");              
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            out.println("<table style=\"width:20%\" border= 1px solid black>");
            out.println("<tr>");
            out.println("<th>Imię</th>");
            out.println("<th>Nazwisko</th>");
            out.println("<th>Data dodania</th>");
            out.println("</tr>");
            
            while (resultSet.next()){
                out.println("<tr>");
                out.println("<td>"+resultSet.getString("first_name")+"</td>");
                out.println("<td>"+resultSet.getString("last_name")+"</td>");
                out.println("<td>"+resultSet.getString("add_date")+"</td>");
                out.println("</tr>");
            }
                out.println("</table>");
                
            
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet DbServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h4><a href='/Lesson12/MyServlet'>Powrót</a></h4>");
                out.println("</body>");
                out.println("</html>");
            
        } catch (ClassNotFoundException ex) {
            String message = ex.getMessage();
            Logger.getLogger(DbServlet.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            Logger.getLogger(DbServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
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
        processRequest(request, response);
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
