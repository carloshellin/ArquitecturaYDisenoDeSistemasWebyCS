package inserts;

import entidades.Ruta;
import entidades.Operacion;
import entidades.Cliente;
import entidades.AcompañantesBillete;
import entidades.Acompañante;
import entidades.Billete_Ruta;
import entidades.Avion;
import entidades.Persona;
import entidades.Billete;
import entidades.Tarjeta_Bancaria;
import entidades.Aeropuerto;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Estadísticas {

    FileWriter fwPersonas, fwClientes, fwAcompañantes, fwTarjetas, fwOperaciones, fwRutas, fwBilletesRutas, fwAcompañantesBilletes, fwBilletes;
    String linea;
    Random rd = new Random();

    public ArrayList<Persona> crearDatosPersonas(String ruta) throws IOException {
        fwPersonas = new FileWriter(ruta);
        //INSERT INTO PERSONA VALUES ('12345678A', 'DNI', 'Francisco', 'Calles', 'Esteban', '2000-1-20', 'Hombre');
        ArrayList<Persona> personas = new ArrayList<Persona>();
        String sexo[] = {"Hombre", "Mujer"};
        ArrayList alDNI = generarDNIsAleatorio(1000);
        String fechaAleatoria = "";
        for (int i = 0; i < 1000; i++) {
            fechaAleatoria = generarFecha(1970, 2020);
            Persona p = new Persona((String) alDNI.get(i), "Nombre" + (i + 1), "Apellido" + (i + 1), "Apellido" + (i + 1), fechaAleatoria, sexo[rd.nextInt(2)]);
            personas.add(p);
            linea = "INSERT INTO PERSONA VALUES ('" + alDNI.get(i) + "', 'DNI', 'Nombre" + (i + 1) + "' , 'Apellido" + (i + 1) + "', 'Apellido" + (i + 1) + "', '" + fechaAleatoria + "', '" + sexo[rd.nextInt(2)] + "');";
            fwPersonas.write(linea + "\n");
        }
        fwPersonas.write("\n");
        fwPersonas.close();
        return personas;
    }

    public String generarFecha(int annoIni, int annoFin) {
        String meses[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String dias[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        int rango = annoFin - annoIni;
        int anno = rd.nextInt(rango + 1) + annoIni;
        String mes = meses[rd.nextInt(12)];
        String dia;

        if (mes.equals("02")) {
            if ((anno % 4 == 0) && ((anno % 100 != 0) || (anno % 400 == 0))) {
                dia = dias[rd.nextInt(29)];
            } else {
                dia = dias[rd.nextInt(28)];
            }
        } else {
            if (mes.equals("04") || mes.equals("06") || mes.equals("09") || mes.equals("11")) {
                dia = dias[rd.nextInt(30)];
            } else {
                dia = dias[rd.nextInt(31)];
            }
        }
        return anno + "-" + mes + "-" + dia;
    }

    public String generarHora() {
        String horas[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String minSegs[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34",
            "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String hora = horas[rd.nextInt(24)];
        String min = minSegs[rd.nextInt(60)];
        String seg = minSegs[rd.nextInt(60)];

        return hora + ":" + min + ":" + seg;
    }

    public ArrayList generarDNIsAleatorio(int cantidad) {
        ArrayList<String> al = new ArrayList<>();
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        String cadenaNums = "";
        long numeros;
        String DNI;
        boolean repetido;
        int resto;

        for (int i = 1; i <= cantidad; i++) {
            repetido = false;
            while (!repetido) {
                for (int num = 1; num <= 8; num++) {
                    cadenaNums += "" + (rd.nextInt(10));
                }
                numeros = Long.parseLong(cadenaNums);
                resto = (int) (numeros % 23);
                DNI = cadenaNums + "" + letras[resto];

                if (!al.contains(DNI)) {
                    al.add(DNI);
                    repetido = true;
                }
                cadenaNums = "";
            }
        }
        return al;
    }

    public ArrayList<String> generarTlf(int cantidad) {
        String NUMEROS = "0123456789";
        String prefijo = "+34";
        String comienzo = "67";
        String tlf = "";
        ArrayList<String> telefonos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            for (int j = 0; j < 8; j++) {
                tlf += (NUMEROS.charAt((int) (Math.random() * NUMEROS.length())));
            }
            telefonos.add(prefijo + " " + comienzo.charAt(rd.nextInt(2)) + tlf);
            tlf = "";
        }

        return telefonos;
    }

    public ArrayList<Long> generarNumTarjetas(int cantidad) {
        ArrayList<Long> numTarjetas = new ArrayList<Long>();
        String numTarjeta = "";

        for (int i = 0; i < cantidad; i++) {
            boolean repetido = true;
            while (repetido) {
                numTarjeta = "";
                for (int j = 0; j < 16; j++) {
                    numTarjeta += "" + (rd.nextInt(10));
                }

                if (!numTarjetas.contains(Long.parseLong(numTarjeta))) {
                    numTarjetas.add(Long.parseLong(numTarjeta));
                    repetido = false;
                }
            }

        }
        return numTarjetas;
    }

    public ArrayList<Integer> generarCVV(int cantidad) {
        ArrayList<Integer> listaCVV = new ArrayList<Integer>();
        String cvv = "";

        for (int i = 0; i < cantidad; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    cvv += (rd.nextInt(9) + 1);
                } else {
                    cvv += rd.nextInt(10);
                }

            }
            listaCVV.add(Integer.parseInt(cvv));
            cvv = "";
        }
        return listaCVV;
    }

    public ArrayList<String> generarLocalizadores(int cantidad) {
        ArrayList<String> localizadores = new ArrayList<>();
        String letrasNums = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String localizador = "";
        boolean repetido;

        for (int i = 0; i < cantidad; i++) {
            repetido = false;
            while (!repetido) {
                for (int num = 0; num < 6; num++) {
                    localizador += letrasNums.charAt(rd.nextInt(letrasNums.length()));
                }
                if (!localizadores.contains(localizador)) {
                    localizadores.add(localizador);
                    repetido = true;
                }
                localizador = "";
            }
        }
        return localizadores;
    }

    ArrayList<Cliente> crearDatosClientes(ArrayList<Persona> personas, String ruta) throws IOException, ParseException {
        fwClientes = new FileWriter(ruta);
        String servidoresCorreo[] = {"@gmail.com", "@outlook.com", "@yahoo.es"};
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        //INSERT INTO CLIENTE(CORREO,CONTRASEÑA,TELÉFONO,PERSONA_NUMID) VALUES ('dlopezcastro@gmail.com', '+34 686597257', '4d3c2b1a','12345678B');
        for (int i = 0; i < personas.size(); i++) {
            if (esMayorEdad(personas.get(i).getFechaNac())) {
                String correo = personas.get(i).getNombre().toLowerCase() + personas.get(i).getApellido1() + personas.get(i).getApellido2() + servidoresCorreo[rd.nextInt(3)];
                GenerarContraseñas gc = new GenerarContraseñas();
                String contraseña = gc.getPassword(rd.nextInt(16) + 5);
                String persona_numid = personas.get(i).getDni();
                clientes.add(new Cliente(correo, contraseña, persona_numid));
            }
        }
        ArrayList<String> telefonos = generarTlf(clientes.size());

        for (int i = 0; i < clientes.size(); i++) {
            clientes.get(i).setTelefono(telefonos.get(i));
            linea = "INSERT INTO CLIENTE(CORREO,CONTRASEÑA,TELÉFONO,PERSONA_NUMID) VALUES ('" + clientes.get(i).getCorreo() + "', '" + clientes.get(i).getContraseña() + "', '" + clientes.get(i).getTelefono() + "', '" + clientes.get(i).getPersona_numID() + "');";
            fwClientes.write(linea + "\n");
        }
        fwClientes.write("\n");
        fwClientes.close();
        return clientes;
    }

    ArrayList<Acompañante> crearDatosAcompañantes(ArrayList<Persona> personas, String ruta) throws IOException, ParseException {
        fwAcompañantes = new FileWriter(ruta);
        ArrayList<Acompañante> acomp = new ArrayList<>();
        //INSERT INTO ACOMPAÑANTE VALUES (1,'12345678G');
        for (int i = 0; i < personas.size(); i++) {
            if (!esMayorEdad(personas.get(i).getFechaNac())) {
                String persona_numid = personas.get(i).getDni();
                acomp.add(new Acompañante(persona_numid));
            }
        }

        for (int i = 0; i < acomp.size(); i++) {
            linea = "INSERT INTO ACOMPAÑANTE VALUES (" + (i + 1) + ", '" + acomp.get(i).getPersona_numID() + "');";
            fwAcompañantes.write(linea + "\n");
        }
        fwAcompañantes.write("\n");
        fwAcompañantes.close();
        return acomp;
    }

    ArrayList<Operacion> crearDatosOperaciones(ArrayList<Cliente> clientes, String ruta) throws IOException, ParseException {
        fwOperaciones = new FileWriter(ruta);
        int numOperaciones;
        ArrayList<Operacion> operaciones = new ArrayList<>();
        double descuentos[] = {0.1, 0.2, 0.3};
        //INSERT INTO OPERACIÓN(FECHA,DESCUENTO,CLIENTE_IDCLIENTE) VALUES('16.1.2022',0.2,1);

        for (int i = 0; i < clientes.size(); i++) {

            numOperaciones = rd.nextInt(6);
            for (int j = 0; j < numOperaciones; j++) {
                operaciones.add(new Operacion(generarFecha(2020, 2021), descuentos[rd.nextInt(3)], (i + 1)));
            }
        }

        for (int i = 0; i < operaciones.size(); i++) {
            linea = "INSERT INTO OPERACIÓN(FECHA,DESCUENTO,CLIENTE_IDCLIENTE) VALUES('" + operaciones.get(i).getFecha() + "', " + operaciones.get(i).getDescuento() + ", " + operaciones.get(i).getId_cliente() + ");";
            fwOperaciones.write(linea + "\n");
        }
        fwOperaciones.write("\n");
        fwOperaciones.close();
        return operaciones;
    }

    ArrayList<Tarjeta_Bancaria> crearDatosTarjetas(ArrayList<Cliente> clientes, String ruta) throws IOException, ParseException {
        fwTarjetas = new FileWriter(ruta);
        ArrayList<Tarjeta_Bancaria> tarjetas = new ArrayList<>();
        //INSERT INTO TARJETABANCARIA VALUES (1234567891234567,5,22,622,1);
        ArrayList<Long> numTarjetas = generarNumTarjetas(clientes.size());
        ArrayList<Integer> cvv = generarCVV(clientes.size());

        for (int i = 0; i < clientes.size(); i++) {
            tarjetas.add(new Tarjeta_Bancaria(numTarjetas.get(i), rd.nextInt(12) + 1, rd.nextInt(5) + 22, cvv.get(i), (i + 1)));
        }

        for (int i = 0; i < tarjetas.size(); i++) {
            linea = "INSERT INTO TARJETABANCARIA VALUES (" + tarjetas.get(i).getNumTarjeta() + ", " + tarjetas.get(i).getMes_caducidad() + ", " + tarjetas.get(i).getAnno_caducidad() + ", " + tarjetas.get(i).getCvv() + ", " + tarjetas.get(i).getId_cliente() + ");";
            fwTarjetas.write(linea + "\n");
        }
        fwTarjetas.write("\n");
        fwTarjetas.close();
        return tarjetas;
    }

    ArrayList<Ruta> crearDatosRutas(ArrayList<Avion> aviones, ArrayList<Aeropuerto> aeropuertosO, ArrayList<Aeropuerto> aeropuertosD, String ruta) throws IOException, ParseException {
        fwRutas = new FileWriter(ruta);
        ArrayList<Ruta> rutas = new ArrayList<>();
        String fecha;
        int duraciones[] = {125, 150, 185, 115, 140, 165};
        int id_avion;
        int aeropuerto_origen;
        int aeropuerto_destino;
        int duracion;
        //INSERT INTO RUTA(FECHA,DURACIÓN,AVIÓN_IDAVIÓN,ORIGEN,DESTINO) VALUES('2022-01-25 08:15:00', 240,1,1,3);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < aeropuertosO.size(); j++) {
                for (int k = 0; k < aeropuertosD.size(); k++) {
                    fecha = "" + generarFecha(2020, 2022) + " " + generarHora();

                    id_avion = aviones.get(aeropuertosD.size() * j + k).getId_avion();

                    aeropuerto_origen = aeropuertosO.get(j).getId_aeropuerto();
                    aeropuerto_destino = aeropuertosD.get(k).getId_aeropuerto();
                    duracion = duraciones[aeropuertosD.size() * j + k];
                    rutas.add(new Ruta(fecha, duracion, id_avion, aeropuerto_origen, aeropuerto_destino));
                }
            }
        }

        for (int i = 0; i < rutas.size(); i++) {
            linea = "INSERT INTO RUTA(FECHA,DURACIÓN,AVIÓN_IDAVIÓN,ORIGEN,DESTINO) VALUES('" + rutas.get(i).getFecha() + "', " + rutas.get(i).getDuracion() + ", " + rutas.get(i).getAvion_id() + ", " + rutas.get(i).getOrigen_id() + ", " + rutas.get(i).getDestino_id() + ");";
            fwRutas.write(linea + "\n");
        }

        fwRutas.write("\n");
        fwRutas.close();
        return rutas;
    }

    ArrayList<Billete_Ruta> crearDatosBilleteRutas(ArrayList<Ruta> vuelos, String ruta) throws IOException, ParseException {
        fwBilletesRutas = new FileWriter(ruta);
        ArrayList<Billete_Ruta> billeteRutas = new ArrayList<>();
        //INSERT INTO BILLETERUTA(PRECIO,IMPUESTOS,RUTA_IDRUTA) VALUES(70.50,0.21,1);
        double precios[] = {60.5, 70, 80.5};
        double precio = 0;
        for (int i = 0; i < vuelos.size(); i++) {
            if (vuelos.get(i).getDestino_id() == 3) {
                precio = precios[0];
            } else if (vuelos.get(i).getDestino_id() == 4) {
                precio = precios[1];
            } else if (vuelos.get(i).getDestino_id() == 5) {
                precio = precios[2];
            }

            billeteRutas.add(new Billete_Ruta(precio, 0.21, (i + 1)));

        }

        for (int i = 0; i < billeteRutas.size(); i++) {
            linea = "INSERT INTO BILLETERUTA(PRECIO,IMPUESTOS,RUTA_IDRUTA) VALUES(" + billeteRutas.get(i).getPrecio() + ", " + billeteRutas.get(i).getImpuestos() + ", " + billeteRutas.get(i).getId_ruta() + ");";
            fwBilletesRutas.write(linea + "\n");
        }

        fwBilletesRutas.write("\n");
        fwBilletesRutas.close();
        return billeteRutas;
    }

    ArrayList<Billete> crearDatosBilletes(ArrayList<Ruta> rutas, ArrayList<Operacion> operaciones, String ruta) throws IOException, ParseException {
        fwBilletes = new FileWriter(ruta);
        ArrayList<Billete> billetes = new ArrayList<>();
        int id_billeteRuta;
        
        //INSERT INTO BILLETE VALUES('NIK23A',1,1,'16.1.2022');
        ArrayList<String> localizadores = generarLocalizadores(operaciones.size());
        for (int i = 0; i < operaciones.size(); i++) {
            id_billeteRuta = rd.nextInt(rutas.size());
            while (fechaMayor(operaciones.get(i).getFecha(),rutas.get(id_billeteRuta).getFecha())) {
                id_billeteRuta = rd.nextInt(rutas.size());
            }
            
            
            while(rutas.get(id_billeteRuta).getCantidadPasajeros() >= 75) {
               
               rutas.get(id_billeteRuta).decPasajerosAvion();
               id_billeteRuta = rd.nextInt(rutas.size()); 
            }
            
            rutas.get(id_billeteRuta).incPasajerosAvion();
            billetes.add(new Billete(localizadores.get(i),id_billeteRuta+1,(i+1),operaciones.get(i).getFecha()));
            

        }

        for (int i = 0; i < billetes.size(); i++) {
            linea = "INSERT INTO BILLETE VALUES('" + billetes.get(i).getLocalizador() + "', " + billetes.get(i).getId_billeteRuta() + ", " + billetes.get(i).getId_operacion() + ", '" + billetes.get(i).getFechaOperacion() + "');";
            fwBilletes.write(linea + "\n");
        }

        fwBilletes.write("\n");
        fwBilletes.close();
        return billetes;
    }
    
    ArrayList<AcompañantesBillete> crearAcompañantesBilletes(ArrayList<Acompañante> acomp, ArrayList<Billete> billetes, String ruta) throws IOException, ParseException {
        fwAcompañantesBilletes = new FileWriter(ruta);
        ArrayList<AcompañantesBillete> acompBilletes = new ArrayList<>();

        
        //INSERT INTO ACOMPAÑANTEBILLETE(ACOMPAÑANTE_IDACOMPAÑANTE,ACOMPAÑANTE_PERSONA_NUMID,BILLETE_LOCALIZADOR) VALUES (1,'12345678G','NIK23A');
        for (int i = 0; i < acomp.size(); i++) {

            acompBilletes.add(new AcompañantesBillete((i+1),acomp.get(i).getPersona_numID(),billetes.get(rd.nextInt(billetes.size())).getLocalizador()));

        }

        for (int i = 0; i < acompBilletes.size(); i++) {
            linea = "INSERT INTO ACOMPAÑANTEBILLETE(ACOMPAÑANTE_IDACOMPAÑANTE,ACOMPAÑANTE_PERSONA_NUMID,BILLETE_LOCALIZADOR) VALUES (" + acompBilletes.get(i).getId_acomp() + ", '" + acompBilletes.get(i).getDNI_acomp() + "', '" + acompBilletes.get(i).getLocalizador_billete() + "');";
            fwAcompañantesBilletes.write(linea + "\n");
        }

        fwAcompañantesBilletes.write("\n");
        fwAcompañantesBilletes.close();
        return acompBilletes;
    }

    public boolean esMayorEdad(String fecha) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(fecha, fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);

        return (periodo.getYears() >= 18);
    }

    public boolean fechaMayor(String fecha1, String fecha2) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicioDate = date.parse(fecha1); 
        Date fechaFinalDate = date.parse(fecha2);
        return (fechaInicioDate.after(fechaFinalDate));
    }
    
}
