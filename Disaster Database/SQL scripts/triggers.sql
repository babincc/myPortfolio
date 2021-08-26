-- Log changes to the Disaster table

DELIMITER //

CREATE TRIGGER Disaster_Change_Log BEFORE UPDATE ON Disaster
	FOR EACH ROW
	BEGIN
		IF NEW.type <> OLD.type OR NEW.start_time <> OLD.start_time OR NEW.end_time <> OLD.end_time OR NEW.time_of_warning <> OLD.time_of_warning OR NEW.wind_speed <> OLD.wind_speed OR NEW.rainfall <> OLD.rainfall OR NEW.flood_depth <> OLD.flood_depth OR NEW.magnitude <> OLD.magnitude OR NEW.cause <> OLD.cause THEN
			INSERT INTO Disaster_Log VALUES (NOW(), OLD.ID, NEW.type, OLD.type, NEW.start_time, OLD.start_time, NEW.end_time, OLD.end_time, NEW.time_of_warning, OLD.time_of_warning, NEW.wind_speed, OLD.wind_speed, NEW.rainfall, OLD.rainfall, NEW.flood_depth, OLD.flood_depth, NEW.magnitude, OLD.magnitude, NEW.cause, OLD.cause);
		END IF;
	END //	

DELIMITER ;
