package pers.yangchang.TaxiDataAnalysis.zookeeper;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKNodeExist implements Watcher {

    @Getter
    @Setter
    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "127.0.0.1:2181";
    public static final Integer timeout = 5000;

    public ZKNodeExist() {
    }

    public ZKNodeExist(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKNodeOperator());
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
        ZKNodeExist zkServer = new ZKNodeExist(zkServerPath);

        /**
         * 参数:
         * path: 节点路径
         * watch: true或者false，注册一个watch事件
         * stat: 状态
         */
        Stat stat = zkServer.getZooKeeper().exists("/imooc-fake", new ZKNodeExist());
        if (stat != null) {
            System.out.println("查询的节点版本为dataVersion:" + stat.getVersion());
        } else {
            System.out.println("该节点不存在");
        }
        countDown.await();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
            System.out.println("节点数据改变");
            countDown.countDown();
        } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
            System.out.println("节点创建");
            countDown.countDown();
        } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            System.out.println("子节点改变");
            countDown.countDown();
        } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
            System.out.println("节点删除");
            countDown.countDown();
        }

    }
}
