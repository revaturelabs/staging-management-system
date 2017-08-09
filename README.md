# Staging Management System
* [Overview](#overview)
* [Setup](#setup)
* [Database](#database)
* [API](#api)
* [Reports](#Reports)


## Overview

### Mission and Scope

#### What problem does this project address?
This application addresses the complexities of managing human
resources that are currently in staging and awaiting placement with clients.
Additionally, the task of planning the allocation of future resources is also addressed.


#### What is the goal of this project?
The goal of this project is to provide a simple and concise system of
managing the status of associates as they move from training to staging to placement with
clients. Additionally, this project seeks to ease the burden of long-term planning.
By offering a resource forecasting functionality, more strategic planning of future resources will be attained.


#### What is the scope of this project?
The original concept behind this application was to give a Superuser the means to Add/View/Manage
human resources and a Manager user to be able to View/Manage said data. Its purpose is to aid in processing business
client requests for staffing and ensuring that associates that are mapped to clients are not accidentally placed with
other business clients.

<<<<<<< HEAD
## Setup 
=======
## Setup
>>>>>>> staging
## (!!!!!!!!!! THIS IS VERY IMPORTANT !!!!!!!!!!!!!!!!!!)

### Node, Webpack, and Babel
1. Download node from https://nodejs.org/en/download/
If there is any uncertainty here is a guide to downloading it
http://blog.teamtreehouse.com/install-node-js-npm-windows

2. Open up Git Bash, the command prompt or whatever else you typically use. Note that if you had this open before installing node
    you will have to reopen it. Navigate to the directory with package.json. It should be in the root folder of the repository.

3. In your command line type: <br />
    npm install <br />
this will install all of the dependencies that have been laid out in the package.json

4. Once all of the dependencies have been installed you can have web pack watch
your code to do this type: <br />
    npm run watch

What this does is allows Webpack and Babel to bundle and transpile all of the
javascript code and it's required dependencies into one js file.
So even though you are working in your individual js files it will all be
bundled into bundle.js. Note everytime you make changes in a js file if webpack is not
watching the code it will not update the bundle.js and the changes will not be reflected.<br />
!!!!!! IF YOU DO NOT HAVE THIS RUNNING YOU WILL NOT SEE THE CHANGES YOU MAKE IN JAVASCRIPT MAKE SURE TO READ THE ECLIPSE SETUP ALSO !!!!!!!!!!!


Why is this a good thing?
Our server now only has to serve up one js file for our entire application
and Bable transpiles it into an older version of JavaScript
that runs on browsers that don't support the newest standard of JavaScript

### Eclipse
Now that you have Webpack watching your code, eclipse doesn't like to automatically refresh
So you have 2 options.
i. Everytime your js code is changed webpack will bundle it and then you have to go into eclipse
and do a manual refresh. The shortcut should be f5. This is the inefficient way.
ii. The other option is to set up eclipse to automatically detect changes.
1. In eclipse click on window -> preference. This will open up a dialogue.
2. In the search bar at the top type in workspace.
3. Click on workspace.
4. Now at the right should be a checkbox that says "Refresh using native hooks or polling"
by default this is not selected but select it and then hit ok.

### Data Generation
* [Methods](#methods)
* [Implementations](#implementations)
* [Postman](#postman)
* [Setup Local Database](#localdatabase)

#### Methods
To generate Data we used three methods
  1. Creating Json objects by hand ex. PermissionLevels, Locations,...
  2. Json objects generated with https://www.mockaroo.com/ ex. associates, credentials,...
  3. Randomized creation services. Batches, Interviews,...

#### Implementations
Objects are delivered and methods are triggered by the postman collection located at misc/Mock-SMS-Data.postman_collection.json. This is what each request does...

(note: json objects can be found under the body tag within postman)

1. Batch Types - Calls the rest controller addBatchTypes in BatchController for each element in the json list, it adds each skill from the contained skill list, and then creates a batch with those skills and value.
2. Trainers - Creates a new trainer for each within the json list.
3. Clients - Adds client objects to the database. (Note: first two are priority the rest are not)
4. Locations - Adds locations to the database (Data not accurate should be changed to real locations and names)
5. Batches - Json object contains a list of objects with a list of associates we generated with Mockaroo and randomized the remaining batch properties. This calls the rest controller addMockBatches in BatchController which calls the service addMockBatches in BatchService. For each batch object...
    * Assigns a trainer.
    * Makes the start dates one week apart beginning on monday of the first week of 2017.
    * Makes the end date 70 days after the start date.
    * Assigns a random batch type.
    * sets location to Revature VA (Should probably be randomized in the future).
    * Adds the list of associates to the db.
6. PermissionLevels - Creates PermissionLevels: ADMIN, MGR, ASSOC
7. InterviewStatuses - Creates InterviewStatuses:
  * MAPPED - For priority clients only when interview is in the future.
  * PENDING_RESPONSE - Interview is over awaiting decision.
  * SCHEDULED - For regular clients when interview is in the future.
  * LIKED - Client liked associate but did not hire.
  * CONFIRMED - Client hired associate.
  * CANCELLED - Interview was cancelled.
  * NOT_INTERESTED - Client failed the interview.
8. InterviewQuestions - Creates a list of 50 OOP InterviewQuestions that could be applied to all batch types.
9. Managers - Creates managers and an admin, first two are the most important with usernames admin and man and their passwords are pass
10. Marketers - Creates Marketers the first one is most important having username/password = mark/pass.
11. Simulate hiring process - This function calls the rest controller generateAssociateMockData within AssociateController. This method calls the function generate() of the autowired DataGeneration instance. This is a very complex class please see class comments for further clarification. On a high level the class randomly creates probability objects for associates and clients using these and a few other randomized variables we attempt to simulate receiving interviews, the status of those interviews, submitting interview questions and the time periods associated with the process. All employees are currently supposed to checkin everyday. After a significant refactoring I introduced a bug I will try to iron out but at least the code should be readable to others now.

Realistically randomizing data is important for seeing what graphs will look like when the application is actually utilized and for detecting logic errors within the program. Good luck utilizing and improving the various generation implementations.

#### Postman
* [Triggering Data Generation Via Postman](#triggering/generating)
* [UrlSetup](#urlsetup)

##### Triggering/Generating
Data generation is triggered by a postman collection in order to run this collection you will need to...
1. Be able to run the application
2. Have downloaded and installed postman (https://www.getpostman.com/)

To generate data follow these steps.
  (Note: Data generation should only be ran once on an empty or nearly empty DB for realistic results)
1. Run application
2. Open Postman
3. Import misc/Mock-SMS-Data.postman_collection.json from the project directory (Import is in the top left corner)
4. [Set project url](#urlsetup)
5. Hover over collection folder on left side, click the '>' symbol then 'Run'.
6. A new window should pop up click 'Start Run'
7. Every request within the collection will be run in the proper order, you should see ok appear next to every request. The last request takes a long time (a couple minutes for [local](#localdatabase), can be a few hours for remote) it is generating the bulk of the data.

##### UrlSetup
The url is defined as a postman environment variable 'domain' to set up this variable...
  1. create an environment (click the settings symbol next to the eye in the top right corner)
  2. Add the variable 'domain' with the value equal to your project url
  3. Exit the environment interface
  4. Use the dropdown to select the new environment.


#### LocalDatabase
1. Download Oracle 11g http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html
2. Ensure The Application Not Running (To avoid port conflicts make sure application is not running in eclipse)
2. Run setup, remember your password
3. Find and run the 'SQL command line' program (Cortona), execute the following commands with $PORT being a new port number like 8000.
  - connect (Connect with username: system and the password you set during installation)
  - Exec DBMS_XDB.SETHTTPPORT($PORT);

This will be the port that your oracle database will listen to.


## Launching the Application
1. Open Eclipse ( or IDE of your choosing )
2. Open the BootApp.java in the com.revature package
3. Run as Spring Boot App
This will launch a Tomcat server for you on port 8080.
4. In your browser go to localhost:8080 and it should automatically redirect to the login page.

<<<<<<< HEAD
At this point if you have webpack bundling and eclipse automatically refreshing. 
Then any changes you make in the JavaScript should automatically be detected and will
show up if you refresh your browser window. 
=======
At this point if you have webpack bundling and eclipse automatically refreshing.
Then any changes you make in the JavaScript should automatically be detected and will
show up if you refresh your browser window.
>>>>>>> staging


## Database
In this section I will not go over every detail of the  database. However I will go over portions I think
are important but could be confusing.
### Tables

#### Associates
Associate_active is used to determine if an associate is actively in staging.
this is essentially a boolean and if true they are available for hire if false they are not.

#### Clients
I don't think client_active is in use but rather than deleting clients if they ever need
to be deleted it would be better to flag them as inactive rather than deleting.

Some clients are priority. What this means is if an associate is "locked-to" a client we can not be given
interviews with other clients until a manager goes in and puts the associate  back on the open market.

## API
## Note that this section is not complete
- [GET /](#get)
- [GET /assocaite/{id}]
- [GET /assocaite/all]
- [GET /assocaite/allActive]
- [GET /assocaite/no-batch]
- [GET /assocaite/by-batch/{id}]
- [GET /assocaite/totaldata]
- [GET /associate/generate/mock-data](#post-associategeneratemock-data)
- [PUT /assocaite]
- [POST /associate](#post-associate)
- [POST /associate/add/all](#post-associateaddall)
- [DELETE /assocaite]
- [GET /batch/{id}]
- [GET /batch/all]
- [PUT /batch]
- [POST /batch]
- [POST /batch/types]
- [POST /batch/mockdata/addmultiple]

### GET
Sending a get request to the root of the project will give us the single page application.
This is the only endpoint in the SPAControllerImpl.

### POST associate
Checks the session object to see who is logged in and if ther session is attached to a 
manager then a new associate will be created based on the associate object sent. 

#### Request
```javascript
'Associate'
```

#### Response
Will send a status code of FORBIDDEN if the session is not attached to a manger

### POST     associate/add/all
Checks the session object to see who is logged in and if their session is attached to a
manager. If a manager is logged in then it will persist all associates in the set sent.

#### Request
```javascript
['Associate']
```

#### Response
Will send a status code of FORBIDDEN if the session is not attached to a manger

### GET associate/generate/mock-data
Used to simulate the hiring process

## Reports
I tried to get a complete example of a good implementation of a report but could not complete in time. This section will layout my intended goal and where to reference what I did complete.

### Key concepts to consider when making a new report.
1. Consider what data can be cached and where.
  * For an associate a graph is most likely personalized and graphing with on the client side should be all that is necessary.
  * For a graph visible to managers defiantly cache the information on within the server. There are multiple managers that will all be referencing the same data. Also caching on the client side could be useful but will make updates more complicated.
  * As for an admin, idk ask joe how many administrators will be looking and how often.

2. Try not to pull data that will almost always be accurate and is not referenced often.
  * A lot of graphs will contain historical data that does not normally change. The best option is to set up a method that checks your cache to see if the data should be contained within a reference and update if necessary.

2. Make sure you are not pulling a lot of data that is not displayed from the database.
  * Since graphs are a generalized representation of large amounts of data you will most likely always want to build the representation of data in sql. For instance an understandable pie chart has less than a dozen numbers with value tags that can represent thousands of rows in the data base, its much more efficient to send a dozen numbers.

### Incomplete example
I only had time to make one graph but I tried to make it contain good examples of how to do things.

Attendance Graph (resources/static/manager-pages/home/attendance-graph)
  1. The graph is multi tiered.
    * As soon as the page is loaded with the graph the data set I pull in is used to represent weekly attendance. Then I build the monthly and annual graphs. so that the data is there to reference quickly.
    * When I created this I did not understand angularjs very well but the data construction should be done in a service separate from the display function.
    * To Fix: The monthly data's focal date is centered unlike the other graphs this is producing unintuitive left right transitions.
  2. Data is built within the database.
    * I wrote two sql functions that return tables.
      - GET_STAGGING_ASSOC_BY_DATE
        * Returns a list of associates that were in staging on a given date and their check in time or null if did not check in.
      - GET_ATTENDANCE_REPORT
        * Uses GET_STAGGING_ASSOC_BY_DATE to return a count of all associates who checked in on a given date and how many were supposed to check in.
  3. caching (I didn't get to this but what should be implemented)
    * Initially grabbing a reasonable window of data say this years attendance report.
    * Updating the 'todays' attendance every time the graph is requested or better yet triggering it from the server on update... not sure how that would be done but it would be ideal.
    * When I made the graph function I assumed all data was present and I just fill in zeros for the missing dates. But the functions I wrote will return the appropriate values for any dates requested. Its simply a matter of figuring out how to concatenate requested data on to the already existing data object.

### Other fixes
1. The pie chart and the staging I believe are pulling in all the associates and building there graphs off that data. That is bogging down the client with unnecessary memory allocation and computation. Should write a sql function to create this data, in fact the staging could probably use my function GET_STAGGING_ASSOC_BY_DATE and join the results with batch and batch type.
2. The pie chart was intended to represent the success of associate placement. In order to more accurately portray that the widow of data being displayed should be the last month or quarter maybe weighting associates by the amount of time they have been in staging.

### Other Graph Ideas
1. Trainer success
  * Give a numerical representation attached to a trainer of how quickly associates they trained were placed.
  * Our data base currently isn't set up for it but... Rate trainers on how well they score in different subjects in their panel review.
2. Batch success
  * Rate the different batches on how well their associates are placed.
3. Client Predictability (It saddens me that I couldn't get to this one)
  * If you look at the job object it has start date, confirm date, projected end date, actual end date, buyout date.
  * The idea behind this graph is that clients don't always keep associates for their projected amount of time.
  * You would take and sum the associates predicted to be working for a specific client for all time(P). As well as the actual number of associates working for them for all time(A). Then (P/A) would be a percentage that described how likely a given client is to live up to or exceed their initial contract. This would be helpful to administration in determining which clients to give preference.

note: One method of creating a rating system...
  * Give 1/(2*n) points for an associate where n is the number of full weeks in staging.
  * Add points up for a given pool(P).
  * Divide by number of associates in pool.
  * Solve for x (P_MAX*x=100) or (x=100/P_MAX) where P_MAX is the highest point pool.
  * Multiply all P's by x to get their percentage based off the champ.

### FusionCharts
Fusion Charts look good are easy to use and are highly customizable. A huge number of examples can be found at http://www.fusioncharts.com/charts/.
To create a chart there are three major components...
  1. dataSource - this consists of three objects.
    1. chart - static properties pertaining to chart display (You can generally copy this of existing chart example)
    2. categories - data labels
    3. dataset  - data values
  2. chartSetupInfo - this is tells Fusion what chart and where to place it
    1. type - type of chart
    2. renderAt - the id of the tag to display within
    3. dataFormat - format of the input data (probably json)
  3. events - Fusion charts offer event handlers for their graphs (http://www.fusioncharts.com/dev/api/fusioncharts/fusioncharts-events.html)

Working examples can be found in StagingManagementSystem/src/main/resources/static/manager-pages/home/*-graph/*.js

Tips: We did not have time to make our graphs efficient redundant calculations of non changing graphs is a waste of resources. Try to implement an effective means of caching data that should not be changing often.

Reports are a powerful tool for administration and this project has been setup to make reporting fairly easy. Try to have fun and create useful graphical representations of relevant data.

### Group Challenges
There are Five key areas your group needs to understand in order for your to succeed.
1. Data generation - Without good data in the database debugging can be difficult if not impossible. (See the data generation section)
2. Sql - Writing sql functions or stored procedures to generate data is essential to an efficient implementation.
3. Spring data/JPA repository - Someone needs to have a firm grasp on how to communicate with the database using spring data. (To call my function I used native sql, this is bad practice and should be converted to decouple our implementation from an oracle database)
4. Angularjs - It is very different from JavaScript and JQuery, learning this can be a challenge.
5. FusionCharts - Its a fairly easy library but can be overwhelming at first.

I did not know these were the things I would have too learn and as a result I tried to tackle all of them myself. If you split the load up among your team members you should be able to accomplish more than we were able to. Good luck and have fun with it.
