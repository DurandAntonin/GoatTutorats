SET NAMES utf8mb4;

-- ============================================
-- USERS
-- ============================================
INSERT INTO user (id, username, password) VALUES
                                              (0x8601E490E414435AB6CC0BCF8C811011, 'dupont', '$2a$12$KGyJjUaI4luzFPP23owztOzIDHfTI37HCRsJozcKTjfNFZDt4C/Uq'),
                                              (0x8601E490E414435AB6CC0BCF8C811012, 'handre', '$2a$12$KGyJjUaI4luzFPP23owztOzIDHfTI37HCRsJozcKTjfNFZDt4C/Uq');

-- ============================================
-- ROLES
-- ============================================
INSERT INTO user_role (id, role_name) VALUES
                                        (0x9001E490E414435AB6CC0BCF8C811001, 'ROLE_USER'),
                                        (0x9001E490E414435AB6CC0BCF8C811002, 'ROLE_TUTOR');

-- ============================================
-- USER_ROLES
-- ============================================
INSERT INTO user_roles (user_id, role_id) VALUES
                                              (0x8601E490E414435AB6CC0BCF8C811011, 0x9001E490E414435AB6CC0BCF8C811001),
                                              (0x8601E490E414435AB6CC0BCF8C811011, 0x9001E490E414435AB6CC0BCF8C811002),
                                              (0x8601E490E414435AB6CC0BCF8C811012, 0x9001E490E414435AB6CC0BCF8C811001),
                                              (0x8601E490E414435AB6CC0BCF8C811012, 0x9001E490E414435AB6CC0BCF8C811002);

-- ============================================
-- TUTORS
-- ============================================
INSERT INTO tutor (user_id) VALUES
                               (0x8601E490E414435AB6CC0BCF8C811011),
                               (0x8601E490E414435AB6CC0BCF8C811012);

-- ============================================
-- APPRENTICES
-- ============================================
INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C81100B, 'Laura', 'Simon', 'laura.simon@example.com', '0601020311', 'Informatique', 'IA', 0x8601E490E414435AB6CC0BCF8C811012);

-- Year
INSERT INTO year (id, year) VALUES
    (0xE601E490E414435AB6CC0BCF8C811118, '2025-09-01');

-- Visit
INSERT INTO visit (id, date, format, comments) VALUES
    (0xE601E490E414435AB6CC0BCF8C811001, '2025-06-15', 0, 'Bonne intégration');

-- ReportEvaluation
INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments) VALUES
    (0xE701E490E414435AB6CC0BCF8C811001, 'Thesis A', 'Topic A', 16.0, 'Bonne qualité');

-- OralExam
INSERT INTO oral_exam (id, date, final_grade, comments) VALUES
    (0xE801E490E414435AB6CC0BCF8C811001, '2025-06-30', 17.5, 'Présentation claire');

INSERT INTO company (id, name, address, access_info) VALUES
    (0xC001E490E414435AB6CC0BCF8C811002, 'SAP', "6 allée", 'Infos accès');

INSERT INTO mentor (id, first_name, last_name, position, email, phone, remarks) VALUES
    (0xB201E490E414435AB6CC0BCF8C811001, 'Olivier', 'Girard', 'Chef de projet', 'olivier.girard@techcorp.com', '0602030401', 'Encadre les alternants en dev');

INSERT INTO academic_year (id, year_id, study_level, apprentice_id, company_id, mentor_id, visit_id, report_evaluation_id, oral_exam_id) VALUES
    (0xD401E490E414435AB6CC0BCF8C811011, 0xE601E490E414435AB6CC0BCF8C811118, 'ING3', 0xA101E490E414435AB6CC0BCF8C81100B, 0xC001E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811001, 0xE601E490E414435AB6CC0BCF8C811001, 0xE701E490E414435AB6CC0BCF8C811001, 0xE801E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
                                                                              (0xF101E490E414435AB6CC0BCF8C811001, 'Java, Spring Boot', 'Backend Developer', 'Développer API REST', 0xD401E490E414435AB6CC0BCF8C811011),
                                                                              (0xF101E490E414435AB6CC0BCF8C811002, 'SQL, Hibernate', 'Database Engineer', 'Optimisation des requêtes', 0xD401E490E414435AB6CC0BCF8C811011);

INSERT INTO note (id, author, comments, academic_year_id) VALUES
                                            (0xE501E490E414435AB6CC0BCF8C811001, 'Dupont', 'Excellent travail', 0xD401E490E414435AB6CC0BCF8C811011);
