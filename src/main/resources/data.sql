-- insert roles
insert into role (id, type) values (1, 'admin');
insert into role (id, type) values (2, 'user');

-- insert accounts
insert into account(id ,username,role) values(10001, 'william', 1);
insert into account(id ,username, role) values(10002, 'kenny', 1);
insert into account(id ,username, role) values(10003, 'william', 1);
insert into account(id ,username, role) values(10004, 'Bob', 2);

-- insert stacks
insert into stack(id, name) values (1, 'JWA');
insert into stack(id, name) values (2, 'Java with Microservices');
insert into stack(id, name) values (3, 'Java with React');

-- insert statuses
insert into status(id, name) values (1, 'IN_ITERATION');
insert into status(id, name) values (2, 'CODE_FREEZE');
insert into status(id, name) values (3, 'CODE_REVIEW');
insert into status(id, name) values (4, 'NEEDS_CLEANUP');
insert into status(id, name) values (5, 'READY_FOR_ITERATION');

-- insert projects
insert into project(id, description, name, created_by, stack, status) values(10001, 'rideforce project', 'rideforce', 10003, 2, 3);

-- insert into project_account_jt
insert into project_account_jt(project_id, account_id) values(10001,10003);
