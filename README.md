# Staging Management System


## Setup

### Node, Webpack, and Babel
1. Download node from https://nodejs.org/en/download/
If there is any uncertainty here is a guide to downloading it 
http://blog.teamtreehouse.com/install-node-js-npm-windows

2. Navigate to the directory with package.json

3. In your command line type, 
    npm install

this will install all of the dependencies that have been laid out in the package.json

4. Once all of the dependencies have been installed you can have web pack watch 
your code to do this type
    webpack --watch

What this does is allows Webpack and Babel to bundle and transpile all of the 
javascript code and it's required dependencies into one js file.
So even though you are working in your individual js files it will all be 
bundled into bundle.js

Why is this a good thing?
Our server now only has to serve up one js file for out javascript application 
and Bable transpiles it into an older version of JavaScript 
that runs on browsers that don't support the newest standard of JavaScript

### Eclipse
All of the node modules will now be in your project. This can cause performance
issues when building your project. However Eclipse does not need to worry about
these. To do this follow the steps below.
1. Go to Project -> Preferences. This will pop up a dialogue box. 
2. Expand the Resource tab and select Resource Filters.
3. Select Add Filter on the right.
4. Select Exclude All option.
5. Select Folders option.
6. In File and Folder Attributes the first dropdown should have Name and the 
    second should have matches. Type "node_modules" in the input box. (Withot the quotes of course).
7. Select OK and then OK again.
8. Navigate to src/main/resources/static and node_modules should no longer appear there.
9. Note this folder is also in the .gitignore so it will not be pushed to the repository

## Launching the Application
1. Open Eclipse ( or IDE of your choosing )
2. Open the BootApp.java in the com.revature package
3. Run as Spring Boot App
This will launch a Tomcat server for you on port 8080.
