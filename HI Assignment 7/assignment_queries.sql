SELECT 
    v.visit_id AS VisitID,
    v.visit_cost AS VisitCost,
    SUM(t.treatment_cost) AS TreatmentTotal,
    v.visit_cost - SUM(t.treatment_cost) AS CostDifference
FROM Visit v
JOIN Treatment t ON v.visit_id = t.visit_id
GROUP BY v.visit_id, v.visit_cost
HAVING v.visit_cost <> SUM(t.treatment_cost);

SELECT 
    p.patient_id,
    p.full_name,
    SUM(v.visit_cost) AS total_cost
FROM Patient p
JOIN Visit v ON p.patient_id = v.patient_id
WHERE p.status = 'Active' AND v.visit_year = 2024
GROUP BY p.patient_id, p.full_name
HAVING COUNT(v.visit_id) > 1 AND SUM(v.visit_cost) > 600;

SELECT 
    pr.department,
    SUM(v.visit_cost) AS total_revenue
FROM Visit v
JOIN Provider pr ON v.provider_id = pr.provider_id
WHERE v.visit_year = 2024
GROUP BY pr.department
HAVING SUM(v.visit_cost) = (
    SELECT MAX(dept_total)
    FROM (
        SELECT SUM(v2.visit_cost) AS dept_total
        FROM Visit v2
        JOIN Provider pr2 ON v2.provider_id = pr2.provider_id
        WHERE v2.visit_year = 2024
        GROUP BY pr2.department
    ) AS dept_sums
);

SELECT p.patient_id, p.full_name
FROM Patient p
WHERE EXISTS (
    SELECT 1 FROM Visit v1 WHERE v1.patient_id = p.patient_id AND v1.visit_year = 2023
)
AND EXISTS (
    SELECT 1 FROM Visit v2 WHERE v2.patient_id = p.patient_id AND v2.visit_year = 2024
)
AND NOT EXISTS (
    SELECT 1 FROM Visit v3
    JOIN Treatment t ON v3.visit_id = t.visit_id
    WHERE v3.patient_id = p.patient_id AND t.treatment_type = 'Surgery'
);

SELECT 
    pr.department,
    SUM(CASE WHEN t.treatment_type = 'Lab' THEN 1 ELSE 0 END) AS lab_count,
    SUM(CASE WHEN t.treatment_type = 'Surgery' THEN 1 ELSE 0 END) AS surgery_count,
    COUNT(t.treatment_id) AS total_treatments
FROM Provider pr
JOIN Visit v ON pr.provider_id = v.provider_id
JOIN Treatment t ON v.visit_id = t.visit_id
GROUP BY pr.department;

SELECT v.*
FROM Visit v
JOIN Patient p ON v.patient_id = p.patient_id
WHERE p.patient_id IN (
    SELECT patient_id FROM Patient WHERE status = 'Active'
);

CREATE VIEW PatientAgeCategory AS
SELECT 
    patient_id,
    full_name,
    CASE 
        WHEN 2024 - birth_year < 40 THEN 'Under 40'
        WHEN 2024 - birth_year BETWEEN 40 AND 59 THEN '40-59'
        ELSE '60+'
    END AS age_group
FROM Patient;

-- Test the view
SELECT * FROM PatientAgeCategory;
