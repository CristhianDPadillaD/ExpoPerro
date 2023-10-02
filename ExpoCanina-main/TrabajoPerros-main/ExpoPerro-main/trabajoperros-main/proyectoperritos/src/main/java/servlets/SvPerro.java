 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;


import com.umariana.mundo.exposicionPerro;
import com.umariana.mundo.perro;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//servlet llamado SvPerro
@MultipartConfig
@WebServlet(name = "SvPerro", urlPatterns = {"/SvPerro"})
public class SvPerro extends HttpServlet {
//array llamado division
    
     ArrayList <perro> division = new ArrayList <>();
    
    @Override
    public void init() throws ServletException{
        super.init();
        
        ServletContext serverletContext = getServletContext();
        division= exposicionPerro.deserializar(serverletContext);

    }
    
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

           String nombre = request.getParameter("nombre");
           perro perro = exposicionPerro.BuscarPerroPorNombre(nombre,division); // Implementa la lógica para buscar el perro en tu lista de perros
        if (perro != null) {
            // Genera la respuesta HTML con los detalles del perro
            String perroHtml = "<h2>Nombre: " + perro.getNombre() + "</h2>" +
                               "<p>Raza: " + perro.getRaza() + "</p>" +
                               "<p>Puntos: " + perro.getPuntos() + "</p>" +
                               "<p>Edad (años): " + perro.getEdad() + "</p>" +
                               "<img src='imagenes/" + perro.getImagen()+ "' alt='" + perro.getNombre() + "' width='100%'/>";
            response.setContentType("text/html");
            response.getWriter().write(perroHtml);
        } else {
            // Maneja el caso en el que no se encuentra el perro
            response.setContentType("text/plain");
            response.getWriter().write("Perro no encontrado");
        }
        
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context=  getServletContext();;
         //obtener la parte de arhivo (foto)
        Part fotoPart = request.getPart("foto");
        System.out.println("1" +fotoPart);
        
        //Nombre original del archivo
        String fileName = fotoPart.getSubmittedFileName();
        System.out.println("2"+fileName);
        
        //Directorio donde se almacenará el archivo
        String uploadDirectory = getServletContext().getRealPath("./imagenes");
        System.out.println("3" +uploadDirectory);
        
        
        //Ruta completa del archivo a guardar
        String filePath = uploadDirectory + File.separator+ fileName;
        
        //Guardar el archivo en sistemas de archivos
        
       try ( InputStream input = fotoPart.getInputStream();
           
           OutputStream output = new FileOutputStream(filePath)){
           
           
           byte[] buffer = new byte[1024];
           int length; 
           while((length = input.read(buffer))> 8 ){
               output.write(buffer, 0, length);
           }
       }
        
                
        //captura de los datos y se imprimen en pantalla
     
     String nombre = request.getParameter("nombre");
     String raza = request.getParameter("raza");
     String foto = request.getParameter(fileName);
     String puntos = request.getParameter("punto");
     String edad = request.getParameter("edad");

      System.out.println(nombre);
      System.out.println(raza);
      System.out.println(foto);
      System.out.println(puntos);
      System.out.println(edad);
     
        
         //ingresar al objeto 
         
          int edadint = Integer.parseInt(edad);
          int puntosint = Integer.parseInt(puntos);
          perro miPerro= new perro(nombre, raza, fileName, puntosint,edadint);
          
    //añado los datos que cree al array
    
        division.add(miPerro);
     
        //agregar el arrayList  al objeto de solicitud como un atributo
        exposicionPerro.guardarDatosPerro(division, context);
        
        
        request.setAttribute("generoDivision", division);
        
        //redireccionamos a la pagina web destino 
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
       
    
    
    @Override   
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
   
 }




