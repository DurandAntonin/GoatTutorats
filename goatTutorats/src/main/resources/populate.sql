-- ============================================
-- INSERT TUTORS
-- ============================================
INSERT INTO tutor (id, username, password) VALUES
                                               (0x8601E490E414435AB6CC0BCF8C811035, 'antonin', '$2a$12$KGyJjUaI4luzFPP23owztOzIDHfTI37HCRsJozcKTjfNFZDt4C/Uq'),
                                               (0x92A7B451C9D34437B5AA7E2D53B011A2, 'dupont', '$2a$12$KGyJjUaI4luzFPP23owztOzIDHfTI37HCRsJozcKTjfNFZDt4C/Uq');

-- ============================================
-- INSERT APPRENTICES
-- ============================================
INSERT INTO apprentice (id, first_name, last_name, email, phone, programme, majeure, tutor_id) VALUES
                                                                                                   (0xA101E490E414435AB6CC0BCF8C811001, 'Alice', 'Martin', 'alice.martin@example.com', '0601020301', 'Informatique', 'IA', 0x8601E490E414435AB6CC0BCF8C811035),
                                                                                                   (0xA101E490E414435AB6CC0BCF8C811002, 'Benoit', 'Dupont', 'benoit.dupont@example.com', '0601020302', 'Informatique', 'Sécurité', 0x8601E490E414435AB6CC0BCF8C811035),
                                                                                                   (0xA101E490E414435AB6CC0BCF8C811003, 'Claire', 'Durand', 'claire.durand@example.com', '0601020303', 'Mathématiques', 'Data', 0x8601E490E414435AB6CC0BCF8C811035),
                                                                                                   (0xA101E490E414435AB6CC0BCF8C811004, 'David', 'Morel', 'david.morel@example.com', '0601020304', 'Informatique', 'IA', 0x8601E490E414435AB6CC0BCF8C811035),
                                                                                                   (0xA101E490E414435AB6CC0BCF8C811005, 'Eva', 'Bernard', 'eva.bernard@example.com', '0601020305', 'Mathématiques', 'Data', 0x8601E490E414435AB6CC0BCF8C811035),
                                                                                                   (0xA101E490E414435AB6CC0BCF8C811006, 'François', 'Petit', 'francois.petit@example.com', '0601020306', 'Informatique', 'Dév Web', 0x92A7B451C9D34437B5AA7E2D53B011A2),
                                                                                                   (0xA101E490E414435AB6CC0BCF8C811007, 'Gaëlle', 'Roux', 'gaelle.roux@example.com', '0601020307', 'Informatique', 'Systèmes', 0x92A7B451C9D34437B5AA7E2D53B011A2),
                                                                                                   (0xA101E490E414435AB6CC0BCF8C811008, 'Hugo', 'Garnier', 'hugo.garnier@example.com', '0601020308', 'Mathématiques', 'Stats', 0x92A7B451C9D34437B5AA7E2D53B011A2),
                                                                                                   (0xA101E490E414435AB6CC0BCF8C811009, 'Inès', 'Lambert', 'ines.lambert@example.com', '0601020309', 'Informatique', 'Réseaux', 0x92A7B451C9D34437B5AA7E2D53B011A2),
                                                                                                   (0xA101E490E414435AB6CC0BCF8C81100A, 'Julien', 'Renard', 'julien.renard@example.com', '0601020310', 'Mathématiques', 'Stats', 0x92A7B451C9D34437B5AA7E2D53B011A2);

-- ============================================
-- INSERT COMPANIES
-- ============================================
INSERT INTO company (id, name) VALUES
                                   (0xC101E490E414435AB6CC0BCF8C811001, 'TechCorp'),
                                   (0xC101E490E414435AB6CC0BCF8C811002, 'DataSolutions');

-- ============================================
-- INSERT MENTORS
-- ============================================
INSERT INTO mentor (id, first_name, last_name, position, email, phone, remarks, company_id) VALUES
                                                                                                (0xB201E490E414435AB6CC0BCF8C811001, 'Olivier', 'Girard', 'Chef de projet', 'olivier.girard@techcorp.com', '0602030401', 'Encadre les alternants en dev', 0xC101E490E414435AB6CC0BCF8C811001),
                                                                                                (0xB201E490E414435AB6CC0BCF8C811002, 'Sophie', 'Leclerc', 'Responsable data', 'sophie.leclerc@datasolutions.com', '0602030402', 'Encadre les alternants data', 0xC101E490E414435AB6CC0BCF8C811002);

-- ============================================
-- INSERT ACADEMIC YEARS
-- ============================================
INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
                                                                               (0xD301E490E414435AB6CC0BCF8C811001, '2024-09-01', 0xA101E490E414435AB6CC0BCF8C811001, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001),
                                                                               (0xD301E490E414435AB6CC0BCF8C811002, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811006, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

-- ============================================
-- INSERT MISSIONS
-- ============================================
INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
                                                                               (0xE401E490E414435AB6CC0BCF8C811001, 'Java, Spring, REST', 'Développeur backend', 'Mise en place d’API REST sécurisées', 0xD301E490E414435AB6CC0BCF8C811001),
                                                                               (0xE401E490E414435AB6CC0BCF8C811002, 'Python, ML, DataViz', 'Data Scientist', 'Projet de visualisation de données', 0xD301E490E414435AB6CC0BCF8C811002);

-- ============================================
-- INSERT NOTES
-- ============================================
INSERT INTO note (id, comments, academic_year_id) VALUES
                                                      (0xE501E490E414435AB6CC0BCF8C811001, 'Excellent travail et progression continue', 0xD301E490E414435AB6CC0BCF8C811001),
                                                      (0xE501E490E414435AB6CC0BCF8C811002, 'Bon niveau technique, amélioration possible sur la communication', 0xD301E490E414435AB6CC0BCF8C811002);

-- ============================================
-- INSERT VISITS
-- ============================================
INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
                                                                     (0xE601E490E414435AB6CC0BCF8C811001, '2024-11-15', 'Présentiel', 'Visite très positive, bonnes conditions de travail', 0xD301E490E414435AB6CC0BCF8C811001),
                                                                     (0xE601E490E414435AB6CC0BCF8C811002, '2025-12-02', 'Distanciel', 'Entretien par visio, suivi régulier assuré', 0xD301E490E414435AB6CC0BCF8C811002);

-- ============================================
-- INSERT REPORT EVALUATIONS
-- ============================================
INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
                                                                                               (0xE701E490E414435AB6CC0BCF8C811001, 'Développement d’un système d’authentification sécurisé', 'Cybersécurité', 16.5, 'Très bon rapport, bien structuré', 0xD301E490E414435AB6CC0BCF8C811001),
                                                                                               (0xE701E490E414435AB6CC0BCF8C811002, 'Analyse prédictive des ventes avec Python', 'Data Science', 17.0, 'Travail pertinent et bien documenté', 0xD301E490E414435AB6CC0BCF8C811002);

-- ============================================
-- INSERT ORAL EXAMS
-- ============================================
INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
                                                                              (0xE801E490E414435AB6CC0BCF8C811001, '2025-06-20', 17.5, 'Présentation claire et maîtrisée', 0xD301E490E414435AB6CC0BCF8C811001),
                                                                              (0xE801E490E414435AB6CC0BCF8C811002, '2026-06-18', 16.0, 'Très bon oral, quelques imprécisions techniques', 0xD301E490E414435AB6CC0BCF8C811002);
