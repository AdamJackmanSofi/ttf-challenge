'use strict';

const request = require('request-promise');
const nconf = require('nconf');

module.exports.search = (phrase) => {

    const resultLimit = nconf.get('giphy:limit');

    const opts = {
        uri: nconf.get('giphy:url'),
        qs: {
            api_key: nconf.get('giphy:api_key'),
            q: phrase,
            limit: 500
        },
        json: true
    };

    return request(opts)
        .then(result => {

            // Format output and restrict number of items
            let output = {data: []};

            if (result.data && result.data.length >= resultLimit) {

                for (let i = 0; i < resultLimit; i++) {
                    output.data.push({
                        'gif_id': result.data[i].id,
                        'url': result.data[i].url
                    });
                }
            }

            return output;
        })
        .catch(err => {
            // Return the error if the called failed
            return err;
        });
};