INSERT INTO tb_users(login, password)
	VALUES ('user1', '$2a$05$djRHtinp5lJlKFb9omQHduQrWjOAy/fMBtRhZ0x1UqmlVcTwxZHNy'),
	       ('user2', '$2a$05$99OQ6ZxAkPriNJe.SNchU.VSdNzgcOKbAqB7CTpu18ee3C97kUAye');
INSERT INTO tb_roles(user_id, role)
    VALUES (1, 'ROLE_APPLICATION_ADMIN'),
           (2, 'ROLE_ADMIN');