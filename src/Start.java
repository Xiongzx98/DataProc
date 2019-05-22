import org.threads.ThreadPoolManager;

public class Start {
    public static void main(String[] args) {
        String filename = "/opt/test";           //文件路径
        String path = filename+"/data";          //落盘路径
        ThreadPoolManager threadPoolManager = new ThreadPoolManager(filename, path);
        threadPoolManager.start();
    }
}
