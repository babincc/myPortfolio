# Normalized Relational Schemas (draft)
*goes with* [*relational schemas draft 2*](relational%20schemas%20draft%202.md) *and* [*e-r diagram draft 3*](e-r%20diagram%20draft%203.jpg)

<br>

Started with:

- Disaster(**ID**, type, start_time, end_time, time_of_warning, wind_speed, rainfall, flood_depth, path, magnitude, cause, **longitude**, **latitude**)

Breakdown:
- ID --> type, start_time, end_time, time_of_warning, wind_speed, rainfall, flood_depth, magnitude, cause, longitude, latitude
- disaster_ID, longitude, latitude --> order

Ended with - BCNF:

- Disaster(**ID**, type, start_time, end_time, time_of_warning, wind_speed, rainfall, flood_depth, magnitude, cause)

  >The Disaster relation identifies each particular disaster that occurs (ex. Hurricane Irma, The Tri-State Tornado)

- DisasterPath(**disaster_ID**, **longitude**, **latitude**, order)

  >The DisasterPath relation identifies each location along the path of the disaster in the order they were hit (ex. Florida -> Georgia -> South Carolina)

<br>

BCNF was used because to go further, longitude and latitude would have to be broken up. Those belong together and would become inefficient in a database search if they were separate.

<hr>

Started with:

- Location(**longitude**, **latitude**, city, state, population_size)

Breakdown:
- longitude, latitude --> city, state
- city, state --> population_size

Ended with - BCNF:

- ExactLocation(**longitude**, **latitude**, city, state)

  >The ExactLocation relation shows each location that a disaster hit as a specific coordinate location along with the corresponding city and state

- LocationPopulation(**city**, **state**, population_size)

  >The LocationPopulation relation shows how many people live in the cities

<br>

BCNF was used because to go further, longitude and latitude would have to be broken up. Those belong together and would become inefficient in a database search if they were separate.

<hr>

Started with:

- ImpactReport(**report_ID**, buildings_destroyed, casualties, permanent_injuries, temporary_injuries, **disaster_ID**)

Breakdown:
- report_ID --> buildings_destroyed, casualties, permanent_injuries, temporary_injuries, disaster_ID
- disaster_ID --> report_ID

Ended with - 4NF:

- ImpactReport(**report_ID**, buildings_destroyed, casualties, permanent_injuries, temporary_injuries, **disaster_ID**)

  >The ImpactReport relation gives a breakdown of the bad things that happened in the disaster

<br>

4NF was used because going into 5NF and breaking each attribute into its own relation with the report_ID would be inefficient and messy.

<hr>

Started with:

- ReliefCampaign(**campaign_ID**, start_date, end_date, **agency_ID**, **disaster_ID**)

Breakdown:
- campaign_ID --> start_date, end_date, agency_ID, disaster_ID

Ended with - 4NF:

- ReliefCampaign(**campaign_ID**, start_date, end_date, **agency_ID**, **disaster_ID**)

  >The ReliefCampaign relation shows each individual campaign that was launched by relief agencies and the specific disaster they were for

<br>

4NF was used because going into 5NF and breaking each attribute into its own relation with the campaign_ID would be inefficient and messy.

<hr>

Started with:

- ReliefAgency(**agency_ID**, name, founded, for_profit, government_run)

Breakdown:
- agency_ID --> name, founded, for_profit, government_run

Ended with - 4NF:

- ReliefAgency(**agency_ID**, name, founded, for_profit, government_run)

  >The ReliefAgency shows each relief agency and the basic information about them

<br>

4NF was used because going into 5NF and breaking each attribute into its own relation with the agency_ID would be inefficient and messy.

<hr>

Started with:

- ReliefSupplies(**item_ID**, name, cost_per_unit, quantity, **campaign_ID**)

Breakdown:
- item_ID --> name, cost_per_unit
- campaign_ID --> item_ID
- campaign_ID, item_ID --> quantity

Ended with - BCNF:

- ReliefItem(**item_ID**, name, cost_per_unit)

  >The ReliefItem relation shows each individual item that can be used in a relief campaign (ex. Blankets $45 for a bundle of 5 blankets)

- ItemsSupplied(**campaign_ID**, **item_ID**, quantity)

  >The ItemsSupplied relation links relief items to specific relief campaigns (ex. Campaign XYZ used 10,000 bandages)

<br>

BCNF was used because the ItemsSupplied relation cannot be broken up any more without losing relational data.
