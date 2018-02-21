package cortes.luis;

import java.util.LinkedList;

public class FCFS implements Scheduler {
    private LinkedList<Job> jobs;
    private double avgTurnAroundTime = 0;
    private double avgProcessingTime = 0;

    public FCFS(LinkedList<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public double getATT() {
        return this.avgTurnAroundTime;
    }

    @Override
    public double getAPT() {
        return this.avgProcessingTime;
    }

    @Override
    public LinkedList<Job> processJobs() {
        int counter = 0;
        double avgPT = 0.00;
        double avgTT = 0.00;

        // Process Every job in order
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            job.setBeginTime(counter); // job begins
            counter += job.getRemainingTime(); // Get job's processing time
            job.reduceTime(counter);  // Run job for the duration of its processing time
            job.setEndTime(counter);  // Job ends at this time
            avgTT += job.getEndTime();
            avgPT += job.getProcessingTime();
        }
        this.avgProcessingTime = avgPT / jobs.size();
        this.avgTurnAroundTime = avgTT / jobs.size();

        return this.jobs;
    }

    @Override
    public Type getType() {
        return Type.FIRST_COME_FIRST_SERVED;
    }
}
