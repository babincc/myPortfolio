-- gets everything from the natural disaster category

CREATE VIEW natural_disasters AS
SELECT *
FROM Disaster
WHERE type = 'hurricane' OR type = 'tornado';



-- gets everything from the manmade disaster category

CREATE VIEW manmade_disasters AS
SELECT *
FROM Disaster
WHERE type = 'plane_crash' OR type = 'vehicle_crash';
