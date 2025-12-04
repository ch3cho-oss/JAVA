import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private static final Queue<Integer> queue = new LinkedList<>();
    private static final int CAPACITY = 5;

    static class Producer extends Thread {
        @Override
        public void run() {
            int num = 1;
            while (true) {
                synchronized (queue) {
                    while (queue.size() == CAPACITY) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(num);
                    System.out.println("生产者生产：" + num);
                    num++;
                    queue.notify();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int num = queue.poll();
                    System.out.println("消费者消费：" + num);
                    queue.notify();
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }
}
