<%-- 
    Document   : index
    Created on : 20/09/2023, 10:56:10 a. m.
    Author     : Grupo 7
--%>

<%@page import="java.io.IOException"%>
<%@include file="temps/header.jsp" %>
       
   <!-- Imagen de encabezado con la clase "PerroBandera" -->

   <image  class= "PerroBandera" src="./img/perro.jpg" >
    <div class="row">
    <div class="col">
     <div class="card">
        <div class="card-body" >

   <!-- Formulario para ingresar informaci√≥n sobre un perro -->

            <form action="SvPerro" method="POST" enctype="multipart/form-data">

   <!-- T√≠tulo del formulario -->
                <div class="container text-center">
                 <legend>Ingresar Perro </legend>
                  </div>
    <!-- Campo para ingresar el nombre del perro -->
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Nombre</span>
                        <input type="text" class="form-control" name="nombre"  placeholder="Nombre" aria-label="Username" aria-describedby="basic-addon1" required="true">
                   </div>
     <!-- Campo para ingresar la raza del perro -->
                     <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Raza</span>
                        <input type="text" class="form-control" name="raza"  placeholder="Raza" aria-label="Username" aria-describedby="basic-addon1" required="true">
                   </div>
     
     
       <!-- Campo para ingresar la URL de la foto del perro -->
                  <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Foto</span>
                        <input type="file" class="form-control" name="foto"  placeholder="url foto" aria-label="Username" aria-describedby="basic-addon1" required="true">
                   </div>
       
       
        <!-- desplegable para asignar puntos a un  perro -->
                    <select class="form-select" aria-label="Default select example" name="punto" required="true" >
                        <option selected>Puntos</option>
                        <option value="1">Uno</option>
                        <option value="2">Dos</option>
                        <option value="3">Tres</option>
                        <option value="4">Cuatro</option>
                        <option value="5">Cinco</option>
                        <option value="6">Seis</option>
                        <option value="7">Seite</option>
                        <option value="8">Ocho</option>
                        <option value="9">Nueve</option>
                        <option value="10">Diez</option>
                      </select>
                <br>
     <!-- Campo para ingresar la edad del perro -->
                        <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Edad</span>
                        <input type="number" class="form-control" name="edad"  placeholder="Numero" aria-label="Username" aria-describedby="basic-addon1" required="true">
                        </div> 

      <!-- Bot√≥n para enviar el formulario -->          
                 <div class="mb-3">
                         <input type="submit" value="Insertar Perro" class="btn btn-primary">
                   </div>
            </form>
             </div>
           </div>
        </div>
    <div class="col">
             <div class="card">
     <div class="card-body">

        <!-- Tabla para mostrar los datos de los perros -->
          <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Raza</th>
                            <th>Foto</th>
                            <th>Puntos</th>
                            <th>Edad</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                  <tbody>
              <%
            
            ArrayList <perro> misPerros = null;
            // Obtener la ruta real del archivo de datos
            String dataPath = application.getRealPath("/Data/datosperros.ser");
            
            // Repetimos el proceso de carga de datos porque:
            // Si invocas directamente la p√°gina JSP de listar videos, el request no tiene el atributo que est√°s buscando
            // Es nulo, de ah√≠ que te d√© ese error. @RubioRic (Stack Overflow en espa√±ol)
            
            File archivo = new File(dataPath);
          try {
                if (archivo.exists()) {
                    FileInputStream fis = new FileInputStream(dataPath);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    misPerros = (ArrayList<perro>) ois.readObject();
                    ois.close();
                    System.out.println("Datos de perros cargados exitosamente desde: " + dataPath);
                }
                } catch (IOException e) {
                    e.printStackTrace();
                    // Manejar el error aqu√≠, por ejemplo, registr√°ndolo o tomando medidas adecuadas.
                }

            
            // Obtener array list de la solicitud
            // Realizamos un cambios de out.print a strong para que la interfaz se adapte mas flexiblemnte
            // Pero respetamos la logica de Java
            
             if (misPerros != null) {
                System.out.println("Se cargaron " + misPerros.size() + " perros exitosamente a√±adidos");
                for (perro miperro : misPerros) {
                
        %>
   
        <!--  -->
                        <tr>
                            <td><%= miperro .getNombre() %></td>
                            <td><%= miperro .getRaza() %></td>
                            <td><%= miperro .getImagen()%></td>
                            <td><%= miperro .getPuntos() %></td>
                            <td><%= miperro .getEdad() %></td>
                             <td>  <div class="btn-group me-2" role="group" aria-label="First group">
                                     
                                     <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-nombre="<%=miperro.getNombre()%>"><i class ="fa-solid fa-eye"></i> </a>
                                     
                                     <button type="button" class="btn btn-secondary"><i class="fa-solid fa-pen"></i></button>

                                    <button type="button" class="btn btn-secondary"><i class="fa-solid fa-trash"></i></button>
                                  </div></td>
                        </tr>
           <%
                }
            } else {
                out.print("No hay Perros disponibles.");
            }
         %>
                    </tbody>
                </table>
     </div>
   </div>
    </div>
                  <!-- Modal -->
     <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> 
         <div class="modal-dialog"> 
             <div class="modal-content"> 
                 <div class="modal-header"> 
                    <h5 class="modal-title" id="exampleModalLabel">Detalles del Perro</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> 
                 </div>
                 <div class="modal-body"> 
                  
                     <div id="perro-details"> 
                         <!-- AquÌ se-->
                </div>
                 </div> 
                 <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button> 
                </div>
             </div> 
         </div> 
     </div>
           
                    
    </div>
<%@include file="temps/footer.jsp" %>

<script>
    // funcion para mostrar los datos en la ventana modal
  $('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // BotÛn que desencadenÛ el evento
    var nombre = button.data('nombre'); // ObtÈn el nombre del perro

    // Realiza una solicitud AJAX al servlet para obtener los detalles del perro por su nombre
    $.ajax({
      url: 'SvPerro?nombre=' + nombre, // Cambia 'id' por el nombre del par·metro que esperas en tu servlet
      method: 'GET',
      success: function (data) {
        // Actualiza el contenido del modal con los detalles del perro
        $('#perro-details').html(data);
      },
      error: function () {
        // Maneja errores aquÌ si es necesario
        console.log('Error al cargar los detalles del perro.');
      }
    });
  });

</script>
