/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sharm
 */
public class riderRegistrationServlet extends HttpServlet {
    
    
        private String studentId = null;
        private String name = null;
        private String email =null ;
        private String phone ;
        private String make =  null;
        private String model = null;
        private String color = null;
        private String size =null;

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
               
            studentId = request.getParameter("schoolId");
            name = request.getParameter("name");
            email = request.getParameter("email");
            phone = request.getParameter("phone");
            make = request.getParameter("make");
            model = request.getParameter("model");
            color = request.getParameter("color");
            size = request.getParameter("size");

            
            
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            String Driver = "org.mariadb.jdbc.Driver";
            Class.forName(Driver);
             //out.println("this");
            String dbUrl = "jdbc:mariadb://localhost:3306/apollo5_carpool";
            String usrName = "apollo5";
            String passWord = "Vandina!321";
    
            
            Connection connection = DriverManager.getConnection(dbUrl, usrName, passWord );
            Statement statement = connection.createStatement();
            
            String user_input = "INSERT INTO riderregistration(studentId, name, email, phone, make, model, color,size)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(user_input);            
            
            preparedStatement.setInt(1, Integer.parseInt(studentId));
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
      
            preparedStatement.setInt(4, Integer.parseInt(phone)); 
            //out.println("this5");
            preparedStatement.setString(5, make);
            preparedStatement.setString(6, model);
             
            preparedStatement.setString(7, color);
            
            preparedStatement.setString(8, size);
            
            preparedStatement.executeUpdate();          
            //out.println("this4");
            connection.close();

            
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(registrationServlet.class.getName()).log(Level.SEVERE, null, ex);
                 System.out.println("Error");
            } catch (SQLException ex) {
                Logger.getLogger(registrationServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error");
            }
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
