/**
 * New node file
 */
var name = "unknown";
var x = 0;
var y = 0;

var socket = null;
var PlayerModel = function () {
	
};
//getters
PlayerModel.prototype.getName = function () { return name; };
PlayerModel.prototype.getSocket = function () { return socket; };
PlayerModel.prototype.getX = function () {	return x; };
PlayerModel.prototype.getY = function () {	return y; };
//setters
PlayerModel.prototype.setX = function (x) {	this.x =  x; };
PlayerModel.prototype.setY = function (y) {	this.y =  y; };
PlayerModel.prototype.setSocket = function (socket) {	this.socket =  socket; };

module.exports = PlayerModel;
