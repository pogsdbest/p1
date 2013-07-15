/**
 * New node file
 */
function Collections() {
	this.size = 0;
	this.elements = {};
}

Collections.prototype.isIDExist = function(id) {
	if (this.elements.hasOwnProperty(id)) {
		return true;
	} else
		return false;
};

Collections.prototype.contains = function(obj) {
	if (obj.hasOwnProperty('id')) {
		if (this.elements.hasOwnProperty(obj.id)) {
			return true;
		} else
			return false;
	}
	return false;
};

Collections.prototype.add = function(obj) {
	if (obj.hasOwnProperty('id')) {
		this.elements[obj.id] = obj;
		this.size++;
	}
};

Collections.prototype.get = function(id) {
	if (this.elements.hasOwnProperty(id)) {
		if (this.contains(obj)) {
			var obj = this.elements[id];
			return obj;
		}
	}
	return null;
};

Collections.prototype.remove = function(obj) {
	if (obj.hasOwnProperty('id')) {
		if (this.contains(obj)) {
			delete this.elements[obj.id];
			this.size -= this.size - 1 < 0 ? 0 : 1;
		}
	}
};

Collections.prototype.printAllElements = function() {
	var ids = Object.keys(this.elements);
	ids.forEach(function(id) {
		console.log(id);
	});
};

module.exports = Collections;