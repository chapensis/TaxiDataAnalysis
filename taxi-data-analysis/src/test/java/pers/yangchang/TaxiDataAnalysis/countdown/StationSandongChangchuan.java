package pers.yangchang.TaxiDataAnalysis.countdown;

import java.util.concurrent.CountDownLatch;

public class StationSandongChangchuan extends DangerCenter {

    public StationSandongChangchuan(CountDownLatch countdown) {
        super(countdown, "山东长川调度站");
    }

    @Override
    public void check() {
        System.out.println("正在检查[" + this.getStation() + " ]...");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("检查[" + this.getStation() + "] 完毕，可以发车~~~");
    }
}
