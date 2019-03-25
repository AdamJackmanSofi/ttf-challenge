'use strict';

const nconf = require('nconf');
const express = require('express');
const app = express();

// Connect the environment variables
nconf.env('__');
nconf.file('./config/config.json');

// Connect middleware
app.use('/', require('./app/routes/root'));
app.use('/search', require('./app/routes/search'));


app.listen(nconf.get('port'));