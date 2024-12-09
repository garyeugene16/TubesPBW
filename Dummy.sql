--jalanin ini  dulu
INSERT INTO "user" (user_id, username, email, password, role) VALUES
(1, 'admin1', 'admin1@example.com', 'hashed_admin1', 'admin'),
(2, 'admin2', 'admin2@example.com', 'hashed_admin2', 'admin'),
(3, 'user1', 'user1@example.com', 'hashed_user1', 'member'),
(4, 'user2', 'user2@example.com', 'hashed_user2', 'member'),
(5, 'user3', 'user3@example.com', 'hashed_user3', 'member');

--lalu ini (created_by bs beda2 sesuaiin sama user_id di db)
INSERT INTO artists (artist_id, name, created_by, created_at) VALUES
(1, 'Lany', 10, '2024-12-09 15:00:00'),
(2, 'Taylor Swift', 10, '2024-12-09 15:10:00'),
(3, 'The Weeknd', 9, '2024-12-09 15:20:00'),
(4, 'Adele', 9, '2024-12-09 15:30:00'),
(5, 'Coldplay', 8, '2024-12-09 15:40:00'),
(6, 'Ed Sheeran', 8, '2024-12-09 15:50:00'),
(7, 'Billie Eilish', 4, '2024-12-09 16:00:00'),
(8, 'BTS', 4, '2024-12-09 16:10:00'),
(9, 'Olivia Rodrigo', 5, '2024-12-09 16:20:00'),
(10, 'Imagine Dragons', 5, '2024-12-09 16:30:00');

--lalu ini (created_by bs beda2 sesuaiin sama user_id di db)
INSERT INTO shows (show_id, artist_id, venue, date, created_by, created_at) VALUES
(1, 1, 'Madison Square Garden', '2024-12-20', 4, '2024-12-09 16:00:00'),
(2, 2, 'Staples Center', '2024-12-22', 4, '2024-12-09 16:10:00'),
(3, 3, 'Wembley Stadium', '2025-01-15', 10, '2024-12-09 16:20:00'),
(4, 4, 'O2 Arena', '2025-01-20', 10, '2024-12-09 16:30:00'),
(5, 5, 'Tokyo Dome', '2025-02-10', 9, '2024-12-09 16:40:00'),
(6, 6, 'Sydney Opera House', '2025-02-15', 9, '2024-12-09 16:50:00'),
(7, 7, 'Hollywood Bowl', '2025-03-01', 8, '2024-12-09 17:00:00'),
(8, 8, 'Seoul Olympic Stadium', '2025-03-10', 8, '2024-12-09 17:10:00'),
(9, 9, 'The Forum', '2025-03-20', 5, '2024-12-09 17:20:00'),
(10, 10, 'Red Rocks Amphitheater', '2025-04-01', 5, '2024-12-09 17:30:00');
