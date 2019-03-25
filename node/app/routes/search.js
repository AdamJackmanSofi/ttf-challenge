'use strict';

const express = require('express');
const router = express.Router();
const giphy = require('../models/giphy');


function getWithSearchTerm(req, res) {
    return giphy.search(req.params.searchTerm)
        .then(results => {
            return res.json(results);
        });
}

router.get('/:searchTerm', getWithSearchTerm);

module.exports = router;