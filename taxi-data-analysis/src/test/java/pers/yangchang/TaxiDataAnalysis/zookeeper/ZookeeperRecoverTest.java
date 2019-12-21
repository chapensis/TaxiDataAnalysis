package pers.yangchang.TaxiDataAnalysis.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

@Slf4j
public class ZookeeperRecoverTest implements Watcher {
    // public static final String zkServerPath = "127.0.0.1:2181";
    public static final String zkServerPath = "192.168.159.129:2181,192.168.159.130:2181,192.168.159.131:2181";
    public static final Integer timeout = 5000;

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 第三个参数表示，当前连接上了，需要去通知哪个类（目前就是通知自己）
        ZooKeeper zooKeeper = new ZooKeeper(zkServerPath, timeout, new ZookeeperRecoverTest());

        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPassword = zooKeeper.getSessionPasswd();

        String ssid = "0x" + Long.toHexString(sessionId);
        System.out.println(ssid);

        log.debug("客户端开始连接zookeeper服务器...");
        log.debug("连接状态：{}", zooKeeper.getState());
        new Thread().sleep(2000);
        log.debug("连接状态：{}", zooKeeper.getState());

        new Thread().sleep(2000);
        log.debug("客户端开始重新连接zookeeper服务器...");

        ZooKeeper zkSession = new ZooKeeper(zkServerPath, timeout, new ZookeeperRecoverTest(), sessionId, sessionPassword);

        log.debug("重新连接状态：{}", zkSession.getState());
        new Thread().sleep(2000);
        log.debug("重新连接状态：{}", zkSession.getState());
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.debug("接收到watch通知：{}", watchedEvent);
    }
}
