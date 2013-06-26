
var net = require('net');
var playerModel = require('./playerModel.js'); //must use require to import some dependencies, more likely import in java 

var server = net.createServer(function(c) { // 'connection' listener
	// log('Java client connected to this nodeServer');
	c.on('data', function(data) {
		var obj = JSON.parse(data);
		console.log(obj.key);
	});
	c.on('end', function() {
		console.log('nodeServer disconnected');
	});
	c.on("connect", function() {
		c.write("{message:'Successfully connected!'}");
		console.log('someone connected');
	});
});
server.listen(6000, function() {
	console.log('nodeServer listening ');
});

console.log("Running");

//calling a method of a module requires(require (./path/to/file.js)
console.log(playerModel.createPlayer('pogs').id);