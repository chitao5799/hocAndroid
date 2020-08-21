<?php
 require "dbConnect.php";

 $idsv=$_POST['idSinhVien'];
 $query="DELETE FROM student WHERE id='$idsv' ";
 if(mysqli_query($connect,$query))
 	echo "xoa thanh cong";
 else
 	echo "xoa that bai";
?>