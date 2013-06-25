var net = require('net');
var server = net.createServer(function (c)
{ //'connection' listener
    //log('Java client connected to this nodeServer');
    c.on('data', function (data)
    {
        console.log(data);   
    });
    c.on('end', function ()
    {
        console.log('nodeServer disconnected');
    });
});
server.listen( 80 , function ()
{ //'listening' listener
    console.log('nodeServer listening port:6000');
});

console.log("Running");
/*
var net = require("net");
var mySocket;
var server = net.createServer(function(socket) {
	mySocket = socket;
	
	mySocket.on("connect",function() {
		console.log("Connected!");
	});
	
	mySocket.on("data",function(data) {
	
		
		if(data == "exit\0")
		{
			console.log("exit");
			mySoket.end();
			server.close();
		}
		else if(data == '<policy-file-request/>\0')
			mySocket.write(policy()+"\0");
		
		try{
			
			console.log(data.id);
			
		} catch(error) { console.log(error); }
	});

	mySocket.on('end', function() {
		//server.close();
		//mySoket.end();
		console.log('server disconnected');
	});
	mySocket.on('error', function(error) {
		console.log('FATAL ERROR:', error);
	});
	
	function policy() {
	var xml = '<?xml version="1.0"?>\n<!DOCTYPE cross-domain-policy SYSTEM'
          + ' "http://www.macromedia.com/xml/dtds/cross-domain-policy.dtd">\n<cross-domain-policy>\n';

	xml += '<allow-access-from domain="*" to-ports="*"/>\n';
	xml += '</cross-domain-policy>\n';
  
	return xml;
	}
}
//server.listen(process.env.VMC_APP_PORT || 1337, null);
server.listen(8000 , '127.0.0.1');
*/

/*
http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/html'});
  res.end('Server is Up! - pogs');
}).listen(process.env.VMC_APP_PORT || 1337, null);
*/