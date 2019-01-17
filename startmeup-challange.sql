USE startmeup;

DROP TABLE usuario;

CREATE TABLE startmeup.Usuario(id INT NOT NULL AUTO_INCREMENT, 
nome varchar(25),
email varchar(40),
senha varchar(20),PRIMARY KEY(id));

INSERT INTO startmeup.Usuario(nome,email,senha)VALUES('Bruno','bruno@bruno.com', '123456');

SELECT * FROM startmeup.Usuario;

UPDATE startmeup.Usuario SET nome='Tania', email='tania@tania.com', senha='123ta' WHERE id = 1;  

DELETE FROM startmeup.Usuario WHERE id = 5;

DROP TABLE agenda;

INSERT INTO agenda(nome, fkUsuario)VALUES("Agenda Google", 1);

CREATE TABLE startmeup.Evento(id int not null auto_increment,
titulo varchar(100) not null,
dataInicio timestamp not null,
dataFim timestamp not null,
fkUsuario int not null,
FOREIGN KEY(fkUsuario) references  startmeup.Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY(id));

DESC startmeup.Evento;

SELECT * FROM startmeup.Evento;

DROP TABLE startmeup.Evento;

INSERT INTO startmeup.Evento(titulo, dataInicio,dataFim,fkUsuario)values('Evento Teste',NOW(), NOW()+interval 1 hour ,2);


/*ARRUMANDO TIME ZONE*/
#https://elias.praciano.com/2015/07/como-ajustar-o-fuso-horario-ou-timezone-no-mysql/

SET time_zone='America/Sao_Paulo';

SELECT NOW();

DELETE FROM startmeup.Evento;	

