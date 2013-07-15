/**
 * New node file
 */
var Data = require('../data/data.js');
var Collections = require('../utils/collections.js');
var PlayerModel = require('../model/playermodel.js');

var data = new Data();

function Game () {
	
	this.players = new Collections();
	this.player = new PlayerModel();
	
}

Game.prototype.isPlayerExist = function (username) {
	return this.players.isIDExist(username);
};

Game.prototype.loginPlayer = function (loginData) {
	this.player.init(loginData);
	this.players.add(this.player);
};
module.exports = Game;