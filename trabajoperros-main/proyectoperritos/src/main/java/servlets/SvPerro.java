/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;


import com.umariana.mundo.perro;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvPerro", urlPatterns = {"/SvPerro"})
public class SvPerro extends HttpServlet {

    ArrayList <perro> division = new ArrayList <>();
    
    public void init() throws ServletException{
        super.init();
        
        ServletContext serverletContext = getServletContext();
        
        subirDatosPerro(serverletContext);
        
        
    }
 
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //captura de los datos 
     
     String nombre = request.getParameter("nombre");
     String raza = request.getParameter("raza");
     String foto = request.getParameter("foto");
     String puntos = request.getParameter("punto");
     String edad = request.getParameter("edad");

      System.out.println(nombre);
      System.out.println(raza);
      System.out.println(foto);
      System.out.println(puntos);
      System.out.println(edad);
     
     try {
                  
         //ingresar al objeto 
         
          int edadint = Integer.parseInt(edad);
          int puntosint = Integer.parseInt(puntos);
          perro miPerro= new perro(nombre, raza, foto, puntosint,edadint);
          
    //añado los datos que cree al array
    
     division.add(miPerro);
     
     } catch (NumberFormatException e) {
         
         e.printStackTrace();
         System.out.println("Ah ocurrido un error" + e.getMessage());
     }
     
    
        
        //agregar el arrayList  al objeto de solicitud como un atributo
        guardarDatosPerro();
        
        request.setAttribute("generoDivision", division);
        
        //redireccionamos a la pagina web destino 
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
       
      
    
        private void guardarDatosPerro() {
        try {
           
            String dataPath = getServletContext().getRealPath("/Data");

           
            File dataFolder = new File(dataPath);
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }

           
            
            
            String filePath = dataPath + File.separator + "datosperros.ser";
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(division);
            oos.close();
            System.out.println("los datos de videos guardados exitosamente!!: " + filePath);
        } catch (IOException e) {
          e.printStackTrace();
            System.out.println("Error al guardar los datos de el video añadido: " + e.getMessage());
        }
        
    }

    
    @Override   
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void subirDatosPerro(ServletContext servletContext) {
            
     try {
            String dataPath = servletContext.getRealPath("/Data/datosperros.ser");
            File archivo = new File (dataPath);
            
            if(archivo.exists()) {
           FileInputStream fis = new FileInputStream(dataPath);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    division = (ArrayList<perro>) ois.readObject();
                    ois.close();
                    
                }
              
                System.out.println("Los datos que usted buscaban eran desde: " + dataPath);
            }  else {
       
        System.out.println("Ups! el archivo no existe: " + dataPath);
    }
            
        } catch (EOFException e) {
    
    System.out.println("Llegaste al final del archivo! ");
    e.printStackTrace(); 
     }
    catch (IOException  | ClassNotFoundException e) {
            
            e.printStackTrace();
            
            System.out.println("Error al cargar los datos de los videos digitados: " + e.getMessage());
            
        }
        
    }
 }


