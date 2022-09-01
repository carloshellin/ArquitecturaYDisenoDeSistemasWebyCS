CREATE TABLE LIBROS (
    TITULO varchar(80) primary key,
    AUTOR varchar(60),
    PRECIO integer
);


INSERT INTO LIBROS VALUES ('Don Quijote de La Mancha','Miguel de Cervantes',11);
INSERT INTO LIBROS VALUES ('Romeo y Julieta','Willian Shakespeare',11);
INSERT INTO LIBROS VALUES ('Odisea','Homero',9);

SELECT * FROM LIBROS;
