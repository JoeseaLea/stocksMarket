package com.stocksmarket.thread;

import com.stocksmarket.service.StockHisDataService;
import com.stocksmarket.utils.SpringContextUtil;
import com.stocksmarket.utils.StockDataAnalysisUtil;
import com.stocksmarket.vo.StockHisDataVo;
import com.stocksmarket.vo.StockVo;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/28</p>
 * <p>@description : </p>
 */
public class MaNGtMaKThread implements Runnable {

    private StockVo stockVo;
    private int n;
    private int k;

    public MaNGtMaKThread(StockVo stockVo, int n, int k) {
        this.stockVo = stockVo;
        this.n = n;
        this.k = k;
    }

    @Override
    public void run() {
        int size = (n > k ? n : k) + 2;

        StockHisDataService stockHisDataService = SpringContextUtil.getBean(StockHisDataService.class);

        List<StockHisDataVo> stockHisDataList = stockHisDataService.getStockHisDataDesc(stockVo, 1, size);

        double[] maN = new double[2];
        double[] maK = new double[2];

        maN[0] = StockDataAnalysisUtil.getAvgtClose(stockHisDataList.subList(0, n));
        maN[1] = StockDataAnalysisUtil.getAvgtClose(stockHisDataList.subList(1, n + 1));

        maK[0] = StockDataAnalysisUtil.getAvgtClose(stockHisDataList.subList(0, k));
        maK[1] = StockDataAnalysisUtil.getAvgtClose(stockHisDataList.subList(1, k + 1));

        if (maN[0] >= maK[0] && maN[1] < maK[1]) {
            // TODO: 2019/12/23 提醒购买股票 
        }
    }
}
