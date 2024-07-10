drop table product;
drop table discount_card;

create table product
(id bigserial not null,
description varchar(50) not null,
price decimal not null,
quantity_in_stock integer not null,
wholesale_product boolean not null,
    constraint pk_product primary key(id),
    constraint description_unique unique ("description"));

create table discount_card
(id bigserial not null,
number integer not null,
discount_amount smallint not null,
    constraint pk_discount_card primary key(id),
    constraint number_unique unique ("number"),
    constraint valid_discount_amount check (discount_amount >= 0 and discount_amount <= 100));

    insert into product(description, price, quantity_in_stock, wholesale_product)
values('Milk', 1.07, 10, true),
      ('Cream 400g', 2.71, 20, true),
      ('Yogurt 400g', 2.10, 7, true),
      ('Packed potatoes 1kg', 1.47, 30, false),
      ('Packed cabbage 1kg', 1.19, 15, false),
      ('Packed tomatoes 350g', 1.60, 50, false),
      ('Packed apples 1kg', 2.78, 18, false),
      ('Packed oranges 1kg', 3.20, 12, false),
      ('Packed bananas 1kg', 1.10, 25, true),
      ('Packed beef fillet 1kg', 12.8, 7, false),
      ('Packed pork fillet 1kg', 8.52, 14, false),
      ('Packed chicken breasts 1kgSour', 10.75, 18, false),
      ('Baguette 360g', 1.30, 10, true),
      ('Drinking water 1,51', 0.80, 100, false),
      ('Olive oil 500ml', 5.30, 16, false),
      ('Sunflower oil 11', 1.20, 12, false),
      ('Chocolate Ritter sport 100g', 1.10, 50, true),
      ('Paulaner 0,51', 1.10, 100, false),
      ('Whiskey Jim Beam 11', 13.99, 30, false),
      ('Whiskey Jack Daniels 11', 17.19, 20, false);

insert into discount_card(number, discount_amount)
values(1111, 3),
      (2222, 3),
      (3333, 4),
      (4444, 5),
      (1234, 2),
      (1235, 2),
      (1236, 2),
      (1237, 2),
      (1238, 2),
      (1239, 2);