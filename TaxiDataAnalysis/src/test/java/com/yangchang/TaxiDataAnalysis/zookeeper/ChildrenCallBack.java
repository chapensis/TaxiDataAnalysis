package com.yangchang.TaxiDataAnalysis.zookeeper;

import org.apache.zookeeper.AsyncCallback;

import java.util.List;

public class ChildrenCallBack implements AsyncCallback.ChildrenCallback {
    @Override
    public void processResult(int i, String s, Object o, List<String> list) {
        list.forEach(System.out::println);
        System.out.println("ChildrenCallBack：" + s);
        System.out.println("Ctx：" + o);
    }
}
