USE vanilla;

-- DOUGLAS LIMA
INSERT INTO users(`fullname`, `cpf`, `birth_date`)
	VALUES ('Douglas de Oliveira Lima', '46918694847', '1997-05-16');

INSERT INTO cnh(`user_id`, `register`, `category`, `city`, `uf`, `emission_at`, `due_date_at`, `validate_code`)
	VALUES (1, '06611840909', 'AB', 'Osasco', 'SP', '2017-05-31', '2020-09-23', 'JOOOOOOJ');

INSERT INTO ctps(`user_id`, `number`, `serie`,  `birth_city`,  `birth_uf`,  `emission_at`, `validate_code`)
	VALUES (1, '95034', '00422-SP', 'Osasco', 'SP', '2016-04-29', 'ATAPOKKK');

INSERT INTO etitulo(`user_id`, `register`, `zone`,  `section`,  `city`,  `uf`,  `emission_at`, `validate_code`)
	VALUES (1, '422041510132', 332, '0132', 'Osasco', 'SP', '2018-10-07', 'PQFAZISU');

-- VINICIUS AMORIM
INSERT INTO users(`fullname`, `cpf`, `birth_date`)
	VALUES ('Vinicius Amorim', '12345678910', '1999-12-20');

INSERT INTO cnh(`user_id`, `register`, `category`, `city`, `uf`, `emission_at`, `due_date_at`, `validate_code`)
	VALUES (2, '10987654321', 'B', 'Capão Redondo', 'SP', '2018-12-01', '2022-09-23', '11111111');

INSERT INTO ctps(`user_id`, `number`, `serie`,  `birth_city`,  `birth_uf`,  `emission_at`, `validate_code`)
	VALUES (2, '12345', '00422-SP', 'Capão Redondo', 'SP', '2017-04-29', '22222222');

INSERT INTO etitulo(`user_id`, `register`, `zone`,  `section`,  `city`,  `uf`,  `emission_at`, `validate_code`)
	VALUES (2, '135790246813', 450, '0101', 'Campo Limpo', 'SP', '2017-10-07', '33333333');
    
-- Gabriel Mohamed
INSERT INTO users(`fullname`, `cpf`, `birth_date`)
	VALUES ('Gabriel Pereira', '78912345670', '1999-06-14');

INSERT INTO etitulo(`user_id`, `register`, `zone`,  `section`,  `city`,  `uf`,  `emission_at`, `validate_code`)
	VALUES (3, '321987654708', 602, '0197', 'São Paulo', 'SP', '2017-08-06', '99999999');