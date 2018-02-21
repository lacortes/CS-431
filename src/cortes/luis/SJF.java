package cortes.luis;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class SJF implements Scheduler {
    private PriorityQueue<Job> jobs;
    private double avgProcessingTime;
    private double avgTurnAroundTime;

    public SJF(LinkedList<Job> jobs) {
        this.jobs = this.makeQueue(jobs);
        this.avgProcessingTime = 0.00;
        this.avgTurnAroundTime = 0.00;
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
        LinkedList<Job> processedJobs = new LinkedList<>();

        double avgPT = 0;
        double avgTT = 0;
        int counter = 0;
        while (!jobs.isEmpty()) {
            Job job = jobs.poll();  // Job with shortest time remaining

            job.setBeginTime(counter);  // Set begin time for job
            counter += job.getRemainingTime();  // Run job for its whole time
            job.setEndTime(counter);  // Set end time for job
            processedJobs.add(job);  // Add completed job to list of completed jobs
            avgPT += job.getProcessingTime();
            avgTT += job.getEndTime();
        }
        this.avgProcessingTime = avgPT / processedJobs.size();
        this.avgTurnAroundTime = avgTT / processedJobs.size();
        return processedJobs;
    }

    private PriorityQueue<Job> makeQueue(LinkedList<Job> incoming) {
        PriorityQueue<Job> temp = new PriorityQueue<>((Job o1, Job o2) ->{
            if (o1.getRemainingTime() < o2.getRemainingTime())
                return -1;
            else if (o1.getRemainingTime() > o2.getRemainingTime())
                return 1;
            else
                return 0;
        });
        incoming.forEach(job -> temp.add(job));
        return temp;
    }

    @Override
    public Type getType() {
        return Type.SHORTEST_JOB_FIRST;
    }
}
