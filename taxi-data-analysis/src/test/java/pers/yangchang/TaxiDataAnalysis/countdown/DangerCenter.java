package pers.yangchang.TaxiDataAnalysis.countdown;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CountDownLatch;

/**
 * 抽象类，用于演示 危险品化工车监控中心 统一检查
 */
@Data
public abstract class DangerCenter implements Runnable {
    private CountDownLatch countdown; // 计数器
    private String station; // 调度站
    private boolean ok; // 调度站针对当前自己的站点进行检查，是否检查ok的标志

    public DangerCenter(CountDownLatch countdown, String station) {
        this.countdown = countdown;
        this.station = station;
        this.ok = false;
    }

    @Override
    public void run() {
        try {
            check();
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        } finally {
            if (countdown != null) {
                countdown.countDown();
            }
        }
    }

    /**
     * 检查危化品车
     * 蒸罐
     * 汽油
     * 轮胎
     * gps
     * ...
     */
    public abstract void check();
}
