# data

This folder is used to store the data for the database as well as the generators for that data.

### File Name Key ###

Files starting with:

**"filler"** means the file is fake data to test the database with (ie. "filler_tornado_data.csv")

**"real"** means the file is real data (ie. "real_earthquake_data.csv")

**"mixed"** means the file contains both real and fake data (ie. "mixed_wild-fire_data.csv")

**"frag"** means the file contains fragments of data / it is incomplete. The the "frag" prefix must be combined with one of the other three to portray what type of incomplete data you have (ie. "frag_real_flood_data.csv") and the "frag" prefix must always be the first one in the name

NOTE: All data files end with "data.csv"

<br>

## data used in database

- [filler_hurricane_data.csv](filler_hurricane_data.csv) This is the data for hurricanes. It is entirely fabricated based on hurricane facts.
- [filler_plane-crash_data.csv](filler_plane-crash_data.csv) This is the data for plane crashes. It is entirely fabricated based on passenger plane facts.
- [filler_relief-campaign_data.csv](filler_relief-campaign_data.csv) This is the data for relief campaigns. It is entirely fabricated based on the disaster data made by DisGen.
- [filler_relief-supply_data.csv](filler_relief-supply_data.csv) This is the data for relief supplies. It is entirely fabricated based on the relief campaings made by RelGen.
- [filler_tornado_data.csv](filler_tornado_data.csv) This is the data for tornados. It is entirely fabricated based on tornado facts.
- [filler_vehicle-crash_data.csv](filler_vehicle-crash_data.csv) This is the data for vehicle crashes. It is entirely fabricated based on vehicle facts.
- [real_relief-agency_data.csv](real_relief-agency_data.csv) This is the data for the relief agencies. It is all real data (the agency id's were made up for this project, but they are not being counted as "fake data").

<br>

## data not used in database (it helped create the data that's used)

- [mixed_location_data.csv](mixed_location_data.csv) This is the data for the locations. It is a mix of real and filler data. The cities do belong in the countries they are listed with, and the populations are real; however, the coordinate ranges are off. If plotted on a map, they would draw boxes around the countries they represent. This was done to make programming infinitely easier.
- [real_supply-items_data.csv](real_supply-items_data.csv) This is the data for relief items. It is all real pricing data and covers common items that a disaster relief agency would need to take to a disaster area (the item id's were made up for this project, but they are not being counted as "fake data").

<br>

## helpful programs

I created these programs so that I could easily generate random but realistic data for the project.

- [DisGen](DisGen) is a program used to generate filler disaster data for this project. Details are in the README file in the DisGen folder.
- [Pseudotude](Pseudotude) is a program used to generate filler latitude and longitude range data for this project. Details are in the README file in the Pseudotude folder.
- [RelGen](RelGen) is a program used to generate filler disaster relief data for this project. Details are in the README file in the RelGen folder.
- [Tables R Us](../database%20tables/Tables%20R%20Us) is a program used to generate the database tables for this project. Details are in the README file in the /home/database tables/Tables R Us/ folder.
