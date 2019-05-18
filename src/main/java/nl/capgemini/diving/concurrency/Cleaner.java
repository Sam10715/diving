package nl.capgemini.diving.concurrency;

public class Cleaner extends Thread {
    private Toilet toilet;

    private Cleaner(Toilet toilet) {
        this.toilet = toilet;
    }

    private static Cleaner instance = null;

    public static synchronized Cleaner getInstance() {
        if (instance == null) {
            instance = new Cleaner(Toilet.getInstance());

        }
        return instance;
    }

    public void run() {


        try {
            toilet.clean();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
