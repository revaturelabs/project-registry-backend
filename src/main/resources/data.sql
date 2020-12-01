-- insert roles
insert into role (type) values ('admin');
insert into role (type) values ('user');

-- insert accounts
insert into account(username,role) values('william', 1);
insert into account(username, role) values('kenny', 1);
insert into account(username, role) values('william', 1);
insert into account(username, role) values('Bob', 2);

-- insert stacks
insert into stack(name) values ('JWA');
insert into stack(name) values ('Java with Microservices');
insert into stack(name) values ('Java with React');

-- insert statuses
insert into status(name) values ('IN_ITERATION');
insert into status(name) values ('CODE_FREEZE');
insert into status(name) values ('CODE_REVIEW');
insert into status(name) values ('NEEDS_CLEANUP');
insert into status(name) values ('READY_FOR_ITERATION');

-- insert projects
insert into project(description, name, created_by, stack, status) values('rideforce project', 'rideforce', 3, 2, 3);

-- insert into project_account_jt
insert into project_account_jt(project_id, account_id) values(1,3);
