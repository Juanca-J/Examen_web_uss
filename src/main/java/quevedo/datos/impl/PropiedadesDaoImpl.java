package quevedo.datos.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import quevedo.database.Conexion;
import quevedo.datos.PropiedadesDao;
import quevedo.dominio.*;

public class PropiedadesDaoImpl implements PropiedadesDao<Propiedades> {
	
	private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public PropiedadesDaoImpl(){
        CON=Conexion.getInstancia();
    }

	

	@Override
	public List<Propiedades> listar(String texto) {
		
		 List<Propiedades> lista = new ArrayList();
	        try {
	            ps=CON.conectar().prepareStatement("select * from propiedades where nombre LIKE ?");
	            ps.setString(1,"%"+texto+"%");
	            rs=ps.executeQuery();
	            while(rs.next()){
	                lista.add(new Propiedades(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getDouble(6)));
	            }
	            ps.close();
	            rs.close();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            ps=null;
	            rs=null;
	            CON.desconectar();
	        }
		return lista;
	}

	@Override
	public boolean insertar(Propiedades obj) {
		boolean resp=false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO propiedades (nombre, direccion, caracteristicas, estado, precioAlquiler) VALUES (?,?,?,?,?)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDireccion());
            ps.setString(3, obj.getCaracteristicas());
            ps.setString(4, obj.getEstado());
            ps.setDouble(5, obj.getPrecioalquiler());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
	}

	@Override
	public boolean actualizar(Propiedades obj) {
		boolean resp=false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE propiedades set nombre=?, direccion=?, caracteristicas=?, estado=?, precioAlquiler=? WHERE id=?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDireccion());
            ps.setString(3, obj.getCaracteristicas());
            ps.setString(4, obj.getEstado());
            ps.setDouble(5, obj.getPrecioalquiler());
            ps.setInt(6, obj.getId());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
	}

	@Override
	public boolean eliminar(int id) {
		boolean resp=false;
        try {
            ps = CON.conectar().prepareStatement("DELETE FROM propiedades WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
	}

	@Override
	public Propiedades obtener(int id) {
		Propiedades reg=new Propiedades();
        try {
            ps=CON.conectar().prepareStatement("SELECT id, nombre,direccion, caracteristicas, estado, precioAlquiler FROM propiedades WHERE id=?");
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                reg.setId(rs.getInt(1));
                reg.setNombre(rs.getString(2));
                reg.setDireccion(rs.getString(3));
                reg.setCaracteristicas(rs.getString(4));
                reg.setEstado(rs.getString(5));
                reg.setPrecioalquiler(rs.getDouble(6));
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        return reg;
	}

}
