package com.protocol.protocolnetty.proto;

import io.netty.channel.group.ChannelGroup;

public interface TrackerConnector extends LifecycleObject {

    boolean isDatagram();

    boolean isSecure();

    ChannelGroup getChannelGroup();

}
