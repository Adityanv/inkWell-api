-- ============================================================
--  INKWELL - Blog Platform | Database Schema
--  Run this before starting your backend.
-- ============================================================

CREATE DATABASE IF NOT EXISTS inkwell;
USE inkwell;

-- ── AUTHORS ────────────────────────────────────────────────────
CREATE TABLE authors (
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(150) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    bio        TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ── TAGS ───────────────────────────────────────────────────────
CREATE TABLE tags (
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)  NOT NULL UNIQUE,
    slug VARCHAR(50)  NOT NULL UNIQUE
);

-- ── POSTS ──────────────────────────────────────────────────────
CREATE TABLE posts (
    id           INT PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    content      LONGTEXT     NOT NULL,
    excerpt      VARCHAR(500),
    status       ENUM('DRAFT','PUBLISHED') NOT NULL DEFAULT 'DRAFT',
    author_id    INT NOT NULL,
    published_at TIMESTAMP NULL DEFAULT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

-- ── POST_TAGS (join table for @ManyToMany) ─────────────────────
CREATE TABLE post_tags (
    post_id INT NOT NULL,
    tag_id  INT NOT NULL,
    PRIMARY KEY (post_id, tag_id),
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id)  REFERENCES tags(id)  ON DELETE CASCADE
);

-- ── COMMENTS ───────────────────────────────────────────────────
CREATE TABLE comments (
    id           INT PRIMARY KEY AUTO_INCREMENT,
    post_id      INT         NOT NULL,
    author_name  VARCHAR(100) NOT NULL,
    author_email VARCHAR(255) NOT NULL,
    body         TEXT         NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
);

