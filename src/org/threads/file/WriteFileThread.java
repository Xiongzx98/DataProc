package org.threads.file;

import org.apache.log4j.Logger;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class WriteFileThread extends Thread {
    private static Logger logger = Logger.getLogger(WriteFileThread.class);

    private String path;
    private TempData data;
    private LinkedBlockingDeque list;

    public WriteFileThread(TempData data, LinkedBlockingDeque list) {
        this.path = data.getFilename();
        this.list = list;
        this.data = data;
    }

    @Override
    public void run() {
        if(data.getData() != null) {
            RandomAccessFile file;
            FileChannel channel;
            ByteBuffer buffer;
            long position = data.getPosition();
            try {
                file = new RandomAccessFile(data.getFilename(), "rw");
                file.seek(position);
                channel = file.getChannel();
                logger.info("=========" + path + " begin write positon: " + position + "============");
                buffer = ByteBuffer.wrap(data.getData().toString().replaceAll("(\\, +)|\\[|\\]", "").getBytes());
                position += buffer.capacity();
                logger.info("=========" + path + " end write positon: " + position + "============");
                TempData wrt = new TempData(path, null, position);
                list.putLast(wrt);
                channel.write(buffer);
                channel.close();
                file.close();
                data = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println("null");
    }
}

