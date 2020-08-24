var express = require("express");
var app = express();
var server = require("http").createServer(app);
var io = require("socket.io").listen(server);
var fs = require("fs");
server.listen(process.env.PORT || 3000);
var arrUser=[];
var tonTai=true;
io.sockets.on('connection',function(socket){
	console.log('co thiet bi ket noi den');
	
	socket.on('client-register-user',function(data){
      //  console.log('server nhan data: '+data);
        if(arrUser.indexOf(data)==-1)
        {
            //chưa tồn tại user -> cho đăng ký
            arrUser.push(data);
            socket.un = data; //socket.un sẽ lưu tên người đăng ký
          //  console.log("đăng ký thành công: "+data);
            tonTai=false;
            //server gửi danh sách user cho tất cả các máy
            io.sockets.emit('server-send-list-user',{danhsach:arrUser});
        }
        else{
         //  console.log('đã tồn tại user');
            tonTai=true;
        }
        //gửi kết quả đăng ký cho user vừa gửi thông tin đăng ký
        socket.emit('server-send-result',{ketqua : tonTai});
    });
    
    socket.on('client-send-chat',function(data){
        io.sockets.emit('server-send-chat',{ contentsChat: socket.un+": "+data});
    });
});
