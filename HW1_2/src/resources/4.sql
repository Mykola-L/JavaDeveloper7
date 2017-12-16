use homework_1_2;

alter table projects add cost int not null after name;
SET SQL_SAFE_UPDATES = 0; -- if there are some errors in safe mode: comment out

update projects p set p.cost = (select sum(d.salary)
from developers d
where d.project_id = p.id
);