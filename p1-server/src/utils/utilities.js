/**
 * New node file
 */

function Utilities() {

	this.date = new Date();
	this.lastTime = Date.now();
	this.highest = 0;
	this.isDebug = true;
}

Utilities.prototype.printHighestElapsedTime = function() {
	var elapsedTime = Date.now() - this.lastTime;
	this.lastTime = Date.now();
	if (elapsedTime > this.highest)
		this.highest = elapsedTime;
	console.log("highest : " + this.highest);
};

Utilities.prototype.wtf = function(txt) {
	if (this.isDebug)
		console.log(txt);
};

module.exports = Utilities;