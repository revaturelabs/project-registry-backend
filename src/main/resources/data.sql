-- insert roles
insert into role (type) values ('admin');
insert into role (type) values ('user');

-- insert accounts
insert into account(username, role_id) values('william', 1);
insert into account(username, role_id) values('kenny', 1);
insert into account(username, role_id) values('william', 1);
insert into account(username, role_id) values('Bob', 2);

-- insert statuses
insert into status(name) values ('IN_ITERATION');
insert into status(name) values ('CODE_FREEZE');
insert into status(name) values ('CODE_REVIEW');
insert into status(name) values ('NEEDS_CLEANUP');
insert into status(name) values ('READY_FOR_ITERATION');

-- insert projects
insert into project(description, name, created_by, status) values('rideforce project', 'rideforce', 3, 3);

-- insert into project_account_jt
insert into project_account_jt(project_id, account_id) values(1,3);

-- insert phase
insert into phase (kind, description) values ('BACKLOG_GENERATED', 'CoE has completed the iterations backlog, awaiting trainer approval');
insert into phase (kind, description) values ('TRAINER_APPROVED', 'Trainer has reviewed backlog and approves of scope and domain');
insert into phase (kind, description) values ('HANDOFF_SCHEDULED', 'Scheduled the Handoff meeting to introduce P3');
insert into phase (kind, description) values ('RESOURCE_ALLOCATION', 'Cloud Resources, access to Kanban boards and GH Organization and Repositories');
insert into phase (kind, description) values ('CHECKPOINT_MEETING', 'Progress meeting at halfway point finished');
insert into phase (kind, description) values ('CODE_REVIEW', 'Code Review of work completed in Iteration has commenced');
insert into phase (kind, description) values ('COMPLETE', 'Iteration has completed, ready to merge into upstream');

