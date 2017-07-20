-- liquibase formatted sql

-- changeset action:alter_table_books
ALTER TABLE book ADD description VARCHAR(4000);

-- changeset action:insert_demo_data
INSERT INTO book(title, author, edition, isbn, year_of_publication, description)
VALUES
  ("Description Test", "DK", "", "0747532700", 2017, "LOREM IPSUM"),
  ("Long Description Test", "DK", "", "0747532701", 2017, "LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM");