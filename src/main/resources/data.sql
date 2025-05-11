INSERT INTO users (
    id,
    created_at,
    email,
    name,
    password,
    role,
    updated_at
) VALUES (
    '9f8c8a58-b8e2-4d79-9a9d-90884f0a56c1',
    NOW(),
    'admin@prueba.com',
    'Administrador',
    '$2a$10$kuf30XH.LjKhhxBudL1CLeSZVOfpOaWbeHGO.XidhcP/G3kAOh3La',
    'ADMIN',
    NOW()
)
ON DUPLICATE KEY UPDATE email = VALUES(email); -- No cambia nada pero evita error