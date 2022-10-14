create database obligatorioDDA;
use obligatorioDDA;

create table jugador(
ci int primary key,
nombre varchar(15),
apellido varchar(20),
puesto varchar(15),
dorsal int,
edad int,
idEquipo int references Equipo(id)
);

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


create table partido(
id int primary key,
local int references equipo(id),
visitante int references equipo(id),
estadio varchar(25),
fecha varchar(15),
hora varchar(7),
clima varchar(15)
);
insert into partido values (1,2,'Bernabeu','2022/08/21','15:00','Soleado');

create table arbitroPartido(
idPartido int references partido(id),
ciArbitro int references arbitro(ci),
primary key(idPartido,ciArbitro)
);
insert into arbitroPartido values(1,5234131)
insert into arbitroPartido values(1,43771124)
insert into arbitroPartido values(1,52397633)

create table titulares(
ciJugador int references jugador(ci),
idEquipo int references equipo(id),
idPartido int references partido(id),
);

create table suplentes(
ciJugador int references jugador(ci),
idEquipo int references equipo(id),
idPartido int references partido(id),
);

create table gol(
idPartido int references partido(id),
idEquipo int references equipo(id),
ciJugador int references jugador(ci),
minuto int not null);

create table cambio(
idPartido int references partido(id),
idEquipo int references equipo(id),
ciEntra int references jugador(ci),
ciSale int references jugador(ci),
minuto int not null,
);

--INSERT EQUIPO 1
insert into jugador values(47944188,'Roberto', 'Meli','defensa',26,40,1);
insert into jugador values(52397634,'Maicol', 'Bargas','delantero',13,19,1);
insert into jugador values(48712346,'Lucas', 'Suarez','defensa',2,27,1);
insert into jugador values(38124631,'Matias', 'Lopez','defensa',4,29,1);
insert into jugador values(39774124,'Richard', 'Porta','delantero',9,34,1);
insert into jugador values(49741123,'Kevin', 'De Bruyne','mediocampista',17,26,1);
insert into jugador values(21142258,'Paolo', 'Maldini','defensa',3,35,1);
insert into jugador values(59784132,'Oliver', 'Kahn','portero',1,40,1);
insert into jugador values(46625489,'Peter', 'Cech','portero',12,36,1);
insert into jugador values(48711123,'Marcelo', 'Gallardo','mediocampista',8,24,1);
insert into jugador values(47812463,'Lucas', 'Ocampos','delantero',7,20,1);
insert into jugador values(34451234,'Giorgio', 'Chiellini','defensa',5,34,1);
insert into jugador values(47451328,'Kevin', 'Prince','mediocampista',69,26,1);
insert into jugador values(35489712,'Kingsley', 'Coman','delantero',11,21,1);
insert into jugador values(21463978,'Ricardo', 'Quaresma','mediocampista',10,35,1);
insert into jugador values(42213697,'Bernardo', 'Silva','mediocampista',21,24,1);
---

--INSERT EQUIPO 2
insert into jugador values(42213457,'Manuel', 'Neuer','portero',1,30,2);
insert into jugador values(52143331,'Gianluiggi', 'Buffon','portero',12,40,2);
insert into jugador values(38914421,'Renan', 'Lodi','defensa',6,29,2);
insert into jugador values(39874124,'Aurelio', 'Buta','defensa',4,21,2);
insert into jugador values(14235481,'Lucas', 'Hernandez','defensa',19,21,2);
insert into jugador values(41197458,'Matias', 'Jensen','mediocampista',8,22,2);
insert into jugador values(37412298,'Paolo', 'Nesta','defensa',3,35,2);
insert into jugador values(48851242,'Sergio', 'Casas','defensa',25,27,2);
insert into jugador values(32145689,'Kyle', 'Walker','defensa',2,27,2);
insert into jugador values(47894413,'Phillipe', 'Billing','mediocampista',22,23,2);
insert into jugador values(68794213,'Joao', 'Moutinho','mediocampista',18,30,2);
insert into jugador values(91246897,'Giorgian', 'DeArrascaeta','mediocampista',10,27,2);
insert into jugador values(37999444,'Rodri', 'Hernandez','mediocampista',5,26,2);
insert into jugador values(31111222,'Benjamin', 'Aude','delantero',13,21,2);
insert into jugador values(74412364,'Iago', 'Aspas','delantero',17,28,2);
insert into jugador values(26874921,'Divoc', 'Origi','delantero',9,29,2);
--

Create or alter Procedure AltaSuplentes
 @idPartido int,
 @idEquipo  int,
 @ciJugador int
 AS
BEGIN
	 IF(@ciJugador not in (Select ciJugador from titulares where idEquipo = @idEquipo and idPartido = @idPartido))
	 BEGIN
		 INSERT INTO suplentes VALUES (@ciJugador,@idEquipo,@idPartido);
	 END
END

create or alter trigger Verificar16Jugadores
on jugador
after insert 
as
	declare @cantidad int
	declare @idEquipo int
begin
	select @idEquipo = idEquipo
	from inserted

	select @cantidad = COUNT(*)
	from jugador
	where idEquipo = @idEquipo;

	if @cantidad > 16 
	begin
		RAISERROR('ERROR!, máximo de jugadores alcanzado',12,1)
		ROLLBACK;
 end
end

create or alter trigger VerificarDorsal
on jugador
instead of insert
as
	DECLARE
	@ci int ,
	@nombre varchar(15),
	@apellido varchar(20),
	@puesto varchar(15),
	@dorsal int,
	@edad int,
	@idEquipo int
begin
	select @ci = ci from inserted
	select @nombre = nombre from inserted
	select @apellido = apellido from inserted
	select @puesto = puesto from inserted
	select @dorsal = dorsal from inserted
	select @edad = edad from inserted 
	select @idEquipo = idEquipo from inserted

	IF(@dorsal NOT IN (SELECT dorsal from jugador where idEquipo = @idEquipo))
	BEGIN
		INSERT INTO jugador VALUES (@ci,@nombre,@apellido,@puesto,@dorsal,@edad,@idEquipo)
	END
	ELSE 
	BEGIN
		RAISERROR('ERROR! Dorsal repetido!',12,1)
	    ROLLBACK TRANSACTION;
	END
end

CREATE or ALTER TRIGGER VerificarCantidadDeJugadores
ON partido
AFTER INSERT
AS
	DECLARE @idLocal int
	DECLARE @idVisitante int
	DECLARE @cantidadLocal int
	DECLARE @cantidadVisitante int
BEGIN
	select @idLocal = local from inserted
	select @idVisitante = visitante from inserted
	select @cantidadLocal = COUNT(*) from jugador where idEquipo = @idLocal
	select @cantidadVisitante = COUNT(*) from jugador where idEquipo = @idVisitante

	IF(@cantidadLocal < 16)
	BEGIN
		RAISERROR('ERROR! EL EQUIPO LOCAL NO TIENE SUFICIENTES JUGADORES!',12,1)
		ROLLBACK;
	END
	ELSE IF(@cantidadVisitante < 16)
	BEGIN
		RAISERROR('ERROR! EL EQUIPO VISITANTE NO TIENE SUFICIENTES JUGADORES!',12,1)
		ROLLBACK;
	END
END

CREATE or ALTER TRIGGER VerificarCambios
ON cambio
AFTER INSERT
AS
	DECLARE @idEquipo int
	DECLARE @idPartido int
	DECLARE @ciEntra int
	DECLARE @ciSale int
BEGIN
	select @idEquipo = idEquipo from inserted
	select @idPartido = idPartido from inserted
	select @ciEntra = ciEntra from inserted
	select @ciSale = ciSale from inserted

	IF(@ciEntra in (SELECT ciJugador from titulares))
	BEGIN
		RAISERROR('ERROR! NO PUEDE ENTRAR UN TITULAR!',12,1)
		ROLLBACK;
	END
	ELSE IF(@ciSale in (SELECT ciJugador from suplentes) )
	BEGIN
		RAISERROR('ERROR! NO PUEDE SALIR UN SUPLENTE!',12,1)
		ROLLBACK;
	END
	ELSE IF(@ciEntra in (SELECT @ciEntra from cambio) or @ciSale in (SELECT @ciEntra from cambio))
	BEGIN
		RAISERROR('ERROR!ESTE CAMBIO YA SE REALIZO!',12,1)
		ROLLBACK;
	END
END

CREATE or ALTER TRIGGER VerificarGoleador
ON gol
AFTER INSERT
AS
	DECLARE @idPartido int
	DECLARE @idEquipo int
	DECLARE @ciJugador int
	DECLARE @minuto int
BEGIN
	select @idPartido = idPartido from inserted
	select @idEquipo = idEquipo from inserted
	select @ciJugador = ciJugador from inserted
	select @minuto = minuto from inserted

	IF(@ciJugador in(SELECT ciJugador from titulares where idPartido = @idPartido and idEquipo = @idEquipo))
	BEGIN
		IF(@ciJugador in (SELECT ciSale from cambio where idPartido = @idPartido and idEquipo = @idEquipo))
		BEGIN
			IF(@minuto > (SELECT minuto from cambio where idPartido = @idPartido and idEquipo = @idEquipo and ciSale = @ciJugador))
			BEGIN
				RAISERROR('ERROR! EL JUGADOR YA HA SIDO SUSTITUIDO!',12,1)
				ROLLBACK;
			END
		END
	END
	ELSE 
	IF(@ciJugador in(SELECT ciJugador from suplentes where idPartido = @idPartido and idEquipo = @idEquipo))
	BEGIN
		IF(@ciJugador in (SELECT ciEntra from cambio where idPartido = @idPartido and idEquipo = @idEquipo))
		BEGIN
			IF(@minuto < (SELECT minuto from cambio where idPartido = @idPartido and idEquipo = @idEquipo and ciEntra = @ciJugador))
			BEGIN
				RAISERROR('ERROR! EL JUGADOR SE ENCUENTRA EN EL BANCO DE SUPLENTES!',12,1)
				ROLLBACK;
			END
		END
		ELSE
			RAISERROR('ERROR! EL JUGADOR NO INGRESO!',12,1)
			ROLLBACK;
	END
END
