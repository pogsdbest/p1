/**
 * New node file
 */
var Connection = require('./connection.js');

function Core() {
	this.connection = new Connection();
}

Core.prototype.process = function() {

};

Core.prototype.data = function(data) {

};

Core.prototype.close = function() {

};

Core.prototype.error = function() {

};

Core.prototype.end = function() {

};

Core.prototype.connect = function(socket) {
	this.connection.clientConnected(socket);
};

module.exports = Core;