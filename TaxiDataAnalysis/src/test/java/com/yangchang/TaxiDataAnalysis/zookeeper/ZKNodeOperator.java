package com.yangchang.TaxiDataAnalysis.zookeeper;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.List;

public class ZKNodeOperator implements Watcher {

    @Getter
    @Setter
    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "192.168.159.129:2181,192.168.159.130:2181,192.168.159.131:2181";
    public static final Integer timeout = 5000;

    public ZKNodeOperator() {
    }

    public ZKNodeOperator(String connectString) {
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


    /**
     * 主函数
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ZKNodeOperator zkServer = new ZKNodeOperator(zkServerPath);

        // 1. 创建节点
        zkServer.createZKNode("/testnode", "testnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

        // 2. 更新节点
        // Stat status = zkServer.getZooKeeper().setData("/testnode", "modify".getBytes(), 0);
        // System.out.println(status.getVersion());

        // 3.同步删除节点
        // zkServer.getZooKeeper().delete("/testnode", 1);

        // 4.异步删除节点
        String ctx = "{'delete':'success'}";
        zkServer.getZooKeeper().delete("/testnode", 0, new DeleteCallback(), ctx);
        new Thread().sleep(2000);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
