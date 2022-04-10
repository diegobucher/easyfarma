CREATE TABLE IF NOT EXISTS medicamentos (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(250) NOT NULL,
    data_fabricacao DATE NOT NULL,
    data_vencimento DATE NOT NULL,
    quantidade INT
)

CREATE TABLE IF NOT EXISTS pessoa (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    tipo_pessoa INT NOT NULL,
    email VARCHAR(250) NOT NULL,
    numero VARCHAR(250) NOT NULL,
    endereco VARCHAR(250) NOT NULL
)