

drop table user.user;

create schema user;

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

CREATE TABLE IF NOT EXISTS book (
   isbn VARCHAR(20) PRIMARY KEY,  -- Assuming ISBN is a string, manually inserted
   name VARCHAR(255) NOT NULL,
   category VARCHAR(255) NOT NULL,
   price DECIMAL(10, 2) -- Example of using a decimal for monetary values
);

INSERT INTO book (isbn, name, category, price) VALUES
("112233c", "자바의 정석", "프로그래밍", 112233),
("112233d", "리액트 딥 다이브", "프로그래밍", 223344),
("212233c", "RNN 과 트랜스포머 아키택처 이해하기", "프로그래밍", 334455);
