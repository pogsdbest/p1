/**
 * New node file
 */

var MessageData = function (object) {
	this.text = object.txt;
	this.key = object.key;
};

MessageData.prototype.getText = function () {return this.text;};

MessageData.prototype.setText = function (text) {this.text = text;};

MessageData.prototype.getJSON = function () {
	var obj = {};
	obj.key = this.key;
	obj.txt = this.text;
	return JSON.stringify(obj);
};

module.exports = MessageData;