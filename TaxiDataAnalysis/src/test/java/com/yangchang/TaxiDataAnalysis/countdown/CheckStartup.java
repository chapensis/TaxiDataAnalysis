package com.yangchang.TaxiDataAnalysis.countdown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CheckStartup {
    private static List<DangerCenter> stationList;

    private static CountDownLatch countdown;

    public CheckStartup() {

    }

    public static boolean CheckAllStations() throws Exception {
        // 初始化三个调度站
        countdown = new CountDownLatch(3);

        // 把所有站点添加到list
        stationList = new ArrayList<>();
        stationList.add(new StationBeijingIMooc(countdown));
        stationList.add(new StationJiangsuSanling(countdown));
        stationList.add(new StationSandongChangchuan(countdown));

        // 使用线程池
        Executor executor = Executors.newFixedThreadPool(stationList.size());
        for (DangerCenter center : stationList) {
            executor.execute(center);
        }

        // 等待线程执行完毕
        countdown.await();

        for (DangerCenter center : stationList) {
            if (!center.isOk()) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) throws Exception {
        boolean result = CheckStartup.CheckAllStations();
        System.out.println("监控中心针对所有危化品调度站的检查结果为:" + result);
    }
}
