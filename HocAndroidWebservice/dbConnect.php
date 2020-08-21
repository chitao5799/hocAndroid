<?php
//mysqli_connect(<host/domain>,<user name truy cập db>,<password>,<database name>);
$connect=mysqli_connect("localhost","root","","sinhvien");//sinhvien là tên database
mysqli_query($connect,"SET NAMES 'utf8'");

?>