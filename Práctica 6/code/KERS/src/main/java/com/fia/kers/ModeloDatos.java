package com.fia.kers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloDatos
{
    private Connection con; 
    private Statement set; 
    private PreparedStatement ps;
    private ResultSet rs; 

    public void abrirConexion()
    { 
        try 
        { 
            Class.forName("org.apache.derby.jdbc.ClientDriver"); 
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");  
            System.out.println("Se ha conectado"); 
        } 
        catch(ClassNotFoundException | SQLException e)
        { 
            System.out.println("No se ha conectado: " + e.getMessage());
        } 
    } 
    
    public boolean existeCircuito(String nombre)
    { 
        boolean existe = false; 
        String cad; 
        
        try 
        { 
            set = con.createStatement(); 
            rs = set.executeQuery("SELECT * FROM CIRCUITO"); 

            while (rs.next()) 
            { 
                cad = rs.getString("nombre");
                cad = cad.trim(); 
                if (cad.compareTo(nombre.trim()) == 0) existe = true; 
            } 
            
            rs.close(); 
            set.close(); 
        }
        catch(SQLException e)
        { 
            System.out.println("No lee de la tabla CIRCUITO: " + e.getMessage()); 
        }
        
        return existe;
    }
    
    public boolean existeCoche(String nombre)
    { 
        boolean existe = false; 
        String cad; 
        
        try 
        { 
            set = con.createStatement(); 
            rs = set.executeQuery("SELECT * FROM COCHE"); 

            while (rs.next()) 
            { 
                cad = rs.getString("nombre");
                cad = cad.trim(); 
                if (cad.compareTo(nombre.trim()) == 0) existe = true; 
            } 
            
            rs.close(); 
            set.close(); 
        }
        catch(SQLException e)
        { 
            System.out.println("No lee de la tabla COCHE: " + e.getMessage()); 
        }
        
        return existe;
    }
    
    void actualizarCircuito(String nombre, String ciudad, String pais, Integer vueltas, Integer longitud, Integer curvas)
    {
        try
        {
            set = con.createStatement(); 
            ps = con.prepareStatement("UPDATE CIRCUITO SET ciudad=?, pais=?, vueltas=?, longitud=?, curvas=? WHERE nombre = ?");
            ps.setString(1, ciudad);
            ps.setString(2, pais);
            ps.setInt(3, vueltas);
            ps.setInt(4, longitud);
            ps.setInt(5, curvas);
            ps.setString(6, nombre);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e)
        { 
            System.out.println("No modifica la tabla CIRCUITO: " + e.getMessage()); 
        } 
    }
    
    void actualizarCoche(String nombre, Integer kwcurva)
    {
        try
        {
            ps = con.prepareStatement("UPDATE COCHE SET kwcurva=? WHERE nombre = ?");
            ps.setInt(1, kwcurva);
            ps.setString(2, nombre);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e)
        { 
            System.out.println("No modifica la tabla COCHE: " + e.getMessage()); 
        } 
    }
    
    public void insertarCircuito(String nombre, String ciudad, String pais, Integer vueltas, Integer longitud, Integer curvas)
    { 
        try 
        { 
            set = con.createStatement(); 
            set.executeUpdate("INSERT INTO CIRCUITO " + " (nombre, ciudad, pais, vueltas, longitud, curvas)"
                    + " VALUES ('" + nombre + "','" + ciudad + "','" + pais + "'," + vueltas + "," + longitud + "," + curvas + ")");
            rs.close(); 
            set.close(); 
        }
        catch(SQLException e)
        { 
            System.out.println("No inserta en la tabla CIRCUITO: " + e.getMessage()); 
        } 
    }
    
    public void insertarCoche(String nombre, Integer kwcurva)
    { 
        try 
        { 
            set = con.createStatement(); 
            set.executeUpdate("INSERT INTO COCHE " + " (nombre, kwcurva) VALUES ('" + nombre + "'," + kwcurva + ")"); 
            rs.close(); 
            set.close(); 
        }
        catch(SQLException e)
        { 
            System.out.println("No inserta en la tabla COCHE: " + e.getMessage()); 
        } 
    } 
    
    public Integer obtenerCoche(String nombre)
    { 
        Integer kwcurva = 0;
        try 
        { 
            set = con.createStatement(); 
            rs = set.executeQuery("SELECT kwcurva FROM COCHE WHERE NOMBRE = '" + nombre + "'");
            rs.next();
            kwcurva = rs.getInt(1);
            rs.close(); 
            set.close(); 
        }
        catch(SQLException e)
        { 
            System.out.println("No lee de la tabla COCHE: " + e.getMessage()); 
        }
        
        return kwcurva;
    }
    
    public Integer[] obtenerCircuito(String nombre)
    { 
        Integer[] array = new Integer[2];
        try 
        { 
            set = con.createStatement(); 
            rs = set.executeQuery("SELECT vueltas, curvas FROM CIRCUITO WHERE NOMBRE = '" + nombre + "'");
            rs.next();
            array[0] = rs.getInt(1);
            array[1] = rs.getInt(2);
            rs.close(); 
            set.close(); 
        }
        catch(SQLException e)
        { 
            System.out.println("No lee de la tabla CIRCUITO: " + e.getMessage()); 
        }
        
        return array;
    }

    public void cerrarConexion() 
    { 
        try
        { 
            con.close(); 
        }
        catch (SQLException e)
        {
            System.out.println("No se ha cerrado la conexión: " + e.getMessage());
        }
    }
}
