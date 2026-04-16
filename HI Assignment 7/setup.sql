-- -----------------------
-- CREATE TABLES
-- -----------------------
CREATE TABLE Patient (
    patient_id INT PRIMARY KEY,
    full_name VARCHAR(100),
    birth_year INT,
    status VARCHAR(20)
);

CREATE TABLE Provider (
    provider_id INT PRIMARY KEY,
    provider_name VARCHAR(100),
    department VARCHAR(50)
);

CREATE TABLE Visit (
    visit_id INT PRIMARY KEY,
    patient_id INT,
    provider_id INT,
    visit_year INT,
    visit_cost DECIMAL(8,2),
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    FOREIGN KEY (provider_id) REFERENCES Provider(provider_id)
);

CREATE TABLE Treatment (
    treatment_id INT PRIMARY KEY,
    visit_id INT,
    treatment_type VARCHAR(50),
    treatment_cost DECIMAL(8,2),
    FOREIGN KEY (visit_id) REFERENCES Visit(visit_id)
);

-- -----------------------
-- INSERT SAMPLE DATA
-- -----------------------
INSERT INTO Patient VALUES
(1,'Alice Green',1985,'Active'),
(2,'Bob Smith',1970,'Inactive'),
(3,'Carol Jones',1992,'Active'),
(4,'Dan White',1965,'Active'),
(5,'Eva Black',1985,'Inactive');

INSERT INTO Provider VALUES
(101,'Dr. Adams','Cardiology'),
(102,'Dr. Brown','General'),
(103,'Dr. Chen','Endocrinology');

INSERT INTO Visit VALUES
(1001,1,102,2024,200.00),
(1002,1,101,2024,500.00),
(1003,2,101,2023,400.00),
(1004,3,103,2024,350.00),
(1005,4,101,2024,700.00),
(1006,5,102,2023,150.00);

INSERT INTO Treatment VALUES
(9001,1001,'Lab',50.00),
(9002,1001,'Consult',150.00),
(9003,1002,'Surgery',500.00),
(9004,1004,'Lab',100.00),
(9005,1004,'Medication',50.00),
(9006,1005,'Surgery',600.00);

-- -----------------------
-- CHECK TABLE STRUCTURE
-- -----------------------
PRAGMA table_info(Patient);
PRAGMA table_info(Provider);
PRAGMA table_info(Visit);
PRAGMA table_info(Treatment);
