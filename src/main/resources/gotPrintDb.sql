
drop table if exists note_user;

CREATE TABLE note_user(
    user_id int,
    email varchar(50),
    password varchar(30),
    create_time timestamp without time zone,
    update_date_time timestamp without time zone,
    PRIMARY KEY(user_id)
);

drop table if exists note;
CREATE TABLE note (

    note_id int,
    title varchar(50),
    note text,
    user_id int,
    create_time timestamp without time zone,
    update_date_time timestamp without time zone,
    PRIMARY KEY(note_id)
);

ALTER TABLE ONLY note



