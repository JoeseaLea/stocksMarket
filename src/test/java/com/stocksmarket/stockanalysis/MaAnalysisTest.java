package com.stocksmarket.stockanalysis;

import com.stocksmarket.base.BaseTest;
import com.stocksmarket.service.StockHisDataService;
import com.stocksmarket.service.StockService;
import com.stocksmarket.utils.StockDataAnalysisUtil;
import com.stocksmarket.vo.StockHisDataVo;
import com.stocksmarket.vo.StockVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/12/25</p>
 * <p>@description : </p>
 */
public class MaAnalysisTest extends BaseTest {
    @Autowired
    private StockService stockService;
    @Autowired
    private StockHisDataService stockHisDataService;

    @Test
    public void maNGtMaK() {
        final int n=3, k=12, m=6;

        List<StockVo> stockVoList = stockService.getAllDownHisDataStockVo();

        for (StockVo stockVo : stockVoList) {
            List<StockHisDataVo> stockHisDataVoList = stockHisDataService.getAllStockHisDataAsc(stockVo, 1);

            int size = stockHisDataVoList.size();
            double maN[] = new double[size], maK[] = new double[size], maM[] = new double[size];

            for (int i = 0; i < size; i ++) {
                if (i + 1 >= n) {
                    maN[i] = StockDataAnalysisUtil.getAvgtClose(stockHisDataVoList.subList(i + 1 - n, i + 1));
                }

                if (i + 1 >= k) {
                    maK[i] = StockDataAnalysisUtil.getAvgtClose(stockHisDataVoList.subList(i + 1 - k, i + 1));
                }

                if (i + 1 >= m) {
                    maM[i] = StockDataAnalysisUtil.getAvgtClose(stockHisDataVoList.subList(i + 1 - m, i + 1));
                }

                if (i >= Math.max(Math.max(n, k), m) && maN[i] >= maK[i] && maN[i-1] < maK[i-1] && maM[i-1] < maM[i]) {
                    System.out.println(stockHisDataVoList.get(i).toString());
                }

            }
        }

    }
}
