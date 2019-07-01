create table stock (
  id int(8) primary key auto_increment comment '主键',
  code varchar(6) not null comment '股票代码',
  name varchar(8) not null comment '股票名称',
  unique key (code)
)