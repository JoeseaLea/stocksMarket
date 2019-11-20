drop table if exists stock;

create table stock (
  id int(8) primary key auto_increment comment '主键',
  code varchar(6) not null comment '股票代码',
  name varchar(8) not null comment '股票名称',
  last_his_data_down_date timestamp not null comment '历史数据最后下载日期',
  last_his_data_down_flag int(1) default 0 not null comment '历史数据下载情况（0:未下载   1:已下载）',
  unique key (code)
);