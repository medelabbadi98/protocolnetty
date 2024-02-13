package com.protocol.protocolnetty.proto.model;

public class BaseCommand extends Message {

    private boolean textChannel;

    public boolean getTextChannel() {
        return textChannel;
    }

    public void setTextChannel(boolean textChannel) {
        this.textChannel = textChannel;
    }

}
