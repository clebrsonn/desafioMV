CREATE TABLE users(
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	pwd VARCHAR(150) NOT NULL
);
--senha: admin
INSERT INTO users (id, name, email, pwd) values (1, 'Administrador', 'admin@admin.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
--senha: maria
INSERT INTO users (id, name, email, pwd) values (2, 'Maria Silva', 'maria@admin.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');
