

drop table user.user;
create table if not exists user (
	id int auto_increment primary key,
    name varchar(255) not null,
    age int,
    email varchar(255)
);
select * from user;
insert into user (name, age, email) values 
("foo", 20, "foo@test.com"),
("bar", 22, "bar@test.com"),
("barz", 24, "barz@test.com");
