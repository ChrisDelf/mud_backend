DELETE
FROM users;

DELETE
FROM userroles;

DELETE
FROM useremails;

DELETE
FROM rooms;

DELETE
FROM monsters;

DELETE
FROM cells;

DELETE
FROM maps;

--CREATE TABLE rooms(
--roomid INTEGER AUTO_INCREMENT PRIMARY KEY ,
--x INTEGER DEFAULT ,
--y INTEGER DEFAULT ,
--height INTEGER DEFAULT,
--width INTEGER DEFAULT,
--doorY INTEGER DEFAULT,
--doorX INTEGER DEFAULT,
--monsterLimit  INTEGER DEFAULT,
--monsters VARCHAR(250) DEFAULT NULL,
--)
--
--CREATE TABLE monsters(
--monsterid INT PRIMARY KEY,
--monsterName VARCHAR(250) DEFAULT NULL,
--monsterHealth INT DEFAULT NULL,
--strength INT DEFAULT NULL,
--agility INT DEFAULT NULL,
--intellect INT DEFAULT NULL,
--stamina INT DEFAULT NULL,
--monsterX INT DEFAULT NULL,
--monsterY INT DEFAULT NULL,
--
--)


alter sequence hibernate_sequence restart with 20;