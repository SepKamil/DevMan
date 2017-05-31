INSERT INTO `hibernate_sequence` (`next_val`) VALUES(37),(37),(37),(37),(37),(37),(37);

INSERT INTO `users` (`id`, `accountType`, `email`, `lastName`, `login`, `name`, `password`, `pesel`, `manager_id`, `userState`) VALUES(1, 'MANAGER', 'admin@devman.pl', 'Kowalski', 'admin', 'Jan', 'devman2017', '11111111111', NULL, 'ACTIVE'),(2, 'EMPLOYEE', 'kuba3351@gmail.com', 'Sierżęga', 'kuba3351', 'Jakub', 'Jakubs12343351577@', '95101409036', 1, 'ACTIVE'),(17, 'EMPLOYEE', 'kamillo@malpa.pl', 'Sęp', 'kamillo', 'Kamil', 'kamillo', '38473920382', 1, 'ACTIVE'),(18, 'EMPLOYEE', 'jacek@wacek.pl', 'Wacek', 'jacekWacek', 'Jacek', 'jacekWacek', '37293736283', 1, 'INACTIVE'),(19, 'EMPLOYEE', 'jan@kowalski.pl', 'Kowalski', 'JanJan', 'Jan', 'kowalski', '37283712832', 1, 'INACTIVE'),(20, 'EMPLOYEE', 'jan@nowak.pl', 'Nowak', 'janjan', 'Jan', 'nowak1', '73829172834', 1, 'ACTIVE');

INSERT INTO `leaves` (`id`, `numberOfDays`, `startDate`, `status`, `employee_id`) VALUES(10, 3, '2017-04-05', 'ZAAKCEPTOWANY', 2),(11, 7, '2017-04-07', 'ODRZUCONY', 2),(13, 3, '2017-04-02', 'ZAAKCEPTOWANY', 2),(14, 6, '2017-04-09', 'ODRZUCONY', 2),(35, 5, '2017-04-14', 'OCZEKUJE', 2),(36, 10, '2017-05-18', 'OCZEKUJE', 2);

INSERT INTO `projects` (`id`, `endDate`, `name`, `startDate`, `projectState`) VALUES(15, '2017-04-07', 'aaa', '2017-04-04', 'IN_PROGRESS'),(27, '2017-04-08', 'bbb', '2017-04-07', 'FINISHED'),(29, '2017-04-16', 'ccc', '2017-04-07', 'IN_PROGRESS'),(32, '2017-04-09', 'vvv', '2017-04-07', 'FINISHED');

INSERT INTO `teams` (`id`, `name`, `project`) VALUES(16, 'bbb', 15),(21, 'ccc', 15),(28, 'eee', 27),(31, 'qwerty', 15),(33, 'zxcvbnm', 15);

INSERT INTO `tasks` (`id`, `endDate`, `name`, `predictedTime`, `startDate`, `team_id`, `taskState`) VALUES(22, '2017-04-16', 'zadanie1', 5, '2017-04-10', NULL, 'FINISHED'),(23, '2017-05-07', 'zadanie2', 4, '2017-04-05', 16, 'IN_PROGRESS'),(24, '2017-04-30', 'zadanie3', 3, '2017-04-22', 16, 'IN_PROGRESS'),(25, '2017-04-15', 'zadanie4', 7, '2017-04-03', 16, 'FINISHED'),(26, '2017-04-16', 'zadanie5', 10, '2017-04-12', NULL, 'FINISHED'),(30, '2017-04-16', 'vvv', 4, '2017-04-07', NULL, 'FINISHED'),(34, '2017-04-28', 'task123', 5, '2017-04-15', NULL, 'IN_PROGRESS');

INSERT INTO `users_teams` (`User_id`, `teams_id`) VALUES (18, 16), (20, 16), (2, 16), (2, 28);
