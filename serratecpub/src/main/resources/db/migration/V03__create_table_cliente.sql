CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	email VARCHAR(100) UNIQUE,
	nome VARCHAR(150),
	cpf VARCHAR(14) UNIQUE,
	telefone VARCHAR(15) UNIQUE,
	data_nascimento DATE,
	endereco_id BIGINT,
	
	CONSTRAINT fk_endereco FOREIGN KEY (endereco_id)
	REFERENCES endereco(id)
)