<?php
require "dbConnect.php";

$hoten =$_POST['hotenSV'];
$namsinh=$_POST['namsinhSV'];
$diachi=$_POST['diachiSV'];

$query="INSERT INTO student VALUES(null,'$hoten','$namsinh','$diachi')";

if(mysqli_query($connect,$query)){
	echo "thanh cong";

}else{
	echo "loi";
}


?>