var express = require("express");
var app = express();
var server = require("http").createServer(app);
var io = require("socket.io").listen(server);
var fs = require("fs");
server.listen(process.env.PORT || 3000);

io.sockets.on('connection',function(socket){
	console.log('co thiet bi ket noi den');
	
	socket.on('client-sent-data',function(data){
		console.log('server nhan data: '+data);
		io.sockets.emit('server-send-data',{noidung:'dữ liệu gửi từ server'});
	});
});
