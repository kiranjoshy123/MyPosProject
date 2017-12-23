CREATE TABLE category (
	
	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

CREATE TABLE userinfo (
	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	user_name VARCHAR(100),
	address VARCHAR(255),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY(id),
);

CREATE TABLE product (
	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES userinfo(id),	
);	


CREATE TABLE cart (
	id IDENTITY,
	user_id int,
	grand_total DECIMAL(10,2),
	cart_lines int,
	CONSTRAINT fk_cart_user_id FOREIGN KEY (user_id ) REFERENCES userinfo (id),
	CONSTRAINT pk_cart_id PRIMARY KEY (id)
);


INSERT INTO category (name, description,image_url,is_active) VALUES ('Grocery', 'This is description for Grocery category!', 'CAT_1.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Electronic', 'This is description for Electronic category!', 'CAT_2.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Vegetables', 'This is description for Vegetables category!', 'CAT_3.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Fruits', 'This is description for Fruits category!', 'CAT_4.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Dress', 'This is description for Dress category!', 'CAT_5.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Bakery', 'This is description for Bakery category!', 'CAT_6.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Liquor', 'This is description for Liquor category!', 'CAT_7.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Misc', 'This is description for Misc category!', 'CAT_8.png', true);



INSERT INTO userinfo 
(first_name, last_name, role, enabled, password, email, user_name, address, contact_number) 
VALUES ('Virat', 'Kohli', 'ADMIN', true, '$2a$10$yvjrz1qy66/TQ8sigm4.b.knJRqnfc9cvqo2n5TAMR6JWLRnxm/GO', 'vk@gmail.com', 'viratKohli', 'Address', '9752435255');

INSERT INTO userinfo 
(first_name, last_name, role, enabled, password, email, user_name, address, contact_number) 
VALUES ('Ravindra', 'Jadeja', 'SUPPLIER', true, '$2a$10$Gb3J2Go.s5ZKHK4hSobRyuCZZC6w/wMdNetqzNtw.JR9oj4oo1EB.', 'rj@gmail.com', 'RavindraJadeja', 'Address', '9876363544');
INSERT INTO userinfo 
(first_name, last_name, role, enabled, password, email, user_name, address, contact_number) 
VALUES ('Ravichandra', 'Ashwin', 'SUPPLIER', true, '$2a$10$75SvWVBBP35esShVRoUsKOVhae.uxopQoFYt.wK0/oh0vJqrNIz3G',  'ra@gmail.com', 'RavichandraAshwin', 'Address', '9765243566');



INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC123DEFX', 'Tata-Tea-500g', 'Tata', 'This is one of the best tea available in the market right now!', 100, 50, true, 1, 2 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDDEF123DEFX', 'Tata-Salt-1kg', 'Tata', 'Tata Salt!', 20, 20, true, 1, 2 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDPQR123WGTX', 'Nescafe Classic-500g', 'Nestle', 'Nestle Coffee!', 500, 50, true, 1, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDMNO123PQRX', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 2, 2 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC1YZDEFX', 'Dell Latitude E6510', 'dell', 'This is one of the best laptop series from dell that can be used!', 48000, 5, true, 2, 3 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC123DEFY', 'iPhone 5s', 'apple', 'This is one of the best phone available in the market right now!', 18000, 5, true, 2, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDMNO124PQRX', 'Onion-1kg', 'STORE1', 'Onion-1kg!', 40, 30, true, 3, 2 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC2YZDEFX', 'Potato-1kg', 'STORE1', 'Potato-1kg!', 20, 50, true, 3, 3 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC124DEFY', 'Tomato-2kg', 'STORE1', 'Tomato-2kg!', 40, 50, true, 3, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDMNO125PQRX', 'Banana-1kg', 'STORE1', 'Banana-1kg!', 30, 100, true, 4, 2 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC3YZDEFX', 'Apple-1kg', 'STORE1', 'Apple-1kg!', 150, 500, true, 4, 3 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC125DEFY', 'Orange-2kg', 'STORE1', 'Orange-2kg!', 180, 500, true, 4, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDMNO126PQRX', 'Men White Regular Fit Shirt', 'Arrow', 'Men White Regular Fit Shirt!', 800, 100, true, 5, 2 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC4YZDEFX', 'Men Red Regular Casual Shirt', 'Tommy Hilfiger', 'Men Red Regular Casual Shirt!', 2000, 50, true, 5, 3 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC126DEFY', 'Basics Red & Blue Sweater', 'Basics', 'Basics Red & Blue Sweater!', 900, 500, true, 5, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDMNO127PQRX', 'Tea Cake-Marble-250gm', 'STORE1', 'Tea Cake-Marble!', 100, 10, true, 6, 2 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC5YZDEFX', 'Plum Cake-Rich Fruit-500 gm', 'STORE1', 'Plum Cake-Rich Fruit-500 gm!', 300, 50, true, 6, 3 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC127DEFY', 'Cup Cakes-Assorted-6 pcs', 'STORE1', 'Cup Cakes-Assorted-6 pcs!', 300, 10, true, 6, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDMNO128PQRX', 'Chivas Regal 12Year', 'STORE1', 'Chivas Regal 12Year!', 4000, 10, true, 7, 2 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC6YZDEFX', 'Bira 91 White', 'Bira', 'Bira 91 White!', 200, 50, true, 7, 3 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC128DEFY', 'Kingfisher Premium', 'UBL', 'Kingfisher Premium!', 230, 10, true, 7, 2 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC129DEFY', 'Carlsberg Premium', 'Carlsberg', 'Carlsberg Premium!', 230, 0, true, 7, 2 );