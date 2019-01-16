CREATE DATABASE listavip;
USE listavip;

CREATE TABLE convidado(id INT NOT NULL AUTO_INCREMENT, 
nome varchar(25),
email varchar(40),
telefone int,PRIMARY KEY(id));
USE startmeup;
DROP TABLE usuario;

CREATE TABLE usuario(id INT NOT NULL AUTO_INCREMENT, 
nome varchar(25),
email varchar(40),
senha varchar(20),PRIMARY KEY(id));

INSERT INTO usuario(nome,email,senha)VALUES('Bruno','bruno@bruno.com', '123456');

SELECT * FROM usuario;

UPDATE usuario SET nome='Tania', email='tania@tania.com', senha='123ta' WHERE id = 1;  

DELETE FROM usuario WHERE id = 5;

CREATE TABLE agenda(id int not null auto_increment,
nome varchar(50) not null,
fkUsuario int not null,
descricao varchar(100),
FOREIGN KEY(fkUsuario) references  usuario(id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY(id));

DROP TABLE agenda;

INSERT INTO agenda(nome, fkUsuario)VALUES("Agenda Google", 1);

SELECT * FROM agenda;


CREATE TABLE evento(id int not null auto_increment,
nome varchar(50) not null,
endereco varchar(100) not null,
dataEvent timestamp not null,
fkAgenda int not null,
FOREIGN KEY(fkAgenda) references  agenda(id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY(id));

DESC evento;

SELECT * FROM evento;

DROP TABLE evento;

INSERT INTO evento(nome, endereco, dataEvent,fkAgenda)values('Evento Teste','Vargem grande',NOW() ,2);


/*ARRUMANDO TIME ZONE*/
#https://elias.praciano.com/2015/07/como-ajustar-o-fuso-horario-ou-timezone-no-mysql/

SET time_zone='America/Sao_Paulo';

