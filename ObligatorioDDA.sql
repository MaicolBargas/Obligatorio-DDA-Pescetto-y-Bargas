create database obligatorioDDA;
use obligatorioDDA;

create table jugador(
ci int primary key,
nombre varchar(15),
apellido varchar(20),
puesto varchar(15),
dorsal int unique,
edad int
);
insert into jugador values(52397633,'Maicol', 'Bargas','Lateral',6,19);

delete from jugador;
create table arbitro(
ci int primary key,
nombre varchar(15),
apellido varchar(20),
puesto varchar(20)
);

create table tecnico(
ci int primary key,
nombre varchar(15),
apellido varchar(20),
edad int
);


create table equipo(
id int primary key,
nombre varchar(30) not null,
tecnico int references tecnico(ci)
);

create table jugadorEquipo(
ci int references jugador(ci),
id int references equipo(id),
primary key(ci,id)
);

create table partido(
id int primary key,
local int references equipo(id),
visitante int references equipo(id),
estadio varchar(25),
fecha date,
hora varchar(5),
clima varchar(15)
);

