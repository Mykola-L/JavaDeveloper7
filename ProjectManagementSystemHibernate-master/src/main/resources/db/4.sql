USE projectmanagementdb;

ALTER TABLE projects
  ADD cost INT NOT NULL
  AFTER name;
SET SQL_SAFE_UPDATES = 0; -- if there are some errors in safe mode: comment out

UPDATE projects p
SET p.cost = (SELECT sum(d.salary)
              FROM developers d
              WHERE d.project_id = p.id
);