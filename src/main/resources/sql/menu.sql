CREATE TABLE menu (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Icon VARCHAR(255),
    `Key` VARCHAR(255),
    `Link` VARCHAR(255),
    `Order` INT,
    Parent_id INT,
    Title VARCHAR(255),
    `Type` VARCHAR(255)
);
