CREATE TABLE location(id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(250));
CREATE TABLE department(id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(250),location_id int(11),FOREIGN KEY F_LOCATION(location_id) REFERENCES location(id));
CREATE TABLE category(id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(250),department_id int(11),FOREIGN KEY F_LOCATION(department_id) REFERENCES department(id));
CREATE TABLE subcategory(id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(250),category_id int(11),FOREIGN KEY F_LOCATION(category_id) REFERENCES category(id));
CREATE TABLE sku_data(id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(250),location_id int(11),department_id int(11),category_id int(11),subcategory_id int(11),FOREIGN KEY F_LOCATION(location_id) REFERENCES location(id),FOREIGN KEY F_DEPARTMENT(department_id) REFERENCES department(id),
FOREIGN KEY F_CATEGORY(category_id) REFERENCES category(id),FOREIGN KEY F_SUBCATEGORY(subcategory_id) REFERENCES subcategory(id));
