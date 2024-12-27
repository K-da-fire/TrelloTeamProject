INSERT INTO users (email, password, name, auth, deleted_at)
VALUES ('test@test.com', 'password1', 'name', 'ADMIN', null);

INSERT INTO workspace (title, content)
VALUES ('title', 'content');

INSERT INTO board (title, background, workspace_id)
VALUES ('title', 'background', 1);

INSERT INTO lists (content, orders, board_id)
VALUES ('content', 1, 1);

INSERT INTO cards (user_id, lists_id, title, explanation, attach_file_id, deadline)
VALUES (1, 1, 'title', 'explanation', null, '2024-12-31T00:00:01');
