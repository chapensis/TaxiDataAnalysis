package pers.yangchang.TaxiDataAnalysis.zookeeper;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZKNodeAcl implements Watcher {

    @Getter
    @Setter
    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "127.0.0.1:2181";
    public static final Integer timeout = 5000;

    public ZKNodeAcl() {
    }

    public ZKNodeAcl(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKNodeAcl());
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
            System.out.println("createZKNode创建节点：" + result + " 成功");
            new Thread().sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CountDownLatch countDown = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZKNodeAcl zkServer = new ZKNodeAcl(zkServerPath);

        // 1. 创建节点,acl 任何人都能访问
        // zkServer.createZKNode("/testnode", "testnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

        // 2. 自定义用户认证访问
//        List<ACL> acls = new ArrayList<>();
//        // Id即用户
//        Id imooc1 = new Id("digest", AclUtils.getDigestUserPwd("imooc1:123456"));
//        Id imooc2 = new Id("digest", AclUtils.getDigestUserPwd("imooc2:123456"));
//        acls.add(new ACL(ZooDefs.Perms.ALL, imooc1));
//        acls.add(new ACL(ZooDefs.Perms.READ, imooc2));
//        acls.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.CREATE, imooc2));
//        zkServer.createZKNode("/aclimooc/testdigest", "testdigest".getBytes(), acls);

        // zkServer.getZooKeeper().addAuthInfo("digest", "imooc1:123456".getBytes());
        // 3. 创建节点
        // zkServer.createZKNode("/aclimooc/testdigest/childtest3", "childtest".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL);

//        Stat stat = new Stat();
//        byte[] data = zkServer.getZooKeeper().getData("/aclimooc/testdigest", false, stat);
//        System.out.println("Stat:" + new String(data));

        // zkServer.getZooKeeper().setData("/aclimooc/testdigest", "12345678".getBytes(), 1);

        // 1. 创建节点
//        List<ACL> aclsIP = new ArrayList<>();
//        Id ipId1 = new Id("ip", "127.0.0.1");
//        aclsIP.add(new ACL(ZooDefs.Perms.ALL, ipId1));
//        zkServer.createZKNode("/aclimooc/iptest8", "iptest".getBytes(), aclsIP);

        // 验证IP是否有权限
        // zkServer.getZooKeeper().setData("/aclimooc/iptest8", "12345678".getBytes(), 0);

        Stat stat = new Stat();
        byte[] data = zkServer.getZooKeeper().getData("/aclimooc/iptest8", false, stat);
        System.out.println("Stat:" + new String(data));
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
