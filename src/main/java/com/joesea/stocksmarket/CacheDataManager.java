package com.joesea.stocksmarket;

import com.joesea.stocksmarket.vo.EmailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/28</p>
 * <p>@description : 管理缓存的各种全局数据</p>
 */
public class CacheDataManager {

    private static final Logger logger = LoggerFactory.getLogger("");

    /**
     * 邮件队列
     */
    private static BlockingQueue<EmailVo> emailsNeedSend = new ArrayBlockingQueue<EmailVo>(100);
    /**
     * 下载成功的股票数据文件
     */
    private static BlockingQueue<String> stockFiles = new ArrayBlockingQueue<String>(10000);

    /**
     * 插入到邮件队列的末尾，邮件队列满则等待队列有可用的空间直至插入成功。
     * @param emailVo
     */
    public static void putEmailsNeedSend(EmailVo emailVo) {
        try {
            emailsNeedSend.put(emailVo);
        } catch (InterruptedException e) {
            logger.error("需要发送的邮件信息入队异常", e);
            logger.error(emailVo.toString());
        }
    }

    /**
     * 插入邮件队列尾部，插入成功返回true，邮件队列已满插入返回false
     * @param emailVo
     * @return 邮件入队结果
     */
    public static boolean offerEmailsNeedSend(EmailVo emailVo) {
        return emailsNeedSend.offer(emailVo);
    }

    /**
     * 插入邮件队列尾部，插入成功返回true，邮件队列已满等待指定时间仍不能插入返回false
     * @param emailVo
     * @return 邮件入队结果
     */
    public static boolean offerEmailsNeedSend(EmailVo emailVo, long timeout, TimeUnit unit) {
        try {
            return emailsNeedSend.offer(emailVo, timeout, unit);
        } catch (InterruptedException e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * 检索但不删除邮件队列的头部，如果该队列为空，则返回null。
     * @return 邮件队列头部
     */
    public static EmailVo peekEmailsNeedSend() {
        return emailsNeedSend.peek();
    }

    /**
     * 检索并删除邮件队列的头部，如果该队列为空，则返回null。
     * @return 邮件队列头部
     */
    public static EmailVo pollEmailsNeedSend() {
        return emailsNeedSend.poll();
    }

    /**
     * 检索并删除邮件队列的头，如果需要，将等待到指定的等待时间，直到元素可用为止。
     * @param timeout
     * @param unit
     * @return 邮件队列头部
     */
    public static EmailVo pollEmailsNeedSend(long timeout, TimeUnit unit) {
        try {
            return emailsNeedSend.poll(timeout, unit);
        } catch (InterruptedException e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 返回邮件队列中的元素数量。
     * @return 邮件队列元素数量
     */
    public static int getemailsNeedSendSize() {
        return emailsNeedSend.size();
    }

    /**
     * 插入到已下载股票数据队列的末尾，队列满则等待队列有可用的空间直至插入成功。
     * @param stockCode
     */
    public static void putstockFileQueues(String stockCode) {
        try {
            stockFiles.put(stockCode);
        } catch (InterruptedException e) {
            logger.error("下载股票数据股票代码入队异常", e);
            logger.error(stockCode);
        }
    }

    /**
     * 检索但不删除下载股票数据队列的头部，如果该队列为空，则返回null。
     * @return 下载股票数据队列头部
     */
    public static String peekstockFileQueues() {
        return stockFiles.peek();
    }

    /**
     * 检索并删除下载股票数据队列的头部，如果该队列为空，则返回null。
     * @return 下载股票数据队列头部
     */
    public static String pollstockFileQueues() {
        return stockFiles.poll();
    }
}
