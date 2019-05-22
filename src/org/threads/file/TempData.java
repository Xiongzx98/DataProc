package org.threads.file;

import java.util.List;

public class TempData {

    private String filename;
    private List data;
    private long position;

    public TempData(String filename, List data, long position) {
        this.filename = filename;
        this.data = data;
        this.position = position;
    }

    public String getFilename() {
        return filename;
    }

    public List getData() {
        return data;
    }
    public void setData(List list){
        data = list;
    }

    public long getPosition() {
        return position;
    }

}
