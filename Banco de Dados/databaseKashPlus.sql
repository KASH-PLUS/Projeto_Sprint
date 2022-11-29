CREATE USER 'kashUser'@'localhost' IDENTIFIED BY 'kash';
GRANT ALL PRIVILEGES ON dbkashplus.* TO 'kashUser'@'localhost';
create database dbkashplus;
use dbkashplus;

CREATE TABLE tbEmpresa(
cnpj CHAR(14) PRIMARY KEY
,nome VARCHAR(100)
,email VARCHAR(100)
,telefoneFixo CHAR(10)
,telefoneCelular CHAR(11)
);

CREATE TABLE tbUsuario(
idUsuario INT PRIMARY KEY AUTO_INCREMENT
,fkEmpresa CHAR(14), FOREIGN KEY(fkEmpresa) REFERENCES tbEmpresa(cnpj)
,nome VARCHAR(100)
,login VARCHAR(100)
,senha VARCHAR(100)
,cargo CHAR(3)
);

CREATE TABLE tbMaquina(
serialNumber VARCHAR(30) PRIMARY KEY
,fkEmpresa CHAR(14), FOREIGN KEY(fkEmpresa) REFERENCES tbEmpresa(cnpj)
,nome VARCHAR(100)
,cep CHAR(8)
,cidade VARCHAR(100)
,regiao VARCHAR(15)
);

CREATE TABLE tbComponente(
idComponente INT PRIMARY KEY AUTO_INCREMENT
,fkMaquina VARCHAR(30), FOREIGN KEY(fkMaquina) REFERENCES tbMaquina(serialNumber)
,tipo VARCHAR(100)
,metrica VARCHAR(100)
,metricaMaxima INT
)
;

CREATE TABLE tbRegistro(
idRegistro INT PRIMARY KEY AUTO_INCREMENT
,fkComponente INT, FOREIGN KEY(fkComponente) REFERENCES tbComponente(idComponente)
,registro VARCHAR(50)
,dataHora DATETIME
);

CREATE TABLE tbProcesso(
idProcesso INT PRIMARY KEY AUTO_INCREMENT
,fkMaquina VARCHAR(30), FOREIGN KEY(fkMaquina) REFERENCES tbMaquina(serialNumber)
,processo VARCHAR(60)
,usoCpu VARCHAR(45)
,usoRam VARCHAR(45)
,dataHora DATETIME
);

-- Criando Views --

CREATE VIEW vwMaquina AS
    SELECT 
        tbEmpresa.nome AS 'Empresa'
        ,cnpj AS 'Cnpj'
        ,tbMaquina.nome AS 'Maquina'
        ,serialNumber AS 'NumeroSerial'
        ,cep AS 'Cep'
        ,tipo AS Componente
        ,metricaMaxima as valorMaximo
    FROM
        tbEmpresa
            JOIN
        tbMaquina ON cnpj = fkEmpresa
			JOIN
		tbComponente ON fkMaquina = serialNumber
;
        
CREATE VIEW vwConsumo AS
    SELECT 
		idRegistro AS 'ID'
        ,tbMaquina.nome AS 'Maquina'
        ,serialNumber AS 'NumeroSerial'
        ,tipo AS 'Componente'
        ,metrica AS 'Metrica'
        ,registro AS 'Registro'
        ,dataHora AS 'Horario'
    FROM
        tbMaquina
            JOIN
		tbComponente ON fkMaquina = serialNumber
			JOIN
		tbRegistro ON fkComponente = idComponente
;

CREATE VIEW vwFuncionario AS
    SELECT 
		tbEmpresa.nome AS 'Empresa'
		,tbUsuario.nome AS 'Funcionario'
        ,cargo AS 'Cargo'
        ,login AS 'Login'
    FROM
        tbEmpresa
            JOIN
        tbUsuario ON cnpj = fkEmpresa
;

INSERT INTO tbEmpresa VALUES ('75885017000172', 'banco', 'banco@email.com', '', '');
SELECT * FROM tbEmpresa;
INSERT INTO tbUsuario VALUES (null, '75885017000172', 'adm', 'adm@email.com', '123', 'ADM');
SELECT * FROM tbUsuario;
INSERT INTO tbMaquina VALUES ('BRJ123', '75885017000172', 'Maquina1', '09541270', 'São Caetano do Sul', 'Sudeste');
SELECT * FROM tbMaquina;
INSERT INTO tbComponente VALUES (null, 'BRJ123', 'cpu', '%', null);
INSERT INTO tbComponente VALUES (null, 'BRJ123', 'disco', 'mb', null);
INSERT INTO tbComponente VALUES (null, 'BRJ123', 'ram', 'gb', null);
SELECT * FROM tbComponente;
SELECT * FROM tbRegistro;
SELECT * FROM tbProcesso;

SELECT tbRegistro.dataHora, registro, processo, usoCpu FROM tbRegistro, tbComponente, tbProcesso WHERE fkComponente = idComponente 
    AND tipo = 'cpu' AND tbComponente.fkMaquina = 'BRJ123' AND tbRegistro.dataHora = tbProcesso.dataHora ORDER BY idRegistro DESC;
    
SELECT tbRegistro.dataHora, registro, processo, usoRam FROM tbRegistro, tbComponente, tbProcesso WHERE fkComponente = idComponente 
    AND tipo = 'ram' AND tbComponente.fkMaquina = 'BRJ123' AND tbRegistro.dataHora = tbProcesso.dataHora ORDER BY idRegistro DESC;
