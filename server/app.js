/**
 *
 * A dummy server for the android market app project
 *
 * */


const express = require('express')
const app = express();

const index_router = require('./route/index_router')

app.use(express.static('public'));

// index router
app.use('/', index_router)

// handle 404 exception
app.all('*', (req, res) => {
    res.send("<h1>404</h1>")
});

app.listen(process.env.PORT || 8080, () => {
    // process.env.PORT is for Heroku's port
    if (process.env.PORT) {
        console.log('port: ' + process.env.PORT);
    } else {
        console.log('port: 8080')
    }
})

module.exports = app