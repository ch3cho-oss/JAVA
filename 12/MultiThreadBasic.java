public class MultiThreadBasic {
    static class NumberThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.print(i + " ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class LetterThread extends Thread {
        @Override
        public void run() {
            for (char c = 'A'; c <= 'J'; c++) {
                System.out.print(c + " ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new NumberThread().start();
        new LetterThread().start();
    }
}
