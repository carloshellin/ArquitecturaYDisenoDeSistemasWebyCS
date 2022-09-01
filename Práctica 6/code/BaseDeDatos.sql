CREATE TABLE "CIRCUITO" (
	NOMBRE varchar(20) not null primary key,
	CIUDAD varchar(20) not null,
	PAIS varchar(20) not null,
	VUELTAS integer not null,
	LONGITUD integer not null,
	CURVAS integer not null
);

CREATE TABLE "COCHE" (
	NOMBRE varchar(20) not null primary key,
	KWCURVA integer not null
);

INSERT INTO "CIRCUITO" VALUES ('Mónaco', 'Montecarlo', 'Mónaco', 78, 3337, 19);
INSERT INTO "COCHE" VALUES ('Alpine A521', 5);