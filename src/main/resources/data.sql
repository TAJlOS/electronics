delete from cpu;
INSERT INTO cpu(name,producer,socket,base_frequency,boost_frequency,n_core,n_thread,tdp,l3_cache,overclocking,integrated_graphic_card,graphic_card,img,price)
values
('Ryzen 5 3600 OEM','AMD','AM4',3600,4200,6,12,65,32,true,false,null,'imgs/ryzen53600oem.jpg',12000),
('Ryzen 5 3600X OEM','AMD','AM4',3800,4400,6,12,95,32,true,false,null,'imgs/ryzen53600xoem.jpg',14500),
('Ryzen 5 3600 BOX','AMD','AM4',3600,4200,6,12,65,32,true,false,null,'imgs/ryzen53600box.jpg',14000),
('Ryzen 5 2600 OEM','AMD','AM4',3400,3900,6,12,65,16,true,false,null,'imgs/ryzen52600oem.jpg',8500),
('Ryzen 5 2600 BOX','AMD','AM4',3400,3900,6,12,65,16,true,false,null,'imgs/ryzen52600box.jpg',9500),
('Ryzen 7 2700X BOX','AMD','AM4',3700,4300,8,16,105,16,true,false,null,'imgs/ryzen72700xbox.jpg',13000),
('Ryzen 7 2700 OEM','AMD','AM4',3200,4100,8,16,65,16,true,false,null,'imgs/ryzen72700oem.jpg',10500),
('Ryzen 3 3200G OEM','AMD','AM4',3600,4000,4,4,65,4,true,true,'Radeon Vega 8','imgs/ryzen33200Goem.jpg',5500),
('Ryzen 3 3200G BOX','AMD','AM4',3600,4000,4,4,65,4,true,true,'Radeon Vega 8','imgs/ryzen33200Gbox.jpg',7000),
('Ryzen 7 3700X OEM','AMD','AM4',3600,4400,8,16,65,32,true,false,null,'imgs/ryzen73700xoem.jpg',22000),
('Ryzen 7 3700X BOX','AMD','AM4',3600,4400,8,16,65,32,true,false,null,'imgs/ryzen73700xbox.jpg',24000),
('Core i7-9700K OEM','Intel','LGA 1151-v2',3600,4900,8,8,95,12,true,true,'Intel UHD Graphics 630','imgs/i79700koem.jpg',28000),
('Core i7-9700K BOX','Intel','LGA 1151-v2',3600,4900,8,8,95,12,true,true,'Intel UHD Graphics 630','imgs/i79700kbox.jpg',30000),
('Core i5-9600K OEM','Intel','LGA 1151-v2',3700,4600,6,6,95,9,true,true,'Intel UHD Graphics 630','imgs/i59600koem.jpg',17500),
('Core i5-9400 OEM','Intel','LGA 1151-v2',2900,4100,6,6,65,9,false,true,'Intel UHD Graphics 630','imgs/i59400oem.jpg',13000),
('Core i5-9400 BOX','Intel','LGA 1151-v2',2900,4100,6,6,65,9,false,true,'Intel UHD Graphics 630','imgs/i59400box.jpg',14500);


