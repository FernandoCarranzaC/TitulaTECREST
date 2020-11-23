use sie_db;
select * from usuarios;
select * from alumnos;

delimiter //
create procedure insertaAlumno (pnombre varchar(100),
sexo char,telefono varchar(20),pemail varchar(100),clave  varchar(20),nc varchar(8),idC int,idE int,prom float,creditos int,out msg varchar(500))
begin
declare idT int;
declare idU int;
declare continue handler for SQLException
begin
	set msg='Error: No se puede registrar el usuario, intente mas tarde.';
    rollback;
end;
if not exists(select email from usuarios where email=pemail) then
	start transaction;
	select max(idUsuario)+1 into idU from Usuarios;
	insert into usuarios values(idU,pnombre,sexo,telefono,pemail,clave,'E','A');
    insert into alumnos values(nc,idC,idU,idE,prom,creditos);
	commit;
    set msg='Usuario creado con exito.';
else
    set msg='Error:El email de usuario que desea agregar ya existe, favor de elegir otro';
end if;    
end //

delimiter ;
call insertaAlumno('Andres Perez Lopez','M','351-345-1876','andi@gmail.com','Hola123','02420415',1,1,90,260,@m);
select @m;
select * from usuarios;
select * from alumnos;
select * from carreras