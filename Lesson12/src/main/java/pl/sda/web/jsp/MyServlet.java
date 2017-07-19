/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sda.web.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Grzegorz
 */
public class MyServlet extends HttpServlet {
    
    
    

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
//            if (request.getMethod().equals("GET"))
//                response.sendRedirect("/Lesson12/");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            //out.println("<meta charset=\"utf-8\">");
            out.println("<title>Servlet MyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyServlet at " + request.getContextPath() + "</h1>");
            out.println("<h3>wersja aplikacji: " + getServletContext().getInitParameter("version") + "</h3>");
            if (request.getParameter("param1") != null && request.getParameter("param2") != null)
            out.println("<p>param1 = "+request.getParameter("param1")+" param2 = "+request.getParameter("param2")+"</p>");
            out.println("<a href='/Lesson12/ParameterServlet?param1=33&param2=44'>ParameterServlet link</a></p>");
            
            out.println("<form action=\"/Lesson12/HelloServlet\" method=\"post\">");
            out.println("First name:<br>");
            out.println("<input type=\"text\" name=\"firstname\"><br>");
            out.println("Last name:<br>");
            out.println("<input type=\"text\" name=\"lastname\"></p>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</form>");
            
            out.println("</p>");
            
            
            if(request.getParameter("firstname") != null && request.getParameter("lastname") != null)
                out.println("<h2>Witaj "+request.getParameter("firstname")+" "+request.getParameter("lastname")+"</h2>");
            
            out.println("</p>");
            
            out.println("<a href='/Lesson12/DbServlet'>Dodaj użytkownika do bazy</a>");
            out.println("</p>");
            out.println("<a href='/Lesson12/DbShowServlet'>Pokaż bazę użytkowników</a>");
            out.println("</p>");
            out.println("<a href='/Lesson12/AddBookServlet'>Dodaj książkę do bazy</a>");
            out.println("</p>");
            out.println("<a href='/Lesson12/ShowBooksServlet'>Pokaż bazę książek</a>");
            
            HttpSession session = request.getSession();
            if (session.isNew()) {
                session.setAttribute("licznik", 1);               
            }else {
                Integer counter = Integer.valueOf(session.getAttribute("licznik").toString());
                session.setAttribute("licznik", ++counter);
            }
            out.println("<h5>Licznik: "+session.getAttribute("licznik")+"</h5>");
            
            
//            Object attribute = session.getAttribute("licznik");
//            Integer counter = null;
//            if (attribute != null) {
//                counter = Integer.valu;               
//            }else {
//                Integer counter = Integer.valueOf(session.getAttribute("licznik").toString());
//                session.setAttribute("licznik1", ++counter);
//            }
//            out.println("<h5>Licznik: "+session.getAttribute("licznik1")+"</h5>");
//            
            //out.println("<p>"+request.getAttribute("Filtr1")+"</p>");
            
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
