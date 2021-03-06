/**
 * New node file
 */
var Utilities = require('../utils/utilities.js');
var MessageData = require('../data/messagedata.js');
var Data = require('../data/data.js');

function Connection(game) {
	this.game = game;
	this.socket = null;
	this.util = new Utilities();
	this.data = new Data();
}

Connection.prototype.clientConnected = function (socket) {
	this.game.player.socket = socket;
	this.util.wtf('New Client Connected...');
	var obj = {};
	obj[this.data.KEY] = this.data.MESSAGE;
	obj[this.data.TEXT] = 'Successfuly Connected...';
	
	var msg = new MessageData(obj);
	socket.write(msg.getJSON());
};

module.exports = Connection;