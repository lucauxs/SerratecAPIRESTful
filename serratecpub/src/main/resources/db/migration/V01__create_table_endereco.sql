CREATE TABLE endereco(
	id SERIAL PRIMARY KEY,
	cep VARCHAR(15),
	rua VARCHAR(50),
	bairro VARCHAR(50),
	cidade VARCHAR(50),
	numero VARCHAR(10),
	complemento VARCHAR(50),
	uf VARCHAR(2)
)