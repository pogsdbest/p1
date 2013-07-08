/**
 * New node file
 */
var Data = require('../data/data.js');

function DataHandle() {
	this.data = new Data();
}

DataHandle.prototype.handleData = function (object) {
	var newData = JSON.parse(object);
	if(newData.hasOwnProperty(this.data.KEY)) {
		var key = newData[this.data.KEY];
		if(key == this.data.MESSAGE) {
			
		} else if(key == this.data.LOGIN) {
			
		}
	}
};

module.exports = DataHandle;