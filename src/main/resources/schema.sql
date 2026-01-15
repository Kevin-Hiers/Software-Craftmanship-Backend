DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS recipe_steps;
DROP TABLE IF EXISTS group_users;
DROP TABLE IF EXISTS proposals;
DROP TABLE IF EXISTS leftovers;

-- =========
-- USERS
-- =========
CREATE TABLE IF NOT EXISTS users (
    id            UUID         NOT NULL,
    username      VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uq_users_username UNIQUE (username),
    CONSTRAINT uq_users_email UNIQUE (email)
    );

-- =========
-- RECIPES
-- =========
CREATE TABLE IF NOT EXISTS recipes (
    recipe_id  UUID         NOT NULL,
    name       VARCHAR(255) NOT NULL,
    is_public  BOOLEAN      NOT NULL,
    PRIMARY KEY (recipe_id)
    );

CREATE TABLE IF NOT EXISTS recipe_steps (
    step_id          UUID          NOT NULL,
    recipe_id        UUID          NOT NULL,
    step_order       INT           NOT NULL,
    ingredient_name  VARCHAR(255)  NOT NULL,
    amount           DECIMAL(19,4) NOT NULL,
    unit             VARCHAR(64)   NOT NULL,
    instruction      VARCHAR(1000) NOT NULL,
    PRIMARY KEY (step_id),
    CONSTRAINT uq_recipe_steps_order UNIQUE (recipe_id, step_order)
    );

CREATE INDEX IF NOT EXISTS idx_recipe_steps_recipe_id ON recipe_steps(recipe_id);

-- =========
-- GROUPS
-- =========
CREATE TABLE IF NOT EXISTS groups (
    group_id UUID NOT NULL,
    PRIMARY KEY (group_id)
    );

CREATE TABLE IF NOT EXISTS group_users (
    group_id UUID NOT NULL,
    user_id  UUID NOT NULL,
    PRIMARY KEY (group_id, user_id)
    );

CREATE INDEX IF NOT EXISTS idx_group_users_user_id ON group_users(user_id);

CREATE TABLE IF NOT EXISTS proposals (
    proposal_id UUID NOT NULL,
    group_id    UUID NOT NULL,
    recipe_id   UUID NOT NULL,
    votes       INT  NOT NULL DEFAULT 0,
    PRIMARY KEY (proposal_id),
    CONSTRAINT chk_proposals_votes CHECK (votes >= 0)
    );

CREATE INDEX IF NOT EXISTS idx_proposals_group_id ON proposals(group_id);
CREATE INDEX IF NOT EXISTS idx_proposals_recipe_id ON proposals(recipe_id);

-- =========
-- LEFTOVERS
-- =========
CREATE TABLE IF NOT EXISTS leftovers (
    leftover_id      UUID          NOT NULL,
    user_id          UUID          NOT NULL,
    ingredient_name  VARCHAR(255)  NOT NULL,
    amount           DECIMAL(19,4) NOT NULL,
    unit             VARCHAR(64)   NOT NULL,
    expiration_date  DATE          NOT NULL,
    PRIMARY KEY (leftover_id)
    );

CREATE INDEX IF NOT EXISTS idx_leftovers_user_id ON leftovers(user_id);
CREATE INDEX IF NOT EXISTS idx_leftovers_expiration ON leftovers(expiration_date);