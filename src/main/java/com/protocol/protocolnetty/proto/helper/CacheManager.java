package com.protocol.protocolnetty.proto.helper;

import com.protocol.protocolnetty.proto.config.Config;
import com.protocol.protocolnetty.proto.model.BaseModel;
import com.protocol.protocolnetty.proto.model.Device;
import com.protocol.protocolnetty.proto.model.Server;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheManager {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final CacheGraph graph = new CacheGraph();
    public Config getConfig() {
        return null;
    }

    public <T extends BaseModel> T getObject(Class<T> clazz, long id) {
        try {
            lock.readLock().lock();
            return graph.getObject(clazz, id);
        } finally {
            lock.readLock().unlock();
        }
    }

    public Server getServer() {
        return null;
    }
}
