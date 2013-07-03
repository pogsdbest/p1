
var net = require('net');
//var playerModel = require('./playerModel.js'); //must use require to import some dependencies, more likely import in java 

var port = 6000;
var players = {};
var playerCount = 0;

var server = net.createServer(function(socket) { // 'connection' listener
	// log('Java client connected to this nodeServer');
	var player = {};
	
	socket.on('data', function(data) {
		var obj = JSON.parse(data);
		var key = obj.key;
		if(key == "msg") {
			var message = obj.txt;
			console.log(message);
		} else if(key == "login") {
			var username = obj.username;
			var newPlayer = {};
			newPlayer.username = username;
			var isSuccessful = addPlayer(newPlayer);
			var obj = {};
			obj.key = "login";
			obj.txt = "-";
			if(isSuccessful) {
				obj.txt = "Successfully login as "+username;
				player = newPlayer;
			}
			else obj.txt = "Failed to login";
			socket.write(JSON.stringify(obj));
			
			printPlayerList();
		}
	});
	socket.on('close', function() {
		console.log('Connection closed for '+player.username);
		if(getPlayer(player.username)!=null) {
			removePlayer(player);
		}
	});
	socket.on('error', function() {
		console.log('server error for '+player.username);
	});
	socket.on('end', function() {
		console.log('nodeServer disconnected...');
	});
	socket.on("connect", function() {
		var obj = {};
		obj.key = "msg";
		obj.txt = "Successfully connected to server."
		socket.write(JSON.stringify(obj));
		console.log('new client connected');
	});
});
server.listen(port, function() {
	console.log('listening in port '+port);
});

console.log("Running");
var date = new Date();
var lastTime = Date.now();
var highest = 0;

/**
 * server main loop.
 */
setInterval(function(){
	
	//printHighestElapsedTime();
},1000/60);

/**
 * function for printing highest elapsed time.
 * good for checking the highest time elasped for every tick
 */
function printHighestElapsedTime() {
	var elapsedTime = Date.now() - lastTime;
	lastTime = Date.now();
	if(elapsedTime > highest) highest = elapsedTime;
	console.log("highest : "+highest);
}

/**
 * function for printing the current elements of players
 * or the list of players currently login
 */
function printPlayerList() {
	console.log("*****players "+playerCount+"*****");
	for(var player in players) {
		console.log(players[player].username);
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
	if(players[player.username]!=null) {
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
//calling a method of a module requires(require (./path/to/file.js)
//console.log(playerModel.createPlayer('pogs').id);