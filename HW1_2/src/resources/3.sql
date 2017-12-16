use homework_1_2;

select skills.*, sum(developers.salary) as total_amount
from developers
	join developers_skills on developers.id = developers_skills.developer_id
    join skills on skills.id = developers_skills.skill_id
where skills.name like 'Java';