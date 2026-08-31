DROP DATABASE IF exists auditoria_usuario_producto_in4am;
create database auditoria_usuario_producto_in4am;
use auditoria_usuario_producto_in4am;

create table Users(
	name varchar(50) not null check(length(name) <= 50),
	lastname varchar(50) not null check(length(lastname) <= 50),
	email varchar(50) not null check(length(email) <= 50),
	user varchar(25) not null check( length(user) <= 25),
	password varchar(35) not null check(length(password) <= 35),
	id_user varchar(36) not null,
	constraint pk_users primary key (id_user)
);

Delimiter $$
	create procedure sp_create_users(in name_p varchar(50),
                                     in lastname_p varchar(50),
                                     in email_p varchar(50),
                                     in user_p varchar(20),
                                     in password_p varchar(35))
	begin
		insert into Users( name,lastname, email, user, password,id_user)
			values( name_p , lastname_p , email_p , user_p , password_p,uuid());
	end $$
Delimiter ;

Delimiter $$
	create procedure sp_find_user_by_user_or_email(in user_or_email_p varchar(50))
	begin
		select id_user, name, lastname, email, user, password
			from Users
			where user = user_or_email_p or email = user_or_email_p;
	end $$
Delimiter ;

Delimiter $$
	create procedure sp_login(in user_or_email_p varchar(50), in password_p varchar(35))
	begin
		select id_user, name, lastname, email, user, password
			from Users
			where (user = user_or_email_p or email = user_or_email_p)
			and password = password_p;
	end $$
Delimiter ;

 select * from Users;