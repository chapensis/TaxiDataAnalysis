package com.yangchang.TaxiDataAnalysis.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class Children2CallBack implements AsyncCallback.Children2Callback {
    @Override
    public void processResult(int i, String s, Object o, List<String> list, Stat stat) {
        list.forEach(System.out::println);
        System.out.println("ChildrenCallBack：" + s);
        System.out.println("Ctx：" + o);
        System.out.println("Version:" + stat.getVersion());
    }
}
