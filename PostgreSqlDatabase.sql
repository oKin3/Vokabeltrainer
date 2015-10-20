DROP TABLE IF EXISTS wrong;
DROP TABLE IF EXISTS notknown;
DROP TABLE IF EXISTS correct;
DROP TABLE IF EXISTS translation;
DROP TABLE IF EXISTS sentence;
DROP TABLE IF EXISTS german;
DROP TABLE IF EXISTS japanese;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS userlogin;

CREATE TABLE userlogin (
    id          serial          NOT NULL,
    username    varchar(16)     NOT NULL,
    password    varchar(128)    NOT NULL,
    email       varchar(64)     NOT NULL,
    admin       boolean         NOT NULL        DEFAULT false,
    CONSTRAINT  PK_UserID       PRIMARY KEY (id)
);

CREATE TABLE japanese (
    id          serial          NOT NULL,
    kanji       varchar(64)      NOT NULL,
    kana        varchar(64)     NOT NULL,
    romaji      varchar(64)     NOT NULL,
    sentence    varchar(128)            ,
    jlpt        int             NOT NULL,
    category    varchar(32)     NOT NULL,
    CONSTRAINT  PK_JapaneseID   PRIMARY KEY (id)
);

CREATE TABLE german (
    id          serial          NOT NULL,
    word        varchar(64)     NOT NULL,
    sentence    varchar(128)            ,
    category    varchar(32)     NOT NULL,
    CONSTRAINT  PK_GermanID     PRIMARY KEY (id)
);

CREATE TABLE translation (
    japanese_id int             NOT NULL,
    german_id   int             NOT NULL,
    CONSTRAINT FK_JapaneseID    FOREIGN KEY (japanese_id)   REFERENCES japanese (id),
    CONSTRAINT FK_GermanID      FOREIGN KEY (german_id)     REFERENCES german (id)
);

CREATE TABLE correct (
    id          serial          NOT NULL,
    user_id     int             NOT NULL,
    japanese_id int                     ,
    german_id   int                     ,
    CONSTRAINT  PK_CorrectID    PRIMARY KEY (id),
    CONSTRAINT  FK_UserID       FOREIGN KEY (user_id)       REFERENCES userlogin (id),
    CONSTRAINT  FK_JapaneseID   FOREIGN KEY (japanese_id)   REFERENCES japanese (id),
    CONSTRAINT  FK_GermanID     FOREIGN KEY (german_id)     REFERENCES german (id)
);

CREATE TABLE wrong (
    id          serial          NOT NULL,
    user_id     int             NOT NULL,
    japanese_id int                     ,
    german_id   int                     ,
    CONSTRAINT  PK_WrongID      PRIMARY KEY (id),
    CONSTRAINT  FK_UserID       FOREIGN KEY (user_id)       REFERENCES userlogin (id),
    CONSTRAINT  FK_JapaneseID   FOREIGN KEY (japanese_id)   REFERENCES japanese (id),
    CONSTRAINT  FK_GermanID     FOREIGN KEY (german_id)     REFERENCES german (id)
);

CREATE TABLE notknown (
    id          serial          NOT NULL,
    user_id     int             NOT NULL,
    japanese_id int                     ,
    german_id   int                     ,
    CONSTRAINT  PK_NotknownID   PRIMARY KEY (id),
    CONSTRAINT  FK_UserID       FOREIGN KEY (user_id)       REFERENCES userlogin (id),
    CONSTRAINT  FK_JapaneseID   FOREIGN KEY (japanese_id)   REFERENCES japanese (id),
    CONSTRAINT  FK_GermanID     FOREIGN KEY (german_id)     REFERENCES german (id)
);