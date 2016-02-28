<?Php
include("connect.php");

$sql = "select * from tuser";
$query = mysql_query($sql) or die("Error: ".$sql);

while($rs=mysql_fetch_array($query)){
	 $jx[]=array("id"=>$rs['id'],"username"=>$rs['username'],"password"=>$rs['password'],"officer"=>$rs['officer']);
	}
$json_data = $jx;	
$json = json_encode($json_data);
echo $json;


?>