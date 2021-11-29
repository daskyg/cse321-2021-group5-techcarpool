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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sharm
 */
public class carpoolServlet extends HttpServlet {
    
    
    private String userName = null;
    private String password = null;
    

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
        //processRequest(request, response);
         userName =  request.getParameter("userName");
         password = request.getParameter("password");
         String storePassword = null;
         String someMessage = "Incorrect Password !";

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
            
            String imprtQuery = "SELECT * FROM registrationtable WHERE email = '" + userName+ "';" ;
            ResultSet rs = statement.executeQuery(imprtQuery);

            while(rs.next()){
                storePassword = rs.getString("password");
                
            }
            /*===user name and password validation===*/
            if(!((storePassword.length() == password.length()) && storePassword.matches(password) )){
                out.println("<script type='text/javascript'>");
                out.println("alert(" + "'" + someMessage + "'" + ");</script>");
                out.println("</head><body></body></html>");
                RequestDispatcher rd=request.getRequestDispatcher("index.html");  
                rd.include(request,response);  
                
            }else {
                 RequestDispatcher rd=request.getRequestDispatcher("home.html"); 
                 rd.include(request,response);
            }
            
            rs.close();
            statement.close();
            connection.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(carpoolServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(carpoolServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         /*
         

    
            
  
            
            String educationQuery = "SELECT * FROM education;";
            String course1 = "SELECT * FROM Relevent_course WHERE ID ='1';";
            String course2 = "SELECT courses FROM Relevent_course WHERE ID ='2';";
         
         
         
          ResultSet rs = statement.executeQuery(educationQuery);
            
            ResultSet crs1 = statement.executeQuery(course1);
            ResultSet crs2 = statement.executeQuery(course2);
         
         
         
            
            
            
         */
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
