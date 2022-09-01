CREATE TABLE PROFESOR  (
    DNI char(9) not null primary key,
    NOMBRE varchar(20) not null,
    APELLIDO varchar(20) not null,
    SUELDO integer not null
);

CREATE TABLE ALUMNO  (
    DNI char(9) not null primary key,
    NOMBRE varchar(20) not null,
    APELLIDO varchar(20) not null,
    SUELDO integer not null
);

CREATE TABLE ASIGNATURA  (
    ID_ASIGNATURA char(6) not null primary key,
    NOMBRE varchar(50) not null,
    DESCRIPCION varchar(200),
    DNI_PROFESOR char(9) not null,
    constraint "FK_asignatura_profesor" FOREIGN KEY (DNI_PROFESOR) REFERENCES PROFESOR(DNI)
);

CREATE TABLE ASISTENCIA  (
    ID_ASISTENCIA char(4) not null,
    DNI_ALUMNO char(9) not null,
    ID_ASIGNATURA char(6) not null,
    primary key(DNI_ALUMNO, ID_ASIGNATURA),
    constraint "FK_asistencia_alumno" FOREIGN KEY (DNI_ALUMNO) REFERENCES ALUMNO(DNI),
    constraint "FK_asistencia_asignatura" FOREIGN KEY (ID_ASIGNATURA) REFERENCES ASIGNATURA(ID_ASIGNATURA)
);