package com.joesea.stocksmarket.vo;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/1</p>
 * <p>@description : 股票代码及名称实体</p>
 */
public class StockVo {
    /**
     * 主键
     */
    private long id;
    /**
     * 股票代码
     */
    private String code;
    /**
     * 股票名称
     */
    private String name;
    /**
     * 历史数据下载标志
     * 0:未下载   1:已下载
     */
    private int hisDataDownFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHisDataDownFlag() {
        return hisDataDownFlag;
    }

    public void setHisDataDownFlag(int hisDataDownFlag) {
        this.hisDataDownFlag = hisDataDownFlag;
    }
}
