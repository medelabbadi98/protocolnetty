package com.protocol.protocolnetty.proto;

import com.protocol.protocolnetty.proto.helper.Checksum;
import com.protocol.protocolnetty.proto.helper.DataConverter;
import com.protocol.protocolnetty.proto.model.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;


public class TeltonikaProtocolEncoder extends ChannelOutboundHandlerAdapter {

    public TeltonikaProtocolEncoder() {

    }

    private ByteBuf encodeContent(byte[] content) {

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(0);
        buf.writeInt(content.length + 8);
        buf.writeByte(TeltonikaProtocolDecoder.CODEC_12);
        buf.writeByte(1); // quantity
        buf.writeByte(5); // type
        buf.writeInt(content.length);
        buf.writeBytes(content);
        buf.writeByte(1); // quantity
        buf.writeInt(Checksum.crc16(Checksum.CRC16_IBM, buf.nioBuffer(8, buf.writerIndex() - 8)));

        return buf;
    }


    protected Object encodeCommand(Command command) {

        if (command.getType().equals(Command.TYPE_CUSTOM)) {
            String data = command.getString(Command.KEY_DATA);
            if (data.matches("(\\p{XDigit}{2})+")) {
                return encodeContent(DataConverter.parseHex(data));
            } else {
                return encodeContent((data + "\r\n").getBytes(StandardCharsets.US_ASCII));
            }
        } else {
            return null;
        }
    }

}
