<?Php
include("connect.php");

if(isset($_POST)){
   
   if( $_POST['isAdd']=='true' ){

   			$officer = $_POST['officer'];
   			$desk = $_POST['desk'];
   			$food = $_POST['food'];
   			$item = $_POST['item'];

   }


}// Check POST METHOD

$sql = "insert into torder(Officer, desk, Date, Food, Item) values('$officer','$desk',now(),'$food','$item')";
$query = mysql_query($sql) or die("Error: ".$sql);




?>