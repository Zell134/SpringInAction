create table if not exists Ingredient (
id varchar(4) not null,
name varchar(25) not null,
type varchar(10) not null
);

create table if not exists Taco (
id identity,
name varchar(50) not null,
created_at timestamp not null
);

create table if not exists Taco_Ingredients (
taco_id bigint not null,
ingredients_id varchar(4) not null
);

alter table Taco_Ingredients
add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients
add foreign key (ingredients_id) references Ingredient(id);

create table if not exists Taco_Order (
id identity,
name varchar(50) not null,
street varchar(50) not null,
city varchar(50) not null,
state varchar(20) not null,
zip varchar(10) not null,
cc_Number varchar(16) not null,
cc_Expiration varchar(5) not null,
ccCVV varchar(3) not null,
placed_At timestamp not null
);

create table if not exists Taco_Order_Taco (
Order_id bigint not null,
Taco_id bigint not null
);

alter table Taco_Order_Taco
add foreign key (Order_id) references Taco_Order(id);
alter table Taco_Order_Taco
add foreign key (taco_id) references Taco(id);
