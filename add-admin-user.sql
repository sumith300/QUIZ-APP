-- Script to add new admin users to the database
-- Replace the values below with your desired admin credentials

-- To generate a BCrypt password hash, you can use online tools or Spring Boot's BCryptPasswordEncoder
-- For password 'newadmin123', the BCrypt hash would be:
-- $2a$10$example...

-- Add a new admin user
INSERT INTO users (username, email, password, role, enabled) 
VALUES 
    ('superadmin', 'superadmin@quizapp.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'ADMIN', true)
ON CONFLICT (username) DO NOTHING;

-- To change password for existing admin (update the BCrypt hash):
-- UPDATE users SET password = '$2a$10$NEW_BCRYPT_HASH_HERE' WHERE username = 'admin';

-- To promote an existing user to admin:
-- UPDATE users SET role = 'ADMIN' WHERE username = 'existing_username';

-- Common BCrypt password hashes (DO NOT use in production):
-- 'admin123' = $2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa
-- 'password123' = $2a$10$e0MYzXyjpJS7Pd0RVvHwHe.LNDwATxDrJ3LdD9lQy6T4/1O3hCcAG
-- 'secure123' = $2a$10$GwJnBqoKWkQrJxD7LKP1l.HVJ3WqVZn5BvmF7KYQ8cHyDJeJKv1HG

-- Query to check all admin users:
-- SELECT username, email, role, enabled FROM users WHERE role = 'ADMIN';
