INSERT INTO  `heroku_8579846581b38bf`.`course` (`id`,`title`) VALUES (1,'CS5610');
INSERT INTO  `heroku_8579846581b38bf`.`course` (`id`,`title`) VALUES (2,'CS4550');
INSERT INTO  `heroku_8579846581b38bf`.`course` (`id`,`title`) VALUES (3,'CS5200');
INSERT INTO  `heroku_8579846581b38bf`.`course` (`id`,`title`) VALUES (4,'CS1100');
INSERT INTO  `heroku_8579846581b38bf`.`course` (`id`,`title`) VALUES (5,'CS3200');
INSERT INTO  `heroku_8579846581b38bf`.`course` (`id`,`title`) VALUES (6,'CS1800');
INSERT INTO  `heroku_8579846581b38bf`.`course` (`id`,`title`) VALUES (7,'CS4500');

INSERT INTO  `heroku_8579846581b38bf`.`module` (`id`,`title`,`course_id`) VALUES (1,'React',1);
INSERT INTO  `heroku_8579846581b38bf`.`module` (`id`,`title`,`course_id`) VALUES (2,'Angular',1);
INSERT INTO  `heroku_8579846581b38bf`.`module` (`id`,`title`,`course_id`) VALUES (3,'Node.js',1);
INSERT INTO  `heroku_8579846581b38bf`.`module` (`id`,`title`,`course_id`) VALUES (4,'MongoDB',1);
INSERT INTO  `heroku_8579846581b38bf`.`module` (`id`,`title`,`course_id`) VALUES (5,'SQL',3);
INSERT INTO  `heroku_8579846581b38bf`.`module` (`id`,`title`,`course_id`) VALUES (6,'UML',3);

INSERT INTO  `heroku_8579846581b38bf`.`lesson` (`id`,`title`,`module_id`) VALUES (1,'State',1);
INSERT INTO  `heroku_8579846581b38bf`.`lesson` (`id`,`title`,`module_id`) VALUES (2,'Components',1);
INSERT INTO  `heroku_8579846581b38bf`.`lesson` (`id`,`title`,`module_id`) VALUES (3,'Native',1);
INSERT INTO  `heroku_8579846581b38bf`.`lesson` (`id`,`title`,`module_id`) VALUES (4,'JSX',1);
INSERT INTO  `heroku_8579846581b38bf`.`lesson` (`id`,`title`,`module_id`) VALUES (5,'AJAX',1);
INSERT INTO  `heroku_8579846581b38bf`.`lesson` (`id`,`title`,`module_id`) VALUES (6,'SELECT',5);
INSERT INTO  `heroku_8579846581b38bf`.`lesson` (`id`,`title`,`module_id`) VALUES (7,'INSERT',5);
INSERT INTO  `heroku_8579846581b38bf`.`lesson` (`id`,`title`,`module_id`) VALUES (8,'UPDATE',5);
INSERT INTO  `heroku_8579846581b38bf`.`lesson` (`id`,`title`,`module_id`) VALUES (9,'DELETE',5);

INSERT INTO  `heroku_8579846581b38bf`.`topic` (`id`,`title`,`lesson_id`) VALUES (1,'Lifecycle',1);
INSERT INTO  `heroku_8579846581b38bf`.`topic` (`id`,`title`,`lesson_id`) VALUES (2,'Functions',1);
INSERT INTO  `heroku_8579846581b38bf`.`topic` (`id`,`title`,`lesson_id`) VALUES (3,'Mobile',1);
INSERT INTO  `heroku_8579846581b38bf`.`topic` (`id`,`title`,`lesson_id`) VALUES (4,'Reference',1);
INSERT INTO  `heroku_8579846581b38bf`.`topic` (`id`,`title`,`lesson_id`) VALUES (5,'Methods',1);
INSERT INTO  `heroku_8579846581b38bf`.`topic` (`id`,`title`,`lesson_id`) VALUES (6,'Overview',2);
INSERT INTO  `heroku_8579846581b38bf`.`topic` (`id`,`title`,`lesson_id`) VALUES (7,'Lifecycle',2);
INSERT INTO  `heroku_8579846581b38bf`.`topic` (`id`,`title`,`lesson_id`) VALUES (8,'Properties',2);
INSERT INTO  `heroku_8579846581b38bf`.`topic` (`id`,`title`,`lesson_id`) VALUES (9,'Render',2);
