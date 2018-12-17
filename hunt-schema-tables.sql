DROP SCHEMA IF EXISTS Hunt;

CREATE SCHEMA IF NOT EXISTS Hunt DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE Hunt;

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
    userId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL unique,
    Password VARCHAR(255) NOT NULL,
    PRIMARY KEY (userId)
);

DROP TABLE IF EXISTS DungeonLevel;

CREATE TABLE IF NOT EXISTS DungeonLevel (
    dungeonLevelId INT UNSIGNED NOT NULL UNIQUE,
    PRIMARY KEY (dungeonLevelId)
);

DROP TABLE IF EXISTS DungeonRow;

CREATE TABLE IF NOT EXISTS DungeonRow (
    dungeonRow INT UNSIGNED,
    rowTiles TEXT NOT NULL,
    dungeonLevelId INT UNSIGNED NOT NULL
);

DROP TABLE IF EXISTS Player;

CREATE TABLE IF NOT EXISTS Player (
    PlayerId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    PlayerName VARCHAR(255) NOT NULL,
    Username varchar(255) NOT NULL,
    CurrentDungeonLevelId INT UNSIGNED,
    PlayerStrength INT UNSIGNED DEFAULT 1,
    PlayerAgility INT UNSIGNED DEFAULT 1,
    PlayerVitality INT UNSIGNED DEFAULT 1,
    PlayerDamage INT UNSIGNED DEFAULT 1,
    PlayerSpeed INT UNSIGNED DEFAULT 1,
    PlayerHealthPoints INT UNSIGNED DEFAULT 15,
    PlayerCurrentHealthPoints INT UNSIGNED DEFAULT 15,
    PlayerWeapon VARCHAR(255) DEFAULT 'Fist',
    PlayerArmor VARCHAR(255) DEFAULT 'Flesh',
    PlayerPosX INT UNSIGNED DEFAULT 0,
    PlayerPosY INT UNSIGNED DEFAULT 0,
    PlayerLevel INT UNSIGNED DEFAULT 1,
    PlayerExpForNextLevel INT UNSIGNED DEFAULT 10,
    PlayerExp INT UNSIGNED DEFAULT 0,
    PlayerHunger INT UNSIGNED DEFAULT 100,
    PlayerInventory VARCHAR(255) DEFAULT '',
    PRIMARY KEY (PlayerId)
);

DROP TABLE IF EXISTS Monster;

CREATE TABLE IF NOT EXISTS Monster (
    MonsterId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    MonsterType VARCHAR(255) NOT NULL,
    CurrentDungeonLevelId INT UNSIGNED NOT NULL,
    Strength INT UNSIGNED DEFAULT 1,
    Agility INT UNSIGNED DEFAULT 1,
    Vitality INT UNSIGNED DEFAULT 1,
    Damage INT UNSIGNED DEFAULT 1,
    Speed INT UNSIGNED DEFAULT 1,
    HealthPoints INT UNSIGNED DEFAULT 15,
    CurrentHealthPoints INT UNSIGNED DEFAULT 15,
    Weapon VARCHAR(255) DEFAULT 'Null',
    Armor VARCHAR(255) DEFAULT 'Flesh',
    PosX INT UNSIGNED DEFAULT 0,
    PosY INT UNSIGNED DEFAULT 0,
    ExpOnKill INT UNSIGNED DEFAULT 10,
    TurnCount DOUBLE DEFAULT 0,
    PRIMARY KEY (MonsterId)
);

DROP TABLE IF EXISTS TimeFreeze;

CREATE TABLE IF NOT EXISTS TimeFreeze (
    timeFreezeId INT AUTO_INCREMENT,
    timeFreeze INT,
    PRIMARY KEY (timeFreezeId)
);

DROP TABLE IF EXISTS ItemFloor;

CREATE TABLE IF NOT EXISTS ItemFloor (
    item varchar(30),
    x int,
    y int
);

DROP TABLE IF EXISTS Projectile;

CREATE TABLE IF NOT EXISTS Projectile (
    projectileId INT AUTO_INCREMENT,
    color VARCHAR(30),
    x INT,
    y INT,
    movX INT,
    movY INT,
    agility INT,
    damage INT,
    PRIMARY KEY (projectileId)
);









