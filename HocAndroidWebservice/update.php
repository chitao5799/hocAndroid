<?php
require "dbConnect.php";

$id = $_POST['id_SV'];
$hoten=$_POST['hoten_SV'];
$namsinh=$_POST['namsinh_SV'];
$diachi=$_POST['diachi_SV'];

$query="UPDATE student SET hoten='$hoten', namsinh='$namsinh', diachi='$diachi' WHERE id='$id' ";

if(mysqli_query($connect,$query)){
	echo "cap nhat thanh cong";
}
else{
	echo "cap nhat that bai";
}
?>