create schema Congreso;
use Congreso;

create table Asistentes(
dni varchar(9),
nombre varchar(40),
centro varchar(100),
edad int
);

INSERT INTO Asistentes values (
"12345678A", "Pepe", "Ilerna", "21",
"32165478J", "Lucas", "Universidad de Sevilla", "23",
"81725432P", "Mario", "Universidad Pablo de Olavide", "22"
);