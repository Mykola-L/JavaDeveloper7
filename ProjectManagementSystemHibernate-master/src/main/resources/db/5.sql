USE projectmanagementdb;

SELECT
  companies.name,
  customers.id,
  customers.name,
  sum(projects.cost)
FROM companies, projects, customers
WHERE projects.company_id = (SELECT companies.id
                             FROM companies
                             WHERE companies.id = projects.company_id)
      AND projects.customer_id = customers.id AND projects.company_id = companies.id
GROUP BY customer_id
ORDER BY sum(projects.cost)