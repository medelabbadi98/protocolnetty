package com.protocol.protocolnetty.proto;

public interface LifecycleObject {
    void start() throws Exception;
    void stop() throws Exception;
}
