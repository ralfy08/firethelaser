var socket = io('');
socket.on('connect', function(){

    console.log("this works");
    socket.emit('data', { data : 'this is data' });

    socket.on('readyForFile', function (data) {
        asyncFileSend(data);
    });

    // Responds to cursor move event
    // data.x = +/- x position
    // data.y = +/- y position
    socket.on('moveCursor', function (data){
        var current = $('#reticle').offset()
        current.left += parseInt(data.x);
        current.top += parseInt(data.y);
        //$('#reticle').offset({top:data.x,left:data.y})
        $('#reticle').offset({'top':current.top, 'left':current.left});
        console.log('moving cursor %j', data);
    });
});
