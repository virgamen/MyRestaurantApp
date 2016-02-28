<?Php
include("connect.php");

$sql = "select * from tfood";
$query = mysql_query($sql) or die("Error: ".$sql);

while($rs=mysql_fetch_array($query)){
	 $jx[]=array("id"=>$rs['id'],"foodname"=>$rs['foodname'],"price"=>$rs['price']);
	}
$json_data = $jx;	
$json = json_encode($json_data);
echo $json;


?>