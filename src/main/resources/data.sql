INSERT INTO tb_users(login, password)
	VALUES ('user1', '$2a$05$djRHtinp5lJlKFb9omQHduQrWjOAy/fMBtRhZ0x1UqmlVcTwxZHNy');
INSERT INTO tb_roles(user_id, role)
    VALUES (1, 'ROLE_ADMIN');