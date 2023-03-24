package com.lp;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ZooKeeperServer;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ZookeeperUtil {

    private volatile ZooKeeper zookeeper = null;

    private final static CuratorFramework CURATOR_FRAME_WORK = CuratorFrameworkFactory.builder()
            .connectString("192.168.2.22:2181")
            .connectionTimeoutMs(5000)
            .retryPolicy(new RetryNTimes(0, 1000))
            .build();

    public ZookeeperUtil() throws InterruptedException {
        try {
            zookeeper = new ZooKeeper("localhost:2181", 10000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.Disconnected) {
                        System.out.println("失去连接");
                    }
                    if(watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        System.out.println("-------------连接成功-----------");
//                            cd.countDown();
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String LOCK_ROOT_PATH = "/Locks";

    private static final String LOCK_NODE_NAME = "Lock_";

    private static String lockPath;

    public void done() {
        ZooKeeperServer zooKeeperServer = new ZooKeeperServer();
//        zooKeeperServer
    }

    /**
     * 根据ip:端口获取dubbo所有注册的服务
     *
     * @throws Exception
     */
    public void getConnections() throws Exception {
        CURATOR_FRAME_WORK.start();
        List<String> list = CURATOR_FRAME_WORK.getChildren().forPath("/dubbo");
        list.removeIf("metadata"::equals);
        System.err.println(list);
    }

    public static void main(String[] args) throws Exception {
        ZookeeperUtil util = new ZookeeperUtil();
        CURATOR_FRAME_WORK.start();
//        util.getConnections();
//        util.lock();
//        util.unlock();
        for(int i = 0; i < 50; i++) {
            util.build();
            if (util.tryLock()) {
                util.lock();
                try {
//                    Thread.sleep(5000);
                    System.err.println("处理业务");
                } finally {
                    util.unlock();
                }
            }
        }
    }

    public void build() {
        try {
            Stat stat = zookeeper.exists(LOCK_ROOT_PATH, Boolean.FALSE);
            if(stat == null) {
                zookeeper.create(LOCK_ROOT_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            String lockPath = zookeeper.create(
                    LOCK_ROOT_PATH + "/" + LOCK_NODE_NAME,
                    Thread.currentThread().getName().getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL
            );
            this.lockPath = lockPath;
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void lock() {
        System.err.println("上锁");
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
/*        try {
            Stat stat = zookeeper.exists(LOCK_ROOT_PATH, Boolean.FALSE);
            if(stat == null) {
                zookeeper.create(LOCK_ROOT_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            String lockPath = zookeeper.create(
                    LOCK_ROOT_PATH + "/" + LOCK_NODE_NAME,
                    Thread.currentThread().getName().getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL
            );
            System.err.println(Thread.currentThread().getName() + " 锁创建: " + lockPath);
            this.lockPath = lockPath;
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }

    public Boolean tryLock() {
        Boolean res = false;
        try {
            List<String> list = CURATOR_FRAME_WORK.getChildren().forPath(LOCK_ROOT_PATH);
            Collections.sort(list);
            if(CollectionUtils.isNotEmpty(list) && lockPath.split("/")[2].equals(list.get(0))) {
                res = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public void unlock() {
        String path = this.lockPath;
        try {
            Stat st = zookeeper.exists(path, false);
            if(st != null) {
                zookeeper.delete(path, st.getAversion());
                System.err.println("执行删除临时节点完毕");
            }
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
