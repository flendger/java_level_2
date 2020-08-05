import java.util.Arrays;

public class DemoLesson5 {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        doVariant1();
        doVariant2();
    }

    public static void doVariant1() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1.0f);
        System.out.println("Start var.1...");
        long a = System.currentTimeMillis();
        changeArray(arr);
        System.out.println("time ms: " + (System.currentTimeMillis() - a));

    }

    public static void doVariant2() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1.0f);
        System.out.println("Start var.2...");
        long startTime = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        long splitTime = System.currentTimeMillis() - startTime;
        long[] aTime = new long[2];

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                aTime[0] = System.currentTimeMillis();
                changeArray(a1);
                aTime[0] = System.currentTimeMillis() - aTime[0];
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                aTime[1] = System.currentTimeMillis();
                changeArray(a2);
                aTime[1] = System.currentTimeMillis() - aTime[1];
            }
        });
        t2.start();

        if (t1.isAlive()) {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (t2.isAlive()) {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long joinTime = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        joinTime = System.currentTimeMillis() - joinTime;
        long fullTime = System.currentTimeMillis() - startTime;

        System.out.println("Time to split array: " + splitTime);
        System.out.println("Time arr1: " + aTime[0]);
        System.out.println("Time arr2: " + aTime[1]);
        System.out.println("Time to join: " + joinTime);
        System.out.println("Sum time: " + (splitTime + Math.max(aTime[0], aTime[1]) + joinTime));
        System.out.println("Full time ms: " + fullTime);
    }

    public static void changeArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
