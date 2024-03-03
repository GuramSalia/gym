CREATE TABLE IF NOT EXISTS Users (
    UserID SERIAL PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    IsActive BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS Customers (
    CustomerID SERIAL PRIMARY KEY,
    DateOfBirth DATE,
    Address VARCHAR(255),
    UserID INT NOT NULL UNIQUE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS TrainingTypes (
    TrainingTypeID SERIAL PRIMARY KEY,
    TrainingTypeName VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Trainers (
    TrainerID SERIAL PRIMARY KEY,
    TrainingTypeID INT,
    UserID INT NOT NULL UNIQUE,
    FOREIGN KEY (TrainingTypeID) REFERENCES TrainingTypes(TrainingTypeID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Trainings (
    TrainingID SERIAL PRIMARY KEY,
    CustomerID INT,
    TrainerID INT,
    TrainingName VARCHAR(255) NOT NULL,
    TrainingTypeID INT,
    TrainingDate DATE NOT NULL,
    TrainingDuration INT NOT NULL,
    FOREIGN KEY (TrainingTypeID) REFERENCES TrainingTypes(TrainingTypeID),
    FOREIGN KEY (TrainerID) REFERENCES Trainers(TrainerID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Customers_Trainers (
    CustomerID INT,
    TrainerID INT,
    PRIMARY KEY (CustomerID, TrainerID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE,
    FOREIGN KEY (TrainerID) REFERENCES Trainers(TrainerID) ON DELETE CASCADE
);


--CREATE TABLE IF NOT EXISTS Trainings_Customers (
--    TrainingID INT,
--    CustomerID INT,
--    PRIMARY KEY (TrainingID, CustomerID),
--    FOREIGN KEY (TrainingID) REFERENCES Trainings(TrainingID),
--    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
--    UNIQUE (CustomerID)  -- Ensure that each Customer is associated with a specific Training only once
--);
--
--CREATE TABLE IF NOT EXISTS Trainings_Trainers (
--    TrainingID INT,
--    TrainerID INT,
--    PRIMARY KEY (TrainingID, TrainerID),
--    FOREIGN KEY (TrainingID) REFERENCES Trainings(TrainingID),
--    FOREIGN KEY (TrainerID) REFERENCES Trainers(TrainerID),
--    UNIQUE (TrainerID)  -- Ensure that each Trainer is associated with a specific Training only once
--);
--
--
--
--CREATE TABLE IF NOT EXISTS Trainings_TrainingTypes (
--    TrainingID INT,
--    TrainingTypeID INT,
--    PRIMARY KEY (TrainingID, TrainingTypeID),
--    FOREIGN KEY (TrainingID) REFERENCES Trainings(TrainingID),
--    FOREIGN KEY (TrainingTypeID) REFERENCES TrainingTypes(TrainingTypeID),
--    UNIQUE (TrainingTypeID)  -- Ensure that each TrainingType is associated with a specific Training only once
--);

--CREATE TABLE IF NOT EXISTS Trainers_TrainingTypes (
--    TrainerID INT,
--    TrainingTypeID INT,
--    PRIMARY KEY (TrainerID, TrainingTypeID),
--    FOREIGN KEY (TrainerID) REFERENCES Trainers(TrainerID),
--    FOREIGN KEY (TrainingTypeID) REFERENCES TrainingTypes(TrainingTypeID)
--);
