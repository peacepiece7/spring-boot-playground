

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

create table if not exists book (
   isbn text auto_increment primary key,
   name varchar(255) not null,
   category varchar(255) not null,
   BigDecimal bigint
);

insert into book (name, category, BigDecimal) values
("자바의 정석", "프로그래밍", 112233),
("리액트 딥 다이브", "프로그래밍", 223344),
("RNN 과 트랜스포머 아키택처 이해햐기", "프로그래밍", 334455)