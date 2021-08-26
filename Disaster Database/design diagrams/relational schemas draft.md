# Relational Schemas (draft)
*goes with* [*e-r diagram draft 2*](e-r%20diagram%20draft%202.jpg)

<br>

Incident(**ID**, start_time, end_time, time_of_warning)

DisasterType(type, wind_speed, rainfall_inches, flood_depth, path, magnitude, cause)

Location(**longitude**, **latitude** , city, state, area_type, population_size)

Impact(buildings_destroyed, casualties, injuries)

ReliefCampaign(**ID**, name, start_date, end_date)

ReliefAgency(**ID**, name, founded, for_profit, government_run)

ReliefSupplies(**ID**, name, cost_per_unit, quantity)
