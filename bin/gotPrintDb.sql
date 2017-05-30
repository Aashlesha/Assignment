
CREATE DATABASE gotPringDB;

use gotPringDB

drop table if exists note_user;

CREATE TABLE note_user(
    user_id int,
    email varchar(50),
    password varchar(30),
    create_time timestamp,
    update_date_time timestamp,
    role varchar(50) NOT NULL,
    PRIMARY KEY(user_id)
);

drop table if exists note;

CREATE TABLE note (

    note_id int,
    title varchar(50),
    note text,
    user_id int,
    create_time timestamp ,
    update_date_time timestamp,
    PRIMARY KEY(note_id)
);

ALTER TABLE  note
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES note_user(user_id);

    
INSERT INTO note_user (user_id, email, password, create_time,update_date_time,role) VALUES (1, "aashlesha@gp.com", "got@print", NOW(),NOW(),"ROLE_ADMIN");
INSERT INTO note_user (user_id, email, password, create_time,update_date_time,role) VALUES (2, "drew@gp.com", "got@print2", NOW(),NOW(),"ROLE_ADMIN");
INSERT INTO note_user (user_id, email, password, create_time,update_date_time,role) VALUES (3, "henric@gp.com", "got@print3", NOW(),NOW(),"ROLE_USER");
INSERT INTO note_user (user_id, email, password, create_time,update_date_time,role) VALUES (4, "vinayak@gp.com", "got@print4", NOW(),NOW(),"ROLE_USER");

INSERT INTO note (note_id, title, note, user_id,create_time,update_date_time) VALUES (1, "Got Print", "Note : Got Print",1, NOW(),NOW());


