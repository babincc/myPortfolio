# Relational Schemas (draft 2)
*goes with* [*e-r diagram draft 3*](e-r%20diagram%20draft%203.jpg)

<br>

Disaster(**ID**, type, start_time, end_time, time_of_warning, wind_speed, rainfall, flood_depth, magnitude, cause, **longitude**, **latitude**)

DisasterPath(**disaster_ID**, order, **longitude**, **latitude**)

Location(**longitude**, **latitude** , city, state, population_size)

ImpactReport(**report_ID**, buildings_destroyed, casualties, permanent_injuries, temporary_injuries, **disaster_ID**)

ReliefCampaign(**ID**, start_date, end_date, **agency_ID**, **disaster_ID**)

ReliefAgency(**ID**, name, founded, for_profit, government_run)

ReliefSupplies(**ID**, name, cost_per_unit, quantity, **campaign_ID**)
