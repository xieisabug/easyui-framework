$(function(){
	var flashvars = {};
	flashvars.cssSource = "/zk/flash/piecemaker.css";
	flashvars.xmlSource = "/zk/flash/piecemaker.xml";

	var params = {};
	params.play = "true";
	params.menu = "false";
	params.scale = "showall";
	params.wmode = "transparent";
	params.allowfullscreen = "true";
	params.allowscriptaccess = "always";
	params.allownetworking = "all";

	swfobject.embedSWF('/zk/flash/piecemaker.swf', 'piecemaker', '900', '450', '10',
			null, flashvars, params, null);
});