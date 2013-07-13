/**
 * New node file
 */
var Connection = require('./connection.js');
var DataHandle = require('./datahandle.js');

function Core() {
	this.connection = new Connection();
	this.dataHandle = new DataHandle();
}

Core.prototype.process = function() {

};

Core.prototype.data = function(data) {
	this.dataHandle.handleData(data);
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