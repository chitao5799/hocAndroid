<?php
require "dbConnect.php";

$query="SELECT * FROM student";

$data=mysqli_query($connect,$query);

// while ($row = mysqli_fetch_assoc($data)) {
// 	echo $row['id'] . "</br>";
// 	echo $row['hoten'] . "</br>";
// 	echo $row['namsinh'] . "</br>";
// 	echo $row['diachi'] . "</br>";
// }

//1 tạo class sinhvien
/**
 * 
 */
class SinhVien 
{
	function SinhVien($id,$hoten,$namsinh,$diachi){
		$this->ID =$id;
		$this->HoTen = $hoten;
		$this->NamSinh = $namsinh;
		$this->DiaChi = $diachi;
	}	
}
//2 tạo mảng
$mangSV =array();
//3 thêm phần tử vào mảng
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangSV,new SinhVien($row['id'],$row['hoten'],$row['namsinh'],$row['diachi']));
	//id,hoten,namsinh,diachi là tên cột
}


//4 chuyển định dạng của mảng  thành json
echo json_encode($mangSV);
/* //kết quả hiển thị trên trình duyệt
[
	{"ID":"1","HoTen":"Nguy\u1ec5n V\u0103n A","NamSinh":"1999","DiaChi":"H\u00e0 N\u1ed9i"},
	{"ID":"2","HoTen":"Nguy\u1ec5n V\u0103n B","NamSinh":"2000","DiaChi":"\u0110\u00e0 N\u1eb5ng"},
	{"ID":"3","HoTen":"L\u00ea th\u1ecb trang","NamSinh":"1998","DiaChi":"H\u00e0 T\u0129nh"},
	{"ID":"4","HoTen":"\u0110\u00e0o Tr\u1ecdng T\u1ea5n","NamSinh":"2001","DiaChi":"Qu\u1ea3ng Nam"}
]
*/

?>