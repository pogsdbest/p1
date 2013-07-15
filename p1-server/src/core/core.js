/**
 * New node file
 */
var Connection = require('./connection.js');
var DataHandle = require('./datahandle.js');
var Game = require('./game.js');

function Core() {
	this.game = new Game();
	this.connection = new Connection(this.game);
	this.dataHandle = new DataHandle(this.game);
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