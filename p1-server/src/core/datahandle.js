/**
 * New node file
 */
var Utilities = require('../utils/utilities.js');
var Data = require('../data/data.js');
var LoginData = require('../data/logindata.js');
var MessageData = require('../data/messagedata.js');

function DataHandle(game) {
	this.data = new Data();
	this.util = new Utilities();
	this.game = game;
}

DataHandle.prototype.handleData = function (object) {
	var newData = JSON.parse(object);
	if(newData.hasOwnProperty(this.data.KEY)) {
		var key = newData[this.data.KEY];
		if(key == this.data.MESSAGE) {
			
		} else if(key == this.data.LOGIN) {
			this.handleLogin(newData);
		} else if(key == this.data.LOGOUT) {
			
		}
	}
};

DataHandle.prototype.handleLogin = function (newData) {
	var loginData = new LoginData(newData);
	if(this.game.isPlayerExist(loginData.username)) {
		loginData.isSuccessfull = false;
		loginData.message = 'username already login.';
		this.game.player.socket.write(loginData.getJSON());
	} else {
		loginData.isSuccessfull = true;
		loginData.message = 'Successfully login as '+loginData.name;
		this.game.player.socket.write(loginData.getJSON());
		
		this.game.loginPlayer(loginData);
	}
};

module.exports = DataHandle;