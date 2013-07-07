
var net = require('net');

var MessageData = require('./data/MessageData.js');
var LoginData = require("./data/LoginData.js");
var Data = require ('./utils/data.js');
var PlayerModel = require('./model/playerModel'); //must use require to import some dependencies, more likely import in java 

var D = new Data();

var isDebug = true;
var port = 6000;
var players = {};
var playerCount = 0;

var server = net.createServer(function(socket) { // 'connection' listener
	// log('Java client connected to this nodeServer');
	var isLogin = false;
	var player = new PlayerModel();
	
	socket.on('data', function(data) {
		var obj = JSON.parse(data);
		var key = obj.key;
		if(key == D.MESSAGE) {
			var message = obj.txt;
			log(message);
		} else if(key == D.LOGIN) {
			var loginData = new LoginData(obj);;
			
			var isSuccessful = addPlayer(newPlayer);
			var toClient = {};
			toClient.key = D.LOGIN;
			toClient.txt = "-";
			if(isSuccessful) {
				toClient.txt = "Successfully login as "+username;
				isLogin = true;
				player = newPlayer;
			}
			else toClient.txt = "Failed to login";
			socket.write(JSON.stringify(toClient));
			printPlayerList();
		} else if (key == D.MOVE) {
			if(!isLogin) return;
			var move = obj.dir;
			player.direction = move;
			//log(player.direction);
		}
	});
	socket.on('close', function() {
		log('Connection closed for '+player.username);
		if(getPlayer(player.username)!=null) {
			removePlayer(player);
			isLogin = false;
		}
	});
	socket.on('error', function() {
		log('server error for '+player.username);
	});
	socket.on('end', function() {
		log('nodeServer disconnected...');
	});
	socket.on("connect", function() {
		var msg = new MessageData({});
		msg.setText("Successfully connected to server.");
		//socket.write(msg.getText());
		log(msg.getText());
		log('new client connected');
	});
});
server.listen(port, function() {
	log('listening in port '+port);
});

/**
 * server main loop.
 */
setInterval(function(){
	broadcastAllPlayers();
	//printHighestElapsedTime();
},1000/60);

/*
 * function for broadcasting all the data to players
 */
function broadcastAllPlayers() {
	var allPlayerData = {};
	allPlayerData.key = D.ALL_PLAYER_DATA;
	//allPlayerData.players = players;
	//log(allPlayerData);
	var jsonData = JSON.stringify(allPlayerData);
	for(var p in players) {
		var player = players[p];
		player.socket.write(jsonData);
		
	}
}