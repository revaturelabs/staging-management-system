#Staging Management System


##Setup

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