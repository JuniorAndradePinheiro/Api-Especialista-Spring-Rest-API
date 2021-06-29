insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Brasileira');
insert into cozinha (id, nome) values (4, 'Francesa');
insert into cozinha (id, nome) values (5, 'Japonesa');
insert into cozinha (id, nome) values (6, 'Arabe');
insert into cozinha (id, nome) values (7, 'Italiana');
insert into cozinha (id, nome) values (8, 'Chinesa');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Ongbak', 5, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('IndiaFood', 5, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante do Ze', 2, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Paris', 6, 4);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Shogunato', 10, 5);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Homem Bomba', 0, 6);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Zio', 6, 7);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('ChinaFood', 6, 8);

insert into forma_pagamento (id, descricao) values (1, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'Credito');
insert into forma_pagamento (id, descricao) values (3, 'Debito');
insert into forma_pagamento (id, descricao) values (4, 'Cheque');

insert into cidade (nome) values ('Praia Grande');
insert into cidade (nome) values ('Santos');
insert into cidade (nome) values ('Sao Vicente');
insert into cidade (nome) values ('Foz do Iguaçu');
insert into cidade (nome) values ('Blumenal');
insert into cidade (nome) values ('Goianaia');
insert into cidade (nome) values ('Salvador'); 
insert into cidade (nome) values ('Pouso Alegre');
      

insert into estado (id, nome) values(1,'SP');
insert into estado (id, nome) values(2,'RS');
insert into estado (id, nome) values(3,'GO');
insert into estado (id, nome) values(4,'BA');
insert into estado (id, nome) values(5,'MG');