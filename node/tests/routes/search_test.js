const request = require('supertest');
const express = require('express');
const proxyquire = require('proxyquire').noPreserveCache();
const test = require('tape');
const Promise = require('bluebird');

const routerPath = '../../app/routes/search';
const giphyPath = '../../app/models/giphy';


test('Is the GET search term route hooked up properly to the model?', t => {

	const response = 'hello';
	const requestStub = {
		'search': function() {return Promise.resolve(response);}
	}

	const search = proxyquire(routerPath, {'../models/giphy': requestStub});

	const app = express();
	app.use('/search', search);

	request(app)
	.get('/search/search_term')
	.expect(200)
	.then(res => {
		t.equal(response, res.text);
		t.end();
	});
});