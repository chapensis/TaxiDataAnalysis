package com.yangchang.TaxiDataAnalysis.countdown;

import java.util.concurrent.CountDownLatch;

public class StationBeijingIMooc extends DangerCenter {

    public StationBeijingIMooc(CountDownLatch countdown) {
        super(countdown, "北京慕课调度站");
    }

    @Override
    public void check() {
        System.out.println("正在检查[" + this.getStation() + " ]...");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("检查[" + this.getStation() + "] 完毕，可以发车~~~");
    }
}
