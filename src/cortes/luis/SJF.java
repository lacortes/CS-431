package cortes.luis;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class SJF implements Scheduler {
    PriorityQueue<Job> jobs;

    public SJF(LinkedList<Job> jobs) {
        this.jobs = this.makeQueue(jobs);
    }

    @Override
    public LinkedList<Job> processJobs() {
        LinkedList<Job> processedJobs = new LinkedList<>();
        int counter = 0;
        while (!jobs.isEmpty()) {
            Job job = jobs.poll();  // Job with shortest time remaining

            job.setBeginTime(counter);  // Set begin time for job
            counter += job.getRemainingTime();  // Run job for its whole time
            job.setEndTime(counter);  // Set end time for job
            processedJobs.add(job);  // Add completed job to list of completed jobs
        }
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
}
