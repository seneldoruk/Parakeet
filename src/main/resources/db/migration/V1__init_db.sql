create sequence post_seq start with 1 increment by 50;
create table parakeet_user (password varchar(255), username varchar(255) not null, primary key (username));
create table post (id bigint not null, parent_id bigint, post_date timestamp(6), text varchar(255), user_username varchar(255), primary key (id));
create table user_followers (followed_id varchar(255) not null, follower_id varchar(255) not null, primary key (followed_id, follower_id));
alter table if exists post add constraint FK5yhuvatu7cubfxyltetys1c3n foreign key (parent_id) references post;
alter table if exists post add constraint FKlhdyvm43f0my3fihgnw4s428j foreign key (user_username) references parakeet_user;
alter table if exists user_followers add constraint FK66nmyxxlq2l9vnnfiiv93wsm9 foreign key (follower_id) references parakeet_user;
alter table if exists user_followers add constraint FKrwudyy5j17pp99f1sg8rxsroa foreign key (followed_id) references parakeet_user;
