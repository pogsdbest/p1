
var net = require('net');
var d = require ('./utils/data.js');
var playerModel = require('./playerModel'); //must use require to import some dependencies, more likely import in java 

var isDebug = true;
var port = 6000;
var players = {};
var playerCount = 0;

var server = net.createServer(function(socket) { // 'connection' listener
	// log('Java client connected to this nodeServer');
	var isLogin = false;
	var player = {};
	
	socket.on('data', function(data) {
		var obj = JSON.parse(data);
		var key = obj.key;
		if(key == d.MESSAGE) {
			var message = obj.txt;
			log(message);
		} else if(key == d.LOGIN) {
			var username = obj.username;
			var x = obj.x;
			var y = obj.y;
			
			var newPlayer = {};
			newPlayer.username = username;
			newPlayer.x = x;
			newPlayer.y = y;
			newPlayer.socket = socket;
			
			var isSuccessful = addPlayer(newPlayer);
			var toClient = {};
			toClient.key = d.LOGIN;
			toClient.txt = "-";
			if(isSuccessful) {
				toClient.txt = "Successfully login as "+username;
				isLogin = true;
				player = newPlayer;
			}
			else toClient.txt = "Failed to login";
			socket.write(JSON.stringify(toClient));
			printPlayerList();
		} else if (key == d.MOVE) {
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
		var obj = {};
		obj.key = d.MESSAGE;
		obj.txt = "Successfully connected to server.";
		socket.write(JSON.stringify(obj));
		log('new client connected');
	});
});
server.listen(port, function() {
	log('listening in port '+port);
});

console.log("Running");
var date = new Date();
var lastTime = Date.now();
var highest = 0;

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
	allPlayerData.key = d.ALL_PLAYER_DATA;
	allPlayerData.players = this.players;
	var jsonData = JSON.stringify(allPlayerData);
	//allPlayerData.players = players;
	for(var p in players) {
		var player = players[p];
		player.socket.write(jsonData);
		//log(allPlayerData.key);
	}
}
/**
 * function for printing highest elapsed time.
 * good for checking the highest time elasped for every tick
 */
function printHighestElapsedTime() {
	var elapsedTime = Date.now() - lastTime;
	lastTime = Date.now();
	if(elapsedTime > highest) highest = elapsedTime;
	log("highest : "+highest);
}

/**
 * function for printing the current elements of players
 * or the list of players currently login
 */
function printPlayerList() {
	log("*****players "+playerCount+"*****");
	for(var player in players) {
		log(players[player].username);
	}
}

/**
 * function for adding a new player on the server
 * the index of the new added player is based on its username
 * if the current username of the player exist, it returns false
 * true if the player successfully added.
 * @param player - the new player
 */
function addPlayer(player) {
	if(players[player.username] == null) {
		players[player.username] = player;
		playerCount++;
		return true;
	} else {
		return false;
	}
}
/**
 * function for removing a player on the players object
 * @param player - the player to remove
 */
function removePlayer(player) {
	if(players[player.username] != null) {
		delete players[player.username];
		playerCount--;
	}
}
/**
 * function for getting a player using a specified name
 * @param username - the username of the player you wish to get
 */
function getPlayer(username) {
	return players[username];
}
/**
 * function for printing logs, useful for debugging 
 */
function log(txt) {
	if(isDebug)
		console.log(txt);
}
//calling a method of a module requires(require (./path/to/file.js)
//console.log(playerModel.name());
//console.log(d.message);