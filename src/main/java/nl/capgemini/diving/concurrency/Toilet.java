package nl.capgemini.diving.concurrency;

import sun.security.jca.GetInstance;

public class Toilet {

    private int numberOfVisits;

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    private Toilet() {

    }

    private static Toilet instance = null;

    public static synchronized Toilet getInstance() {
        if (instance == null) {

            instance = new Toilet();
        }
        return instance;

    }

    public synchronized void visit() throws InterruptedException {
        while (numberOfVisits >= 5) {
            System.out.println("Waiting for the cleaner");
            wait();
            System.out.println("Done waiting");
        }
        System.out.println("Shitting ... " + numberOfVisits);
        numberOfVisits++;
        notify();
    }

    public synchronized void clean() throws InterruptedException {

        for (; ; ) {
            if (numberOfVisits >= 5) {
                System.out.println("Cleaning ... ");
                numberOfVisits = 0;
                notify();
            } else {
                wait();
            }

        }

    }


}
