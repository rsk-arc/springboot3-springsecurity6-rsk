create table IF NOT EXISTS languages (
	id serial, 
	messagecontent varchar(255), 
	messagekey varchar(255), 
	locale varchar(255), 
	primary key (id));	