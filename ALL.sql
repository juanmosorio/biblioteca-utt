-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
--------------------------------------Creacion de la base de datos-------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
create database BibliotecaQ;
go

use Biblioteca;

create table Carrera( 
id_carrera int identity(1,1),
nombre nchar(50), 
primary key(id_carrera));

create table Actividad(
id_actividad int identity(1,1),
nombre nchar(30),
primary key(id_actividad) ); 

create table Usuario(
id_usuario int identity(1,1),
matricula bigint not null,
nombre nchar(30), 
apellidoPaterno nchar(50),
apellidoMaterno nchar(50),
genero bit, 
grado nchar(20), 
hora_entrada time, 
hora_salida time, 
fecha date, 
id_carrera int , 
id_actividad int,
tipo nchar(20)
constraint FK_CARRERA2 foreign key (id_carrera) references Carrera(id_carrera),
constraint FK_ACTIVIDAD2 foreign key (id_actividad) references Actividad(id_actividad));

create table Student(
id_usuario int identity(1,1),
matricula nchar(20) not null,
nombre nchar(30), 
apellidoPaterno nchar(50),
apellidoMaterno nchar(50),
genero bit, 
grado nchar(20), 
id_carrera int 
);

create table Teacher(
id_teacher int identity(1,1),
numero_empleado nchar(20) not null,
nombre nchar(30), 
apellidoPaterno nchar(50),
apellidoMaterno nchar(50),
genero bit, 
grado nchar(20), 
id_carrera int 
)

-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
----------------------------------------------Tabla login----------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
CREATE TABLE UserLogin (
  id int identity(1,1),
  nombre varchar(40) DEFAULT NULL,
  pass varchar(50) NOT NULL,
  rool varchar(40) DEFAULT NULL
)

INSERT INTO UserLogin (nombre,pass,rool) VALUES ('ADMIN','ADMIN','Administrador')
SELECT * FROM UserLogin WHERE nombre = 'ADMIN' AND pass = 'ADMIN'


-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
----------------------------------Ingresar a BD datos necesarios---------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------

insert into Carrera (nombre) values ('TICS');
insert into Carrera (nombre) values ('Desarrollo de Negocios');
insert into Carrera (nombre) values ('Agricultura');
insert into Carrera (nombre) values ('Energias Renovables');
insert into Carrera (nombre) values ('Procesos Industriales');
insert into Carrera (nombre) values ('Procesos Alimentarios');
insert into Carrera (nombre) values ('Mecatronica');



insert into Actividad (nombre) values ('Lectura');
insert into Actividad (nombre) values ('Consulta o Investigacion');
insert into Actividad (nombre) values ('Tarea o Trabajo');
insert into Actividad (nombre) values ('Prestamo y Entrega de Libros');
insert into Actividad (nombre) values ('Otro');

insert into Student (matricula,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(3516110026,'Juan','Martinez','Osorio',1,'TSU',1)--tuya

insert into Student (matricula,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(3516110283,'Félix Alberto','García','Martínez',1,'TSU',1)--tuya

insert into Student (matricula,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(3516110054,'Jorge Luis Fernando','Martínez','Hernández',1,'TSU',1)--jorge

insert into Student (matricula,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(3516110345,'Luis Fernando','Martínez','Osorio',1,'TSU',1)--laguera

insert into Student (matricula,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(3516110673,'Juan Carlos','Sánchez','Pacheco',1,'TSU',1)--carla

insert into Student(matricula,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(3516110111,'Luisa Fernanda','Martínez','Osorio',0,'TSU',1)--laguera

insert into Student (matricula,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(3516110222,'Carla','Sánchez','Pacheco',0,'TSU',1)--carla




insert into Teacher(numero_empleado,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(12341,'Yedid','Curioca','Varela',0,'TSU',1)

insert into Teacher (numero_empleado,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(12342,'Christian','Galicia','Garcia',1,'TSU',1)

insert into Teacher (numero_empleado,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(12343,'Luis Alverto','Cordova','Osorio',1,'TSU',1)

insert into Teacher (numero_empleado,nombre,apellidoPaterno,apellidoMaterno, genero, grado,id_carrera) values
(12344,'Sheila Adriana','Gonzales','Olivares',0,'TSU',2)

-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
--------------------------------Creacion de la tablabitacora-------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
use BibliotecaQ

create table Bitacora(
id_bitacora int identity(1,1),
id_usuario int,
matricula bigint not null,
nombre nchar(30), 
apellidoPaterno nchar(50),
apellidoMaterno nchar(50),
genero bit, 
grado nchar(20), 
hora_entrada time, 
hora_salida time, 
fecha date, 
id_carrera int , 
id_actividad int,
usuario nchar(30),
fecha_bitacora date,
hora time, 
accion nchar(30));
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-----------------------------------Creacion de Triggers para la bitacora-------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------

CREATE TRIGGER BitacoraInsert
ON Usuario 
AFTER INSERT
AS
BEGIN 
	INSERT Bitacora(id_usuario,matricula,nombre,apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,
	hora_salida,fecha,id_carrera,id_actividad,usuario,fecha_bitacora,hora,accion)
	SELECT id_usuario,matricula,nombre,apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,
	hora_salida,fecha,id_carrera,id_actividad,matricula,GETDATE(),GETDATE(),'INSERT' FROM inserted 
END

CREATE TRIGGER BitacoraUpdate
ON Usuario 
AFTER UPDATE
AS
BEGIN 
INSERT Bitacora(id_usuario,matricula,nombre,apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,
	hora_salida,fecha,id_carrera,id_actividad,usuario,fecha_bitacora,hora,accion)
	SELECT id_usuario,matricula,nombre,apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,
	hora_salida,fecha,id_carrera,id_actividad,matricula,GETDATE(),GETDATE(),'UPDATE' FROM inserted 
END

CREATE TRIGGER BitacoraDelete
ON Usuario 
FOR DELETE
AS
BEGIN 
INSERT Bitacora(id_usuario,matricula,nombre,apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,
	hora_salida,fecha,id_carrera,id_actividad,usuario,fecha_bitacora,hora,accion)
	SELECT id_usuario,matricula,nombre,apellidoPaterno,apellidoMaterno,genero,grado,hora_entrada,
	hora_salida,fecha,id_carrera,id_actividad,matricula,GETDATE(),GETDATE(),'INSERT' FROM deleted 
END


-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-----------------------------script para visualizar el Diccionario de datos----------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------

SELECT
  SCHEMA_NAME(o.schema_id) AS Esquema,
  O.Name AS Tabla,
  P1.Value AS [Descripción Tabla],
  C.Name AS Columna,
  T.Name AS Tipo,
  C.max_length AS Longitud,
  C.[Precision] AS Presición,
  C.scale AS Escala,
  CASE
    WHEN C.Is_Nullable = 0 Then 'No'
    WHEN C.Is_Nullable = 1 Then 'Si'
  END [Nulo?],
  P2.value AS [Descripción Columna]
FROM
 sys.tables O
  INNER JOIN sys.Columns C
    ON O.object_id = C.object_id
  INNER JOIN sys.Types T
    ON C.system_type_id = T.system_type_id
    AND C.system_type_id = T.user_type_id
  LEFT JOIN sys.extended_properties P1
    ON C.object_id = P1.major_id
    AND P1.minor_id = 0
    LEFT JOIN sys.extended_properties P2
    ON C.object_id = P2.major_id
    AND C.Column_id = P2.minor_id
    AND P2.Class = 1
ORDER BY
  O.Name,
  C.Column_id
GO
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------Ingresar descripcion a la tabla-----------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------


--Ingresar Descripcion de Tabla
EXEC sys.sp_addextendedproperty
@name='MS_Description',
@value='Almacena a los usuarios registrados' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario'
GO
--Ingresar Descripcion de Tabla
EXEC sys.sp_addextendedproperty
@name='MS_Description',
@value='Registra los cabios de la tabla "Usuario"' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora'
GO
--Ingresar Descripcion de Tabla
EXEC sys.sp_addextendedproperty
@name='MS_Description',
@value='Almacena los datos de la Carrera del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Carrera'
GO

--Ingresar Descripcion de Tabla
EXEC sys.sp_addextendedproperty
@name='MS_Description',
@value='Almacena los datos de los Alumnos' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Student'
GO

--Ingresar Descripcion de Tabla
EXEC sys.sp_addextendedproperty
@name='MS_Description',
@value='Almacena los datos de los Docentes' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Teacher'
GO

--Ingresar Descripcion de Tabla
EXEC sys.sp_addextendedproperty
@name='MS_Description',
@value='Almacena datos de administrador' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='UserLogin'
GO

--Ingresar Descripcion de Tabla
EXEC sys.sp_addextendedproperty
@name='MS_Description',
@value='Almacena los datos de la Carrera del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Actividad'
GO
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
--------------------------------------Ingresar descripcion a las columnas------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------


--------------------------------------Ingresar descripcion a las columnas de Usuario---------------------------------------
--Ingresar Descripción de Usuario
EXEC sp_addextendedproperty
@name='MS_Description',
@value='id de el Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='id_usuario'
GO
--matricula
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la matricula del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='matricula'
GO
--nombre
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Nombre del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='nombre'
GO
--apellidos
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Apellido paterno del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='apellidoPaterno'
GO
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Apellido materno del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='apellidoMaterno'
GO
--genero
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Indica el genero del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='genero'
GO
--grado
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el grado escolar del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='grado'
GO
--hora de entrada
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la hora de entrada' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='hora_entrada'
GO
--hora de salida
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la hora de salida' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='hora_salida'
GO
--fecha
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la fecha de registro' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='fecha'
GO
--id de carrera FK 
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el id de la tabla Carrera' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='id_carrera'
GO
--id de actividad
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el id de la tabla Actividad' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='id_actividad'
GO
--tipo
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Indica si el Usuario es Estudiante o Docente' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Usuario',
@level2type='COLUMN',
@level2name='tipo'
GO
--------------------------------------Ingresar descripcion a las columnas de Bitacora---------------------------------------
--id_bitacora
EXEC sp_addextendedproperty
@name='MS_Description',
@value='id de Bitacora' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='id_bitacora'
GO
--id_usuario
EXEC sp_addextendedproperty
@name='MS_Description',
@value='id de la tabla Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='id_usuario'
GO
--matricula
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la matricula del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='matricula'
GO
--nombre
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Nombre del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='nombre'
GO
--apellidos
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Apellido paterno del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='apellidoPaterno'
GO
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Apellido materno del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='apellidoMaterno'
GO
--genero
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Indica el genero del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='genero'
GO
--grado
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el grado escolar del Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='grado'
GO
--hora de entrada
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la hora de entrada' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='hora_entrada'
GO
--hora de salida
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la hora de salida' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='hora_salida'
GO
--fecha
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la fecha de registro' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='fecha'
GO
--id de carrera FK 
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el id de la tabla Carrera' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='id_carrera'
GO
--id de actividad
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el id de la tabla Actividad' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='id_actividad'
GO
--usuario
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el tipo de usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='usuario'
GO
--fecha de bitacora
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la fecha' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='fecha_bitacora'
GO
--hora de bitacora
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la hora' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='hora'
GO
--accion de bitacora
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Indica la accion de Usuario' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Bitacora',
@level2type='COLUMN',
@level2name='accion'
GO
--------------------------------------Ingresar descripcion a las columnas de Teacher---------------------------------------
--id de docente
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Id de Docente' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Teacher',
@level2type='COLUMN',
@level2name='id_teacher'
GO
--id de numero de empleado
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el numero de empleado',
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Teacher',
@level2type='COLUMN',
@level2name='numero_empleado'
GO
--nombre
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Nombre del Docente' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Teacher',
@level2type='COLUMN',
@level2name='nombre'
GO
--apellidos
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Apellido paterno del Docente' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Teacher',
@level2type='COLUMN',
@level2name='apellidoPaterno'
GO
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Apellido materno del Docente' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Teacher',
@level2type='COLUMN',
@level2name='apellidoMaterno'
GO
--genero
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Indica el genero del Docente' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Teacher',
@level2type='COLUMN',
@level2name='genero'
GO
--grado
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el grado escolar del Docente' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Teacher',
@level2type='COLUMN',
@level2name='grado'
GO
--id de carrera FK 
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el id de la tabla Carrera' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Teacher',
@level2type='COLUMN',
@level2name='id_carrera'
GO
--------------------------------------Ingresar descripcion a las columnas de Student---------------------------------------
--id de alumno
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Id de Alumno' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Student',
@level2type='COLUMN',
@level2name='id_usuario'
GO
--id de numero de empleado
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la matricula del Alumno',
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Student',
@level2type='COLUMN',
@level2name='matricula'
GO
--nombre
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Nombre del Alumno' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Student',
@level2type='COLUMN',
@level2name='nombre'
GO
--apellidos
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Apellido paterno del Alumno' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Student',
@level2type='COLUMN',
@level2name='apellidoPaterno'
GO
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Apellido materno del Alumno' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Student',
@level2type='COLUMN',
@level2name='apellidoMaterno'
GO
--genero
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Indica el genero del Alumno' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Student',
@level2type='COLUMN',
@level2name='genero'
GO
--grado
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el grado escolar del Alumno' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Student',
@level2type='COLUMN',
@level2name='grado'
GO
--id de carrera FK 
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el id de la tabla Carrera' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Student',
@level2type='COLUMN',
@level2name='id_carrera'
GO
--------------------------------------Ingresar descripcion a las columnas de Actividad---------------------------------------
--id de Actividad
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Id de Actividad' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Actividad',
@level2type='COLUMN',
@level2name='id_actividad'
GO
--nombre de la actividad
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el nombre de la Actividad',
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Actividad',
@level2type='COLUMN',
@level2name='nombre'
GO
--------------------------------------Ingresar descripcion a las columnas de Carrera---------------------------------------
--id de Carrera
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Id de Carrera' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Carrera',
@level2type='COLUMN',
@level2name='id_carrera'
GO
--nombre de la Carrera
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el nombre de la Carrera',
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='Carrera',
@level2type='COLUMN',
@level2name='nombre'
GO
--------------------------------------Ingresar descripcion a las columnas de UserLogin---------------------------------------
--id de Login 
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Id de login' ,
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='UserLogin',
@level2type='COLUMN',
@level2name='id'
GO
--nombre de Administrador
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena el nombre del Administrador',
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='UserLogin',
@level2type='COLUMN',
@level2name='nombre'
GO
--pass de Administrador
EXEC sp_addextendedproperty
@name='MS_Description',
@value='Almacena la contraseña',
@level0type='SCHEMA',
@level0name='dbo',
@level1type='TABLE',
@level1name='UserLogin',
@level2type='COLUMN',
@level2name='pass'
GO
