-- Insert Question Lists
INSERT INTO tbl_question_list (question_list_id, topic, name) VALUES
(1, 'Java Programming', 'Java Basics'),
(2, 'Java Programming', 'Java OOP Concepts'),
(3, 'Database', 'SQL Fundamentals');

-- Insert Questions for Java Basics
INSERT INTO tbl_question (question_id, question, answer, marks, question_list_id) VALUES
(1, 'Which of the following is not a Java keyword?', 'sizeof', 5, 1),
(2, 'What is the default value of a boolean variable in Java?', 'false', 5, 1),
(3, 'Which method is the entry point of a Java application?', 'main', 5, 1),
(4, 'What does JVM stand for?', 'Java Virtual Machine', 5, 1);

-- Insert Options for Java Basics Questions
INSERT INTO tbl_question_options (option_id, option_text, question_id) VALUES
-- Question 1 Options
(1, 'class', 1),
(2, 'interface', 1),
(3, 'sizeof', 1),
(4, 'extends', 1),

-- Question 2 Options
(5, 'true', 2),
(6, 'false', 2),
(7, '0', 2),
(8, 'null', 2),

-- Question 3 Options
(9, 'start', 3),
(10, 'main', 3),
(11, 'execute', 3),
(12, 'run', 3),

-- Question 4 Options
(13, 'Java Variable Machine', 4),
(14, 'Java Virtual Machine', 4),
(15, 'Java Verified Machine', 4),
(16, 'Java Visual Machine', 4);

-- Insert Questions for Java OOP Concepts
INSERT INTO tbl_question (question_id, question, answer, marks, question_list_id) VALUES
(5, 'Which OOP concept allows one class to acquire the properties of another class?', 'Inheritance', 10, 2),
(6, 'Which keyword is used to prevent a class from being inherited?', 'final', 10, 2),
(7, 'What is the process of hiding implementation details and showing only functionality?', 'Abstraction', 10, 2),
(8, 'Which principle allows a method to perform different functions based on the object it is acting upon?', 'Polymorphism', 10, 2);

-- Insert Options for Java OOP Questions
INSERT INTO tbl_question_options (option_id, option_text, question_id) VALUES
-- Question 5 Options
(17, 'Encapsulation', 5),
(18, 'Polymorphism', 5),
(19, 'Inheritance', 5),
(20, 'Abstraction', 5),

-- Question 6 Options
(21, 'static', 6),
(22, 'final', 6),
(23, 'private', 6),
(24, 'sealed', 6),

-- Question 7 Options
(25, 'Encapsulation', 7),
(26, 'Inheritance', 7),
(27, 'Abstraction', 7),
(28, 'Polymorphism', 7),

-- Question 8 Options
(29, 'Inheritance', 8),
(30, 'Encapsulation', 8),
(31, 'Polymorphism', 8),
(32, 'Abstraction', 8);

-- Insert Questions for SQL Fundamentals
INSERT INTO tbl_question (question_id, question, answer, marks, question_list_id) VALUES
(9, 'Which SQL clause is used to filter records?', 'WHERE', 8, 3),
(10, 'Which SQL keyword is used to sort the result set?', 'ORDER BY', 8, 3),
(11, 'Which command is used to remove a table from the database?', 'DROP TABLE', 8, 3),
(12, 'Which type of join returns all records when there is a match in either left or right table?', 'FULL OUTER JOIN', 8, 3);

-- Insert Options for SQL Fundamentals Questions
INSERT INTO tbl_question_options (option_id, option_text, question_id) VALUES
-- Question 9 Options
(33, 'FILTER', 9),
(34, 'WHERE', 9),
(35, 'HAVING', 9),
(36, 'CONDITION', 9),

-- Question 10 Options
(37, 'SORT BY', 10),
(38, 'ORDER BY', 10),
(39, 'ARRANGE BY', 10),
(40, 'GROUP BY', 10),

-- Question 11 Options
(41, 'DELETE TABLE', 11),
(42, 'REMOVE TABLE', 11),
(43, 'DROP TABLE', 11),
(44, 'TRUNCATE TABLE', 11),

-- Question 12 Options
(45, 'INNER JOIN', 12),
(46, 'LEFT JOIN', 12),
(47, 'RIGHT JOIN', 12),
(48, 'FULL OUTER JOIN', 12);