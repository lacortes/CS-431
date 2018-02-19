package cortes.luis;

import java.util.LinkedList;

public class FCFS implements Scheduler {
    private LinkedList<Job> jobs;

    public FCFS(LinkedList<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public LinkedList<Job> processJobs() {
        int counter = 0;

        // Process Every job in order
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            job.setBeginTime(counter); // job begins
            counter += job.getRemainingTime(); // Get job's processing time
            job.reduceTime(counter);  // Run job for the duration of its processing time
            job.setEndTime(counter);  // Job ends at this time
        }
        return this.jobs;
    }

}
