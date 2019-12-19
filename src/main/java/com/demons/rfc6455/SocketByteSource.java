package com.demons.rfc6455;

import com.google.common.base.Optional;
import com.google.common.io.ByteSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author dongwei
 * @since 2019/12/12
 * Time: 17:12
 */
public class SocketByteSource extends ByteSource {

    private InputStream in;

    public SocketByteSource(InputStream in){
        this.in = in;
    }
    @Override
    public InputStream openStream() throws IOException {
        return this.in;
    }

    @Override
    public Optional<Long> sizeIfKnown() {
        try {
            return Optional.of((long)in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.absent();
    }
}
