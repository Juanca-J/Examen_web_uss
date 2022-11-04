package quevedo.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quevedo.datos.impl.PropiedadesDaoImpl;
import quevedo.dominio.Propiedades;

import java.io.IOException;
import java.util.List;




/**
 * Servlet implementation class PropiedadesController
 */
public class PropiedadesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PropiedadesDaoImpl datos;
	Propiedades obj;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropiedadesController() {
        super();
    	
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	datos = new PropiedadesDaoImpl();
    	obj = new Propiedades();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion=request.getParameter("accion");
		if(accion == null) {
			accion = "list";
		}
		switch(accion) {
			case "create":{
				create(request, response); break;
			}
			case "edit":{
				edit(request, response); break;			
			}
			case "store":{
				store(request, response); break;
			}
			case "update":{
				update(request, response); break;
			}
			case "delete":{
				delete(request, response); break;
			}
			case "list":{
				list(request, response, null); break;			
			}
			default: {
				list(request, response, ""); break;
			}
		}


}

	private void list(HttpServletRequest request, HttpServletResponse response, String mensaje) {
		String texto=request.getParameter("texto");
		if(texto== null){
			texto="";
		}	
		try {
			List<Propiedades> lista = datos.listar(texto);
			request.setAttribute("lista", lista);
			request.setAttribute("texto",texto);
			request.setAttribute("mensaje",mensaje);
			request.getRequestDispatcher("propiedades/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean resp;
		
		String mensaje="Error al eliminar el registro";
		try {
			resp=datos.eliminar(id);
			if(resp) {
				mensaje="Registro eliminado correctamente";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		list(request,response,mensaje);
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		
		int id=Integer.parseInt(request.getParameter("id"));
		String nombre=request.getParameter("nombreP");
		String direccion = request.getParameter("direccionP");
		String caracteristicas = request.getParameter("caracteristicaP");
		String estado = request.getParameter("estadoP");
		Double precioalquiler = Double.parseDouble(request.getParameter("precioP"));
		
		String mensaje="Error al actualizar el registro";
		boolean resp;
		try {
			obj.setId(id);
			obj.setNombre(nombre);
			obj.setDireccion(direccion);
			obj.setCaracteristicas(caracteristicas);
			obj.setEstado(estado);
			obj.setPrecioalquiler(precioalquiler);
			resp=datos.actualizar(obj);
			if(resp) {
				mensaje="Registro actualizado correctamente";
			}
			list(request,response,mensaje);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void store(HttpServletRequest request, HttpServletResponse response) {
		
		String nombre=request.getParameter("nombreP");
		String direccion = request.getParameter("direccionP");
		String caracteristicas = request.getParameter("caracteristicaP");
		String estado = request.getParameter("estadoP");
		Double precioalquiler = Double.parseDouble(request.getParameter("precioP"));
		
		String mensaje="Error al insertar el registro";
		boolean resp;
		try {
			obj.setNombre(nombre);
			obj.setDireccion(direccion);
			obj.setCaracteristicas(caracteristicas);
			obj.setEstado(estado);
			obj.setPrecioalquiler(precioalquiler);
			resp=datos.insertar(obj);
			if(resp) {
				mensaje="Registro insertado correctamente";
			}
			list(request,response,mensaje);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		obj=datos.obtener(Integer.parseInt(id));	
		try {
			request.setAttribute("reg",obj);
			request.getRequestDispatcher("propiedades/form.jsp").forward(request,response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void create(HttpServletRequest request, HttpServletResponse response) {
		
		obj=new Propiedades();
		obj.setNombre("");
		obj.setDireccion("");
		obj.setCaracteristicas("");
		obj.setEstado("");
		obj.setPrecioalquiler(0.0);
		
		try {
			request.setAttribute("reg",obj);
			request.getRequestDispatcher("propiedades/form.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
