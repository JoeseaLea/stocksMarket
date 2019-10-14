package com.joesea.stocksmarket.service.impl;

import com.joesea.stocksmarket.dao.StockHisDataDao;
import com.joesea.stocksmarket.service.StockHisDataAnalyzeService;
import com.joesea.stocksmarket.vo.StockHisDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/5</p>
 * <p>@description : </p>
 */
@Service
public class StockHisDataAnalyzeServiceImpl implements StockHisDataAnalyzeService {
    @Autowired
    private StockHisDataDao stockHisDataDao;

    @Override
    public void ma5Gtma10(StockHisDataVo stockHisDataVo) {
        stockHisDataVo.setStockCode("000009");
        stockHisDataVo.setMarketFlag(1);
        List<StockHisDataVo> stockHisDataList = stockHisDataDao.getStockHisData(stockHisDataVo);

        int length = stockHisDataList.size();
        double[] ma5 = new double[length];
        double[] ma10 = new double[length];

        int num = 0;
        double buy = 0;
        double count = 0;
        int days = 0;
        double maxamout = 0;
        for (int i = 100; i < length; i ++) {
            ma5[i] = getAverage(stockHisDataList.subList(i-4, i));
            ma10[i] = getAverage(stockHisDataList.subList(i-9, i));
            if (10 == i && ma5[i] > ma10[i]) {
//                flag = true;
            }

//            if (!flag && ma5[i] > ma10[i]) {
//                num += 1000;
//                buy = stockHisDataList.get(i).gettClose();
//                flag = true;
//            }
//
//            if (flag && ma5[i] <= ma10[i]) {
//                if (num > 0) {
//                    count += (stockHisDataList.get(i).gettClose()- buy) * num;
//                }
//                flag = false;
//            }
            boolean flag = false;
            if (ma5[i-1] < ma10[i-1] && ma5[i] > ma10[i] && stockHisDataList.get(i).gettClose() > stockHisDataList.get(i-1).gettClose()) {
                num += 1000;
                count -= stockHisDataList.get(i).gettClose() * 1000 * 0.0003;
                buy = stockHisDataList.get(i).gettClose();
                flag = true;
                if (maxamout < 1000*buy) {
                    maxamout = 1000*buy;
                }
                System.out.println(stockHisDataList.get(i).getTransDate() + "买进" + buy);
            }

            if (!flag && stockHisDataList.get(i).gettClose() < stockHisDataList.get(i-1).gettClose() && num > 0) {
                count -= stockHisDataList.get(i).gettClose() * num * 0.0003;
                count += (stockHisDataList.get(i).gettClose()- buy) * num;
                num = 0;
                System.out.println(stockHisDataList.get(i).getTransDate() + "卖出sale" + stockHisDataList.get(i).gettClose());
            }

            if (num > 0) {
                days ++;
            }
        }
        System.out.println(days + "           " + count + "         " + maxamout);
    }

    private double getAverage(List<StockHisDataVo> stockHisDataList) {

        double sum = 0;
        for (StockHisDataVo stockHisDataVo : stockHisDataList) {
            sum += stockHisDataVo.gettClose();
        }

        if (0 >= stockHisDataList.size()) {
            return 0;
        }
        return sum/stockHisDataList.size();
    }
}
