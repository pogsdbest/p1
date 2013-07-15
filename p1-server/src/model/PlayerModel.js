/**
 * New node file
 */
var Data = require('../data/data.js');
var data = new Data();

var PlayerModel = function(s) {

	this.id = 0;
	this.username = 0;
	this.name = '';
	this.x = 0;
	this.y = 0;
	this.state = 0;
	this.socket = null;
};

PlayerModel.prototype.init = function(loginData) {
	this.id = loginData.username;
	this.username = loginData.username;
	this.name = loginData.name;
	this.x = loginData.x;
	this.y = loginData.y;
	this.state = loginData.state;
};

module.exports = PlayerModel;
