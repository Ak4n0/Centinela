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
    id INTEGER PRIMARY KEY AUTO_INCREMENT, -- Con el uso de un integer las tablas no pesarán tanto.
    identificador_unico VARCHAR(20) UNIQUE NOT NULL, -- Se usará éste como identificador real de la blackbox. El hardware usará este valor como credencial.
    nombre VARCHAR(32), -- Nombre con que el usuario podrá identificar a su blackbox.blackbox
    passwd VARCHAR(25) NOT NULL, -- Palabra de seguridad que usará blackbox para cifrar su comunicación.
    info_extra VARCHAR(255), -- Información extra que el usuario crea conveniente incluir.
    usuario_id INTEGER NOT NULL, -- Identificador de usuario la que pertenece la blackbox.
    nombre_O0 VARCHAR(32) DEFAULT 'OUT_0', -- Nombre con que el usuario se podrá referir a dicha salida.
    nombre_O1 VARCHAR(32) DEFAULT 'OUT_1',
    nombre_O2 VARCHAR(32) DEFAULT 'OUT_2',
    nombre_O3 VARCHAR(32) DEFAULT 'OUT_3',
    nombre_I0 VARCHAR(32) DEFAULT 'IN_0', -- Nombre con que el usuario se podrá referir a dicha entrada.
    nombre_I1 VARCHAR(32) DEFAULT 'IN_1',
    nombre_I2 VARCHAR(32) DEFAULT 'IN_2',
    nombre_I3 VARCHAR(32) DEFAULT 'IN_3',
    unidades_I0 VARCHAR(15), -- Unidades de la medición de dicha entrada, por ejemplo: V, A, ºC, cm...
    unidades_I1 VARCHAR(15),
    unidades_I2 VARCHAR(15),
    unidades_I3 VARCHAR(15),
    func_trans_I0 VARCHAR(1024) DEFAULT 'return val;', -- Cuerpo de la función de transferencia para javascript. Este cuerpo espera que la función
    func_trans_I1 VARCHAR(1024) DEFAULT 'return val;', --   tenga un argumento llamado val, por ejemplo 'funcTrans(val) { ... }'.
    func_trans_I2 VARCHAR(1024) DEFAULT 'return val;', --   El cuerpo más sencillo sería 'return val;'
    func_trans_I3 VARCHAR(1024) DEFAULT 'return val;', --   Blackbox devuelve un dato de 0 a 255, esta función debe pasar dicho valor a la medición real.
    func_trans_inv_I0 VARCHAR(1024) DEFAULT 'return val;', -- La función inversa sirve para transformar el dato real al rango 0 a 255 para,
    func_trans_inv_I1 VARCHAR(1024) DEFAULT 'return val;', --    por ejemplo, programar los límites de las alarmas. El procedimiento es parecido a la
    func_trans_inv_I2 VARCHAR(1024) DEFAULT 'return val;', --    función de transferencia
    func_trans_inv_I3 VARCHAR(1024) DEFAULT 'return val;',
    I0_bajo INT, -- Nivel de umbral bajo para activar la alarma
    I0_alto INT, -- Nivel de umbral alto para actiar la alarma
    I1_bajo INT, -- Nivel de umbral bajo para activar la alarma
    I1_alto INT, -- Nivel de umbral alto para actiar la alarma
    I2_bajo INT, -- Nivel de umbral bajo para activar la alarma
    I2_alto INT, -- Nivel de umbral alto para actiar la alarma
    I3_bajo INT, -- Nivel de umbral bajo para activar la alarma
    I3_alto INT, -- Nivel de umbral alto para actiar la alarma
    
    FOREIGN KEY(usuario_id)
        REFERENCES USUARIO(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Información sobre el estado de los puertos
CREATE TABLE IO_PORT (
    blackbox_id INTEGER NOT NULL, -- Identificador a la blackbox a la que pertenece esta captura de datos.
    fecha_hora DATETIME NOT NULL, -- Fecha en que se produjo esta captura de datos.
    O0 INT, -- Estado de la salida digital.
    O1 INT, -- Estado de la salida digital.
    O2 INT, -- Estado de la salida digital.
    O3 INT, -- Estado de la salida digital.
    I0 INT, -- Estado de la entrada analógica.
    I1 INT, -- Estado de la entrada analógica.
    I2 INT, -- Estado de la entrada analógica.
    I3 INT, -- Estado de la entrada analógica.
    
    PRIMARY KEY (blackbox_id, fecha_hora),
    
    FOREIGN KEY (blackbox_id)
        REFERENCES BLACKBOX(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Información sobre una alarma lanzada por blackbox
CREATE TABLE ALARMA (
    blackbox_id INTEGER NOT NULL, -- Identificador de la blackbox que lanzó la alarma.
    fecha_hora DATETIME NOT NULL, -- Fecha y hora en que se produjo la alarma.
    puerto CHAR(2) NOT NULL, -- Puerto que hizo saltar la alarma.
    valor TINYINT NOT NULL, -- Nivel de la alarma: 1 (Alto), 2 (Crítico)
    valor_umbral TINYINT NOT NULL, -- Valor que ha sobrepasado y activado la alarma
    
    PRIMARY KEY (blackbox_id, fecha_hora, puerto),
    
    FOREIGN KEY (blackbox_id)
        REFERENCES BLACKBOX(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Control de cambios de contraseñas de usuario
CREATE TABLE PETICION_NUEVO_PASSWD (
    id VARCHAR(64) PRIMARY KEY, -- SHA256 como identificador único
    usuario_id INTEGER UNIQUE NOT NULL, -- Identificador de usuario ha realizado la petición. Un usuario una petición.
    fecha_hora DATETIME NOT NULL, -- Fecha y hora en que se ha realizdo dicha petición.
    
    FOREIGN KEY(usuario_id)
        REFERENCES USUARIO(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Control de nuevos usuarios. Guarda cuándo un nuevo usuario se registra, así se podrá borrar si no ha validado su cuenta.
CREATE TABLE PETICION_NUEVO_USUARIO (
    id VARCHAR(64) PRIMARY KEY, -- SHA256 como identificador único
    usuario_id INTEGER UNIQUE NOT NULL, -- Id del usuario registrado
    fecha_hora DATETIME NOT NULL, -- momento en que se registra
    
    FOREIGN KEY(usuario_id)
        REFERENCES USUARIO(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
    
-- Creación del usuario administrador
INSERT INTO USUARIO (nombre, passwd, email, administrador)
VALUES ('Administrador', 'admin', 'centinela.soluciones@gmail.com', 1);

-- Creación del usuario para manejo desde la aplicación
CREATE USER 'centinelapp'@'localhost' IDENTIFIED BY 'B0h3m!';
GRANT SELECT, INSERT, UPDATE, DELETE ON centinela.* TO 'centinelapp'@'localhost';
