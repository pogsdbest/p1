/**
 * New node file
 */
var Utilities = require('../utils/utilities.js');

function Connection(socket) {
	this.socket = socket;
	this.util = new Utilities();
}

Connection.prototype.clientConnected = function () {
	this.util.wtf('New Client Connected...');
};

module.exports = Connection;