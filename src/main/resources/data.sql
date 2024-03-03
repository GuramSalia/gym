-- Insert Trainers
INSERT INTO Users (FirstName, LastName, Username, Password, IsActive) VALUES
('Tim', 'Smith', 'Tim.Smith', 'abcdefghij', TRUE),
('Sam', 'Jones', 'Sam.Jones', 'bcdefghijk', TRUE),
('John', 'Doe', 'John.Doe', 'ijklmnopqr', TRUE),
('Jane', 'Smith', 'Jane.Smith', 'mnopqrstuv', TRUE);

INSERT INTO Customers (DateOfBirth, Address, UserID) VALUES
(DATE '2000-02-21', 'address of customer 1', 3),
(DATE '2000-01-21', 'address of customer 2', 4);

-- Insert TrainingTypes
INSERT INTO TrainingTypes (TrainingTypeName) VALUES
('CARDIO'),
('STRENGTH'),
('HIIT'),
('YOGA'),
('PILATES'),
('GROUP'),
('PERSONAL');

-- Insert Trainers
INSERT INTO Trainers (UserID, TrainingTypeID) VALUES
(1, 1), -- Tim is a CARDIO trainer
(2, 2); -- Sam is a STRENGTH trainer

-- Insert Trainings
INSERT INTO Trainings (CustomerID, TrainerID, TrainingName, TrainingTypeID, TrainingDate, TrainingDuration) VALUES
(1, 1, 'Cardio Workout', 1, DATE '2024-02-21', 60),
(2, 2, 'Strength Training', 2, DATE '2024-02-22', 45);