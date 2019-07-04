drop table if exists stock_his_data;

create table stock_his_data(
  id varchar(32) primary key comment '主键',
  trans_date datetime comment '交易日期',
  stock_code varchar(6) comment '股票代码',
  t_open decimal(6, 2) comment '开盘价',
  t_close  decimal(6, 2) comment '收盘价',
  t_high decimal(6, 2) comment '最高价',
  t_low decimal(6, 2) comment '最低价',
  l_close decimal(6, 2) comment '前收盘价',
  chg decimal(8,2) comment '涨跌额',
  pchg decimal(12,6) comment '涨跌幅',
  turn_over decimal(8,6) comment '换手率',
  vo_turn_over varchar(32) comment '成交量',
  va_turn_over varchar(32) comment '成交金额',
  tcap  varchar(32) comment '总市值',
  mcap  varchar(32) comment '流通市值',
  unique key (trans_date, stock_code)
);