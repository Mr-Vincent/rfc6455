package com.demons.rfc6455.util;

import com.google.common.io.ByteProcessor;
import com.google.common.io.ByteStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author dongwei
 * @since 2019/12/12
 * Time: 16:26
 * I/O工具类
 */
public class Streams {

    private static final Logger LOGGER = LoggerFactory.getLogger(Streams.class);


    private Streams() {

    }

    public static String fromStream(InputStream in) throws IOException {
        String ret = ByteStreams.readBytes(in, new ByteProcessor<String>() {
            private byte[] bytes = {};

            @Override
            public boolean processBytes(byte[] buf, int off, int len) throws IOException {
                bytes = Arrays.copyOf(buf, len);
                return false;
            }

            @Override
            public String getResult() {
                String result = new String(bytes);
                LOGGER.debug(HexUtil.byteArrToHex(bytes));
                return result;
            }
        });
        return ret;
    }
}
