-- 1. What is the deadliest/least deadly disaster type, on average?

    -- deadliest
    SELECT Disaster.type
    FROM Disaster
    INNER JOIN ImpactReport
    ON Disaster.ID = ImpactReport.disaster_ID
    ORDER BY ImpactReport.casualties DESC
    LIMIT 1;

    -- least deadly
    SELECT Disaster.type
    FROM Disaster
    INNER JOIN ImpactReport
    ON Disaster.ID = ImpactReport.disaster_ID
    ORDER BY ImpactReport.casualties ASC
    LIMIT 1;


-- 2. What is the deadliest specific disaster in the database?

SELECT Disaster.ID
FROM Disaster
INNER JOIN ImpactReport
ON Disaster.ID = ImpactReport.disaster_ID
ORDER BY casualties DESC
LIMIT 1;


-- 3. What is the most/least common disaster type?

SELECT type, COUNT(Disaster.type)
FROM Disaster
GROUP BY type;


-- 4. What is the most/least common disaster per area?

SELECT 
    D.type, city, state, COUNT(*) AS occurances
FROM
    (SELECT 
        city, state, DP.disaster_ID AS disID
    FROM
        ExactLocation EL
    RIGHT JOIN DisasterPath DP ON EL.longitude = DP.longitude
        AND EL.latitude = DP.latitude) cityTbl
        RIGHT JOIN
    Disaster D ON cityTbl.disID = D.ID
GROUP BY cityTbl.city
ORDER BY state, city, occurances DESC;


-- 5. What disaster type has the longest/shortest duration?

    -- longest
    SELECT type, AVG(dur) AS Duration
    FROM (
    	SELECT *, DATEDIFF(end_time, start_time) AS dur
    	FROM Disaster
        WHERE end_time IS NOT NULL
    ) AS DisasterDuration
    GROUP BY type
    ORDER BY Duration DESC
    LIMIT 1;
    
    -- shortest
    SELECT type, AVG(dur) AS Duration
    FROM (
        SELECT *, DATEDIFF(end_time, start_time) AS dur
        FROM Disaster
        WHERE end_time IS NOT NULL
    ) AS DisasterDuration
    GROUP BY type
    ORDER BY Duration ASC
    LIMIT 1;


-- 6. What disaster type produces the most injuries?

SELECT type, SUM(total) AS Total_injuries
FROM (
	SELECT type, permanent_injuries + temporary_injuries AS total
	FROM Disaster d
	JOIN ImpactReport i
	ON d.ID = i.disaster_ID
) AS Injuries
GROUP BY type
ORDER BY Total_injuries DESC
LIMIT 1;


-- 7. What type of disaster has the highest number of occurrences?

SELECT type, COUNT(type) AS occurences
FROM Disaster
GROUP BY type;


-- 8. What type of disaster is typically the most expensive in terms of relief and cleanup?

SELECT type, AVG(Expenses) AS Average_Expense
FROM (
    SELECT type, cost*quantity AS Expenses
    FROM (	
        SELECT cost, quantity, disaster_ID
    	FROM (
    		SELECT r.cost_per_unit AS cost, i.quantity AS quantity, i.campaign_ID
    		FROM ReliefItem r
    		JOIN ItemsSupplied i
    		ON r.item_ID = i.item_ID
        ) AS relief_items
    	JOIN ReliefCampaign
    	ON relief_items.campaign_ID = ReliefCampaign.campaign_ID
    ) AS relief
JOIN Disaster
ON relief.disaster_ID = Disaster.ID
) AS result
GROUP BY type
ORDER BY Average_Expense DESC
LIMIT 1;


-- 9. What time of year has the most/least disaster activity?

    -- most
    SELECT SUBSTRING(start_time, 6,2) AS month, COUNT(*) activity
    FROM Disaster
    WHERE start_time != "0000-00-00"
    GROUP BY month
    ORDER BY activity DESC
    LIMIT 1;

    -- least
    SELECT SUBSTRING(start_time, 6,2) AS month, COUNT(*) activity
    FROM Disaster
    WHERE start_time != "0000-00-00"
    GROUP BY month
    ORDER BY activity ASC
    LIMIT 1;


-- 10. How many disasters happen per year?

SELECT YEAR(start_time) AS 'Year', COUNT(*) AS 'Disasters in year'
FROM Disaster
GROUP BY YEAR(start_time)
ORDER BY Year ASC;


-- 11. Are there any disasters that have become more survivable as the years have gone by and technology has progressed? If so, what types of disasters are we better at surviving now?

SELECT 
    type, YEAR(D.start_time) AS year, SUM(IR.casualties) AS total_deaths
FROM
    Disaster D
INNER JOIN ImpactReport IR ON D.ID = IR.disaster_ID
GROUP BY YEAR(D.start_time), type
ORDER BY type, year;


-- 12. Have disasters become worse over time with our advancing technologies pumping more CO2 into the atmosphere?

SELECT 
    type, YEAR(D.start_time) AS year, SUM(IR.casualties) AS total_deaths, SUM(IR.permanent_injuries) AS total_permanent_injuries, SUM(IR.temporary_injuries) AS total_temp_injuries
FROM
    Disaster D
INNER JOIN ImpactReport IR ON D.ID = IR.disaster_ID
GROUP BY YEAR(D.start_time), type
ORDER BY type, year;


-- 13. Does warning time have a noticeable affect on the survivability of a disaster?

SELECT 
    type, TIMEDIFF(D.start_time, D.time_of_warning) AS amount_of_warning, IR.casualties AS total_deaths
FROM
    Disaster D
INNER JOIN ImpactReport IR ON D.ID = IR.disaster_ID
WHERE D.time_of_warning IS NOT NULL
ORDER BY type, amount_of_warning;


-- 14. Which disaster types received the most/least aid afterward?

SELECT type AS 'Disaster Type', campaign_cost AS 'Amount of aid (USD)'
FROM (
    SELECT type, ReliefCampaign.campaign_ID, SUM(quantity*cost_per_unit) AS campaign_cost
    FROM ReliefCampaign
    NATURAL JOIN ItemsSupplied
    NATURAL JOIN ReliefItem
    JOIN Disaster ON (Disaster.ID = ReliefCampaign.disaster_ID)
    GROUP BY campaign_ID) AS campaign_sums
ORDER BY campaign_cost DESC, type;


-- 15. What is the most dangerous location?

SELECT city, state, COUNT(*) AS disaster_occurrences
FROM ExactLocation EL
JOIN DisasterPath DP ON
    (EL.longitude = DP.longitude AND 
        EL.latitude = DP.latitude)
GROUP BY city, state
ORDER BY disaster_occurrences DESC;


-- 16. Which cause more casualties, manmade or natural disasters?

SELECT D.type AS disaster_type, SUM(IR.casualties) AS total_casualties
FROM ImpactReport IR
INNER JOIN Disaster D ON IR.disaster_ID = D.ID
GROUP BY D.type
ORDER BY total_casualties DESC;


-- 17. Who offers the most relief campaigns overall between government and private organizations?

SELECT np_count AS "private count", govt_count AS "govt count"
FROM (
    SELECT COUNT(*) AS np_count 
    FROM ReliefCampaign
    NATURAL JOIN ReliefAgency
    WHERE government_run = 0) AS np,
    (
    SELECT COUNT(*) AS govt_count
    FROM ReliefCampaign
    NATURAL JOIN ReliefAgency
    WHERE government_run = 1) AS govt;


-- 18. What specific relief agency has responded to the most disasters?

SELECT DISTINCT rc1.agency_ID, name
FROM ReliefAgency
JOIN ReliefCampaign rc1 ON (ReliefAgency.agency_ID = rc1.agency_ID)
HAVING 
    (
    SELECT COUNT(*)
    FROM ReliefCampaign rc2
    WHERE rc2.agency_ID = ReliefAgency.agency_ID
    ) = (
    SELECT MAX(count) 
    FROM (
        SELECT COUNT(*) AS count 
        FROM ReliefCampaign rc3 
        GROUP BY rc3.agency_id
    ) counts);


-- 19. What is the highest/lowest number of items used in a relief campaign?

	-- highest
	SELECT 
		MAX(tbl.quantity) AS quantity,
		tbl.campaign_ID,
		tbl.itemID,
		tbl.RI_Name
	FROM
		(SELECT 
			itmS.quantity,
				itmS.campaign_ID,
				itmS.item_ID AS itemID,
				RI.name AS RI_Name
		FROM
			ItemsSupplied itmS
		LEFT JOIN ReliefItem RI ON itmS.item_ID = RI.item_ID
		GROUP BY campaign_ID) tbl;

	-- lowest
	SELECT 
		MIN(tbl.quantity) AS quantity,
		tbl.campaign_ID,
		tbl.itemID,
		tbl.RI_Name
	FROM
		(SELECT 
			itmS.quantity,
				itmS.campaign_ID,
				itmS.item_ID AS itemID,
				RI.name AS RI_Name
		FROM
			ItemsSupplied itmS
		LEFT JOIN ReliefItem RI ON itmS.item_ID = RI.item_ID
		GROUP BY campaign_ID) tbl;


-- 20. How much was the most/least expensive relief campaign?

	-- most
	SELECT 
		totalTbl.campaignID, MAX(totalTbl.total)
	FROM
		(SELECT 
			RC.campaign_ID AS campaignID, SUM(costTbl.totals) AS total
		FROM
			(SELECT 
			RI.item_ID,
				itmS.campaign_ID AS campaignID,
				(RI.cost_per_unit * itmS.quantity) AS totals
		FROM
			ReliefItem RI
		RIGHT JOIN ItemsSupplied itmS ON RI.item_ID = itmS.item_ID) costTbl
		LEFT JOIN ReliefCampaign RC ON costTbl.campaignID = RC.campaign_ID
		GROUP BY RC.campaign_ID) totalTbl;
        
	-- least
	SELECT 
		totalTbl.campaignID, MIN(totalTbl.total)
	FROM
		(SELECT 
			RC.campaign_ID AS campaignID, SUM(costTbl.totals) AS total
		FROM
			(SELECT 
			RI.item_ID,
				itmS.campaign_ID AS campaignID,
				(RI.cost_per_unit * itmS.quantity) AS totals
		FROM
			ReliefItem RI
		RIGHT JOIN ItemsSupplied itmS ON RI.item_ID = itmS.item_ID) costTbl
		LEFT JOIN ReliefCampaign RC ON costTbl.campaignID = RC.campaign_ID
		GROUP BY RC.campaign_ID) totalTbl;


-- 21. Are there certain locations that are more likely to receive more aid for the same disaster?

SELECT
    DISTINCT totalTbl.disID, D.type, D.magnitude, city, totalTbl.total AS aid
FROM
    (SELECT 
        city, DP.disaster_ID AS disID
    FROM
        ExactLocation EL
    RIGHT JOIN DisasterPath DP ON EL.longitude = DP.longitude
        AND EL.latitude = DP.latitude) cityTbl
        RIGHT JOIN
    Disaster D ON cityTbl.disID = D.ID
        LEFT JOIN
    ImpactReport IR ON D.ID = IR.disaster_ID
        INNER JOIN
    ReliefCampaign RC ON D.ID = RC.disaster_ID
        INNER JOIN
    ItemsSupplied itmS ON RC.campaign_ID = itmS.campaign_ID
        INNER JOIN
    ReliefItem RI ON itmS.item_ID = RI.item_ID
        INNER JOIN
    (SELECT 
        totTbl.dID AS disID, totTbl.campID AS campaignID, SUM(totTbl.tot) AS total
    FROM
        (SELECT 
        RC.disaster_ID AS dID,
            RC.campaign_ID AS campID,
            SUM(costTbl.totals) AS tot
    FROM
        (SELECT 
        RI.item_ID,
            itmS.campaign_ID AS campaignID,
            (RI.cost_per_unit * itmS.quantity) AS totals
    FROM
        ReliefItem RI
    RIGHT JOIN ItemsSupplied itmS ON RI.item_ID = itmS.item_ID) costTbl
    LEFT JOIN ReliefCampaign RC ON costTbl.campaignID = RC.campaign_ID
    GROUP BY RC.campaign_ID) totTbl
    GROUP BY totTbl.dID) totalTbl ON D.ID = totalTbl.disID
ORDER BY D.type , D.magnitude , city;


-- 22. Which disasters are likely to re-occur in the same area?

SELECT 
    D.type, city, COUNT(*) AS occurances
FROM
    (SELECT 
        city, DP.disaster_ID AS disID
    FROM
        ExactLocation EL
    RIGHT JOIN DisasterPath DP ON EL.longitude = DP.longitude
        AND EL.latitude = DP.latitude) cityTbl
        RIGHT JOIN
    Disaster D ON cityTbl.disID = D.ID
GROUP BY cityTbl.city
ORDER BY occurances DESC, city;


-- 23. If a disaster does re-occur, will it be worse or not as bad?

SELECT 
    D.type,
    city,
    D.magnitude,
    IR.casualties,
    IR.permanent_injuries,
    IR.temporary_injuries,
    IR.buildings_destroyed
FROM
    (SELECT 
        city, DP.disaster_ID AS disID
    FROM
        ExactLocation EL
    RIGHT JOIN DisasterPath DP ON EL.longitude = DP.longitude
        AND EL.latitude = DP.latitude) cityTbl
        RIGHT JOIN
    Disaster D ON cityTbl.disID = D.ID
        LEFT JOIN
    ImpactReport IR ON D.ID = IR.disaster_ID
ORDER BY D.type , city;


-- 24. What is the average relief campaign duration for a certain disaster?

SELECT 
    D.type, AVG(daysTbl.Days) AS days
FROM
    (SELECT 
        RC.disaster_ID AS disID,
            DATEDIFF(RC.end_date, RC.start_date) AS Days
    FROM
        ReliefCampaign RC) daysTbl
        INNER JOIN
    Disaster D ON daysTbl.disID = D.ID
GROUP BY D.type;


-- 25. How do the numbers combine when multiple disasters hit at once?

SELECT DISTINCT
    D1.*, D2.*
FROM
    (Disaster D1
    LEFT JOIN DisasterPath DP1 ON D1.ID = DP1.disaster_ID
    LEFT JOIN ExactLocation EL1 ON DP1.longitude = EL1.longitude
        AND DP1.latitude = EL1.latitude)
        INNER JOIN
    (Disaster D2
    LEFT JOIN DisasterPath DP2 ON D2.ID = DP2.disaster_ID
    LEFT JOIN ExactLocation EL2 ON DP2.longitude = EL2.longitude
        AND DP2.latitude = EL2.latitude) ON (D2.start_time >= D1.start_time
        AND D2.start_time <= D1.end_time)
        AND D1.ID <> D2.ID
WHERE
    EL1.city = EL2.city;


-- 26. How does the death toll grow as a disaster with a long duration takes place? (linear, exponential, logarithmic)

SELECT 
    D.ID,
    D.type,
    EL.city,
    D.magnitude,
    DATEDIFF(D.end_time, D.start_time) AS days,
    IR.casualties
FROM
    Disaster D
        INNER JOIN
    DisasterPath DP ON D.ID = DP.disaster_ID
        INNER JOIN
    ExactLocation EL ON DP.longitude = EL.longitude
        AND DP.latitude = EL.latitude
        INNER JOIN
    ImpactReport IR ON D.ID = IR.disaster_ID
ORDER BY D.type , EL.city , D.magnitude , days;


-- 27. How many people in total were affected?

SELECT 
    D.ID,
    D.type,
    IR.casualties,
    IR.permanent_injuries,
    IR.temporary_injuries,
    (IR.casualties + IR.permanent_injuries + IR.temporary_injuries) AS total_affected,
    SUM(LP.population_size) AS total_population
FROM
    Disaster D
        INNER JOIN
    ImpactReport IR ON D.ID = IR.disaster_ID
        INNER JOIN
    DisasterPath DP ON D.ID = DP.disaster_ID
        INNER JOIN
    ExactLocation EL ON DP.longitude = EL.longitude
        AND DP.latitude = EL.latitude
        INNER JOIN
    LocationPopulation LP ON EL.city = LP.city
GROUP BY D.ID;


-- 28. How many fatalities per year are attributed to disasters?

SELECT 
    AVG(ytTbl.yearly_totals) AS avg_deaths_per_year
FROM
    (SELECT 
        COUNT(IR.casualties) AS yearly_totals
    FROM
        Disaster D
    INNER JOIN ImpactReport IR
    GROUP BY YEAR(D.start_time)) AS ytTbl;
