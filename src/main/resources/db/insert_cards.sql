DROP PROCEDURE IF EXISTS cardInsert;
CREATE PROCEDURE cardInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 100000
        DO
            INSERT INTO cards (user_id, lists_id, title, explanation, attach_file_id, deadline)
            VALUES (1, 1, CONCAT('title ', i), 'explanation', null, '2024-12-31T00:00:01');
            SET i = i + 1;
end while;
end;
CALL cardInsert();