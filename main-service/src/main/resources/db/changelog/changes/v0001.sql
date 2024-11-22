create table tasks (
        id bigint generated by default as identity,
        owner_id bigint not null,
        executor_id bigint,
        name varchar(50) not null unique,
        commit varchar(255),
        description varchar(255),
        priority_task varchar(255) check (priority_task in ('HIGH','MEDIUM','LOW')),
        status_task varchar(255) check (status_task in ('PENDING','PROGRESS','COMPLETED')),
        primary key (id)
    );
