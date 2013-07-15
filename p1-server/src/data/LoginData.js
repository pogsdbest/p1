/**
 * New node file
 */
var Data = require('../data/data.js');

var LoginData = function(obj) {
	var data = new Data();
	this.data = data;

	this.username = obj[data.USERNAME];
	this.name = this.username;
	this.x = obj[data.X];
	this.y = obj[data.Y];
	this.state = obj[data.STATE];

	this.isSuccessfull = false;
	this.message = '';
};

LoginData.prototype.getJSON = function() {
	var obj = {};
	obj[this.data.KEY] = this.data.LOGIN;
	obj[this.data.SUCCESS] = this.isSuccessfull;
	obj[this.data.TEXT] = this.message;

	return JSON.stringify(obj);
};

module.exports = LoginData;