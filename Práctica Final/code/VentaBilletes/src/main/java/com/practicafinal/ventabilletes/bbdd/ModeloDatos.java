package com.practicafinal.ventabilletes.bbdd;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ModeloDatos
{
    private Connection con; 
    private Statement set; 
    private PreparedStatement ps;
    private ResultSet rs;

    public void abrirConexion() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            System.out.println("Se ha conectado");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se ha conectado: " + e.getMessage());
        }
    }

    public void insertarPersona(Persona persona) {
        try {
            String query = "INSERT INTO PERSONA VALUES ('"
                    + persona.getNumID() + "','"
                    + persona.getTipoIdentificacion() + "','"
                    + persona.getNombre() + "','"
                    + persona.getApellido1() + "','"
                    + persona.getApellido2() + "','"
                    + persona.getFechaNacString() + "','"
                    + persona.getSexo() + "')";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Persona: " + e.getMessage());
        }
    }

    public void insertarAeropuerto(Aeropuerto aeropuerto) {
        try {
            String query = "INSERT INTO Aeropuerto(NOMBRE,DIRECCI??N,CIUDAD,PA??S,TEL??FONO) VALUES ('"
                    + aeropuerto.getNombre() + "','"
                    + aeropuerto.getDireccion() + "','"
                    + aeropuerto.getCiudad() + "','"
                    + aeropuerto.getPais() + "','"
                    + aeropuerto.getTelefono() + "')";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Aeropuerto: " + e.getMessage());
        }
    }

    public void insertarAvion(Avion avion) {
        try {
            String query = "INSERT INTO AVI??N(MODELO,NUMPLAZAS) VALUES ('"
                    + avion.getModelo() + "',"
                    + avion.getNumPlazas() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            if (rs != null) {
                rs.close();
            }

            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Avi??n: " + e.getMessage());
        }
    }

    public void insertarRuta(Ruta ruta) {
        try {
            String query = "INSERT INTO Ruta(FECHA,DURACI??N,AVI??N_IDAVI??N,ORIGEN,DESTINO) VALUES ('"
                    + ruta.getFechaString() + "',"
                    + ruta.getDuraction() + ","
                    + ruta.getAvion_idAvion() + ","
                    + ruta.getOrigen() + ","
                    + ruta.getDestino() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Ruta: " + e.getMessage());
        }
    }

    public void insertarBilleteRuta(BilleteRuta billeteRuta) {
        try {
            String query = "INSERT INTO BilleteRuta VALUES ('"
                    + billeteRuta.getPrecio() + "','"
                    + billeteRuta.getPrecio() + "','"
                    + billeteRuta.getImpuestos() + "','"
                    + billeteRuta.getRuta_idRuta() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla BilleteRuta: " + e.getMessage());
        }
    }

    public void insertarCliente(Cliente cliente) {
        try {
            String query = "INSERT INTO CLIENTE(CORREO,CONTRASE??A,TEL??FONO,PERSONA_NUMID) VALUES ('"
                    + cliente.getCorreo() + "','"
                    + cliente.getContrase??a() + "','"
                    + cliente.getTelefono() + "','"
                    + cliente.getPersona_numID() + "')";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Cliente: " + e.getMessage());
        }
    }
    
    public void insertarTarjetaBancaria(TarjetaBancaria tarjetaBancaria)
    { 
        try 
        {
            set = con.createStatement();
            ps = con.prepareStatement("INSERT INTO TarjetaBancaria(numTarjeta,mesCaducidad,a??oCaducidad,CVV,Cliente_idCliente) VALUES (?,?,?,?,?)");
            ps.setBigDecimal(1,new BigDecimal(tarjetaBancaria.getNumTarjeta()));
            ps.setInt(2, tarjetaBancaria.getMesCaducidad());
            ps.setInt(3, tarjetaBancaria.getA??oCaducidad());
            ps.setInt(4, tarjetaBancaria.getCVV());
            ps.setInt(5, tarjetaBancaria.getCliente_idCliente());
            ps.execute();
            
            ps.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla TarjetaBancaria: " + e.getMessage());
        }
    }

    public void insertarOperacion(Operacion operacion) {
        try {
            String query = "INSERT INTO Operacion VALUES ('"
                    + operacion.getFecha() + "','"
                    + operacion.getDescuento() + "','"
                    + operacion.getCliente_idCliente() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Operacion: " + e.getMessage());
        }
    }
    
    public void insertarBillete(Billete billete)
    { 
        try 
        { 
            set = con.createStatement();
            ps = con.prepareStatement("INSERT INTO Billete(localizador,BilleteRuta_idBilleteRuta,Operaci??n_idOperaci??n,Operaci??n_fecha) VALUES (?,?,?,?)");
            ps.setString(1, billete.getLocalizador());
            ps.setInt(2, billete.getBilleteRuta_idBilleteRuta());
            ps.setInt(3, billete.getOperacion_idOperacion());
            ps.setDate(4, new java.sql.Date(billete.getOperacion_fecha().getTime()));
            ps.execute();
            
            ps.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Billete: " + e.getMessage());
        }
    }

    public void insertarClase(Clase clase) {
        try {
            String query = "INSERT INTO Clase VALUES ('"
                    + clase.getNombre() + "','"
                    + clase.getDescripcion() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Clase: " + e.getMessage());
        }
    }

    public void insertarAcompa??ante(Acompa??ante acompa??ante) {
        try {
            String query = "INSERT INTO Acompa??ante VALUES ('"
                    + acompa??ante.getIdAcompa??ante() + "','"
                    + acompa??ante.getPersona_numID() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Acompa??ante: " + e.getMessage());
        }
    }

    public void insertarAdministrador(Administrador administrador) {
        try {
            String query = "INSERT INTO Administrador VALUES ('"
                    + administrador.getCorreo() + "','"
                    + administrador.getContrase??a() + "','"
                    + administrador.getPersona_numID() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Administrador: " + e.getMessage());
        }
    }

    public void insertarTasas(Tasas tasas) {
        try {
            String query = "INSERT INTO Tasas VALUES ('"
                    + tasas.getGestion() + "','"
                    + tasas.getSeguridad() + "','"
                    + tasas.getCombustible() + "','"
                    + tasas.getAeropuerto_idAeropuerto() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Tasas: " + e.getMessage());
        }
    }

    public void insertarClaseAvion(ClaseAvion claseAvion) {
        try {
            String query = "INSERT INTO CLASEAVI??N VALUES ("
                    + claseAvion.getClase_idClase() + ","
                    + claseAvion.getAvion_idAvion() + ","
                    + claseAvion.getNumPlazas() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla ClaseAvion: " + e.getMessage());
        }
    }

    public void insertarAcompa??anenteBillete(Acompa??anteBillete acompa??anteBillete) {
        try {
            String query = "INSERT INTO Acompa??anteBillete VALUES ('"
                    + acompa??anteBillete.getAcompa??ante_idAcompa??ente() + "','"
                    + acompa??anteBillete.getAcompa??ante_idAcompa??ente() + "','"
                    + acompa??anteBillete.getBillete_localizador() + ")";
            set = con.createStatement();
            set.executeUpdate(query);
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No se ha insertado en la tabla Acompa??anteBillete: " + e.getMessage());
        }
    }

    public boolean existeAeropuerto(Aeropuerto aeropuerto) {
        boolean existe = false;
        String cad;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Aeropuerto");

            while (rs.next()) {
                cad = rs.getString("nombre");
                cad = cad.trim();
                if (cad.compareTo(aeropuerto.getNombre().trim()) == 0) {
                    existe = true;
                }
            }

            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Aeropuerto: " + e.getMessage());
        }

        return existe;
    }

    public boolean existePersona(Persona persona) {
        boolean existe = false;
        String cad;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Persona");

            while (rs.next()) {
                cad = rs.getString("numID");
                cad = cad.trim();
                if (cad.compareTo(persona.getNumID().trim()) == 0) {
                    existe = true;
                }
            }

            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Persona: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeBilleteRuta(BilleteRuta billeteRuta) {
        boolean existe = false;
        int n;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM BilleteRuta");

            while (rs.next()) {
                n = rs.getInt("Ruta_idRuta ");
                if (n == billeteRuta.getRuta_idRuta()) {
                    existe = true;
                }
            }

            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla BilleteRuta: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeCliente(Cliente cliente) {
        boolean existe = false;
        String cad;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Cliente");

            while (rs.next()) {
                cad = rs.getString("correo");
                cad = cad.trim();
                if (cad.compareTo(cliente.getCorreo().trim()) == 0) {
                    existe = true;
                }
            }

            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Cliente: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeTarjetaBancaria(TarjetaBancaria tarjetaBancaria)
    {
        boolean existe = false; 
        BigInteger n; 

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM TarjetaBancaria");

            while (rs.next())
            {
                n = rs.getBigDecimal("numTarjeta").toBigInteger();
                if (n == tarjetaBancaria.getNumTarjeta()) existe = true;
            }

            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla TarjetaBancaria: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeOperacion(Operacion operacion) {
        boolean existe = false;
        int n;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Operacion");

            while (rs.next()) {
                n = rs.getInt("Cliente_idCliente");
                if (n == operacion.getIdOperacion()) {
                    existe = true;
                }
            }

            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Operacion: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeBillete(Billete billete) {
        boolean existe = false;
        String cad;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Billete");

            while (rs.next()) {
                cad = rs.getString("localizador");
                cad = cad.trim();
                if (cad.compareTo(billete.getLocalizador().trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Billete: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeClase(Clase clase) {
        boolean existe = false;
        String cad;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Clase");

            while (rs.next()) {
                cad = rs.getString("nombre");
                cad = cad.trim();
                if (cad.compareTo(clase.getNombre().trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Clase: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeAcompa??ante(Acompa??ante acompa??ante) {
        boolean existe = false;
        int n;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Acompa??ante");

            while (rs.next()) {
                n = rs.getInt("idAcompa??ante");
                if (n == acompa??ante.getIdAcompa??ante()) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Acompa??ante: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeAdministrador(Administrador administrador) {
        boolean existe = false;
        String cad;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Administrador");

            while (rs.next()) {
                cad = rs.getString("correo");
                cad = cad.trim();
                if (cad.compareTo(administrador.getCorreo().trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Administrador: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeTasas(Tasas tasas) {
        boolean existe = false;
        int n;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Tasas");

            while (rs.next()) {
                n = rs.getInt("Aeropuerto_idAeropuerto");
                if (n == tasas.getAeropuerto_idAeropuerto()) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Tasas: " + e.getMessage());
        }

        return existe;
    }

    public boolean existeClaseAvion(ClaseAvion claseAvion) {
        boolean existe = false;
        int n;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM ClaseAvion");

            while (rs.next()) {
                n = rs.getInt("Clase_idClase");
                if (n == claseAvion.getClase_idClase()) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Clase Avion");
        }

        return existe;
    }

    public boolean existeAcompa??anteBillete(Acompa??anteBillete acompa??antebillete) {
        boolean existe = false;
        int n;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Acompa??anteBillete");

            while (rs.next()) {
                n = rs.getInt("Acompa??ante_idAcompa??ante");
                if (n == acompa??antebillete.getAcompa??ante_idAcompa??ente()) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Acompa??antebillete");
        }

        return existe;
    }

    public boolean loginCliente(String correo, String contrase??a) {
        boolean login = false;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Cliente "
                    + "WHERE correo='" + correo + "' AND contrase??a='" + contrase??a + "'");
            if (rs.next()) {
                login = true;
            }
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Cliente: " + e.getMessage());
        }
        return login;
    }

    public boolean loginAdmin(String correo, String contrase??a) {
        boolean login = false;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Administrador "
                    + "WHERE correo='" + correo + "' AND contrase??a='" + contrase??a + "'");
            if (rs.next()) {
                login = true;
            }
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Administrador: " + e.getMessage());
        }
        return login;
    }

    public void actualizarFechaRuta(int idRuta, Date fecha) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Ruta SET fecha = " + fecha
                    + "' WHERE idRuta = " + idRuta);
            set.close();
        } catch (SQLException e) {
            System.out.println("No actualiza la tabla Ruta: " + e.getMessage());
        }
    }

    public void actualizarPrecioBilleteRuta(int billeteRuta, float precio) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE BilleteRuta SET precio = " + precio
                    + "' WHERE billeteRuta = " + billeteRuta);
            set.close();
        } catch (SQLException e) {
            System.out.println("No actualiza la tabla BilleteRuta: " + e.getMessage());
        }
    }

    public ArrayList<Persona> getPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Persona");

            while (rs.next()) {
                Persona p = new Persona(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
                personas.add(p);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Persona: " + e.getMessage());
        } catch (ParseException pe) {
            System.out.println("Error de parseo: " + pe.getMessage());
        }

        return personas;
    }

    public ArrayList<Aeropuerto> getAeropuertos() {
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Aeropuerto");

            while (rs.next()) {
                Aeropuerto a = new Aeropuerto(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6));
                aeropuertos.add(a);
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("No lee de la tabla Aeropuerto: " + e.getMessage());
        }

        return aeropuertos;
    }

    public ArrayList<Avion> getAviones() {
        ArrayList<Avion> aviones = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM AVI??N");

            while (rs.next()) {
                Avion a = new Avion(rs.getInt(1), rs.getString(2), rs.getInt(3));
                aviones.add(a);
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("No lee de la tabla Avion: " + e.getMessage());
        }

        return aviones;
    }

    public ArrayList<Ruta> getRutas() {
        ArrayList<Ruta> rutas = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Ruta");

            while (rs.next()) {
                Date fecha = new Date(rs.getDate(2).getTime());
                Ruta r = new Ruta(rs.getInt(1), fecha, rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6));
                rutas.add(r);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Ruta: " + e.getMessage());
        }

        return rutas;
    }

    public ArrayList<BilleteRuta> getBilletesRuta() {
        ArrayList<BilleteRuta> billetesRuta = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM BilleteRuta");

            while (rs.next()) {
                BilleteRuta br = new BilleteRuta(rs.getInt(1), rs.getFloat(2),
                        rs.getFloat(3), rs.getInt(4));
                billetesRuta.add(br);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla BilleteRuta: " + e.getMessage());
        }

        return billetesRuta;
    }

    public ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Cliente");

            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
                clientes.add(c);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Cliente: " + e.getMessage());
        }

        return clientes;
    }

    public ArrayList<TarjetaBancaria> getTarjetasBancaria() {
        ArrayList<TarjetaBancaria> tarjetasBancaria = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM TarjetaBancaria");

            while (rs.next())
            {
                TarjetaBancaria tb = new TarjetaBancaria(rs.getBigDecimal(1).toBigInteger(),
                        rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5));
                tarjetasBancaria.add(tb);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla TarjetaBancaria: " + e.getMessage());
        }

        return tarjetasBancaria;
    }

    public ArrayList<Operacion> getOperaciones() {
        ArrayList<Operacion> operaciones = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Operacion");

            while (rs.next()) {
                Date fecha = new Date(rs.getDate(2).getTime());
                Operacion o = new Operacion(rs.getInt(1), fecha, rs.getFloat(3),
                        rs.getInt(4));
                operaciones.add(o);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Operacion: " + e.getMessage());
        }

        return operaciones;
    }

    public ArrayList<Billete> getBilletes() {
        ArrayList<Billete> billetes = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Billete");

            while (rs.next()) {
                Date fecha = new Date(rs.getDate(4).getTime());
                Billete b = new Billete(rs.getString(1), rs.getInt(2),
                        rs.getInt(3), fecha);
                billetes.add(b);
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Billete: " + e.getMessage());
        }

        return billetes;
    }

    public ArrayList<Clase> getClases() {
        ArrayList<Clase> clases = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM CLASE");

            while (rs.next()) {
                Clase c = new Clase(rs.getInt(1), rs.getString(2),
                        rs.getString(3));
                clases.add(c);
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("No lee de la tabla Clase: " + e.getMessage());
        }

        return clases;
    }

    public ArrayList<Acompa??ante> getAcompa??antes() {
        ArrayList<Acompa??ante> acompa??antes = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Acompa??ante");

            while (rs.next()) {
                Acompa??ante a = new Acompa??ante(rs.getInt(1), rs.getString(2));
                acompa??antes.add(a);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Acompa??ante: " + e.getMessage());
        }

        return acompa??antes;
    }

    public ArrayList<Administrador> getAdministradores() {
        ArrayList<Administrador> administradores = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Administrador");

            while (rs.next()) {
                Administrador a = new Administrador(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
                administradores.add(a);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Administrador: " + e.getMessage());
        }

        return administradores;
    }

    public ArrayList<Tasas> getTasas() {
        ArrayList<Tasas> tasas = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Tasas");

            while (rs.next()) {
                Tasas t = new Tasas(rs.getInt(1), rs.getFloat(2), rs.getFloat(3),
                        rs.getFloat(4), rs.getInt(5));
                tasas.add(t);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Tasas: " + e.getMessage());
        }

        return tasas;
    }

    public ArrayList<ClaseAvion> getClasesAvion() {
        ArrayList<ClaseAvion> clasesAvion = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM CLASEAVI??N");

            while (rs.next()) {
                ClaseAvion ca = new ClaseAvion(rs.getInt(1), rs.getInt(2),
                        rs.getInt(3));
                clasesAvion.add(ca);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla ClaseAvion: " + e.getMessage());
        }

        return clasesAvion;
    }

    public ArrayList<Acompa??anteBillete> getAcompa??antesBillete() {
        ArrayList<Acompa??anteBillete> acompa??antesBillete = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Acompa??anteBillete");

            while (rs.next()) {
                Acompa??anteBillete ac = new Acompa??anteBillete(rs.getInt(1),
                        rs.getInt(2), rs.getString(3), rs.getString(4));
                acompa??antesBillete.add(ac);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Acompa??anteBillete: " + e.getMessage());
        }

        return acompa??antesBillete;
    }

    public void ModificarOrigenRuta(int idRuta, int origen)
    {
        try
        {
            set = con.createStatement();
            set.executeUpdate("UPDATE RUTA SET ORIGEN = " + origen + " WHERE IDRUTA = "+ idRuta);
        }
        catch (SQLException e)
        {
            System.out.println("Error SQL en la tabla ruta: " + e.getMessage());
        }
    }
    
    public void ModificarDestinoRuta(int idRuta, int destino)
    {
        try
        {
            set = con.createStatement();
            set.executeUpdate("UPDATE RUTA SET DESTINO = " + destino + " WHERE IDRUTA = "+ idRuta);
        }
        catch (SQLException e)
        {
            System.out.println("Error SQL en la tabla billete, billeteruta o ruta." + e.getMessage());
        }
    }

    public void ModificarPrecioRuta(int idRuta, int precio) 
    {
        try
        {
            set = con.createStatement();
            set.executeUpdate("UPDATE BILLETERUTA SET PRECIO = " + precio + "WHERE RUTA_IDRUTA = " + idRuta);
        }
        catch (SQLException e)
        {
            System.out.println("Error SQL en la tabla billete o en la tabla billeteruta: " + e.getMessage());
        }
    }

    public int getEdad(String fecha) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        Date firstDate = sdf.parse("2022-1-16");
        Date secondDate = sdf.parse(fecha);

        long diff = firstDate.getTime() - secondDate.getTime();
        long difference = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        return (int) (difference / 365);
    }

    public boolean esMayorEdad(String fecha) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(fecha, fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);

        return (periodo.getYears() >= 18);
    }

    public int[] rangoEdad(ArrayList<Date> fechas) throws ParseException {
        int contadorEdades[] = {0, 0, 0, 0};

        for (Date fecha : fechas) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
            String strDate = dateFormat.format(fecha); 
            int edad = getEdad(strDate);
            if (edad <= 20) {
                contadorEdades[0]++;
            } else if (edad >= 21 && edad <= 30) {
                contadorEdades[1]++;
            } else if (edad >= 31 && edad <= 40) {
                contadorEdades[2]++;
            } else {
                contadorEdades[3]++;
            }
        }
        return contadorEdades;
    }

    public int[] getNumVuelos() {
        int[] n = null;
        int numAviones = 0;
        
        try {
            set = con.createStatement();
            
            rs = set.executeQuery("SELECT count(*) FROM AVI??N");
            rs.next();
            numAviones = rs.getInt(1);
            
            //set2 = con.createStatement();
            n = new int[numAviones];
            
            Statement set2 = con.createStatement();

            ResultSet rs2 = null;
            for (int i = 1; i <= numAviones; i++) {
                rs2 = set2.executeQuery("SELECT count(*) FROM RUTA R INNER JOIN Avi??n A ON R.\"AVI??N_IDAVI??N\" = A.\"IDAVI??N\" where A.\"IDAVI??N\" = " + i);
                rs2.next();
                n[i - 1] += rs2.getInt(1);
            }
            
            rs2.close();
            set2.close();
            rs.close();
            set.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Ruta: " + e.getMessage());
        }

        return n;
    }

    public ArrayList<String> getSexoPersonas() {
        ArrayList<String> sexoPersonas = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT SEXO  FROM Persona");
            while(rs.next()) {
                sexoPersonas.add(rs.getString(7));
            }           
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Personas.");
        }
        return sexoPersonas;
    }
    
    public ArrayList<Date> getFechaNacPersonas() {
        ArrayList<Date> fechaNacPersonas = new ArrayList<>();
        try {
            Statement set2 = con.createStatement();
            ResultSet rs2 = set2.executeQuery("SELECT FECHANACIMIENTO FROM Persona");
            while(rs2.next()) {
                    fechaNacPersonas.add(rs2.getDate(1));
            }
            rs2.close();
            set2.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Personas: " + e.getMessage());
        }
        return fechaNacPersonas;
    }
    
    // La fecha de vuelo es en formato yyyy-MM-dd hh:mm:ss
    public ArrayList<String> getPasajerosPorVuelo(String fechaVuelo) {
        ArrayList<String> nombresPasajeros = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT PERSONA.NOMBRE, RUTA.FECHA, RUTA.ORIGEN, RUTA.DESTINO FROM PERSONA INNER JOIN CLIENTE ON PERSONA.NUMID = CLIENTE.PERSONA_NUMID INNER JOIN OPERACI??N ON OPERACI??N.CLIENTE_IDCLIENTE = CLIENTE.IDCLIENTE INNER JOIN BILLETE ON BILLETE.OPERACI??N_IDOPERACI??N = OPERACI??N.IDOPERACI??N INNER JOIN BILLETERUTA ON BILLETE.BILLETERUTA_IDBILLETERUTA = BILLETERUTA.BILLETERUTA INNER JOIN RUTA ON BILLETERUTA.RUTA_IDRUTA = RUTA.IDRUTA WHERE RUTA.FECHA = '" + fechaVuelo + "';");
            while(rs.next()) {
                nombresPasajeros.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Personas.");
        }
        return nombresPasajeros;
    }

    public int getNumHombres() {
        int n = 0;
        try {
            Statement set2 = con.createStatement();
            ResultSet rs2 = set2.executeQuery("SELECT count(*) FROM Persona WHERE sexo = \'Hombre\'");
            rs2.next();
            n += rs2.getInt(1);
            rs2.close();
            set2.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Persona: " + e.getMessage());
        }
        return n;
    }
    
    public int getNumMujeres() {
        int n = 0;
        try {
            Statement set2 = con.createStatement();
            ResultSet rs2 = set2.executeQuery("SELECT count(*) FROM Persona WHERE sexo = \'Mujer\'");
            rs2.next();
            n += rs2.getInt(1);
            rs2.close();
            set2.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Persona: " + e.getMessage());
        }
        return n;
    }

    public ArrayList<Aeropuerto> getVuelosOrigen() {
        ArrayList<Aeropuerto> vuelosOrigen = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT DISTINCT IDAEROPUERTO, Ciudad FROM Aeropuerto INNER JOIN ruta ON ruta.ORIGEN = Aeropuerto.IDAEROPUERTO");
            while (rs.next()) {
                Aeropuerto aeropuerto = new Aeropuerto(rs.getInt(1), "", "", rs.getString(2), "", "");
                vuelosOrigen.add(aeropuerto);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Aeropuerto: " + e.getMessage());
        }
        return vuelosOrigen;
    }

    public ArrayList<Aeropuerto> getVuelosDestino() {
        ArrayList<Aeropuerto> vuelosDestino = new ArrayList<>();

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT DISTINCT IDAEROPUERTO, Ciudad FROM Aeropuerto INNER JOIN ruta ON ruta.DESTINO = Aeropuerto.IDAEROPUERTO");
            while (rs.next()) {
                Aeropuerto aeropuerto = new Aeropuerto(rs.getInt(1), "", "", rs.getString(2), "", "");
                vuelosDestino.add(aeropuerto);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Aeropuerto: " + e.getMessage());
        }
        return vuelosDestino;
    }

    public ArrayList<Ruta> getVuelos(Date fecha, int origen, int destino) {
        ArrayList<Ruta> vuelos = new ArrayList<>();

        try {
            con.setAutoCommit(true);
            set = con.createStatement();
            java.sql.Timestamp fechaSQL = new java.sql.Timestamp(fecha.getTime());
            ps = con.prepareStatement("select * from Ruta where fecha >= ? and origen = ? and destino = ?");
            ps.setTimestamp(1, fechaSQL);
            ps.setInt(2, origen);
            ps.setInt(3, destino);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date rutaFecha = new Date(rs.getDate(2).getTime());
                Ruta r = new Ruta(rs.getInt(1), rutaFecha, rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6));
                vuelos.add(r);
            }

            rs.close();
            ps.close();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Aeropuerto: " + e.getMessage());
        }

        return vuelos;
    }

    public Aeropuerto getAeropuerto(int idAeropuerto) {
        Aeropuerto aeropuerto = null;

        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Aeropuerto WHERE idAeropuerto = " + idAeropuerto);
            while (rs.next()) {
                aeropuerto = new Aeropuerto(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("No lee de la tabla Aeropuerto: " + e.getMessage());
        }
        return aeropuerto;
    }
    
    public Ruta getRuta(int idRuta)
    {
        Ruta ruta = null;
        
        try
        {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Ruta WHERE idRuta = " + idRuta);
            while (rs.next())
            {
                Date rutaFecha = new Date(rs.getDate(2).getTime());
                ruta = new Ruta(rs.getInt(1), rutaFecha, rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6));
            }
            
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("No lee de la tabla Ruta: " + e.getMessage());
        }
        return ruta;
    }
    
    public BilleteRuta getBilleteRuta(int idRuta)
    {
        BilleteRuta billeteRuta = null;
        
        try
        {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM BilleteRuta WHERE Ruta_idRuta = " + idRuta);
            while (rs.next())
            {
                billeteRuta = new BilleteRuta(rs.getInt(1), rs.getFloat(2),
                        rs.getFloat(3), rs.getInt(4));
            }
            
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("No lee de la tabla BilleteRuta: " + e.getMessage());
        }
        return billeteRuta;
    }
    
    public Cliente getCliente(String correo)
    {
        Cliente cliente = null;
        
        try
        {
            set = con.createStatement();
            ps = con.prepareStatement("SELECT * FROM Cliente WHERE correo = ?");
            ps.setString(1, correo);
            rs = ps.executeQuery();
            while (rs.next())
            {
                cliente = new Cliente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
            }
            
            rs.close();
            ps.close();
        }
        catch (SQLException e)
        {
            System.out.println("No lee de la tabla Cliente: " + e.getMessage());
        }
        return cliente;
    }
    
    public Persona getPersona(String numID) throws ParseException
    {
        Persona persona = null;
        
        try
        {
            set = con.createStatement();
            ps = con.prepareStatement("SELECT * FROM Persona WHERE numID = ?");
            ps.setString(1, numID);
            rs = ps.executeQuery();
            while (rs.next())
            {
                persona = new Persona(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7));
            }
            
            rs.close();
            ps.close();
        }
        catch (SQLException e)
        {
            System.out.println("No lee de la tabla Persona: " + e.getMessage());
        }
        
        return persona;
    }
    
    public Tasas getTasas(int Aeropuerto_idAeropuerto)
    {
        Tasas tasas = null;
        
        try
        {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Tasas WHERE Aeropuerto_idAeropuerto = " + Aeropuerto_idAeropuerto);
            while (rs.next())
            {
                tasas = new Tasas(rs.getInt(1), rs.getFloat(2), rs.getFloat(3),
                    rs.getFloat(4), rs.getInt(5));
            }
            
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("No lee de la tabla Tasas: " + e.getMessage());
        }
        return tasas;
    }
  
    public void cerrarConexion() 
    { 
        try
        { 
            con.close(); 
        }
        catch (SQLException e)
        {
            System.out.println("No se ha cerrado la conexi??n: " + e.getMessage());
        }
    }
}
