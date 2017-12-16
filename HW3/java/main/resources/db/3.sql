USE projectmanagementdb;

SELECT
  skills.*,
  sum(developers.salary) AS total_amount
FROM developers
  JOIN developers_skills ON developers.id = developers_skills.developer_id
  JOIN skills ON skills.id = developers_skills.skill_id
WHERE skills.name LIKE 'Java';