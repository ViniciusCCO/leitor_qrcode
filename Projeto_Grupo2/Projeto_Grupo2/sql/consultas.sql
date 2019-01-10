use vanilla;

select * from users;
select * from cnh;
select * from ctps;
select * from etitulo;
select * from `query` order by query_at desc;

-- Consulta para carregar cnh
SELECT u.`user_id`, u.`fullname`, u.`cpf`, u.`birth_date`,
	cnh.`document_id`, cnh.`type_document_id`, cnh.`register`, cnh.`category`, cnh.`city`, cnh.`uf`, cnh.`emission_at`,
    cnh.`due_date_at` FROM users u INNER JOIN cnh cnh ON u.`user_id` = cnh.`user_id`
		WHERE u.`user_id` = 1;
        
-- Consulta para carregar ctps
SELECT u.`user_id`, u.`fullname`, u.`cpf`, u.`birth_date`,
	ctps.`document_id`, ctps.`type_document_id`, ctps.`number`, ctps.`serie`, ctps.`birth_city`, ctps.`birth_uf`, ctps.`emission_at`
		FROM users u INNER JOIN ctps ctps ON u.`user_id` = ctps.`user_id` WHERE u.`user_id` = 1;
        
-- Consulta para carregar e-titulo
SELECT u.`user_id`, u.`fullname`, u.`cpf`, u.`birth_date`,
	et.`document_id`, et.`type_document_id`, et.`register`, et.`zone`, et.`section`, et.`city`, et.`uf`, et.`emission_at`
		FROM users u INNER JOIN etitulo et ON u.`user_id` = et.`user_id` WHERE u.`user_id` = 1;