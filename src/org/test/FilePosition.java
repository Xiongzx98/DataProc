package org.test;

public class FilePosition {

    private Long fileSeek;
    private Long fileSkip;
    private int readOver;
    private int writeOver;



    public FilePosition(){
        this.fileSeek=(long)0;
        this.fileSkip=(long)0;
        this.readOver = 1;
        this.writeOver = 1;

    }

    public long getFileSeek() {
        return fileSeek;
    }

    public synchronized void addFileSeek(long fileSeek) {
        this.fileSeek += fileSeek;
    }

    public void setFileSeek(){
        this.fileSeek = (long)0;
    }

    public long getFileSkip() {
        return fileSkip;
    }

    public synchronized void addFileSkip(long fileSkip) {
        this.fileSkip += fileSkip;
    }

    public int getReadOver() {
        return readOver;
    }

    public void setReadOver(int exit) {
        this.readOver = exit;
    }

    public int getWriteOver() {
        return writeOver;
    }

    public void setWriteOver(int writeOver) {
        this.writeOver = writeOver;
    }
}
