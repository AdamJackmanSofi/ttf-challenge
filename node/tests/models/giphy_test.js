const proxyquire = require('proxyquire').noPreserveCache();
const test = require('tape');
const Promise = require('bluebird');
const nconf = require('nconf');
const path = require('path');

const giphyPath = '../../app/models/giphy';

// Get the absolute path
nconf.file(path.join(__dirname, '..', '..', 'config', 'config.json'));

let resultLimit;

test('Setup', t => {
	resultLimit = nconf.get('giphy:limit');
	console.log(__dirname);
	t.end();
});

test('Do we return 0 results when giphy returns 0 results?', t => {
	
	const requestStub = () => {
		const response = { data: [] };
		return Promise.resolve(response);
	}

	const nconfStub = {
		'get': function(param){ 
			if (param === 'giphy:limit')
				return resultLimit;
		}
	}

	const giphy = proxyquire(giphyPath, {'request-promise': requestStub, 'nconf': nconfStub})

	giphy.search('test').then(r => {
		t.deepEqual(r, {data: []} , 'Was no data parsed?');
		t.end();		
	});
});

test(`Do we return 0 results when giphy returns (limit-1) results?`, t => {
	
	const requestStub = () => {
		const response = { 
			data: Array(resultLimit-1).fill({'id': 2, 'url':'x.com'})
		};
		return Promise.resolve(response);
	}

	const nconfStub = {
		'get': function(param){ 
			if (param === 'giphy:limit')
				return resultLimit;
		}
	}

	const giphy = proxyquire(giphyPath, {'request-promise': requestStub, 'nconf': nconfStub})

	giphy.search('test').then(r => {
		t.deepEqual(r, {data: []}, 'Was no data parsed?');
		t.end();		
	});
});

test(`Do we return (limit) results when giphy returns (limit) results?`, t => {
	
	const testResponse = { 
		data: Array(resultLimit).fill({'id': 2, 'url':'x.com'})
	};

	const requestStub = () => {
		return Promise.resolve(testResponse);
	}

	const nconfStub = {
		'get': function(param){ 
			if (param === 'giphy:limit')
				return resultLimit;
		}
	}

	const giphy = proxyquire(giphyPath, {'request-promise': requestStub, 'nconf': nconfStub})

	giphy.search('test').then(r => {
		t.equal(r.data.length, resultLimit, `Are there ${resultLimit} results returned?`);

		for (let i = 0; i < testResponse.data.length; i++) {
			t.equal(testResponse.data[i].id, r.data[i].gif_id, `Is the id expected for the result #${i+1}?`);
			t.equal(testResponse.data[i].url, r.data[i].url, `Is the url expected for the result #${i+1}?`);
		}

		t.end();		
	});
});

test(`Do we return (limit) results when giphy returns (limit+1) results?`, t => {
	
	const testResponse = { 
		data: Array(resultLimit+1).fill({'id': 7, 'url':'y.com'})
	};

	const requestStub = () => {
		return Promise.resolve(testResponse);
	}

	const nconfStub = {
		'get': function(param){ 
			if (param === 'giphy:limit')
				return resultLimit;
		}
	}

	const giphy = proxyquire(giphyPath, {'request-promise': requestStub, 'nconf': nconfStub})

	giphy.search('test').then(r => {
		t.equal(r.data.length, resultLimit, `Are there ${resultLimit} results returned?`);

		for (let i = 0; i < r.data.length; i++) {
			t.equal(testResponse.data[i].id, r.data[i].gif_id, `Is the id expected for the result #${i+1}?`);
			t.equal(testResponse.data[i].url, r.data[i].url, `Is the url expected for the result #${i+1}?`);
		}

		t.end();		
	});
});