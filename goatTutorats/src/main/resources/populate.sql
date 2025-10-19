-- =========================
-- Tutor unique
-- =========================
INSERT INTO tutor (id, username, password)
VALUES ('11111111-1111-1111-1111-111111111111', 'john.doe', '$2a$12$KGyJjUaI4luzFPP23owztOzIDHfTI37HCRsJozcKTjfNFZDt4C/Uq');

-- =========================
-- Companies
-- =========================
INSERT INTO company (id, name, address, accessInfo)
VALUES
    (1, 'TechNova', '12 Rue de l’Innovation, Paris', 'Badge requis à l’entrée'),
    (2, 'AgriSoft', '45 Avenue des Champs, Lyon', 'Parking gratuit');

-- =========================
-- Mentors (1 par company)
-- =========================
INSERT INTO mentor (id, firstName, lastName, position, email, phone, remarks, company_id)
VALUES
    ('22222222-2222-2222-2222-222222222222', 'Alice', 'Martin', 'Chef de projet', 'alice.martin@technova.com', '0601020304', 'Très impliquée', 1),
    ('33333333-3333-3333-3333-333333333333', 'Bob', 'Durand', 'Responsable R&D', 'bob.durand@agrisoft.com', '0605060708', 'Travailleur rigoureux', 2);

-- =========================
-- Apprentices
-- =========================
INSERT INTO apprentice (id, firstName, lastName, email, phone, programme, majeure, tutor_id)
VALUES
    ('44444444-4444-4444-4444-444444444444', 'Emma', 'Dupont', 'emma.dupont@etu.univ.fr', '0611223344', 'Informatique', 'IA', '11111111-1111-1111-1111-111111111111'),
    ('55555555-5555-5555-5555-555555555555', 'Lucas', 'Bernard', 'lucas.bernard@etu.univ.fr', '0677889900', 'AgroTech', 'Robotique', '11111111-1111-1111-1111-111111111111'),
    ('66666666-6666-6666-6666-666666666666', 'Chloé', 'Moreau', 'chloe.moreau@etu.univ.fr', '0601010101', 'Informatique', 'Cybersécurité', '11111111-1111-1111-1111-111111111111'),
    ('77777777-7777-7777-7777-777777777777', 'Noah', 'Petit', 'noah.petit@etu.univ.fr', '0602020202', 'Informatique', 'DevOps', '11111111-1111-1111-1111-111111111111'),
    ('88888888-8888-8888-8888-888888888888', 'Léa', 'Roux', 'lea.roux@etu.univ.fr', '0603030303', 'Mathématiques', 'Statistiques', '11111111-1111-1111-1111-111111111111'),
    ('99999999-9999-9999-9999-999999999999', 'Hugo', 'Fournier', 'hugo.fournier@etu.univ.fr', '0604040404', 'Informatique', 'IA', '11111111-1111-1111-1111-111111111111'),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Camille', 'Girard', 'camille.girard@etu.univ.fr', '0605050505', 'Informatique', 'Data Science', '11111111-1111-1111-1111-111111111111'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Arthur', 'Lemoine', 'arthur.lemoine@etu.univ.fr', '0606060606', 'Mécatronique', 'Robotique', '11111111-1111-1111-1111-111111111111'),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Jade', 'Renaud', 'jade.renaud@etu.univ.fr', '0607070707', 'Informatique', 'Réseaux', '11111111-1111-1111-1111-111111111111'),
    ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Tom', 'Gauthier', 'tom.gauthier@etu.univ.fr', '0608080808', 'AgroTech', 'Systèmes embarqués', '11111111-1111-1111-1111-111111111111'),
    ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Sarah', 'Leclerc', 'sarah.leclerc@etu.univ.fr', '0609090909', 'Informatique', 'Développement Web', '11111111-1111-1111-1111-111111111111'),
    ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Ethan', 'Martinez', 'ethan.martinez@etu.univ.fr', '0610101010', 'Informatique', 'IA', '11111111-1111-1111-1111-111111111111'),
    ('12121212-1212-1212-1212-121212121212', 'Manon', 'Robert', 'manon.robert@etu.univ.fr', '0611111111', 'Informatique', 'Big Data', '11111111-1111-1111-1111-111111111111'),
    ('13131313-1313-1313-1313-131313131313', 'Nathan', 'Richard', 'nathan.richard@etu.univ.fr', '0612121212', 'Mécatronique', 'Systèmes autonomes', '11111111-1111-1111-1111-111111111111'),
    ('14141414-1414-1414-1414-141414141414', 'Zoé', 'Faure', 'zoe.faure@etu.univ.fr', '0613131313', 'Informatique', 'Sécurité', '11111111-1111-1111-1111-111111111111'),
    ('15151515-1515-1515-1515-151515151515', 'Louis', 'David', 'louis.david@etu.univ.fr', '0614141414', 'Mathématiques', 'Data Science', '11111111-1111-1111-1111-111111111111'),
    ('16161616-1616-1616-1616-161616161616', 'Inès', 'Perrin', 'ines.perrin@etu.univ.fr', '0615151515', 'AgroTech', 'Biotechnologies', '11111111-1111-1111-1111-111111111111'),
    ('17171717-1717-1717-1717-171717171717', 'Gabriel', 'Moulin', 'gabriel.moulin@etu.univ.fr', '0616161616', 'Informatique', 'Cloud Computing', '11111111-1111-1111-1111-111111111111'),
    ('18181818-1818-1818-1818-181818181818', 'Lina', 'Lopez', 'lina.lopez@etu.univ.fr', '0617171717', 'Mécatronique', 'Robotique', '11111111-1111-1111-1111-111111111111'),
    ('19191919-1919-1919-1919-191919191919', 'Victor', 'Caron', 'victor.caron@etu.univ.fr', '0618181818', 'Informatique', 'Dev Mobile', '11111111-1111-1111-1111-111111111111');

-- =========================
-- Academic Years
-- =========================
INSERT INTO academic_year (id, year, apprentice_id, company_id, mentor_id)
VALUES
    ('66666666-6666-6666-6666-666666666666', '2024-09-01', '44444444-4444-4444-4444-444444444444', 1, '22222222-2222-2222-2222-222222222222'),
    ('77777777-7777-7777-7777-777777777777', '2024-09-01', '55555555-5555-5555-5555-555555555555', 2, '33333333-3333-3333-3333-333333333333');

-- =========================
-- Missions
-- =========================
INSERT INTO mission (id, keywords, targetJob, comments, academic_year_id)
VALUES
    ('88888888-8888-8888-8888-888888888888', 'IA, Java, Deep Learning', 'Data Scientist', 'Développement d’un modèle de prédiction', '66666666-6666-6666-6666-666666666666'),
    ('99999999-9999-9999-9999-999999999999', 'AgroTech, Robotique, Python', 'Ingénieur Robotique', 'Conception d’un robot agricole', '77777777-7777-7777-7777-777777777777');

-- =========================
-- Defenses
-- =========================
INSERT INTO oral_exam (id, date, finalGrade, comments, academic_year_id)
VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2024-06-20', 16.5, 'Très bonne présentation', '66666666-6666-6666-6666-666666666666'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2024-06-22', 15.0, 'Projet innovant et bien structuré', '77777777-7777-7777-7777-777777777777');

-- =========================
-- Visits
-- =========================
INSERT INTO visit (id, date, format, comments, academic_year_id)
VALUES
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', '2024-03-15', 'Présentiel', 'Suivi de stage sur site', '66666666-6666-6666-6666-666666666666'),
    ('dddddddd-dddd-dddd-dddd-dddddddddddd', '2024-03-18', 'Distanciel', 'Échange par visio sur les objectifs', '77777777-7777-7777-7777-777777777777');

-- =========================
-- School Evaluations
-- =========================
INSERT INTO report_evaluation (id, thesis, topic, finalGrade, comments, academic_year_id)
VALUES
    ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Optimisation IA', 'Deep Learning appliqué à la santé', 17.0, 'Excellent dossier', '66666666-6666-6666-6666-666666666666'),
    ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Robotisation durable', 'Agriculture automatisée', 14.5, 'Travail prometteur', '77777777-7777-7777-7777-777777777777');

-- =========================
-- Notes
-- =========================
INSERT INTO note (id, comments, academic_year_id)
VALUES
    ('12121212-1212-1212-1212-121212121212', 'Bon comportement global', '66666666-6666-6666-6666-666666666666'),
    ('34343434-3434-3434-3434-343434343434', 'Bonnes compétences techniques', '77777777-7777-7777-7777-777777777777');
