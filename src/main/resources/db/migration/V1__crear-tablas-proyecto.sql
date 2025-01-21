-- Crear la tabla 'perfiles'
CREATE TABLE perfiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_perfil VARCHAR(255) NOT NULL
);

-- Crear la tabla 'usuarios', con una clave foránea hacia la tabla 'perfiles'
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    clave VARCHAR(255) NOT NULL,
    perfil_id BIGINT,
    CONSTRAINT fk_perfil
        FOREIGN KEY (perfil_id)
        REFERENCES perfiles(id)
        ON DELETE CASCADE
);

-- Crear la tabla 'cursos', con una columna 'categoria' que es un ENUM
CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Crear la tabla 'topicos', con claves foráneas hacia 'usuarios' y 'cursos'
CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    usuario_id BIGINT,
    curso_id BIGINT,
    CONSTRAINT fk_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE SET NULL,
    CONSTRAINT fk_curso
        FOREIGN KEY (curso_id)
        REFERENCES cursos(id)
        ON DELETE SET NULL
);

-- Crear la tabla 'respuestas', con claves foráneas hacia 'topicos' y 'usuarios'
CREATE TABLE respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico_id BIGINT,
    fecha_respuesta DATETIME NOT NULL,
    usuario_id BIGINT,
    solucion BOOLEAN NOT NULL,
    CONSTRAINT fk_topico
        FOREIGN KEY (topico_id)
        REFERENCES topicos(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_usuario_respuesta
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE SET NULL
);