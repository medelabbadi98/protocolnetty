package com.protocol.protocolnetty.proto.session;

import com.protocol.protocolnetty.proto.Protocol;
import com.protocol.protocolnetty.proto.model.Command;
import io.netty.channel.Channel;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class DeviceSession {

    private final long deviceId;
    private final String uniqueId;
    private final Protocol protocol;
    private final Channel channel;
    private final SocketAddress remoteAddress;

    public DeviceSession(
            long deviceId, String uniqueId, Protocol protocol, Channel channel, SocketAddress remoteAddress) {
        this.deviceId = deviceId;
        this.uniqueId = uniqueId;
        this.protocol = protocol;
        this.channel = channel;
        this.remoteAddress = remoteAddress;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Channel getChannel() {
        return channel;
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }


    public void sendCommand(Command command) {
        protocol.sendDataCommand(channel, remoteAddress, command);
    }

    public static final String KEY_TIMEZONE = "timezone";

    private final Map<String, Object> locals = new HashMap<>();

    public boolean contains(String key) {
        return locals.containsKey(key);
    }

    public void set(String key, Object value) {
        if (value != null) {
            locals.put(key, value);
        } else {
            locals.remove(key);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) locals.get(key);
    }

}
