-- 
-- This file is used to create every table in DB
--


-- Create Disaster Table

CREATE TABLE Disaster(
	ID VARCHAR(255) PRIMARY KEY,
	type VARCHAR(50) NOT NULL,
	start_time DATE,
	end_time DATE,
	time_of_warning DATE,
	wind_speed INTEGER,
	rainfall INTEGER,
	flood_depth INTEGER,
	magnitude INTEGER,
	cause VARCHAR(50)
)

-- Create LocationPopulation Table

CREATE TABLE LocationPopulation(
	city VARCHAR(255) NOT NULL,
	state VARCHAR(255) NOT NULL,
	population_size INTEGER,
	PRIMARY KEY(city, state),
)

-- Create ExactLocation Table

CREATE TABLE ExactLocation(
	city VARCHAR(255) NOT NULL,
	state VARCHAR(255) NOT NULL,
	latitude DECIMAL(10,8),
	longitude DECIMAL(11,8),
	PRIMARY KEY(longitude, latitude),
	FOREIGN KEY(city, state) REFERENCES LocationPopulation(city, state)
)

-- Create DisasterPath Table

CREATE TABLE DisasterPath(
	disaster_ID VARCHAR(255),
	longitude DECIMAL(11,8),
	latitude DECIMAL(10,8),
	order_in_path INTEGER,
	PRIMARY KEY(disaster_ID, longitude, latitudes),
	FOREIGN KEY(disaster_ID) REFERENCES Disaster(id)
	FOREIGN KEY(longitude, latitude) REFERENCES ExactLocation(longitude, latitude)
)

-- Create ReliefItem

CREATE TABLE ReliefItem(
	item_ID VARCHAR(255) PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	cost_per_unit DECIMAL(1000,2) NOT NULL
)

-- Create ReliefAgency

CREATE TABLE ReliefAgency(
	agency_ID VARCHAR(255) PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	founded DATE NOT NULL,
	for_profit BOOLEAN NOT NULL,
	government_run BOOLEAN NOT NULL
)

-- Create ImpactReport Table

CREATE TABLE ImpactReport(
	report_ID VARCHAR(255) PRIMARY KEY,
	buildings_destroyed INTEGER,
	casualties INTEGER,
	permanent_injuries INTEGER,
	temporary_injuries INTEGER,
	disaster_ID VARCHAR(255),
	FOREIGN KEY(disaster_ID) REFERENCES Disaster(id)
)

-- Create ReliefCampaign Table

CREATE TABLE ReliefCampaign(
	campaign_ID VARCHAR(255) PRIMARY KEY,
	start_date DATE,
	end_date DATE,
	agency_ID VARCHAR(255),
	disaster_ID VARCHAR(255),
	FOREIGN KEY(agency_ID) REFERENCES ReliefAgency(agency_ID),
	FOREIGN KEY(disaster_ID) REFERENCES Disaster(id)
)

-- Create ItemsSupplied Table

CREATE TABLE ItemsSupplied(
	item_ID VARCHAR(255),
	campaign_ID VARCHAR(255),
	quantity INTEGER,
	PRIMARY KEY(item_ID, campaign_ID),
	FOREIGN KEY(campaign_ID) REFERENCES ReliefCampaign(campaign_ID)
)

-- This table will log changes to the Disaster table

CREATE TABLE Disaster_Log(
	log_date DATETIME, 
	disaster_ID VARCHAR(255) REFERENCES Disaster(ID),
	new_type VARCHAR(50) NOT NULL REFERENCES Disaster(type),
	old_type VARCHAR(50) NOT NULL REFERENCES Disaster(type),
	new_start_time DATE REFERENCES Disaster(start_time),
	old_start_time DATE REFERENCES Disaster(start_time),
	new_end_time DATE REFERENCES Disaster(end_time),
	old_end_time DATE REFERENCES Disaster(end_time),
	new_time_of_warning DATE REFERENCES Disaster(time_of_warning),
	old_time_of_warning DATE REFERENCES Disaster(time_of_warning),
	new_wind_speed INTEGER REFERENCES Disaster(wind_speed),
	old_wind_speed INTEGER REFERENCES Disaster(wind_speed),
	new_rainfall INTEGER REFERENCES Disaster(rainfall),
	old_rainfall INTEGER REFERENCES Disaster(rainfall),
	new_flood_depth INTEGER REFERENCES Disaster(flood_depth),
	old_flood_depth INTEGER REFERENCES Disaster(flood_depth),
	new_magnitude INTEGER REFERENCES Disaster(magnitude),
	old_magnitude INTEGER REFERENCES Disaster(magnitude),
	new_cause VARCHAR(50) REFERENCES Disaster(cause),
	old_cause VARCHAR(50) REFERENCES Disaster(cause),
	PRIMARY KEY(log_date, disaster_ID)
);
