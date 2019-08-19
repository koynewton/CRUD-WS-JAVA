package com.practiccacrudrest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.practiccacrudrest.model.Producto;


@Path(value = "/productos")
public class ProductosService {
	
	
	Producto miProducto = new Producto();
	
	//CREATE
		@POST
		@Path(value = "/{idProducto}/{nombreProducto}/{precioProducto}/{existenciasProductos}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.TEXT_PLAIN)
	public String crearProducto (@PathParam("idProducto") int idProducto,
								@PathParam("nombreProducto") String nombreProducto,
								@PathParam("precioProducto") double precioProducto,
								@PathParam("existenciasProductos") int existenciasProducto)
											 throws IOException {
			Properties props = new Properties();
			InputStream miStream = null;
			String miArchivoProps = "config.properties";
			miStream = getClass().getClassLoader().getResourceAsStream(miArchivoProps);
			String resultadoJson = null;
			
			if (miStream != null) {
				props.load(miStream);
			} else {
				throw new FileNotFoundException("Archivo de Propiedades: " + miArchivoProps + " no se encuentra");
			}

			// declarar variables
			String user = props.getProperty("user");
			String pass = props.getProperty("pass");
			String urlServidor = props.getProperty("urlServidor");
			String miDriver = props.getProperty("driver");
			String sentenciaSQL = props.getProperty("sentenciaSQLInsertProductos");
			

			// objetos conexion
			Connection conn = null;
			PreparedStatement pstmnt = null;
			
			try {
				Class.forName(miDriver).getDeclaredConstructor().newInstance();

				conn = DriverManager.getConnection(urlServidor, user, pass);
				pstmnt = conn.prepareStatement(sentenciaSQL);
				
				miProducto.setIdProducto(idProducto);
				miProducto.setNombreProducto(nombreProducto);
				miProducto.setPrecioProducto(precioProducto);
				miProducto.setExistenciasProducto(existenciasProducto);
				
				pstmnt.setInt(1, miProducto.getIdProducto());
				pstmnt.setString(2, miProducto.getNombreProducto());
				pstmnt.setDouble(3, miProducto.getPrecioProducto());
				pstmnt.setInt(4, miProducto.getExistenciasProducto());
				pstmnt.executeUpdate();
				Gson miGson = new Gson();
				resultadoJson = miGson.toJson(miProducto);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					pstmnt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		return resultadoJson; 
		}

	//READ (GET)
	@GET
	@Path(value = "/{idProducto}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public String leerProducto (@PathParam("idProducto") int idProducto) throws IOException {
		Properties props = new Properties();
		InputStream miStream = null;
		String miArchivoProps = "config.properties";
		miStream = getClass().getClassLoader().getResourceAsStream(miArchivoProps);
		String resultadoJson = "";
		
		if (miStream != null) {
			props.load(miStream);
		} else {
			throw new FileNotFoundException("Archivo de Propiedades: " + miArchivoProps + " no se encuentra");
		}

		// declarar variables
		String user = props.getProperty("user");
		String pass = props.getProperty("pass");
		String urlServidor = props.getProperty("urlServidor");
		String miDriver = props.getProperty("driver");
		String sentenciaSQL = props.getProperty("sentenciaSQLReadIndividualProductos");

		// objetos conexion
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(miDriver).getDeclaredConstructor().newInstance();

			conn = DriverManager.getConnection(urlServidor, user, pass);
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setInt(1, idProducto);
			rs = pstmnt.executeQuery();
			rs.next();
			
			miProducto.setIdProducto(rs.getInt("idProducto"));
			miProducto.setNombreProducto(rs.getString("nombreProducto"));
			miProducto.setPrecioProducto(rs.getDouble("precioProducto"));
			miProducto.setExistenciasProducto(rs.getInt("existenciasProducto"));
			
			Gson miGson = new Gson();
			resultadoJson = miGson.toJson(miProducto);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	return resultadoJson; 
	}

	//UPDATE
	@PUT
	@Path(value = "/{idProducto}/{nombreProducto}/{precioProducto}/{existenciasProductos}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public String updateProducto (@PathParam("idProducto") int idProducto,
								 @PathParam("nombreProducto") String nombreProducto,
								 @PathParam("precioProducto") double precioProducto,
								 @PathParam("existenciasProductos") int existenciasProducto)
										 throws IOException {
		Properties props = new Properties();
		InputStream miStream = null;
		String miArchivoProps = "config.properties";
		miStream = getClass().getClassLoader().getResourceAsStream(miArchivoProps);
		String resultadoJson = null;
		
		if (miStream != null) {
			props.load(miStream);
		} else {
			throw new FileNotFoundException("Archivo de Propiedades: " + miArchivoProps + " no se encuentra");
		}

		// declarar variables
		String user = props.getProperty("user");
		String pass = props.getProperty("pass");
		String urlServidor = props.getProperty("urlServidor");
		String miDriver = props.getProperty("driver");
		String sentenciaSQL = props.getProperty("sentenciaSQLUpdateProducto");
		

		// objetos conexion
		Connection conn = null;
		PreparedStatement pstmnt = null;
		
		try {
			Class.forName(miDriver).getDeclaredConstructor().newInstance();

			conn = DriverManager.getConnection(urlServidor, user, pass);
			pstmnt = conn.prepareStatement(sentenciaSQL);
			
			miProducto.setIdProducto(idProducto);
			miProducto.setNombreProducto(nombreProducto);
			miProducto.setPrecioProducto(precioProducto);
			miProducto.setExistenciasProducto(existenciasProducto);
			
			pstmnt.setInt(1, miProducto.getIdProducto());
			pstmnt.setString(2, miProducto.getNombreProducto());
			pstmnt.setDouble(3, miProducto.getPrecioProducto());
			pstmnt.setInt(4, miProducto.getExistenciasProducto());
			pstmnt.executeUpdate();
			Gson miGson = new Gson();
			resultadoJson = miGson.toJson(miProducto);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	return resultadoJson; 
	}
	
	//DELETE
	@DELETE
	@Path(value = "/{idProducto}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public String borrarProducto (@PathParam("idProducto") int idProducto) throws IOException {
		Properties props = new Properties();
		InputStream miStream = null;
		String miArchivoProps = "config.properties";
		miStream = getClass().getClassLoader().getResourceAsStream(miArchivoProps);
		String resultado = "";
		
		if (miStream != null) {
			props.load(miStream);
		} else {
			throw new FileNotFoundException("Archivo de Propiedades: " + miArchivoProps + " no se encuentra");
		}

		// declarar variables
		String user = props.getProperty("user");
		String pass = props.getProperty("pass");
		String urlServidor = props.getProperty("urlServidor");
		String miDriver = props.getProperty("driver");
		String sentenciaSQL = props.getProperty("sentenciaSQLDeleteProductos");

		// objetos conexion
		Connection conn = null;
		PreparedStatement pstmnt = null;
		
		try {
			Class.forName(miDriver).getDeclaredConstructor().newInstance();

			conn = DriverManager.getConnection(urlServidor, user, pass);
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setInt(1, idProducto);
			pstmnt.executeUpdate();
			resultado = "Elemento elimnado con exito!";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	return resultado; 
	}

	
	
}
