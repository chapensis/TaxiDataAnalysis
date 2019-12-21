package pers.yangchang.TaxiDataAnalysis.zookeeper;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZKGetChidrenList implements Watcher {

    @Getter
    @Setter
    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "127.0.0.1:2181";
    public static final Integer timeout = 5000;
    private static Stat stat = new Stat();

    public ZKGetChidrenList() {
    }

    public ZKGetChidrenList(String connectString) {
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

    private static CountDownLatch countDown = new CountDownLatch(2);

    public static void main(String[] args) throws Exception {
        ZKGetChidrenList zkServer = new ZKGetChidrenList(zkServerPath);

        /**
         * 参数:
         * path: 父节点路径
         * watch: true或者false，注册一个watch事件
         */
//        List<String> strChildList = zkServer.getZooKeeper().getChildren("/imooc", new ZKGetChidrenList());
//        // strChildList.forEach(x -> System.out.println(x));
//        strChildList.forEach(System.out::println);

        // 异步调用
        String ctx = "{'callback':'ChildrenCallback'}";
       // zkServer.getZooKeeper().getChildren("/imooc", new ZKGetChidrenList(), new ChildrenCallBack(), ctx);
        zkServer.getZooKeeper().getChildren("/imooc", new ZKGetChidrenList(), new Children2CallBack(), ctx);
        countDown.await();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                ZKGetChidrenList zkServer = new ZKGetChidrenList(zkServerPath);
                byte[] resByte = zkServer.getZooKeeper().getData("/imooc", false, stat);
                String result = new String(resByte);
                System.out.println("更改后的值:" + result);
                System.out.println("版本号变化dversion:" + stat.getVersion());
                countDown.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
                System.out.println("NodeCreated");
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                System.out.println("NodeChildrenChanged");
                ZKGetChidrenList zkServer = new ZKGetChidrenList(zkServerPath);
                List<String> strChildList = zkServer.getZooKeeper().getChildren("/imooc", new ZKGetChidrenList());
                strChildList.forEach(System.out::println);
                countDown.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                System.out.println("NodeDeleted");
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
