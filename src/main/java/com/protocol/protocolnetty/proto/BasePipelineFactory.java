package com.protocol.protocolnetty.proto;

import com.protocol.protocolnetty.proto.config.Config;
import com.protocol.protocolnetty.proto.config.ConfigKey;
import com.protocol.protocolnetty.proto.config.Keys;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.Map;
import java.util.Objects;

public abstract class BasePipelineFactory extends ChannelInitializer<Channel> {


    private final TrackerConnector connector;
    private final String protocol;
    private final int timeout;
    public String getString(ConfigKey<String> key) {
        return getString(key.getKey());
    }
    public int getInteger(ConfigKey<Integer> key) {
        String value = getString(key.getKey());
        if (value != null) {
            return Integer.parseInt(value);
        } else {
            Integer defaultValue = key.getDefaultValue();
            return Objects.requireNonNullElse(defaultValue, 0);
        }
    }
    public BasePipelineFactory(TrackerConnector connector, String protocol) {

        this.connector = connector;
        this.protocol = protocol;
        int timeout = getInteger(Keys.PROTOCOL_TIMEOUT.withPrefix(protocol));
        if (timeout == 0) {
            this.timeout = getInteger(Keys.SERVER_TIMEOUT);
        } else {
            this.timeout = timeout;
        }
    }

    protected abstract void addTransportHandlers(PipelineBuilder pipeline);

    protected abstract void addProtocolHandlers(PipelineBuilder pipeline);

    @SafeVarargs
    private void addHandlers(ChannelPipeline pipeline, Class<? extends ChannelHandler>... handlerClasses) {
        for (Class<? extends ChannelHandler> handlerClass : handlerClasses) {
            if (handlerClass != null) {

            }
        }
    }

    public static <T extends ChannelHandler> T getHandler(ChannelPipeline pipeline, Class<T> clazz) {

        return null;
    }

    @Override
    protected void initChannel(Channel channel) {
        final ChannelPipeline pipeline = channel.pipeline();

    }

}
