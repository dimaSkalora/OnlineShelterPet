DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  normal_weight    DECIMAL DEFAULT 0.2    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE pets (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id     INTEGER   NOT NULL,
  createdDate TIMESTAMP NOT NULL,
  typePet     VARCHAR NOT NULL,
  namePet     VARCHAR NOT NULL,
  breed       VARCHAR NOT NULL,
  sex         VARCHAR NOT NULL,
  color       VARCHAR NOT NULL,
  age         DECIMAL NOT NULL,
  growth      INTEGER NOT NULL,
  weight      DECIMAL NOT NULL,
  namePerson  VARCHAR NOT NULL,
  phone       VARCHAR NOT NULL,
  email       VARCHAR NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX pets_unique_user_datetime_idx ON pets (user_id, createdDate)