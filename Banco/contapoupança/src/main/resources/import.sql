INSERT INTO `user` ( nome, email, senha) VALUES ( 'Admin User', 'admin@example.com', '$2a$10$7W0yZk8eQb5fZc2vRzM.EO8bHcyS10h6n1s9Olcf6hRk0pI2jOGBe');
INSERT INTO `user` ( nome, email, senha) VALUES ( 'Finac User', 'admin2@example.com', '$bdgnbfnghnfghnghngnhgfmhjmklh.lj;jçl.6n1s9Olcf6hRk0pI2jOGBe');


INSERT INTO Titular (id, cpf, nome, telefone) VALUES (1, '12345678901', 'João da Silva', '11999998888');
INSERT INTO Titular (id, cpf, nome, telefone) VALUES (2, '98765432100', 'Maria Oliveira', '21988887777');

INSERT INTO Conta ( id, numero, titular_cpf , saldo, dtype) VALUES ( 1, 1001, 1, 5000.00, 'corrente');
INSERT INTO Conta ( id, numero, titular_cpf , saldo, dtype) VALUES ( 2, 1002, 2, 4000.00, 'corrente');