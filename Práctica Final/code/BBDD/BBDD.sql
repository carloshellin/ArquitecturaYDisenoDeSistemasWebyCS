CREATE TABLE Persona(
    numID VARCHAR(20) NOT NULL PRIMARY KEY,
    tipoIdentificacion VARCHAR(20) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellido1 VARCHAR(30) NOT NULL,
    apellido2 VARCHAR(30),
    fechaNacimiento DATE NOT NULL,
    sexo VARCHAR(20) NOT NULL
);


CREATE TABLE Aeropuerto(
	idAeropuerto INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	nombre VARCHAR(40) NOT NULL,
	dirección VARCHAR(50) NOT NULL,
	ciudad VARCHAR(30) NOT NULL,
	país VARCHAR(30) NOT NULL,
	teléfono VARCHAR(15) NOT NULL
);


CREATE TABLE  Avión(
	idAvión INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	modelo VARCHAR(35) NOT NULL,
	numPlazas SMALLINT NOT NULL
);

CREATE TABLE Ruta(
	idRuta INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	fecha TIMESTAMP NOT NULL,
	duración SMALLINT NOT NULL,
	Avión_idAvión INT NOT NULL,
	origen INT NOT NULL,
	destino INT NOT NULL,
	CONSTRAINT fk_Ruta_Avión
		FOREIGN KEY (Avión_idAvión)
		REFERENCES Avión (idAvión)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	CONSTRAINT fk_Ruta_Aeropuerto1
		FOREIGN KEY (origen)
		REFERENCES Aeropuerto (idAeropuerto)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	CONSTRAINT fk_Ruta_Aeropuerto2
		FOREIGN KEY (destino)
		REFERENCES Aeropuerto (idAeropuerto)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

CREATE TABLE BilleteRuta(
    billeteRuta INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    precio DECIMAL(6,2) NOT NULL,
    impuestos DECIMAL(5,2) NOT NULL,
    Ruta_idRuta INT NOT NULL,
    CONSTRAINT fk_BilleteRuta_Ruta1
            FOREIGN KEY (Ruta_idRuta)
            REFERENCES Ruta (idRuta)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE Cliente(
	idCliente INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	correo VARCHAR(60) NOT NULL,
	contraseña VARCHAR(60) NOT NULL,
	teléfono VARCHAR(15) NOT NULL,
	Persona_numID VARCHAR(20) NOT NULL,
	UNIQUE (correo),
	CONSTRAINT fk_Cliente_Persona1
		FOREIGN KEY (Persona_numID)
		REFERENCES Persona(numID)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

CREATE TABLE TarjetaBancaria(
	numTarjeta BIGINT NOT NULL PRIMARY KEY,
	mesCaducidad SMALLINT NOT NULL,
	añoCaducidad SMALLINT NOT NULL,
	CVV SMALLINT NOT NULL,
	Cliente_idCliente INT NOT NULL,
	CONSTRAINT fk_TarjetaBancaria_Cliente1
		FOREIGN KEY (Cliente_idCliente)
		REFERENCES Cliente(idCliente)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);


CREATE TABLE Operación(
	idOperación INT NOT NULL GENERATED ALWAYS AS IDENTITY,
	fecha DATE NOT NULL,
	descuento DECIMAL(5,2) NOT NULL,
	Cliente_idCliente INT NOT NULL,
	PRIMARY KEY (idOperación, fecha),
	CONSTRAINT fk_Operación_Cliente1
		FOREIGN KEY (Cliente_idCliente)
		REFERENCES Cliente(idCliente)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);


CREATE TABLE Billete (
	localizador VARCHAR(6) NOT NULL PRIMARY KEY ,
	BilleteRuta_idBilleteRuta INT NOT NULL,
	Operación_idOperación INT NOT NULL,
	Operación_fecha DATE NOT NULL,
	CONSTRAINT fk_Billete_BilleteRuta
		FOREIGN KEY (BilleteRuta_idBilleteRuta)
		REFERENCES BilleteRuta(billeteRuta)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);


CREATE TABLE Clase (
  idClase INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nombre VARCHAR(15) NOT NULL,
  descripción VARCHAR(250) NOT NULL
);


CREATE TABLE Acompañante (
  idAcompañante INT NOT NULL,
  Persona_numID VARCHAR(20) NOT NULL,
  PRIMARY KEY (idAcompañante, Persona_numID),
  CONSTRAINT fk_Acompañante_Persona
    FOREIGN KEY (Persona_numID)
    REFERENCES Persona (numID)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);


CREATE TABLE Administrador (
  idAdministrador INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  correo VARCHAR(40) NOT NULL,
  contraseña VARCHAR(60) NOT NULL,
  Persona_numID VARCHAR(20) NOT NULL,
  PRIMARY KEY (idAdministrador, Persona_numID),
  UNIQUE (correo),
  CONSTRAINT fk_Administrador_Persona1
    FOREIGN KEY (Persona_numID)
    REFERENCES Persona (numID)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);


CREATE TABLE Tasas (
  idTasas INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  gestión DECIMAL(7,2) NOT NULL,
  seguridad DECIMAL(7,2) NOT NULL,
  combustible DECIMAL(7,2) NOT NULL,
  Aeropuerto_idAeropuerto INT NOT NULL,
  PRIMARY KEY (idTasas, Aeropuerto_idAeropuerto),
  CONSTRAINT fk_Tasas_Aeropuerto
    FOREIGN KEY (Aeropuerto_idAeropuerto)
    REFERENCES Aeropuerto (idAeropuerto)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);


CREATE TABLE ClaseAvión (
  Clase_idClase INT NOT NULL,
  Avión_idAvión INT NOT NULL,
  numPlazas SMALLINT NOT NULL,
  PRIMARY KEY (Clase_idClase, Avión_idAvión),
  CONSTRAINT fk_Clase_has_Avión_Clase
    FOREIGN KEY (Clase_idClase)
    REFERENCES Clase (idClase)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Clase_has_Avión_Avión
    FOREIGN KEY (Avión_idAvión)
    REFERENCES Avión (idAvión)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


CREATE TABLE AcompañanteBillete (
  idAcompañanteBillete INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  Acompañante_idAcompañante INT NOT NULL,
  Acompañante_Persona_numID VARCHAR(20) NOT NULL,
  Billete_localizador VARCHAR(6) NOT NULL,
  CONSTRAINT fk_AcompañanteBillete_Acompañante
    FOREIGN KEY (Acompañante_idAcompañante, Acompañante_Persona_numID)
    REFERENCES Acompañante (idAcompañante,Persona_numID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_AcompañanteBillete_Billete
    FOREIGN KEY (Billete_localizador)
    REFERENCES Billete (localizador)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
