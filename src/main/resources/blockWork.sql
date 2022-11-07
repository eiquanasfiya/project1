create table corsarineri.block(
 	id integer primary key NOT NULL,
 	blockValue integer NOT NULL,
 	date timestamp without time zone NOT NULL
);


ALTER TABLE corsarineri.indizio
ADD block_id integer NOT NULL;

insert into corsarineri.block(id,blockValue,date) values(1,1,'2022-11-03T00:00:00.000+0000');

Alter table corsarineri.indizio
Add photo boolean;

