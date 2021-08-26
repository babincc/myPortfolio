-- Count the number of disasters in this city

DELIMITER //

CREATE PROCEDURE count_disasters_for_city (
	IN cityName VARCHAR(255)
)
BEGIN
	SELECT COUNT(*) AS Disasters_Total
	FROM DisasterPath DP
		LEFT JOIN ExactLocation EL ON DP.longitude = EL.longitude AND DP.latitude = EL.latitude
	WHERE EL.city = cityName
	GROUP BY city;
END //	

DELIMITER ;
