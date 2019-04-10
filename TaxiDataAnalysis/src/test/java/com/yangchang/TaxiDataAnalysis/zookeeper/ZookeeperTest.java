package com.yangchang.TaxiDataAnalysis.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

@Slf4j
public class ZookeeperTest implements Watcher {
    public static final String zkServerPath = "127.0.0.1:2181";
    // public static final String zkServerPath = "127.0.0.1:2181,127.0.0.2:2181,127.0.0.3:2181";
    public static final Integer timeout = 5000;

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 第三个参数表示，当前连接上了，需要去通知哪个类（目前就是通知自己）
        ZooKeeper zooKeeper = new ZooKeeper(zkServerPath, timeout, new ZookeeperTest());
        log.debug("客户端开始连接zookeeper服务器...");
        log.debug("连接状态：{}", zooKeeper.getState());
        new Thread().sleep(2000);
        log.debug("连接状态：{}", zooKeeper.getState());
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.debug("接收到watch通知：{}", watchedEvent);
    }
}
