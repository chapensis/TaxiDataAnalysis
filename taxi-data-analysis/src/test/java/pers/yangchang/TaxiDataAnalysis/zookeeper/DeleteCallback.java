package pers.yangchang.TaxiDataAnalysis.zookeeper;

import org.apache.zookeeper.AsyncCallback;

/**
 * 删除节点时的回调函数
 */
public class DeleteCallback implements AsyncCallback.VoidCallback {
    @Override
    public void processResult(int i, String s, Object o) {
        System.out.println("删除节点：" + s);
        System.out.println((String) o);
    }
}
