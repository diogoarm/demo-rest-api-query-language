DROP TABLE IF EXISTS documents;

CREATE TABLE documents (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  subject VARCHAR(100) NOT NULL,
  body VARCHAR(250) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP
);

INSERT INTO documents (title, subject, body, created_at) VALUES
  ('title 1 Industrialist', 'subject test abc-123', 'Lorem ipsum Industrialist', '2020-04-23 12:24:00'),
  ('title 2 Tech Entrepreneur', 'subject test abc-210', 'Lorem ipsum Tech Entrepreneur', '2019-01-01 08:00:02'),
  ('title 3 Oil', 'subject test xxx-111', 'Lorem ipsum Oil', '2020-01-20 17:14:01');