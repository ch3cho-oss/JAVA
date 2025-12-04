import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 创建固定大小为3的线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // 提交5个任务
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("任务" + taskId + "由线程" + Thread.currentThread().getName() + "执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务" + taskId + "执行完成");
            });
        }
        
        executor.shutdown(); // 关闭线程池
    }
}
