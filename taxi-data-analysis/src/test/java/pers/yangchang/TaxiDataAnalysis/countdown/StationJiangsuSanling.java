package pers.yangchang.TaxiDataAnalysis.countdown;

import java.util.concurrent.CountDownLatch;

public class StationJiangsuSanling extends DangerCenter {

    public StationJiangsuSanling(CountDownLatch countdown) {
        super(countdown, "江苏三菱调度站");
    }

    @Override
    public void check() {
        System.out.println("正在检查[" + this.getStation() + " ]...");
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("检查[" + this.getStation() + "] 完毕，可以发车~~~");
    }
}
