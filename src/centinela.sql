-- Crea la base con charset utf8 de 4 bytes. Versión 0900 del algoritmo de coincidencia Unicode. No sensible a acentos. No sensible a capitalización.
CREATE DATABASE centinela CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE centinela;

-- Información sobre un usuario
CREATE TABLE USUARIO (
    id INTEGER PRIMARY KEY AUTO_INCREMENT, -- Con el uso de un integer las tablas no pesarán tanto.
    nombre VARCHAR(100) NOT NULL, -- Nombre real del usuario.
    passwd  VARCHAR(25) NOT NULL, -- Contraseña de usuario.
    email VARCHAR(320) UNIQUE NOT NULL, -- Se usará éste como identificador real del usuario.
    administrador TINYINT NOT NULL -- 0 Si es usuario normal, 1 si es administrador.
);

-- Información sobre el hardware blackbox
CREATE TABLE BLACKBOX (
    id INTEGER PRIMARY KEY, -- Con el uso de un integer las tablas no pesarán tanto.
    nombre_unico VARCHAR(20) UNIQUE NOT NULL, -- Se usará éste como identificador real de la blackbox.
    nombre VARCHAR(32) NOT NULL, -- Nombre con que el usuario podrá identificar a su blackbox.
    ip_v4 VARCHAR(15) NOT NULL, -- Dirección ip v4. Se guardará en formato aaa.bbb.ccc.ddd
    passwd VARCHAR(25) NOT NULL, -- Palabra de seguridad que usará blackbox para cifrar su comunicación.
    info_extra VARCHAR(255), -- Información extra que el usuario crea conveniente incluir.
    user_id INTEGER NOT NULL, -- Identificador de usuario la que pertenece la blackbox.
    nombre_D2 VARCHAR(32) NOT NULL DEFAULT 'OUT_0', -- Nombre con que el usuario se podrá referir a dicha salida.
    nombre_D3 VARCHAR(32) NOT NULL DEFAULT 'OUT_1', -- Nombre con que el usuario se podrá referir a dicha salida.
    nombre_D4 VARCHAR(32) NOT NULL DEFAULT 'OUT_2', -- Nombre con que el usuario se podrá referir a dicha salida.
    nombre_D5 VARCHAR(32) NOT NULL DEFAULT 'OUT_3', -- Nombre con que el usuario se podrá referir a dicha salida.
    nombre_A0 VARCHAR(32) NOT NULL DEFAULT 'IN_0', -- Nombre con que el usuario se podrá referir a dicha entrada.
    nombre_A1 VARCHAR(32) NOT NULL DEFAULT 'IN_1', -- Nombre con que el usuario se podrá referir a dicha entrada.
    nombre_A2 VARCHAR(32) NOT NULL DEFAULT 'IN_2', -- Nombre con que el usuario se podrá referir a dicha entrada.
    nombre_A3 VARCHAR(32) NOT NULL DEFAULT 'IN_3', -- Nombre con que el usuario se podrá referir a dicha entrada.
    
    FOREIGN KEY(user_id)
        REFERENCES USUARIO(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE IO_PORT (
    id INTEGER PRIMARY KEY AUTO_INCREMENT, -- Identificador de la captura de datos.
    blackbox_id INTEGER NOT NULL, -- Identificador a la blackbox a la que pertenece esta captura de datos.
    fecha_hora DATETIME NOT NULL, -- Fecha en que se produjo esta captura de datos.
    D2 TINYINT, -- Estado de la salida digital.
    D3 TINYINT, -- Estado de la salida digital.
    D4 TINYINT, -- Estado de la salida digital.
    D5 TINYINT, -- Estado de la salida digital.
    A0 TINYINT, -- Estado de la entrada analógica.
    A1 TINYINT, -- Estado de la entrada analógica.
    A2 TINYINT, -- Estado de la entrada analógica.
    A3 TINYINT, -- Estado de la entrada analógica.
    
    FOREIGN KEY(blackbox_id)
        REFERENCES BLACKBOX(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE ALARMA (
    id INTEGER PRIMARY KEY AUTO_INCREMENT, -- Identificador de esta alarma.
    blackbox_id INTEGER NOT NULL, -- Identificador de la blackbox que lanzó la alarma.
    fecha_hora DATETIME NOT NULL, -- Fecha y hora en que se produjo la alarma.
    puerto CHAR(2) NOT NULL, -- Puerto que hizo saltar la alarma.
    nivel TINYINT NOT NULL, -- Nivel de la alarma: 1 (Alto), 2 (Crítico)
    
    FOREIGN KEY(blackbox_id)
        REFERENCES BLACKBOX(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Creación del usuario para manejo desde la aplicación
CREATE USER 'centinelapp'@'localhost' IDENTIFIED BY 'B0h3m!';
GRANT SELECT, INSERT, UPDATE, DELETE ON centinela.* TO 'centinelapp'@'localhost';

-- Creación del usuario administrador
INSERT INTO USUARIO (nombre, passwd, email, administrador)
VALUES ('Administrador', 'admin', 'miquelfs1981@gmail.com', 1);
