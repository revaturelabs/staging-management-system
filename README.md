# Staging Management System
* [Overview](#overview)
* [Setup](#setup)
* [API](#api)

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

## Setup 
##(!!!!!!!!!! THIS IS VERY IMPORTANT !!!!!!!!!!!!!!!!!!)

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
watching the code it will not update the bundle.js and the changes will not be reflected.
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


## Launching the Application
1. Open Eclipse ( or IDE of your choosing )
2. Open the BootApp.java in the com.revature package
3. Run as Spring Boot App
This will launch a Tomcat server for you on port 8080.
4. In your browser go to localhost:8080 and it should automatically redirect to the login page.

At this point if you have webpack bundling and eclipse automatically refreshing. 
Then any changes you make in the JavaScript should automatically be detected and will
show up if you refresh your browser window. 


## API
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
Checks the session object to see who is logged in and if ther session is attached to a 
manager. If a manager is logged in then it will persist all associates in the set sent. 

#### Request
```javascript
['Associate']
```

#### Response
Will send a status code of FORBIDDEN if the session is not attached to a manger

### GET associate/generate/mock-data
Used to simulate the hiring process

