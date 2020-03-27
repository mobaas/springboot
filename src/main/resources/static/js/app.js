var num = 1;
var num1 = 1;

function addparam(tab) {
	$('#' + tab + " .param").last().after("<tr class='param'><td colspan='2'>参数 <input id='param"+num+"' name='param"+num+"' size=100></td></tr>");
	num++;
}

function addenv(tab) {
	$('#' + tab + " .env").last().after("<tr class='env'><td>环境变量 <input id='envname"+num1+"' name='envname"+num1+"' size=100></td><td>值 <input id='envval"+num1+"' name='envval"+num1+"' size=100></td></tr>");
	num1++;
}