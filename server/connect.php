<?Php

$host = "182.50.133.85";
$user = "hungrie";
$password = "Tt@15092523";
$db = "sumret_net_app";

@mysql_connect($host,$user,$password) or die("Can't connect!");

mysql_select_db($db) or die("Can't select DB!");
mysql_query("SET NAMES UTF8");

?>