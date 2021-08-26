# proof of query testing

This document offers proof that the queries are tested and yield the expected results.

## Table of Contents

- [1. What is the deadliest/least deadly disaster type, on average?][1]
- [2. What is the deadliest specific disaster in the database?][2]
- [3. What is the most/least common disaster type?][3]
- [4. What is the most/least common disaster per area?][4]
- [5. What disaster type has the longest/shortest duration?][5]
- [6. What disaster type produces the most injuries?][6]
- [7. What type of disaster has the highest number of occurrences?][7]
- [8. What type of disaster is typically the most expensive in terms of relief and cleanup?][8]
- [9. What time of year has the most/least disaster activity?][9]
- [10. How many disasters happen per year?][10]
- [11. Are there any disasters that have become more survivable as the years have gone by and technology has progressed? If so, what types of disasters are we better at surviving now?][11]
- [12. Have disasters become worse over time with our advancing technologies pumping more CO2 into the atmosphere?][12]
- [13. Does warning time have a noticeable affect on the survivability of a disaster?][13]
- [14. Which disaster types received the most/least aid afterward?][14]
- [15. What is the most dangerous location?][15]
- [16. Which cause more casualties, manmade or natural disasters?][16]
- [17. Who offers the most relief campaigns overall between government and private organizations?][17]
- [18. What specific relief agency has responded to the most disasters?][18]
- [19. What is the highest/lowest number of items used in a relief campaign?][19]
- [20. How much was the most/least expensive relief campaign?][20]
- [21. Are there certain locations that are more likely to receive more aid for the same disaster?][21]
- [22. Which disasters are likely to re-occur in the same area?][22]
- [23. If a disaster does re-occur, will it be worse or not as bad?][23]
- [24. What is the average relief campaign duration for a certain disaster?][24]
- [25. How do the numbers combine when multiple disasters hit at once?][25]
- [26. How does the death toll grow as a disaster with a long duration takes place? (linear, exponential, logarithmic)][26]
- [27. How many people in total were affected?][27]
- [28. How many fatalities per year are attributed to disasters?][28]

## Screenshots and Explanations

#### 1. What is the deadliest/least deadly disaster type, on average? [back to top][0]
![screenshot of SQL query and response][101]

This particular run is for the deadliest disaster type. The result is correct. Hurricanes have the highest total casualties in the database.

<br>

#### 2. What is the deadliest specific disaster in the database? [back to top][0]
![screenshot of SQL query and response][102]

This is correct. H-2000157 is a hurricane, and it has the highest death toll of any disaster in the database.

<br>

#### 3. What is the most/least common disaster type? [back to top][0]
![screenshot of SQL query and response][103]

These results do look funny, but they are accurate. There are 500 of each type of disaster in the database.

<br>

#### 4. What is the most/least common disaster per area? [back to top][0]
![screenshot of SQL query and response][104]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows.

<br>

#### 5. What disaster type has the longest/shortest duration? [back to top][0]
![screenshot of SQL query and response][105]

When running the query to get the longest duration, this is the result. It was expected to be hurricane. It is in hours (this is showing that hurricanes last for an average of 72 hours).

<br>

#### 6. What disaster type produces the most injuries? [back to top][0]
![screenshot of SQL query and response][106]

This is correct. Hurricanes account for the most injuries of all of the disaster types. This is permanent injuries and temporary injuries combined.

<br>

#### 7. What type of disaster has the highest number of occurrences? [back to top][0]
![screenshot of SQL query and response][107]

These results do look funny, but they are accurate. There are 500 of each type of disaster in the database.

<br>

#### 8. What type of disaster is typically the most expensive in terms of relief and cleanup? [back to top][0]
![screenshot of SQL query and response][108]

This is correct. Hurricanes cost more on average to cleanup after than any other disaster type in the database.

<br>

#### 9. What time of year has the most/least disaster activity? [back to top][0]
![screenshot of SQL query and response][109]

This is true. In the database, most disasters happen in February (this query is asking for most not least).

<br>

#### 10. How many disasters happen per year? [back to top][0]
![screenshot of SQL query and response][110]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows.

<br>

#### 11. Are there any disasters that have become more survivable as the years have gone by and technology has progressed? If so, what types of disasters are we better at surviving now? [back to top][0]
![screenshot of SQL query and response][111]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows. It is showing the proper data; so, the user can scroll down the list and see if the total_deaths get lower for any particular disaster type as time goes on.

<br>

#### 12. Have disasters become worse over time with our advancing technologies pumping more CO2 into the atmosphere? [back to top][0]
![screenshot of SQL query and response][112]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows. It is showing the proper data; so, the user can scroll down the list and see if the deaths and injuries get higher for any particular disaster type as time goes on.

<br>

#### 13. Does warning time have a noticeable affect on the survivability of a disaster? [back to top][0]
![screenshot of SQL query and response][113]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows. It is showing the proper data; so, the user can scroll down the list and see if the deaths and injuries get higher for any particular disaster type as the amount of warning gets bigger.

<br>

#### 14. Which disaster types received the most/least aid afterward? [back to top][0]
![screenshot of SQL query and response][114]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows. They are in order from most aid to least aid.

<br>

#### 15. What is the most dangerous location? [back to top][0]
![screenshot of SQL query and response][115]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows. They are in order from most dangerous to least dangerous.

<br>

#### 16. Which cause more casualties, manmade or natural disasters? [back to top][0]
![screenshot of SQL query and response][116]

These are accurate results from the database. They came in just as expected. They are ordered from most to least deadly; so, the user can scroll through and decide what is manmade and what is natural. Based on these results, nature is far more lethal than humans could ever be.

<br>

#### 17. Who offers the most relief campaigns overall between government and private organizations? [back to top][0]
![screenshot of SQL query and response][117]

These results are accurate. None of the government agencies in the database did any relief campaigns.

<br>

#### 18. What specific relief agency has responded to the most disasters? [back to top][0]
![screenshot of SQL query and response][118]

This is true. In the database the American Red Cross has sponsored 88 relief campaigns which is more than any other relief agency.

<br>

#### 19. What is the highest/lowest number of items used in a relief campaign? [back to top][0]
![screenshot of SQL query and response][119]

This query was run for the highest number of items, and it is true. After tornado T-1100000, Action Against Hunger used 30 crates of aluminum bottles. This is the largest quantity of any relief item used in a single relief campaign.

<br>

#### 20. How much was the most/least expensive relief campaign? [back to top][0]
![screenshot of SQL query and response][120]

This query was run for the most expensive campaign, and it is true. This is Action Against Hunger's relief campaign for tornado T-1100000. It is probably the most expensive relief campaign because of those 30 creates of aluminum bottles seen in query 19.

<br>

#### 21. Are there certain locations that are more likely to receive more aid for the same disaster? [back to top][0]
![screenshot of SQL query and response][121]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows. They are in order by type of disaster and the magnitude of it. This way, the user can scroll through and compare similar strength disasters in different locations and see how much aid was given to each location.

<br>

#### 22. Which disasters are likely to re-occur in the same area? [back to top][0]
![screenshot of SQL query and response][122]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows. They are in order from areas with most occurrences to least.

<br>

#### 23. If a disaster does re-occur, will it be worse or not as bad? [back to top][0]
![screenshot of SQL query and response][123]

All of these results are what is expected. They are ordered by type and location. They are also in chronological order; so, the user can scroll down and see if the disasters in the same locations get worse or not.

<br>

#### 24. What is the average relief campaign duration for a certain disaster? [back to top][0]
![screenshot of SQL query and response][124]

This is accurate. The results are as expected.

<br>

#### 25. How do the numbers combine when multiple disasters hit at once? [back to top][0]
![screenshot of SQL query and response][125]

These results are accurate. There are just no disasters that hit at the same time.

<br>

#### 26. How does the death toll grow as a disaster with a long duration takes place? (linear, exponential, logarithmic) [back to top][0]
![screenshot of SQL query and response][126]

Without manually checking each row (which would take hours), it does seem correct. Several rows were audited and they were accurate; so, it is assumed to be accurate for all of the rows. They are in order by disaster type to keep the same type of disaster grouped together. They are then grouped by city so that location can be the same in some cases for more accurate results. Then they are grouped by magnitude and the duration (in days) of the disaster. The user can scroll through knowing these orders and see whether or not longer durations make a difference in death toll.

<br>

#### 27. How many people in total were affected? [back to top][0]
![screenshot of SQL query and response][127]

These results are accurate. They are ordered by disaster ID. They show the number of people kill and injured in each disaster as well as the total population that the disaster could have affected. This way, the user can see how many people were able to escape tragedy as opposed to only seeing the ones who were devastated by the disaster.

<br>

#### 28. How many fatalities per year are attributed to disasters? [back to top][0]
![screenshot of SQL query and response][128]

This is an average for the number of people are killed every year because of disasters.

<br>

[0]: #table-of-contents
[1]: #1-what-is-the-deadliestleast-deadly-disaster-type-on-average-back-to-top
[2]: #2-what-is-the-deadliest-specific-disaster-in-the-database-back-to-top
[3]: #3-what-is-the-mostleast-common-disaster-type-back-to-top
[4]: #4-what-is-the-mostleast-common-disaster-per-area-back-to-top
[5]: #5-what-disaster-type-has-the-longestshortest-duration-back-to-top
[6]: #6-what-disaster-type-produces-the-most-injuries-back-to-top
[7]: #7-what-type-of-disaster-has-the-highest-number-of-occurrences-back-to-top
[8]: #8-what-type-of-disaster-is-typically-the-most-expensive-in-terms-of-relief-and-cleanup-back-to-top
[9]: #9-what-time-of-year-has-the-mostleast-disaster-activity-back-to-top
[10]: #10-how-many-disasters-happen-per-year-back-to-top
[11]: #11-are-there-any-disasters-that-have-become-more-survivable-as-the-years-have-gone-by-and-technology-has-progressed-if-so-what-types-of-disasters-are-we-better-at-surviving-now-back-to-top
[12]: #12-have-disasters-become-worse-over-time-with-our-advancing-technologies-pumping-more-co2-into-the-atmosphere-back-to-top
[13]: #13-does-warning-time-have-a-noticeable-affect-on-the-survivability-of-a-disaster-back-to-top
[14]: #14-which-disaster-types-received-the-mostleast-aid-afterward-back-to-top
[15]: #15-what-is-the-most-dangerous-location-back-to-top
[16]: #16-which-cause-more-casualties-manmade-or-natural-disasters-back-to-top
[17]: #17-who-offers-the-most-relief-campaigns-overall-between-government-and-private-organizations-back-to-top
[18]: #18-what-specific-relief-agency-has-responded-to-the-most-disasters-back-to-top
[19]: #19-what-is-the-highestlowest-number-of-items-used-in-a-relief-campaign-back-to-top
[20]: #20-how-much-was-the-mostleast-expensive-relief-campaign-back-to-top
[21]: #21-are-there-certain-locations-that-are-more-likely-to-receive-more-aid-for-the-same-disaster-back-to-top
[22]: #22-which-disasters-are-likely-to-re-occur-in-the-same-area-back-to-top
[23]: #23-if-a-disaster-does-re-occur-will-it-be-worse-or-not-as-bad-back-to-top
[24]: #24-what-is-the-average-relief-campaign-duration-for-a-certain-disaster-back-to-top
[25]: #25-how-do-the-numbers-combine-when-multiple-disasters-hit-at-once-back-to-top
[26]: #26-how-does-the-death-toll-grow-as-a-disaster-with-a-long-duration-takes-place-linear-exponential-logarithmic-back-to-top
[27]: #27-how-many-people-in-total-were-affected-back-to-top
[28]: #28-how-many-fatalities-per-year-are-attributed-to-disasters-back-to-top
[101]: proof%20of%20query%20testing%20(screenshots)/1.png
[102]: proof%20of%20query%20testing%20(screenshots)/2.png
[103]: proof%20of%20query%20testing%20(screenshots)/3.png
[104]: proof%20of%20query%20testing%20(screenshots)/4.png
[105]: proof%20of%20query%20testing%20(screenshots)/5.png
[106]: proof%20of%20query%20testing%20(screenshots)/6.png
[107]: proof%20of%20query%20testing%20(screenshots)/7.png
[108]: proof%20of%20query%20testing%20(screenshots)/8.png
[109]: proof%20of%20query%20testing%20(screenshots)/9.png
[110]: proof%20of%20query%20testing%20(screenshots)/10.png
[111]: proof%20of%20query%20testing%20(screenshots)/11.png
[112]: proof%20of%20query%20testing%20(screenshots)/12.png
[113]: proof%20of%20query%20testing%20(screenshots)/13.png
[114]: proof%20of%20query%20testing%20(screenshots)/14.png
[115]: proof%20of%20query%20testing%20(screenshots)/15.png
[116]: proof%20of%20query%20testing%20(screenshots)/16.png
[117]: proof%20of%20query%20testing%20(screenshots)/17.png
[118]: proof%20of%20query%20testing%20(screenshots)/18.png
[119]: proof%20of%20query%20testing%20(screenshots)/19.png
[120]: proof%20of%20query%20testing%20(screenshots)/20.png
[121]: proof%20of%20query%20testing%20(screenshots)/21.png
[122]: proof%20of%20query%20testing%20(screenshots)/22.png
[123]: proof%20of%20query%20testing%20(screenshots)/23.png
[124]: proof%20of%20query%20testing%20(screenshots)/24.png
[125]: proof%20of%20query%20testing%20(screenshots)/25.png
[126]: proof%20of%20query%20testing%20(screenshots)/26.png
[127]: proof%20of%20query%20testing%20(screenshots)/27.png
[128]: proof%20of%20query%20testing%20(screenshots)/28.png
