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
public class contactUsServlet extends HttpServlet {
    
    private String name = null;
    private String email = null;
    private String subject = null;
    private String message = null;
    

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
   
        name = request.getParameter("name");
        email = request.getParameter("email");
        subject = request.getParameter("subject");
        message = request.getParameter("message");
        
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
            
            String user_input = "INSERT INTO contactus(name, email, subject, message)" + "VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(user_input);
            
            
            out.println(name + "\n");
            out.println(email + "\n");
            out.println(subject + "\n");
            out.println(message + "\n");
            
            //statement.executeUpdate ("INSERT INTO registration " + "VALUES(studentId, name, email, password, education, address, primary_phone, secondary_phone)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, subject);            
            preparedStatement.setString(4, message);
            out.println("this");
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
