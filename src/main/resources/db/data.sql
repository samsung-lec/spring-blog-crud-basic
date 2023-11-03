insert into user_tb(username, password, email, img_url, created_at, updated_at) values('ssar', '$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy', 'ssar@nate.com', '/images/1.jpg', now(), now());
insert into user_tb(username, password, email, img_url, created_at, updated_at) values('cos', '$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy', 'cos@nate.com', '/images/1.jpg',now(), now());
insert into user_tb(username, password, email, img_url, created_at, updated_at) values('love', '$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy', 'love@nate.com', '/images/1.jpg',now(), now());

insert into post_tb(title, content, user_id, created_at, updated_at) values('title 1', 'content 1', 1, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 2', 'content 2', 1, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 3', 'content 3', 1, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 4', 'content 4', 2, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 5', 'content 5', 2, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 6', 'content 6', 2, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 7', 'content 7', 2, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 8', 'content 8', 2, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 9', 'content 9', 2, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 10', 'content 10', 2, now(), now());
insert into post_tb(title, content, user_id, created_at, updated_at) values('title 11', 'content 11', 2, now(), now());

insert into reply_tb(post_id,user_id,comment, created_at, updated_at) values(1, 1, 'comment 1',now(), now());
insert into reply_tb(post_id,user_id,comment, created_at, updated_at) values(1, 1, 'comment 2',now(), now());
insert into reply_tb(post_id,user_id,comment, created_at, updated_at) values(1, 2, 'comment 3',now(), now());
insert into reply_tb(post_id,user_id,comment, created_at, updated_at) values(1, 2, 'comment 4',now(), now());
insert into reply_tb(post_id,user_id,comment, created_at, updated_at) values(1, 3, 'comment 5',now(), now());
insert into reply_tb(post_id,user_id,comment, created_at, updated_at) values(2, 1, 'comment 6',now(), now());