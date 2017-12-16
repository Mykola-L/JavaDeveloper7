USE projectmanagementdb;

SELECT
  projects.id,
  projects.name,
  sum(developers.salary) sum_salary
FROM developers
  JOIN projects ON projects.id = developers.project_id
GROUP BY projects.name
ORDER BY sum_salary DESC
LIMIT 1;