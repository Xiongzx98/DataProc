package org.test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

public class WriteFileThread extends Thread {

//    private String path = "";
//    private FilePosition position;
//    private List<String> strList = null;
//    RandomAccessFile file;
//    FileChannel channel;
//    ByteBuffer buffer;
//
//
//    WriteFileThread(List list, String path, FilePosition position) {
//        this.path = path;
//        this.strList = list;
//        this.position = position;
//    }
//
//    @Override
//    public void run() {
//
//        try {
//            file = new RandomAccessFile(path, "rw");
//            file.seek(position.getFileSeek());
//            channel = file.getChannel();
//            System.out.println("=========" + path + " begin write positon: " + position.getFileSeek() + "============");
//            buffer = ByteBuffer.wrap(strList.toString().replaceAll("(\\, +)|\\[|\\]", "").getBytes());
//            channel.write(buffer);
//            position.addFileSeek(buffer.capacity());
//            System.out.println("=========" + path + " end write positon: " + position.getFileSeek() + "============");
//            file.close();
//            channel.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}



