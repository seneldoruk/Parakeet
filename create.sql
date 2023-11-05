create table parakeet_user (password varchar(255), username varchar(255) not null, primary key (username));
create table parakeet_user_followed_users (followed_users_username varchar(255) not null unique, parakeet_user_username varchar(255) not null);
alter table if exists parakeet_user_followed_users add constraint FKhoapfa0ydlpyjk3heh7m0radv foreign key (followed_users_username) references parakeet_user;
alter table if exists parakeet_user_followed_users add constraint FKn7jcvfsl7wyko1u0xg780bw2j foreign key (parakeet_user_username) references parakeet_user;
