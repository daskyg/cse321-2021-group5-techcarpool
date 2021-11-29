/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
public class registrationServlet extends HttpServlet {
    
        private String studentId = null;
        private String name = null;
        private String email =null ;
        private String password = null ;
        private String re_password =  null;
        private String education = null;
        private String address = null;
        private String primary_phone =null;
        private String secondary_phone = null;

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
            password = request.getParameter("pass");
            re_password = request.getParameter("re_pass");
            education = request.getParameter("education");
            address = request.getParameter("address");
            primary_phone = request.getParameter("phone");
            secondary_phone = request.getParameter("sec_phone");
            
            
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
            
            String user_input = "INSERT INTO registrationTable(studentId, name, email, password, education, address, primary_phone, secondary_phone)" + "VALUES(?, ?, ?, ?, ?, ?, ? ,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(user_input);
            
            //statement.executeUpdate ("INSERT INTO registration " + "VALUES(studentId, name, email, password, education, address, primary_phone, secondary_phone)");
            preparedStatement.setInt(1, Integer.parseInt(studentId));
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);            
            preparedStatement.setString(5, education);
            preparedStatement.setString(6, address);
            preparedStatement.setInt(7, Integer.parseInt(primary_phone));
            preparedStatement.setInt(8, Integer.parseInt(secondary_phone));
            preparedStatement.executeUpdate();          
            
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
