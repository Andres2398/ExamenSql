drop database if exists examen;
create database if not exists examen;

use examen;

create table if not exists tabla1(
id int not null auto_increment primary key,
nombre varchar (50),
salario int 
) engine = InnoDb;

create table if not exists historico(
id_tabla2 int not null auto_increment primary key,
accion text,
descripcion text
) engine = InnoDB;

create table if not exists errores(
id_tabla2 int not null auto_increment primary key,
mensaje text
) engine = InnoDB;


insert into tabla1(nombre, salario) values 
("pepe", 100),
("Andres",1000),
("Cristiano", 10000);






Create Procedure listar()
Begin
	Select * from tabla1;
End;

CREATE PROCEDURE mediaSalario (OUT MEDIA DOUBLE, OUT NUM INT)  
BEGIN 
  DECLARE C INT; 
  SET MEDIA = 0.0;   
  SET NUM = 0;  
  SELECT COUNT(*) INTO C FROM tabla1;
  IF C = 0 
   THEN 
     SET MEDIA =-1.0;
     SET NUM = 0;
  ELSE 
    SELECT COALESCE(AVG(salario),0), count(id)  INTO MEDIA, NUM FROM tabla1; 
  END IF;    
END;

commit;


Create Procedure agregar (in nombre Varchar(50), in salario int)
Begin
	insert into tabla1 (nombre, salario) values(nombre, salario);
	commit;
end;



Create procedure buscar(in p_id int)
Begin
select * from tabla1 where id = p_id;
end;



Create procedure actualizar(in p_id int, in nombre_nuevo varchar (50), in salario_nuevo int)
Begin 
	update tabla1
	set nombre = nombre_nuevo, salario = salario_nuevo
	where id=p_id;
end;



CREATE FUNCTION listarNombres()
RETURNS TEXT
DETERMINISTIC
READS SQL DATA
BEGIN 
    DECLARE lista_nombres TEXT;
    SELECT GROUP_CONCAT(nombre SEPARATOR ', ') INTO lista_nombres
    FROM tabla1;
    RETURN lista_nombres;
end; 


Create function contarEmplados()
returns int
DETERMINISTIC
READS SQL DATA
Begin 
	Declare total int;
	Select count(*) into total from tabla1;
	REturn total;
end;



drop trigger if exists verificarSalario;
create trigger verificarSalario
Before insert                     
on tabla1 for each row
Begin 
	if new.salario<0 then
	set new.salario = 0;
	SIGNAL SQLSTATE '45000'
	SET MESSAGE_TEXT = 'ERROR de Negocio: Nota erronea.';
	end if;
end;


DROP PROCEDURE IF EXISTS insertarSeguro;


CREATE PROCEDURE insertarSeguro(IN nombre_pe VARCHAR(50), IN salario_pe INT)
BEGIN
    
    DECLARE continue HANDLER FOR SQLEXCEPTION
    BEGIN
     
        INSERT INTO errores (mensaje) 
        VALUES ('Error: Se intentó insertar un salario negativo.');
    END;

   
    INSERT INTO tabla1 (nombre, salario) VALUES (nombre_pe, salario_pe);
    
END;






drop trigger if exists trigger_insert_Historioco;
create trigger trigger_insert_Historioco
after insert 
on tabla1 for each row
Begin 
	INSERT INTO historico(accion, descripcion) 
    VALUES ("Insertar" , CONCAT ("Insertado " , NEW.id, " - ", NEW.nombre));
end;


CREATE FUNCTION listar_empleados_json()
RETURNS JSON
DETERMINISTIC
READS SQL DATA
BEGIN
    RETURN (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'id', id,
                'nombre', nombre,
                'salario', salario
            )
        )
        FROM tabla1
    );
END;






                         