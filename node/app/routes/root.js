'use strict';

const express = require('express');
const router = express.Router();


router.get('/', getIndex);

function getIndex(req, res) {
    res.sendStatus(200);
}

module.exports = router;