package pers.yangchang.TaxiDataAnalysis.zookeeper;

import org.apache.zookeeper.AsyncCallback;

/**
 * 创建节点时的回调函数
 * 这是一个回调函数，当目标达到后可以执行当前回调函数的processResult，还可以接受来自执行前预定义的内容s1
 */
public class CreateCallBack implements AsyncCallback.StringCallback {
    @Override
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println("CreateCallBack创建节点：" + s);
        System.out.println("CreateCallBack Object：" + o);
    }
}
