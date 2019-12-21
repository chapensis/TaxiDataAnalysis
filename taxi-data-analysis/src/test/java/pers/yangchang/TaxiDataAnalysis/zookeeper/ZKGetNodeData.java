package pers.yangchang.TaxiDataAnalysis.zookeeper;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKGetNodeData implements Watcher {

    @Getter
    @Setter
    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "127.0.0.1:2181";
    public static final Integer timeout = 5000;
    private static Stat stat = new Stat();

    public ZKGetNodeData() {
    }

    public ZKGetNodeData(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKGetNodeData());
        } catch (IOException e) {
            e.printStackTrace();
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static CountDownLatch countDown = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZKGetNodeData zkServer = new ZKGetNodeData(zkServerPath);

        /**
         * 参数:
         * path: 节点路径
         * watch: true或者false，注册一个watch事件
         * stat: 状态
         */
        byte[] resByte = zkServer.getZooKeeper().getData("/imooc", new ZKGetNodeData(), stat);
        String result = new String(resByte);
        System.out.println("当前值:" + result);
        countDown.await();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                ZKGetNodeData zkServer = new ZKGetNodeData(zkServerPath);
                byte[] resByte = zkServer.getZooKeeper().getData("/imooc", false, stat);
                String result = new String(resByte);
                System.out.println("更改后的值:" + result);
                System.out.println("版本号变化dversion:" + stat.getVersion());
                countDown.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {

            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                ZKGetNodeData zkServer = new ZKGetNodeData(zkServerPath);
                byte[] resByte = zkServer.getZooKeeper().getData("/imooc", false, stat);
                String result = new String(resByte);
                System.out.println("Children更改后的值:" + result);
                System.out.println("Children版本号变化dversion:" + stat.getVersion());
                countDown.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {

            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
