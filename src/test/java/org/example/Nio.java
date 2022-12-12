package org.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class Nio {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(9090));
        ss.configureBlocking(false);
        SocketChannel client = ss.accept();
        if(client != null){
            client.configureBlocking(false);
            client.read(new ByteBuffer[1024]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.remove(1);
    }
}
