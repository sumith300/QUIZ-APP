-- Sample user data (password is 'admin123' encoded with BCrypt)
INSERT INTO users (username, email, password, role, enabled) 
VALUES 
    ('admin', 'admin@quizapp.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'ADMIN', true),
    ('user1', 'user1@quizapp.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'USER', true)
ON CONFLICT (username) DO NOTHING; 