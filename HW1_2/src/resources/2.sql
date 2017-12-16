use homework_1_2;

select projects.id, projects.name, sum(developers.salary) sum_salary
from developers
	join projects on projects.id = developers.project_id
group by projects.name
order by sum_salary desc
limit 1;