# Problem Statement

The world we live in is very dangerous. There are deadly disasters happening every day, and it seems like there is nothing we can do to avoid them. All we can do is hope that we don't become a statistic. Hopefully, this project ends that way of thinking. Hopefully, it will be able to show people how to stay safe in our dangerous world.

The goal of this project is to gather and analyze data about the most common, deadly disasters around the world. This information will be used to find patterns and correlations with disasters and survival rates. The hope is that this research will give some insight into how to survive a deadly situation and better yet how to avoid them altogether.

This project will look into the most common natural disasters and manmade disasters. Natural disasters include weather events such as tornadoes, hurricanes, and floods as well as other lethal acts of nature such as earthquakes and landslides. Manmade disasters will include accidents like plane crashes as well as purposeful attacks like shootings. Some of these topics can get very dark, but remember, the goal is to let people know how to be safe in these situations and try to make the world a more survivable place.

This database may be used by local officials, government bodies, data scientists, the broader scientific community, and concerned individuals. Eventually, the goal is to get this database to be used by the government and other powerful groups that are in charge of public safety. The hope is that this database will be able to save lives someday. Since governments won't look at this database right away, it will have to be given to the people so they can educate themselves. This database is for everyone. With enough people concerned for the wellbeing of the public, they may be able to lobby and get this database into legislative groups. There is where it will have the most chance to actually help people.

Individuals and communities will have a database storing information regarding natural disasters happening locally and around the world. This database will contain information regarding the types of disasters that have occurred, as well as the time, location, and the damage that was caused. Aggregate data will allow users to be better informed about the type of hazards that exist in their area, as well as being able to recognize patterns across time. This information will help mitigate future damage to infrastructure, ease financial burdens, and prevent loss of life by allowing users to be informed and prepared.

The entity sets for the database are:
- **Disaster** - identifies each particular disaster that occurs (ex. Hurricane Irma, The Tri-State Tornado)
- **DisasterPath** - identifies each location along the path of the disaster in the order they were hit (ex. Florida -> Georgia -> South Carolina)
- **ExactLocation** - shows each location that a disaster hit as a specific coordinate location along with the corresponding city and state
- **LocationPopulation** - shows how many people live in the cities
- **ImpactReport** - gives a breakdown of the bad things that happened in the disaster (ex. casualties, buildings destroyed, injuries)
- **ReliefCampaign** - shows each individual campaign that was launched by relief agencies and the specific disaster they were for
- **ReliefAgency** - shows each relief agency and the basic information about them
- **ReliefItem** - shows each individual item that can be used in a relief campaign (ex. Blankets $45 for a bundle of 5 blankets)
- **ItemsSupplied** - links relief items to specific relief campaigns (ex. Campaign XYZ used 10,000 bandages)

<br>

## Queries

1. What is the deadliest/least deadly disaster type, on average?
2. What is the deadliest specific disaster in the database?
3. What is the most/least common disaster type?
4. What is the most/least common disaster per area?
5. What disaster type has the longest/shortest duration?
6. What disaster type produces the most injuries?
7. What type of disaster has the highest number of occurrences?
8. What type of disaster is typically the most expensive in terms of relief and cleanup?
9. What time of year has the most/least disaster activity?
10. How many disasters happen per year?
11. Are there any disasters that have become more survivable as the years have gone by and technology has progressed? If so, what types of disasters are we better at surviving now?
12. Have disasters become worse over time with our advancing technologies pumping more CO2 into the atmosphere?
13. Does warning time have a noticeable affect on the survivability of a disaster?
14. Which disaster types received the most/least aid afterward?
15. What is the most dangerous location?
16. Which cause more casualties, manmade or natural disasters?
17. Who offers the most relief campaigns overall between government and private organizations?
18. What specific relief agency has responded to the most disasters?
19. What is the highest/lowest number of items used in a relief campaign?
20. How much was the most/least expensive relief campaign?
21. Are there certain locations that are more likely to receive more aid for the same disaster?
22. Which disasters are likely to re-occur in the same area?
23. If a disaster does re-occur, will it be worse or not as bad?
24. What is the average relief campaign duration for a certain disaster?
25. How do the numbers combine when multiple disasters hit at once?
26. How does the death toll grow as a disaster with a long duration takes place? (linear, exponential, logarithmic)
27. How many people in total were affected?
28. How many fatalities per year are attributed to disasters?
