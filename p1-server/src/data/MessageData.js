/**
 * New node file
 */
var Data = require('../utils/data.js');
var D = new Data();

var text = "none";
var from = "";
var to = "";

var MessageData = function (object) {
	this.text = object.txt;
	this.from = object.from;
	this.to = object.to;
};

MessageData.prototype.getText = function () {return this.text;};

MessageData.prototype.setText = function (text) {this.text = text;};

MessageData.prototype.getJSON = function () {
	var obj = {};
	obj.key = D.MESSAGE;
	obj.txt = this.text;
	return JSON.stringify(obj);
};

module.exports = MessageData;