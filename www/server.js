var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var fs = require('fs');

server.listen(3000);

app.get('/', function (req, res) { 
    res.sendFile(__dirname + '/index.html');
    console.log('works');
});

app.get('/js/:file', function (req, res) { 
    console.log(req.params.file);
    res.sendFile(__dirname + '/js/' + req.params.file);
    console.log('works');
});

app.get('/style/:file', function (req, res) { 
    console.log(req.params.file);
    res.sendFile(__dirname + '/style/' + req.params.file);
    console.log('works');
});

// websocket stuff
io.on('connection', function (socket) {
    socket.on('data', function (data){
        console.dir("data: " + data.data);
    });

    // Responds to cursor move event
    // data.x = +/- x position
    // data.y = +/- y position
    socket.on('cursorMove', function (data){
        console.log('cursorMove' + data);
    });

    // Responds to cursor scale event
    // data.z = +/- z position
    socket.on('scale', function (data){
        console.log('cursorMove' + data);
    });
});
