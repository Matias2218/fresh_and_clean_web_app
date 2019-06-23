insert into personas(id_persona,nombre,apellido,fecha_nacimiento,genero) values (1,'Victoria','Rodriguez','1997/10/22','F')
insert into personas(id_persona,nombre,apellido,fecha_nacimiento,genero) values (2,'Matias','Maldonado','1997/08/22','M')
insert into personas(id_persona,nombre,apellido,fecha_nacimiento,genero) values (3,'Sebastian','Zeballos','1997/08/21','M')
insert into personas(id_persona,nombre,apellido,fecha_nacimiento,genero) values (4,'Francisco','Figueroa','1997/10/22','M')
insert into personas(id_persona,nombre,apellido,fecha_nacimiento,genero) values (5,'Leonardo','Barrueto','1997/04/01','M')
insert into personas(id_persona,nombre,apellido,fecha_nacimiento,genero) values (6,'Yeremi','Rodriguez','1994/07/07','M')

insert into tipo_empleados(id_tipo,descripcion) values (1,'Barbero')
insert into tipo_empleados(id_tipo,descripcion) values (2,'Gerente')
insert into tipo_empleados(id_tipo,descripcion) values (3,'Inventario')
insert into tipo_empleados(id_tipo,descripcion) values (4,'Administrador')

insert into clientes(id_cliente,email_cliente,telefono_cliente,persona_id) values (1,'maldo1514@gmail.com','976720550',5)
insert into clientes(id_cliente,email_cliente,telefono_cliente,persona_id) values (2,'yeremy@gmail.com','984713214',6)

insert into empleados(id_empleado,username_empleado,password_empleado,email_empleado,telefono_empleado,sueldo_empleado,bono_empleado,persona_id,tipo_empleado_id,esta_activo) values (1,'barbero','admin','v.rodriguez@hotmail.com','999412421',500000,null,1,1,1)
insert into empleados(id_empleado,username_empleado,password_empleado,email_empleado,telefono_empleado,sueldo_empleado,bono_empleado,persona_id,tipo_empleado_id,esta_activo) values (2,'gerente','admin','m.maldonadoo@hotmail.com','9131020302',300000,null,2,2,1)
insert into empleados(id_empleado,username_empleado,password_empleado,email_empleado,telefono_empleado,sueldo_empleado,bono_empleado,persona_id,tipo_empleado_id,esta_activo) values (3,'inventario','admin','s.zeballos@hotmail.com','912412421',400000,null,3,3,1)
insert into empleados(id_empleado,username_empleado,password_empleado,email_empleado,telefono_empleado,sueldo_empleado,bono_empleado,persona_id,tipo_empleado_id,esta_activo) values (4,'administrador','admin','f.figueroa@hotmail.com','941401214',700000,null,4,4,1)
insert into empleados(id_empleado,username_empleado,password_empleado,email_empleado,telefono_empleado,sueldo_empleado,bono_empleado,persona_id,tipo_empleado_id,esta_activo) values (5,'barbero1','admin','l.barrueto@hotmail.com','912412551',500000,null,5,1,1)
insert into empleados(id_empleado,username_empleado,password_empleado,email_empleado,telefono_empleado,sueldo_empleado,bono_empleado,persona_id,tipo_empleado_id,esta_activo) values (6,'barbero2','admin','y.rodriguez@hotmail.com','912412421',500000,null,6,1,1)


insert into empresas(id_empresa,rut_empresa,razon_social,telefono_empresa,email_empresa,rubro_empresa) values (1,'70.524.523-4','Barberia y salon de belleza','912424214','freshandclean@gmail.com','Otras actividades')

insert into sucursales(id_sucursal,nombre_sucursal,email_sucursal,telefono_sucursal,direccion_sucursal,empresa_id) values(1,'Fresh & Clean Talagante','freshandcleantalagante@gmail.com','954213487','Balmaceda 4211 Talagante',1)

insert into servicios(id_servicio,nombre_servicio,descripcion_servicio,precio_servicio,id_sucursal) values(1,"Perfilado de barba","Asesoría, ritual con toalla caliente, limpieza facial y aplicación de producto.",4550,1);
insert into servicios(id_servicio,nombre_servicio,descripcion_servicio,precio_servicio,id_sucursal) values(2,"Corte de cabello","Asesoría personalizada, corte de cabello, lavado y aplicación de producto.",5990,1);
insert into servicios(id_servicio,nombre_servicio,descripcion_servicio,precio_servicio,id_sucursal) values(3,"Peinado","Lavado de cabello, aplicación de producto y peinado para fiestas y eventos.",2990,1);
insert into servicios(id_servicio,nombre_servicio,descripcion_servicio,precio_servicio,id_sucursal) values(4,"Rasurado(Cabeza)","Ritual de afeitado al ras con toalla caliente. Aplicación de after shave.",5990,1);
insert into servicios(id_servicio,nombre_servicio,descripcion_servicio,precio_servicio,id_sucursal) values(5,"Rasurado(Barba)","Ritual de afeitado al ras con toalla caliente. Aplicación de after shave.",2990,1);

--insert into peticion_horas(id_peticion,hora_atencion,cliente_id,empleado_id) values (1,'2019/05/30 13:00',1,1)
--insert into peticion_horas(id_peticion,hora_atencion,cliente_id,empleado_id) values (2,'2019/06/27 15:00',1,1)

--insert into boletas(id_boleta,descripcion_boleta,fecha_boleta,monto_total,id_peticion) values(1,'Boleta numero 1','2019/05/31 15:21',25000,1)
--insert into boletas(id_boleta,descripcion_boleta,fecha_boleta,monto_total,id_peticion) values(2,'Boleta numero 2','2019/06/29 17:21',10000,2)