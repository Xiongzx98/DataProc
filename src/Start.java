import org.threads.ThreadPoolManager;

public class Start {
    public static void main(String[] args) {
        String filename = "/opt/test";
        String path = filename+"/data";
        ThreadPoolManager threadPoolManager = new ThreadPoolManager(filename, path);
        threadPoolManager.start();
    }
}
