/*==============================================================*/
/* DBMS name:      MySQL 5.0                                  												 	 */
/* DB_NAME:     SIE_DB					                            												 */
/*==============================================================*/
create database SIE_DB;

use SIE_DB;

drop table if exists ADMINISTRATIVOS;

drop table if exists ALUMNOS;

drop table if exists CARRERAS;

drop table if exists DEPARTAMENTOS;

drop table if exists DOCENTES;

drop table if exists ESTATUS;

drop table if exists PUESTOS;

drop table if exists USUARIOS;

/*==============================================================*/
/* Table: ADMINISTRATIVOS                                    												     */
/*==============================================================*/
create table ADMINISTRATIVOS
(
   NOEMPLEADO           int not null,
   IDUSUARIO            int,
   IDPUESTO             int not null,
   IDDEPARTAMENTO       int not null,
   ESTATUS              char(1)  not null,
   primary key (NOEMPLEADO)
);

/*==============================================================*/
/* Table: ALUMNOS                        														                         */
/*==============================================================*/
create table ALUMNOS
(
   NOCONTROL            char(8) not null,
   IDCARRERA            int,
   IDUSUARIO            int,
   IDESTATUS            int,
   PROMEDIO             float,
   CREDITOS             int,
   primary key (NOCONTROL)
);

/*==============================================================*/
/* Table: CARRERAS                                   													             */
/*==============================================================*/
create table CARRERAS
(
   IDCARRERA            int not null,
   NOMBRE               varchar(80),
   SIGLAS               varchar(10),
   CREDITOS             int,
   ESPECIALIDAD         varchar(100),
   ESTATUS              char(1),
   NOEMPLEADO           int,
   primary key (IDCARRERA)
);

/*==============================================================*/
/* Table: DEPARTAMENTOS                                 												         */
/*==============================================================*/
create table DEPARTAMENTOS
(
   IDDEPARTAMENTO       int not null,
   NOMBRE               varchar(100),
   primary key (IDDEPARTAMENTO),
   key uk_nombre_departamento (NOMBRE)
);

/*==============================================================*/
/* Table: DOCENTES                                           													     */
/*==============================================================*/
create table DOCENTES
(
   NODOCENTE            int not null,
   IDCARRERA            int,
   IDUSUARIO            int,
   ESCOLARIDAD          varchar(50),
   ESPECIALIDAD         varchar(80),
   CEDULA               varchar(10),
   ESTATUS              char(1),
   primary key (NODOCENTE)
);

/*==============================================================*/
/* Table: ESTATUS                                               														 */
/*==============================================================*/
create table ESTATUS
(
   IDESTATUS            int not null,
   NOMBRE               varchar(30),
   primary key (IDESTATUS)
);

/*==============================================================*/
/* Table: PUESTOS                                               														 */
/*==============================================================*/
create table PUESTOS
(
   IDPUESTO             int not null,
   NOMBRE               varchar(100),
   primary key (IDPUESTO),
   key uk_nombre_puestos (NOMBRE)
);

/*==============================================================*/
/* Table: USUARIOS                                           													     */
/*==============================================================*/
create table USUARIOS
(
   IDUSUARIO            int not null,
   NOMBRE               varchar(100),
   SEXO                 char(1),
   TELEFONO             varchar(20),
   EMAIL                varchar(100),
   CLAVE                varchar(20) default 'Hola.123',
   TIPO                 char(1),
   ESTATUS              char(1) default 'A',
   primary key (IDUSUARIO)
);

/*==============================================================*/
/* Restricciones  FK					                                           											*/
/*==============================================================*/
alter table ADMINISTRATIVOS add constraint Administrativos_Departamentos_FK foreign key (IDDEPARTAMENTO)
      references DEPARTAMENTOS (IDDEPARTAMENTO);

alter table ADMINISTRATIVOS add constraint Administrativos_Puestos_FK foreign key (IDPUESTO)
      references PUESTOS (IDPUESTO);

alter table ADMINISTRATIVOS add constraint FK_Administrativos_Usuarios foreign key (IDUSUARIO)
      references USUARIOS (IDUSUARIO) on delete restrict on update restrict;

alter table ALUMNOS add constraint FK_Alumnos_Carreras foreign key (IDCARRERA)
      references CARRERAS (IDCARRERA) on delete restrict on update restrict;

alter table ALUMNOS add constraint FK_Alumnos_Estatus foreign key (IDESTATUS)
      references ESTATUS (IDESTATUS) on delete restrict on update restrict;

alter table ALUMNOS add constraint FK_Alumnos_Usuario foreign key (IDUSUARIO)
      references USUARIOS (IDUSUARIO) on delete restrict on update restrict;

alter table CARRERAS add constraint FK_Carreras_Administrativos foreign key (NOEMPLEADO)
      references ADMINISTRATIVOS (NOEMPLEADO) on delete restrict on update restrict;

alter table DOCENTES add constraint FK_Docentes_Carreras foreign key (IDCARRERA)
      references CARRERAS (IDCARRERA) on delete restrict on update restrict;

alter table DOCENTES add constraint FK_Docentes_Usuarios foreign key (IDUSUARIO)
      references USUARIOS (IDUSUARIO) on delete restrict on update restrict;
      
/*==============================================================*/
/* Creacion de VISTAS                                             													 */
/*==============================================================*/
create  view vAlumnos
as
select a.nocontrol,u.nombre nombre_completo,u.sexo,a.promedio,a.creditos,
a.IDCARRERA,c.nombre nombre_carrera,a.IDESTATUS,e.nombre estatus,
u.TELEFONO,u.EMAIL 
from alumnos a join usuarios u on a.idusuario=u.idusuario
join carreras c on a.idCarrera=c.idCarrera
join estatus e on a.idEstatus=e.idEstatus;

create view vDocentes
as
select d.nodocente, u.nombre nombre_completo, u.sexo,d.escolaridad,d.especialidad,
d.cedula,d.idCarrera,c.nombre nombre_carrera,u.TELEFONO,u.EMAIL ,d.estatus
 from docentes d
 join usuarios u on d.idUsuario=u.idUsuario
 join carreras c on d.idCarrera=c.idCarrera;

create view vAdministrativos
as
select a.noempleado,u.nombre nombre_completo, u.sexo,u.TELEFONO,u.EMAIL,
d.idDepartamento,d.nombre Departamento,p.idPuesto,p.nombre Puesto,a.estatus
from administrativos a join usuarios u on a.idUsuario=u.idUsuario
join departamentos d on a.idDepartamento=d.idDepartamento
join puestos p on a.idPuesto=p.idPuesto;

/*==============================================================*/
/* Creacion del Usuario para la conexion   y permisos                                         		 */
/*==============================================================*/
CREATE USER 'SIE_USER'@'localhost' IDENTIFIED BY 'SIE.123';
GRANT ALL PRIVILEGES ON SIE_DB.administrativos TO 'SIE_USER'@'localhost';
GRANT ALL PRIVILEGES ON SIE_DB.alumnos TO 'SIE_USER'@'localhost';
GRANT ALL PRIVILEGES ON SIE_DB.carreras TO 'SIE_USER'@'localhost';
GRANT ALL PRIVILEGES ON SIE_DB.departamentos TO 'SIE_USER'@'localhost';
GRANT ALL PRIVILEGES ON SIE_DB.docentes TO 'SIE_USER'@'localhost';
GRANT ALL PRIVILEGES ON SIE_DB.estatus TO 'SIE_USER'@'localhost';
GRANT ALL PRIVILEGES ON SIE_DB.puestos TO 'SIE_USER'@'localhost';
GRANT ALL PRIVILEGES ON SIE_DB.salas TO 'SIE_USER'@'localhost';
GRANT ALL PRIVILEGES ON SIE_DB.usuarios TO 'SIE_USER'@'localhost';

