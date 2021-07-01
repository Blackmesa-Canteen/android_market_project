const express = require('express');
const router = express.Router();

const indexController = require('../controller/index_controller')

router.get('/', indexController.show_page);

// handle 404 or 500
router.all('*', (req, res) => {
    res.send("<h1>404</h1>")
});


module.exports = router;