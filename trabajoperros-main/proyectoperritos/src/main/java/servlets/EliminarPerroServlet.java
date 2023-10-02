/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.umariana.mundo.perro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    

/**
 *
 * @author sofi
 */
@WebServlet(name = "EliminarPerroServlet", urlPatterns = {"/EliminarPerroServlet"})

public class EliminarPerroServlet extends HttpServlet {
    
        ServletContext context = getServletContext();  
        SvPerro svA = (SvPerro) context.getAttribute("svA");
        ArrayList <perro> arrayPerro = svA.division; 
        perro miPerro = new perro();
        String nombre = miPerro.getNombre();
        
       
     

  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String perroName = request.getParameter("perroName");
        
        if (perroName!=null && !perroName.isEmpty()){
            
            try{
              perro perroEliminar =  null;
              for (perro miperro: arrayPerro){
                  if (nombre == perroName){
                      break;
                  }
                  
              }
              
            }catch(Exception e ){
                e.
                
            }
            
            
            
        }

}  
}
     
