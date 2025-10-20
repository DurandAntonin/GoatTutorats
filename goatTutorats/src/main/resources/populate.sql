DROP DATABASE IF EXISTS Base_GoatTutorats;
CREATE DATABASE IF NOT EXISTS Base_GoatTutorats;
USE Base_GoatTutorats;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

    -- ============================================
    -- INSERT TUTORS
    -- ============================================
    INSERT INTO user (id, username, password) VALUES
                                                   (0x8601E490E414435AB6CC0BCF8C811035, 'michel', '$2a$12$KGyJjUaI4luzFPP23owztOzIDHfTI37HCRsJozcKTjfNFZDt4C/Uq'),
                                                   (0x92A7B451C9D34437B5AA7E2D53B011A2, 'dupont', '$2a$12$KGyJjUaI4luzFPP23owztOzIDHfTI37HCRsJozcKTjfNFZDt4C/Uq');

    INSERT INTO tutor (user_id) VALUES
                                    (0x8601E490E414435AB6CC0BCF8C811035),
                                    (0x92A7B451C9D34437B5AA7E2D53B011A2);

    INSERT INTO user_role (id, role_name) VALUES
                                              (0x8601E490E414435AB6CC0BCF8C811011, 'ROLE_TUTOR'),
                                              (0x92A7B451C9D34437B5AA7E2D53B01112, 'ROLE_USER');

    INSERT INTO user_roles (user_id, role_id) VALUES
                                              (0x8601E490E414435AB6CC0BCF8C811035, 0x8601E490E414435AB6CC0BCF8C811011),
                                              (0x8601E490E414435AB6CC0BCF8C811035, 0x92A7B451C9D34437B5AA7E2D53B01112),
                                              (0x92A7B451C9D34437B5AA7E2D53B011A2, 0x8601E490E414435AB6CC0BCF8C811011),
                                              (0x92A7B451C9D34437B5AA7E2D53B011A2, 0x92A7B451C9D34437B5AA7E2D53B01112);


    -- ============================================
    -- INSERT APPRENTICES
    -- ============================================
    INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
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


INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C81100B, 'Lina', 'Moreau', 'lina.moreau@example.com', '0610000000', 'Informatique', 'IA', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C8110B, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C81100B, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C8110B, 'Python, ML, DataViz', 'Data Scientist', 'Projet d''analyse et de visualisation de données.', 0xD301E490E414435AB6CC0BCF8C8110B);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C8110B, 'Très bon esprit d''analyse, autonome.', 0xD301E490E414435AB6CC0BCF8C8110B);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C8110B, '2025-10-15', 'Présentiel', 'Visite positive, apprentissage en milieu pro satisfaisant.', 0xD301E490E414435AB6CC0BCF8C8110B);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C8110B, 'Développement d’un module d''authentification', 'Cybersécurité', 13.0, 'Rapport structuré et pertinent.', 0xD301E490E414435AB6CC0BCF8C8110B);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C8110B, '2026-06-18', 14.0, 'Présentation claire et maîtrisée.', 0xD301E490E414435AB6CC0BCF8C8110B);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C81100, 'Mateo', 'Rossi', 'mateo.rossi@example.com', '0610000001', 'Mathématiques', 'Sécurité', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C8110C, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C81100, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C8110C, 'JavaScript, React, Frontend', 'Développeur frontend', 'Création d''une application web réactive.', 0xD301E490E414435AB6CC0BCF8C8110C);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C8110C, 'Bon niveau technique, améliorer la communication.', 0xD301E490E414435AB6CC0BCF8C8110C);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C8110C, '2025-11-05', 'Distanciel', 'Entretien par visio, suivi technique établi.', 0xD301E490E414435AB6CC0BCF8C8110C);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C8110C, 'Analyse prédictive des ventes', 'Data Science', 14.5, 'Analyse bien menée, quelques approfondissements possibles.', 0xD301E490E414435AB6CC0BCF8C8110C);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C8110C, '2026-06-20', 15.5, 'Très bon oral, quelques imprécisions techniques.', 0xD301E490E414435AB6CC0BCF8C8110C);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C81101, 'Sofia', 'Gomez', 'sofia.gomez@example.com', '0610000002', 'Électronique', 'Data', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C8110D, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C81101, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C8110D, 'Java, Spring, REST', 'Développeur backend', 'Développement d’API REST sécurisées.', 0xD301E490E414435AB6CC0BCF8C8110D);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C8110D, 'Excellente progression, curiosité technique notable.', 0xD301E490E414435AB6CC0BCF8C8110D);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C8110D, '2025-11-12', 'Présentiel', 'Bonne intégration dans l’équipe, missions claires.', 0xD301E490E414435AB6CC0BCF8C8110D);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C8110D, 'Outil d''automatisation de tests', 'Tests', 15.5, 'Très bon travail pratique, méthodologie correcte.', 0xD301E490E414435AB6CC0BCF8C8110D);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C8110D, '2026-06-22', 16.0, 'Bonne posture, améliorer les exemples pratiques.', 0xD301E490E414435AB6CC0BCF8C8110D);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C81102, 'Noah', 'Klein', 'noah.klein@example.com', '0610000003', 'Gestion', 'Dév Web', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C8110E, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C81102, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C8110E, 'Réseaux, Sécurité, Firewalls', 'Administrateur réseau', 'Audit et renforcement de la sécurité réseau.', 0xD301E490E414435AB6CC0BCF8C8110E);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C8110E, 'Sérieux et appliqué, pratique régulière nécessaire.', 0xD301E490E414435AB6CC0BCF8C8110E);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C8110E, '2025-12-02', 'Distanciel', 'Conditions de travail favorables, points à améliorer: encadrement.', 0xD301E490E414435AB6CC0BCF8C8110E);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C8110E, 'Optimisation de requêtes SQL', 'Bases de données', 16.0, 'Document complet mais amélioration sur clarté.', 0xD301E490E414435AB6CC0BCF8C8110E);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C8110E, '2026-06-24', 17.5, 'Clarté du discours, réflexes techniques solides.', 0xD301E490E414435AB6CC0BCF8C8110E);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C81103, 'Zara', 'Hassan', 'zara.hassan@example.com', '0610000004', 'Design', 'Réseaux', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C8110F, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C81103, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C8110F, 'SQL, ETL, DataPipeline', 'Ingénieur data', 'Mise en place de pipelines de données.', 0xD301E490E414435AB6CC0BCF8C8110F);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C8110F, 'Bon travail mais nécessite plus de rigueur sur la documentation.', 0xD301E490E414435AB6CC0BCF8C8110F);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C8110F, '2025-12-10', 'Présentiel', 'Suivi régulier, progression notable.', 0xD301E490E414435AB6CC0BCF8C8110F);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C8110F, 'Tableaux de bord interactifs', 'DataViz', 17.5, 'Bon rendu, intéressant et applicable.', 0xD301E490E414435AB6CC0BCF8C8110F);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C8110F, '2026-06-26', 14.0, 'Bonne préparation, approfondir certains points.', 0xD301E490E414435AB6CC0BCF8C8110F);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C81104, 'Kai', 'Watanabe', 'kai.watanabe@example.com', '0610000005', 'Informatique', 'Systèmes', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811010, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C81104, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811010, 'Python, ML, DataViz', 'Data Scientist', 'Projet d''analyse et de visualisation de données.', 0xD301E490E414435AB6CC0BCF8C811010);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811010, 'Très bon esprit d''analyse, autonome.', 0xD301E490E414435AB6CC0BCF8C811010);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811010, '2025-10-15', 'Distanciel', 'Visite positive, apprentissage en milieu pro satisfaisant.', 0xD301E490E414435AB6CC0BCF8C811010);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811010, 'Développement d’un module d''authentification', 'Cybersécurité', 13.5, 'Rapport structuré et pertinent.', 0xD301E490E414435AB6CC0BCF8C811010);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811010, '2026-06-18', 15.5, 'Présentation claire et maîtrisée.', 0xD301E490E414435AB6CC0BCF8C811010);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811045, 'Maya', 'Singh', 'maya.singh@example.com', '0610000006', 'Mathématiques', 'Stats', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811011, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811045, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811011, 'JavaScript, React, Frontend', 'Développeur frontend', 'Création d''une application web réactive.', 0xD301E490E414435AB6CC0BCF8C811011);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811011, 'Bon niveau technique, améliorer la communication.', 0xD301E490E414435AB6CC0BCF8C811011);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811011, '2025-11-05', 'Présentiel', 'Entretien par visio, suivi technique établi.', 0xD301E490E414435AB6CC0BCF8C811011);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811011, 'Analyse prédictive des ventes', 'Data Science', 14.0, 'Analyse bien menée, quelques approfondissements possibles.', 0xD301E490E414435AB6CC0BCF8C811011);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811011, '2026-06-20', 16.0, 'Très bon oral, quelques imprécisions techniques.', 0xD301E490E414435AB6CC0BCF8C811011);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811098, 'Lucas', 'Nguyen', 'lucas.nguyen@example.com', '0610000007', 'Électronique', 'IA', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811012, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811098, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811012, 'Java, Spring, REST', 'Développeur backend', 'Développement d’API REST sécurisées.', 0xD301E490E414435AB6CC0BCF8C811012);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811012, 'Excellente progression, curiosité technique notable.', 0xD301E490E414435AB6CC0BCF8C811012);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811012, '2025-11-12', 'Distanciel', 'Bonne intégration dans l’équipe, missions claires.', 0xD301E490E414435AB6CC0BCF8C811012);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811012, 'Outil d''automatisation de tests', 'Tests', 15.5, 'Très bon travail pratique, méthodologie correcte.', 0xD301E490E414435AB6CC0BCF8C811012);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811012, '2026-06-22', 17.5, 'Bonne posture, améliorer les exemples pratiques.', 0xD301E490E414435AB6CC0BCF8C811012);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811099, 'Amelia', 'O''Connor', 'amelia.oconnor@example.com', '0610000008', 'Gestion', 'Sécurité', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811013, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811099, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811013, 'Réseaux, Sécurité, Firewalls', 'Administrateur réseau', 'Audit et renforcement de la sécurité réseau.', 0xD301E490E414435AB6CC0BCF8C811013);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811013, 'Sérieux et appliqué, pratique régulière nécessaire.', 0xD301E490E414435AB6CC0BCF8C811013);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811013, '2025-12-02', 'Présentiel', 'Conditions de travail favorables, points à améliorer: encadrement.', 0xD301E490E414435AB6CC0BCF8C811013);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811013, 'Optimisation de requêtes SQL', 'Bases de données', 16.5, 'Document complet mais amélioration sur clarté.', 0xD301E490E414435AB6CC0BCF8C811013);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811013, '2026-06-24', 14.0, 'Clarté du discours, réflexes techniques solides.', 0xD301E490E414435AB6CC0BCF8C811013);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811075, 'Ethan', 'Scott', 'ethan.scott@example.com', '0610000009', 'Design', 'Data', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811014, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811075, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811014, 'SQL, ETL, DataPipeline', 'Ingénieur data', 'Mise en place de pipelines de données.', 0xD301E490E414435AB6CC0BCF8C811014);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811014, 'Bon travail mais nécessite plus de rigueur sur la documentation.', 0xD301E490E414435AB6CC0BCF8C811014);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811014, '2025-12-10', 'Distanciel', 'Suivi régulier, progression notable.', 0xD301E490E414435AB6CC0BCF8C811014);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811014, 'Tableaux de bord interactifs', 'DataViz', 17.0, 'Bon rendu, intéressant et applicable.', 0xD301E490E414435AB6CC0BCF8C811014);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811014, '2026-06-26', 15.5, 'Bonne préparation, approfondir certains points.', 0xD301E490E414435AB6CC0BCF8C811014);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811076, 'Yara', 'Morin', 'yara.morin@example.com', '0610000010', 'Informatique', 'Dév Web', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811015, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811076, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811015, 'Python, ML, DataViz', 'Data Scientist', 'Projet d''analyse et de visualisation de données.', 0xD301E490E414435AB6CC0BCF8C811015);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811015, 'Très bon esprit d''analyse, autonome.', 0xD301E490E414435AB6CC0BCF8C811015);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811015, '2025-10-15', 'Présentiel', 'Visite positive, apprentissage en milieu pro satisfaisant.', 0xD301E490E414435AB6CC0BCF8C811015);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811015, 'Développement d’un module d''authentification', 'Cybersécurité', 13.5, 'Rapport structuré et pertinent.', 0xD301E490E414435AB6CC0BCF8C811015);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811015, '2026-06-18', 16.0, 'Présentation claire et maîtrisée.', 0xD301E490E414435AB6CC0BCF8C811015);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811078, 'Omar', 'Elbaz', 'omar.elbaz@example.com', '0610000011', 'Mathématiques', 'Réseaux', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811016, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811078, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811016, 'JavaScript, React, Frontend', 'Développeur frontend', 'Création d''une application web réactive.', 0xD301E490E414435AB6CC0BCF8C811016);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811016, 'Bon niveau technique, améliorer la communication.', 0xD301E490E414435AB6CC0BCF8C811016);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811016, '2025-11-05', 'Distanciel', 'Entretien par visio, suivi technique établi.', 0xD301E490E414435AB6CC0BCF8C811016);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811016, 'Analyse prédictive des ventes', 'Data Science', 14.5, 'Analyse bien menée, quelques approfondissements possibles.', 0xD301E490E414435AB6CC0BCF8C811016);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811016, '2026-06-20', 17.5, 'Très bon oral, quelques imprécisions techniques.', 0xD301E490E414435AB6CC0BCF8C811016);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811079, 'Ivy', 'Park', 'ivy.park@example.com', '0610000012', 'Électronique', 'Systèmes', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811017, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811079, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811017, 'Java, Spring, REST', 'Développeur backend', 'Développement d’API REST sécurisées.', 0xD301E490E414435AB6CC0BCF8C811017);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811017, 'Excellente progression, curiosité technique notable.', 0xD301E490E414435AB6CC0BCF8C811017);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811017, '2025-11-12', 'Présentiel', 'Bonne intégration dans l’équipe, missions claires.', 0xD301E490E414435AB6CC0BCF8C811017);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811017, 'Outil d''automatisation de tests', 'Tests', 15.0, 'Très bon travail pratique, méthodologie correcte.', 0xD301E490E414435AB6CC0BCF8C811017);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811017, '2026-06-22', 14.0, 'Bonne posture, améliorer les exemples pratiques.', 0xD301E490E414435AB6CC0BCF8C811017);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811054, 'Nico', 'Reis', 'nico.reis@example.com', '0610000013', 'Gestion', 'Stats', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811018, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811054, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811018, 'Réseaux, Sécurité, Firewalls', 'Administrateur réseau', 'Audit et renforcement de la sécurité réseau.', 0xD301E490E414435AB6CC0BCF8C811018);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811018, 'Sérieux et appliqué, pratique régulière nécessaire.', 0xD301E490E414435AB6CC0BCF8C811018);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811018, '2025-12-02', 'Distanciel', 'Conditions de travail favorables, points à améliorer: encadrement.', 0xD301E490E414435AB6CC0BCF8C811018);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811018, 'Optimisation de requêtes SQL', 'Bases de données', 16.5, 'Document complet mais amélioration sur clarté.', 0xD301E490E414435AB6CC0BCF8C811018);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811018, '2026-06-24', 15.5, 'Clarté du discours, réflexes techniques solides.', 0xD301E490E414435AB6CC0BCF8C811018);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811050, 'Sara', 'Bianchi', 'sara.bianchi@example.com', '0610000014', 'Design', 'IA', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C811019, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811050, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C811019, 'SQL, ETL, DataPipeline', 'Ingénieur data', 'Mise en place de pipelines de données.', 0xD301E490E414435AB6CC0BCF8C811019);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C811019, 'Bon travail mais nécessite plus de rigueur sur la documentation.', 0xD301E490E414435AB6CC0BCF8C811019);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C811019, '2025-12-10', 'Présentiel', 'Suivi régulier, progression notable.', 0xD301E490E414435AB6CC0BCF8C811019);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C811019, 'Tableaux de bord interactifs', 'DataViz', 17.5, 'Bon rendu, intéressant et applicable.', 0xD301E490E414435AB6CC0BCF8C811019);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C811019, '2026-06-26', 16.0, 'Bonne préparation, approfondir certains points.', 0xD301E490E414435AB6CC0BCF8C811019);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811050, 'Theo', 'Martins', 'theo.martins@example.com', '0610000015', 'Informatique', 'Sécurité', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C81101A, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811050, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C81101A, 'Python, ML, DataViz', 'Data Scientist', 'Projet d''analyse et de visualisation de données.', 0xD301E490E414435AB6CC0BCF8C81101A);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C81101A, 'Très bon esprit d''analyse, autonome.', 0xD301E490E414435AB6CC0BCF8C81101A);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C81101A, '2025-10-15', 'Distanciel', 'Visite positive, apprentissage en milieu pro satisfaisant.', 0xD301E490E414435AB6CC0BCF8C81101A);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C81101A, 'Développement d’un module d''authentification', 'Cybersécurité', 13.0, 'Rapport structuré et pertinent.', 0xD301E490E414435AB6CC0BCF8C81101A);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C81101A, '2026-06-18', 17.5, 'Présentation claire et maîtrisée.', 0xD301E490E414435AB6CC0BCF8C81101A);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811030, 'Léon', 'Garcia', 'léon.garcia@example.com', '0610000016', 'Mathématiques', 'Data', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C81101B, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811030, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C81101B, 'JavaScript, React, Frontend', 'Développeur frontend', 'Création d''une application web réactive.', 0xD301E490E414435AB6CC0BCF8C81101B);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C81101B, 'Bon niveau technique, améliorer la communication.', 0xD301E490E414435AB6CC0BCF8C81101B);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C81101B, '2025-11-05', 'Présentiel', 'Entretien par visio, suivi technique établi.', 0xD301E490E414435AB6CC0BCF8C81101B);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C81101B, 'Analyse prédictive des ventes', 'Data Science', 14.5, 'Analyse bien menée, quelques approfondissements possibles.', 0xD301E490E414435AB6CC0BCF8C81101B);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C81101B, '2026-06-20', 14.0, 'Très bon oral, quelques imprécisions techniques.', 0xD301E490E414435AB6CC0BCF8C81101B);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811040, 'Nora', 'Ivanova', 'nora.ivanova@example.com', '0610000017', 'Électronique', 'Dév Web', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C81101C, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811040, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C81101C, 'Java, Spring, REST', 'Développeur backend', 'Développement d’API REST sécurisées.', 0xD301E490E414435AB6CC0BCF8C81101C);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C81101C, 'Excellente progression, curiosité technique notable.', 0xD301E490E414435AB6CC0BCF8C81101C);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C81101C, '2025-11-12', 'Distanciel', 'Bonne intégration dans l’équipe, missions claires.', 0xD301E490E414435AB6CC0BCF8C81101C);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C81101C, 'Outil d''automatisation de tests', 'Tests', 15.5, 'Très bon travail pratique, méthodologie correcte.', 0xD301E490E414435AB6CC0BCF8C81101C);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C81101C, '2026-06-22', 15.5, 'Bonne posture, améliorer les exemples pratiques.', 0xD301E490E414435AB6CC0BCF8C81101C);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811021, 'Adil', 'Benali', 'adil.benali@example.com', '0610000018', 'Gestion', 'Réseaux', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C81101D, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811021, 0xC101E490E414435AB6CC0BCF8C811001, 0xB201E490E414435AB6CC0BCF8C811001);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C81101D, 'Réseaux, Sécurité, Firewalls', 'Administrateur réseau', 'Audit et renforcement de la sécurité réseau.', 0xD301E490E414435AB6CC0BCF8C81101D);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C81101D, 'Sérieux et appliqué, pratique régulière nécessaire.', 0xD301E490E414435AB6CC0BCF8C81101D);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C81101D, '2025-12-02', 'Présentiel', 'Conditions de travail favorables, points à améliorer: encadrement.', 0xD301E490E414435AB6CC0BCF8C81101D);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C81101D, 'Optimisation de requêtes SQL', 'Bases de données', 16.0, 'Document complet mais amélioration sur clarté.', 0xD301E490E414435AB6CC0BCF8C81101D);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C81101D, '2026-06-24', 16.0, 'Clarté du discours, réflexes techniques solides.', 0xD301E490E414435AB6CC0BCF8C81101D);

INSERT INTO apprentice (id, first_name, last_name, email, phone, program, major, tutor_id) VALUES
    (0xA101E490E414435AB6CC0BCF8C811038, 'Mina', 'Kaur', 'mina.kaur@example.com', '0610000019', 'Design', 'Systèmes', 0x92A7B451C9D34437B5AA7E2D53B011A2);

INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id) VALUES
    (0xD301E490E414435AB6CC0BCF8C81101E, '2025-09-01', 0xA101E490E414435AB6CC0BCF8C811038, 0xC101E490E414435AB6CC0BCF8C811002, 0xB201E490E414435AB6CC0BCF8C811002);

INSERT INTO mission (id, keywords, target_job, comments, academic_year_id) VALUES
    (0xE401E490E414435AB6CC0BCF8C81101E, 'SQL, ETL, DataPipeline', 'Ingénieur data', 'Mise en place de pipelines de données.', 0xD301E490E414435AB6CC0BCF8C81101E);

INSERT INTO note (id, comments, academic_year_id) VALUES
    (0xE501E490E414435AB6CC0BCF8C81101E, 'Bon travail mais nécessite plus de rigueur sur la documentation.', 0xD301E490E414435AB6CC0BCF8C81101E);

INSERT INTO visit (id, date, format, comments, academic_year_id) VALUES
    (0xE601E490E414435AB6CC0BCF8C81101E, '2025-12-10', 'Distanciel', 'Suivi régulier, progression notable.', 0xD301E490E414435AB6CC0BCF8C81101E);

INSERT INTO report_evaluation (id, thesis, topic, final_grade, comments, academic_year_id) VALUES
    (0xE701E490E414435AB6CC0BCF8C81101E, 'Tableaux de bord interactifs', 'DataViz', 17.5, 'Bon rendu, intéressant et applicable.', 0xD301E490E414435AB6CC0BCF8C81101E);

INSERT INTO oral_exam (id, date, final_grade, comments, academic_year_id) VALUES
    (0xE801E490E414435AB6CC0BCF8C81101E, '2026-06-26', 17.5, 'Bonne préparation, approfondir certains points.', 0xD301E490E414435AB6CC0BCF8C81101E);