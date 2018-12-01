DROP SCHEMA IF EXISTS `Hunt`;

CREATE SCHEMA IF NOT EXISTS `Hunt` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `Hunt`;

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `Hunt`.`User` (
    `UserID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `Username` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`UserId`)
);

DROP TABLE IF EXISTS `Hunt`.`Player`;
CREATE TABLE IF NOT EXISTS `Hunt`.`Player` (
    `PlayerID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `PlayerName` VARCHAR(255) NOT NULL,
    `UserID` INT UNSIGNED NOT NULL,
    `PlayerStrength` INT UNSIGNED NOT NULL DEFAULT 1,
    `PlayerAgility` INT UNSIGNED NOT NULL DEFAULT 1,
    `PlayerVitality` INT UNSIGNED NOT NULL DEFAULT 1,
    `PlayerDamage` INT UNSIGNED NOT NULL DEFAULT 1,
    `PlayerSpeed` INT UNSIGNED NOT NULL DEFAULT 1,
    `PlayerHealthPoints` INT UNSIGNED NOT NULL DEFAULT 15,
    `PlayerCurrentHealthPoints` INT UNSIGNED NOT NULL DEFAULT 15,
    `PlayerWeapon` VARCHAR(255) NOT NULL DEFAULT 'Fist',
    `PlayerArmor` VARCHAR(255) NOT NULL DEFAULT 'Flesh',
    `isPlayerSolid` BOOLEAN NOT NULL DEFAULT 1,
    `PlayerPosX` INT UNSIGNED NOT NULL DEFAULT 0,
    `PlayerPosY` INT UNSIGNED NOT NULL DEFAULT 0,
    `PlayerLevel` INT UNSIGNED NOT NULL DEFAULT 1,
    `PlayerExpForNextLevel` INT UNSIGNED NOT NULL DEFAULT 10,
    `PlayerExp` INT UNSIGNED NOT NULL DEFAULT 0,
    `PlayerHunger` INT UNSIGNED NOT NULL DEFAULT 100,
    `PlayerBackpack` VARCHAR(255) NOT NULL DEFAULT '',
    CONSTRAINT `Pk_PlayerID` PRIMARY KEY (`PlayerID`),
    CONSTRAINT `Fk_UserID` FOREIGN KEY (`UserID`)
        REFERENCES `Hunt`.`User` (`UserID`)
);

DROP TABLE IF EXISTS `Hunt`.`Dungeon`;
CREATE TABLE IF NOT EXISTS `Hunt`.`Dungeon` (
	`DungeonLevel` int unsigned not null unique,
	`Dungeon` varchar(255) not null,
    CONSTRAINT `Pk_DungeonLevel` PRIMARY KEY (`DungeonLevel`)
);


