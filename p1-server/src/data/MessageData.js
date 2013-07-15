/**
 * New node file
 */
var Data = require('./data.js');


var MessageData = function (obj) {
	var data = new Data();
	this.text = obj[data.TEXT];;
	this.key = obj[data.KEY];
};

MessageData.prototype.getJSON = function () {
	var obj = {};
	obj.key = this.key;
	obj.txt = this.text;
	return JSON.stringify(obj);
};

module.exports = MessageData;