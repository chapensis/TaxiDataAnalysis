package pers.yangchang.TaxiDataAnalysis.zookeeper;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZKNodeAclIp implements Watcher {

    @Getter
    @Setter
    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "127.0.0.1:2181";
    public static final Integer timeout = 5000;

    public ZKNodeAclIp() {
    }

    public ZKNodeAclIp(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKNodeAclIp());
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

    /**
     * 创建节点
     *
     * @param path
     * @param data
     * @param acls
     */
    public void createZKNode(String path, byte[] data, List<ACL> acls) {
        String result = "";
        try {
            /**
             * 同步或者异步创建节点，都不支持子节点的递归创建，异步有一个callback函数
             * 参数：
             * path:创建的路径
             * data:存储的数据
             * acl:控制权限策略
             *    Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
             *    CREATOR_ALL_ACL --> auth:user:password:cdrwa
             * createMode: 节点类型，是一个枚举
             *       PERSISTENT:持久节点
             *       PERSISTENT_SEQUENTIAL:持久顺序节点
             *       EPHEMERALL:临时节点
             *       EPHEMERALL_SEQUENTIAL:临时顺序节点
             */
            // result = zooKeeper.create(path, data, acls, CreateMode.EPHEMERAL);

            String ctx = "{'create':'success'}";
            zooKeeper.create(path, data, acls, CreateMode.PERSISTENT, new CreateCallBack(), ctx);
            System.out.println("创建节点：" + result + " 成功");
            new Thread().sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CountDownLatch countDown = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZKNodeAclIp zkServer = new ZKNodeAclIp(zkServerPath);

        // 1. 创建节点
        List<ACL> aclsIP = new ArrayList<>();
        Id ipId1 = new Id("ip", "192.168.0.103");
        aclsIP.add(new ACL(ZooDefs.Perms.ALL, ipId1));
        zkServer.createZKNode("/acllimooc/iptest", "iptest".getBytes(), aclsIP);
        countDown.await();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
            System.out.println("WatchedEvent节点数据改变");
            countDown.countDown();
        } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
            System.out.println("WatchedEvent节点创建");
            countDown.countDown();
        } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            System.out.println("WatchedEvent子节点改变");
            countDown.countDown();
        } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
            System.out.println("WatchedEvent节点删除");
            countDown.countDown();
        }

    }
}
