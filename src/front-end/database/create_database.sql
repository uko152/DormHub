CREATE DATABASE DormHub;

USE DormHub;

CREATE TABLE USERS
(
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_type ENUM('student','teacher','admin') NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Rooms
(
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    campus VARCHAR(200) NOT NULL,
    building VARCHAR(200) NOT NULL,
    floor INT NOT NULL,
    room_number VARCHAR(200) NOT NULL,
    max_people INT NOT NULL,
    room_type VARCHAR(200) NOT NULL,
    introduction VARCHAR(500),
    image_path VARCHAR(255),
    UNIQUE KEY room_location(campus,building,floor,room_number)
);

CREATE TABLE students
(
    student_id INT PRIMARY KEY NOT NULL,
    student_name VARCHAR(100),
    rest_time VARCHAR(100),
    faculty VARCHAR(100),
    room_id INT,
    bed_number VARCHAR(20),
    contact_number VARCHAR(200),
    introduction TEXT,
    image_path VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES USERS(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE Room_evaluations
(
    evaluation_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    room_id INT NOT NULL,
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    evaluation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    OREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE KEY evaluation_unique(student_id, room_id)
);

CREATE TABLE Student_evaluations
(
    evaluation_id INT PRIMARY KEY AUTO_INCREMENT,
    evaluator_id INT NOT NULL,
    evaluated_student_id INT NOT NULL,
    rating INT NOT NULL CHECK (rating >=1 AND rating <= 5),
    comment TEXT,
    evaluation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (evaluator_id) REFERENCES students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (evaluated_student_id) REFERENCES students(student_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Teachers
(
    teacher_id INT PRIMARY KEY,
    teacher_name VARCHAR(100),
    department VARCHAR(100),
    office_number VARCHAR(200),
    introduction TEXT,
    image_path VARCHAR(255),
    FOREIGN KEY (teacher_id) REFERENCES USERS(User_id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE feedbacks
(
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    teacher_id INT,
    title VARCHAR(200) NOT NULL,
    body TEXT,
    status ENUM('pending','processing','finished') DEFAULT 'pending',
    is_read BOOLEAN DEFAULT FALSE,
    feedback_from_teachers TEXT,
    sent_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES Teachers(teacher_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Room_application
(
    application_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    target_room_id INT,
    target_bed_number INT,
    contents TEXT NOT NULL,
    target_room_type VARCHAR(200),
    target_room_peoples INT,
    target_rest_time VARCHAR(300),
    teacher_id INT,
    status ENUM('pending','processing','rejected','approved','completed') DEFAULT 'pending',
    type ENUM('application','exchange','dropout'),
    feedback TEXT,
    application_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    feedback_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (target_room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES Teachers(teacher_id) ON DELETE CASCADE ON UPDATE CASCADE

);