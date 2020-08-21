<?php

//1 tạo class sinhvien
/**
 * 
 */
class SinhVien 
{
	function SinhVien($hoten,$namsinh,$diachi){
		$this->HoTen = $hoten;
		$this->NamSinh = $namsinh;
		$this->DiaChi = $diachi;
	}	
}
//2 tạo mảng
$mangSV =array();
//3 thêm phần tử vào mảng
array_push($mangSV,new SinhVien("Nguyễn Văn A",1999,"Hà Nội"));
array_push($mangSV,new SinhVien("Nguyễn Văn B",1995,"Hà Nam"));
//4 chuyển định dạng của mảng  thành json
echo json_encode($mangSV);
?>