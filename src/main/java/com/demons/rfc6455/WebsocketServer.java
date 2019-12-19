package com.demons.rfc6455;

import com.demons.rfc6455.util.Checker;
import com.demons.rfc6455.util.HttpRequestParser;
import com.demons.rfc6455.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

import static com.demons.rfc6455.HttpResponseParser.parseToBytes;

/**
 * @author dongwei
 * @since 2019/12/12
 * Time: 15:45
 */
public class WebsocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketServer.class);
    private static final int DEFAULT_PORT = 80;

    private ServerSocket serverSocket;

    public WebsocketServer() throws IOException {
       serverSocket = new ServerSocket();
       serverSocket.bind(new InetSocketAddress(DEFAULT_PORT));
    }

    private void start() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            String s = Streams.fromStream(inputStream);
            HttpRequest httpRequest = HttpRequestParser.parse(s);
            Checker.header(httpRequest);
            OutputStream out = socket.getOutputStream();
            byte[] bytes = parseToBytes(httpRequest);
            if(bytes.length > 0){
                out.write(bytes);
            }
            String s1 = Streams.fromStream(inputStream);
        }
    }



    public static void main(String[] args) throws IOException {
        LOGGER.debug("=======start======");
        new WebsocketServer().start();
    }
}
