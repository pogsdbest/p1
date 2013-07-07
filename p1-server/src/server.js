/**
 * New node file
 */
var net = require('net');
var Core = require('./core/core.js');
var Utilities = require('./utils/utilities.js');

var core = new Core();
var util = new Utilities();

var port = 6000;
var isDebug = true;

var server = net.createServer(function(socket) {

	socket.on('data', function(data) {
		core.data(data);
	});
	socket.on('close', function() {
		core.close();
	});
	socket.on('error', function() {
		core.error();
	});
	socket.on('end', function() {
		core.end();
	});
	socket.on('connect', function() {
		core.connect(socket);
	});

}).listen(port , function() {
	util.wtf('Server started listening on port '+port);
});

setInterval(function(){
	core.process();
	//util.printHighestElapsedTime();
},1000/60.0);

