CREATE DATABASE listavip;
USE listavip;

CREATE TABLE convidado(id INT NOT NULL AUTO_INCREMENT, 
nome varchar(25),
email varchar(40),
telefone int,PRIMARY KEY(id));

INSERT INTO convidado(nome,email,telefone)VALUES('Bruno','bruno@bruno.com', 999999999);

SELECT * FROM convidado;

DROP TABLE convidado;

CREATE DATABASE startmeup;

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
FOREIGN KEY(fkUsuario) references  usuario(id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY(id));

DROP TABLE agenda;

INSERT INTO agenda(nome, fkUsuario)VALUES("Agenda Google", 1);

SELECT * FROM agenda;