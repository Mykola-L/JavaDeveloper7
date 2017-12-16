use homework_1_2;

select projects.*, avg(developers.salary) average_salary
from developers, projects
where developers.project_id = projects.id and projects.id in (
select id
from projects
where cost in (
select min(projects.cost)
from projects)
);