public class SingletonApp {
    public static void main(String[] args) {

        final int SIZE = 20;

        Thread thread[] = new Thread[SIZE];

        for (int i = 0; i < SIZE; i++) {
            thread[i] = new Thread(new Thr());
            thread[i].start();
        }

        for (int i = 0; i < SIZE; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Singleton.count);
    }
}

class Thr implements Runnable {

    @Override
    public void run() {
        Singleton.getInstance();
    }
}

class Singleton {
    public static int count = 0;
    private static volatile Singleton instance = null;

    private Singleton() {
        count++;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
