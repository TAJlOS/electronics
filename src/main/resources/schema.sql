CREATE TABLE IF NOT EXISTS cpu
(
    id long auto_increment primary key ,
    name varchar(50) not null,
    producer varchar(25) not null,
    socket varchar(25) not null,
    base_frequency int not null,
    boost_frequency int not null,
    n_core int not null ,
    n_thread int not null ,
    tdp int not null ,
    l3_cache int not null ,
    overclocking boolean not null ,
    integrated_graphic_card boolean not null ,
    graphic_card varchar(50),
    img varchar(100) not null,
    price double not null
);

CREATE TABLE IF NOT EXISTS  user
(
    id long auto_increment primary key ,
    username varchar(50) not null,
    password varchar(60) not null
);

CREATE TABLE IF NOT EXISTS order_table
(
    id     long auto_increment primary key,
    user_id long not null ,
    sum double not null ,
    status varchar(25) not null ,
    registration datetime not null ,
    foreign key (user_id) references user (id)
);

CREATE TABLE IF NOT EXISTS order_detail
(
    id     long auto_increment primary key,
    order_id long not null ,
    product_id long not null ,
    amount int not null ,
    foreign key (order_id) references order_table (id),
    foreign key (product_id) references cpu (id)
);

