package com.stocksmarket.vo;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/3</p>
 * <p>@description : 股票历史数据</p>
 */
public class StockHisDataVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 交易日期
     */
    private String transDate;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 开盘价
     */
    private double tOpen;
    /**
     * 收盘价
     */
    private double tClose;
    /**
     * 最高价
     */
    private double tHigh;
    /**
     * 最低价
     */
    private double tLow;
    /**
     * 前收盘价
     */
    private double lClose;
    /**
     * 涨跌额
     */
    private double chg;
    /**
     * 涨跌幅
     */
    private double pchg;
    /**
     * 换手率
     */
    private double turnOver;
    /**
     * 成交量
     */
    private String voTurnOver;
    /**
     * 成交金额
     */
    private String vaTurnOver;
    /**
     * 总市值
     */
    private String tcap;
    /**
     * 流通市值
     */
    private String mcap;
    /**
     * 股票交易标志(1:正常    0:停牌)
     */
    private int marketFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public double gettOpen() {
        return tOpen;
    }

    public void settOpen(double tOpen) {
        this.tOpen = tOpen;
    }

    public double gettClose() {
        return tClose;
    }

    public void settClose(double tClose) {
        this.tClose = tClose;
    }

    public double gettHigh() {
        return tHigh;
    }

    public void settHigh(double tHigh) {
        this.tHigh = tHigh;
    }

    public double gettLow() {
        return tLow;
    }

    public void settLow(double tLow) {
        this.tLow = tLow;
    }

    public double getlClose() {
        return lClose;
    }

    public void setlClose(double lClose) {
        this.lClose = lClose;
    }

    public double getChg() {
        return chg;
    }

    public void setChg(double chg) {
        this.chg = chg;
    }

    public double getPchg() {
        return pchg;
    }

    public void setPchg(double pchg) {
        this.pchg = pchg;
    }

    public double getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(double turnOver) {
        this.turnOver = turnOver;
    }

    public String getVoTurnOver() {
        return voTurnOver;
    }

    public void setVoTurnOver(String voTurnOver) {
        this.voTurnOver = voTurnOver;
    }

    public String getVaTurnOver() {
        return vaTurnOver;
    }

    public void setVaTurnOver(String vaTurnOver) {
        this.vaTurnOver = vaTurnOver;
    }

    public String getTcap() {
        return tcap;
    }

    public void setTcap(String tcap) {
        this.tcap = tcap;
    }

    public String getMcap() {
        return mcap;
    }

    public void setMcap(String mcap) {
        this.mcap = mcap;
    }

    public int getMarketFlag() {
        return marketFlag;
    }

    public void setMarketFlag(int marketFlag) {
        this.marketFlag = marketFlag;
    }

    @Override
    public String toString() {
        return "StockHisDataVo{" +
                "id='" + id + '\'' +
                ", transDate='" + transDate + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", tOpen=" + tOpen +
                ", tClose=" + tClose +
                ", tHigh=" + tHigh +
                ", tLow=" + tLow +
                ", lClose=" + lClose +
                ", chg=" + chg +
                ", pchg=" + pchg +
                ", turnOver=" + turnOver +
                ", voTurnOver='" + voTurnOver + '\'' +
                ", vaTurnOver='" + vaTurnOver + '\'' +
                ", tcap='" + tcap + '\'' +
                ", mcap='" + mcap + '\'' +
                ", marketFlag=" + marketFlag +
                '}';
    }
}
