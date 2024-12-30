INSERT INTO users (email, password, name, auth, deleted_at)
VALUES ('test@test.com', 'password1', 'name', 'ADMIN', null);

INSERT INTO workspace (title, content)
VALUES ('title', 'content');

INSERT INTO board (title, background_id, workspace_id)
VALUES ('title', null, 1);

INSERT INTO lists (content, orders, board_id)
VALUES ('content', 1, 1);

DROP PROCEDURE IF EXISTS cardInsert;
CREATE PROCEDURE cardInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 1000
        DO
            INSERT INTO cards (user_id, lists_id, title, explanation, attach_file_id, deadline)
            VALUES (1, 1, CONCAT('title ', i), 'explanation', null, '2024-12-31T00:00:01');
            SET i = i + 1;
        end while;
end;
CALL cardInsert();
