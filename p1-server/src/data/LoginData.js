/**
 * New node file
 */
var username = "";
var x = 0;
var y = 0;

var LoginData = function (object) {
	this.username = object.username;
	this.x = object.x;
	this.y = object.y;
};

LoginData.prototype.getUsername = function () {
	return username;
};

LoginData.prototype.getX = function () { return this.x; };
LoginData.prototype.getY = function () { return this.y; };

module.exports = LoginData;