 -- DDL: Criação da Tabela `category`

-- DROP TABLE IF EXISTS category CASCADE; -- Descomente esta linha se quiser recriar a tabela e remover quaisquer dependências (como a FK em 'products')

CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- DML: Inserção de Dados na Tabela `category`

-- Importante: Os IDs aqui devem corresponder aos IDs usados na tabela 'products'
-- se você for adicionar uma chave estrangeira.
INSERT INTO category (id, name) VALUES
(1, 'Lanche'),
(2, 'Acompanhamento'),
(3, 'Bebidas'),
(4, 'Sobremesa')
ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name; -- Isso ajuda a lidar com IDs já existentes

-- Se você precisar de mais categorias no futuro, adicione-as aqui:
-- INSERT INTO category (name) VALUES ('Café da Manhã');

CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INTEGER NOT NULL,
    category BIGINT NOT NULL, -- IDs das categorias: 1 (Lanche), 2 (Acompanhamento), 3 (Bebidas), 4 (Sobremesa)
    description TEXT,
    image VARCHAR(255),
    active BOOLEAN NOT NULL DEFAULT TRUE
);

-- DML: Inserção de Dados na Tabela `products` (4 itens por categoria)

-- Lanches (Category ID: 1)
INSERT INTO product (name, price, category, description, image, active) VALUES
('Cheeseburger Clássico', 3500, 1, 'Hambúrguer de carne, queijo cheddar, alface, tomate e molho especial.', 'https://example.com/images/cheeseburger.jpg', TRUE),
('Sanduíche Natural de Frango', 2800, 1, 'Pão integral, frango desfiado, ricota, cenoura e mix de folhas.', 'https://example.com/images/sanduiche-natural.jpg', TRUE),
('Pizza Calabresa (fatia)', 1500, 1, 'Fatia generosa de pizza com molho de tomate, queijo e calabresa.', 'https://example.com/images/pizza-calabresa.jpg', TRUE),
('Hot Dog Simples', 1200, 1, 'Salsicha, pão e mostarda.', 'https://example.com/images/hotdog.jpg', TRUE);

-- Acompanhamentos (Category ID: 2)
INSERT INTO product (name, price, category, description, image, active) VALUES
('Batata Frita Grande', 1800, 2, 'Porção generosa de batatas fritas crocantes.', 'https://example.com/images/batata-frita.jpg', TRUE),
('Anéis de Cebola', 2000, 2, 'Porção de anéis de cebola empanados e fritos.', 'https://example.com/images/aneis-cebola.jpg', TRUE),
('Salada Caesar', 2200, 2, 'Alface americana, croutons, queijo parmesão e molho Caesar.', 'https://example.com/images/salada-caesar.jpg', TRUE),
('Pão de Queijo (6 unidades)', 1000, 2, 'Seis unidades de pão de queijo quentinho e crocante.', 'https://example.com/images/pao-queijo.jpg', TRUE);

-- Bebidas (Category ID: 3)
INSERT INTO product (name, price, category, description, image, active) VALUES
('Refrigerante Lata', 800, 3, 'Lata de refrigerante de sua escolha (Coca-Cola, Guaraná, Soda).', 'https://example.com/images/refrigerante.jpg', TRUE),
('Suco Natural de Laranja', 1200, 3, 'Suco natural de laranja, fresco e sem adição de açúcares.', 'https://example.com/images/suco-laranja.jpg', TRUE),
('Água Mineral 500ml', 500, 3, 'Garrafa de água mineral sem gás.', 'https://example.com/images/agua-mineral.jpg', TRUE),
('Café Expresso', 700, 3, 'Pequena xícara de café expresso.', 'https://example.com/images/cafe-expresso.jpg', TRUE);

-- Sobremesas (Category ID: 4)
INSERT INTO product (name, price, category, description, image, active) VALUES
('Brownie com Sorvete', 2500, 4, 'Brownie de chocolate quentinho servido com uma bola de sorvete de creme.', 'https://example.com/images/brownie-sorvete.jpg', TRUE),
('Mousse de Maracujá', 1800, 4, 'Cremosa mousse de maracujá com cobertura de sementes da fruta.', 'https://example.com/images/mousse-maracuja.jpg', TRUE),
('Pudim de Leite Condensado', 1600, 4, 'Fatia de pudim de leite condensado com calda de caramelo.', 'https://example.com/images/pudim.jpg', TRUE),
('Açaí Pequeno', 2000, 4, 'Creme de açaí puro com granola e banana.', 'https://example.com/images/acai.jpg', TRUE);

-- DDL: Criação da Tabela `customer`

-- DROP TABLE IF EXISTS customer CASCADE; -- Descomente esta linha se quiser recriar a tabela do zero

CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE, -- Email pode ser nulo, mas se preenchido, deve ser único
    cpf BIGINT NOT NULL UNIQUE -- CPF é um campo obrigatório e deve ser único
);

-- DML: Inserção de Dados na Tabela `customer`

INSERT INTO customer (name, email, cpf) VALUES
('João Silva', 'joao.silva@email.com', 12345678901),
('Maria Souza', 'maria.souza@email.com', 98765432109),
('Carlos Ferreira', NULL, 11223344556), -- Exemplo com email nulo
('Ana Costa', 'ana.costa@email.com', 65432109876);


-- DDL: Criação da Tabela `order`

-- DROP TABLE IF EXISTS "order" CASCADE; -- Descomente esta linha se quiser recriar a tabela do zero.
                                        -- Note as aspas duplas, pois 'order' é uma palavra reservada em SQL.

CREATE TABLE "orderqueue" (
    id BIGSERIAL PRIMARY KEY,
    idcustomer BIGINT NOT NULL,
    step VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    price INTEGER NOT NULL,
    details TEXT,
    CONSTRAINT fk_customer
        FOREIGN KEY (idcustomer)
        REFERENCES customer (id)
);

-- DML: Inserção de Dados na Tabela `order`

INSERT INTO "orderqueue" (idcustomer, step, date, time, price, details) VALUES
(1, 'Recebido', '2025-06-04', '14:30:00', 7500, '1 Cheeseburger Clássico, 1 Batata Frita Grande, 1 Refrigerante Lata'),
(2, 'Recebido', '2025-06-04', '15:15:00', 4300, '1 Sanduíche Natural de Frango, 1 Suco Natural de Laranja, 1 Mousse de Maracujá');

