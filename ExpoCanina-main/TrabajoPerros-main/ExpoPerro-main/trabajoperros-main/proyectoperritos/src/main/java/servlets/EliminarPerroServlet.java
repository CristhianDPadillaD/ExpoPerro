package servlets;

import com.umariana.mundo.exposicionPerro;
import java.util.ArrayList;
import servlets.SvPerro;
import com.umariana.mundo.perro;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebas
 */
@WebServlet(urlPatterns = {"/EliminarPerroServlet"})

public class EliminarPerroServlet extends HttpServlet {
SvPerro svPerro = new SvPerro();
        // Dentro de un método estático
perro miPerro = new perro(); // Crea una instancia de perro
String nombre = miPerro.getNombre(); // Llama a getNombre() en la instancia

ArrayList <perro> misperros = svPerro.division;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ServletContext context =getServletContext();
            String nombreE=request.getParameter("perroName");
            exposicionPerro.eliminarPerro(context, nombreE);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
            
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            System.out.println("Entraaa");
            String nombreB=request.getParameter("nombreBuscar");
            System.out.println(nombreB);
            request.getRequestDispatcher("index.jsp?nombreBuscar="+nombreB).forward(request, response);
        }
    

}
