use AssignmentJAVA
go

insert into STUDENT values('SV001','Nguyen Van Teo','teonv@gmail.com','0903414749','true','311 Ngo Quyen','obama.jpg'),
						  ('SV002','Le Thi Oi','oilt@gmail.com','0903414749','true','319 Vo Thi Sau','User.jpg'),
						  ('SV003','Nguyen Thi Cu','cunt@gmail.com','0168756382','false','222 Nguyen Thi Dinh','User.jpg'),
						  ('SV004','Le Phung Hieu Kien','kienlph@gmail.com','0259792759','false','222 Binh Dinh','PhungLv.jpg'),
						  ('SV005','Nguyen Thi Tuong Vi','vintt@gmail.com','0925802383','false','234 Hoang Hoa Tham','User.jpg'),
						  ('SV006','Loi Roi','roil@gmail.com','0934532893','true','Ha Noi','loiroi.jpg'),
						  ('SV007','Cu Teo','teoc@gmail.com','0987598293','true','Vung Tau','User.jpg')

select * from STUDENT

insert into GRADE values(1,'SV005',10,10,10),
						(2,'SV001',9,9,9),
						(3,'SV006',8,8,8)

select * from GRADE

insert into USERR values('chaunt2003','ntc231203','Giang vien'),
					   ('tuandc2003','tuan123456','Giang vien'),
					   ('hoanglm123','654321','Can bo')
select * from USERR