create database obligatorioDDA;
use obligatorioDDA;

create table jugador(
ci int primary key,
nombre varchar(15),
apellido varchar(20),
puesto varchar(15),
dorsal int unique,
edad int,
idEquipo int references Equipo(id)
);
insert into jugador values(52397634,'Maicol', 'Bargas','Lateral',6,19,1);

create table arbitro(
ci int primary key,
nombre varchar(15),
apellido varchar(20),
puesto varchar(20)
);
insert into arbitro values(52397633,'Lucas', 'Redondo','principal');
insert into arbitro values(5234131,'Peter', 'Soriano','linea');

create table tecnico(
ci int primary key,
nombre varchar(15),
apellido varchar(20),
edad int
);
insert into tecnico values(52397633,'Ernesto', 'Valverde',51);
insert into tecnico values(47981224,'Matias', 'Pedrin',60);


create table equipo(
id int identity(1,1) primary key,
nombre varchar(30) not null,
tecnico int references tecnico(ci)
);
insert into equipo values('Holiday Voltics',52397633);
insert into equipo values('Huston City',47981224);

create table arbitroPartido(
idPartido int references partido(id),
ciArbitro int references arbitro(ci),
primary key(idPartido,ciArbitro)
);
insert into arbitroPartido values(1,5234131)
insert into arbitroPartido values(1,43771124)
insert into arbitroPartido values(1,52397633)

create table partido(
id int identity(1,1) primary key,
local int references equipo(id),
visitante int references equipo(id),
estadio varchar(25),
fecha date,
hora varchar(7),
clima varchar(15)
);
insert into partido values (1,2,'Bernabeu','2022/08/21','15:00','Soleado');
