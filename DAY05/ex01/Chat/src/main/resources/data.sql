INSERT INTO users (login, passwd) VALUES ('user1', 'passw1');
INSERT INTO users (login, passwd) VALUES ('user2', 'passw2');
INSERT INTO users (login, passwd) VALUES ('user3', 'passw3');
INSERT INTO users (login, passwd) VALUES ('user4', 'passw4');
INSERT INTO users (login, passwd) VALUES ('user5', 'passw5');

INSERT INTO room (chat_name, chat_owner) VALUES ('C', 1);
INSERT INTO room (chat_name, chat_owner) VALUES ('C++', 1);
INSERT INTO room (chat_name, chat_owner) VALUES ('JAVA', 1);
INSERT INTO room (chat_name, chat_owner) VALUES ('HELLO', 3);
INSERT INTO room (chat_name, chat_owner) VALUES ('SQL', 4);

INSERT INTO messages (room_id, author, message, time) VALUES (1, 1, 'C is cool', '2020-06-29 10:23:54');
INSERT INTO messages (room_id, author, message, time) VALUES (2, 1, 'C++ is cool too', '2020-06-29 10:40:00');
INSERT INTO messages (room_id, author, message, time) VALUES (2, 2, 'C++ is bad', '2020-06-29 12:02:04');
INSERT INTO messages (room_id, author, message, time) VALUES (1, 4, 'C is hard', '2020-06-29 16:01:42');
INSERT INTO messages (room_id, author, message, time) VALUES (2, 4, 'C++ is beautifull', '2020-06-29 18:02:05');
INSERT INTO messages (room_id, author, message, time) VALUES (3, 1, 'Java is cool', '2020-06-29 20:20:23');
INSERT INTO messages (room_id, author, message, time) VALUES (5, 4, 'SQL is easy', '2020-06-29 23:15:34');
INSERT INTO messages (room_id, author, message, time) VALUES (1, 1, 'yes', '2020-06-30 00:03:50');
INSERT INTO messages (room_id, author, message, time) VALUES (5, 2, 'SQL is bad', '2020-06-30 09:25:23');
INSERT INTO messages (room_id, author, message, time) VALUES (3, 4, 'Yes Java is cool', '2020-06-30 09:32:30');
INSERT INTO messages (room_id, author, message, time) VALUES (3, 1, 'Java is fast', '2020-06-30 10:15:16');
INSERT INTO messages (room_id, author, message, time) VALUES (4, 4, 'hello', '2020-06-30 10:22:55');
INSERT INTO messages (room_id, author, message, time) VALUES (5, 4, 'No, SQL is beautifull', '2020-06-30 10:23:54');
INSERT INTO messages (room_id, author, message, time) VALUES (3, 4, 'Java is hard', '2020-06-30 12:02:00');
INSERT INTO messages (room_id, author, message, time) VALUES (3, 1, 'No, Java is easy', '2020-06-30 17:00:01');