package cortes.luis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class RR implements Scheduler {
    private int slices;
    private ArrayList<Job> jobs;

    public RR(LinkedList<Job> jobs, int slices) {
        this.jobs = this.makeArrayList(jobs);
        this.slices = slices;
    }

    @Override
    public LinkedList<Job> processJobs() {
        LinkedList<Job> completed = new LinkedList<>();

        int counter = 0;
        while (this.jobs.size() > 0) {
            Stack<Job> toRemove = new Stack<>();
            for (int i = 0; i < this.jobs.size(); i++) {
                Job job = this.jobs.get(i);

                if (job.getBeginTime() < 0) {  // Job begins
                    job.setBeginTime(counter);
                }

                if (job.getRemainingTime() < this.slices) {  // Job time is shorter than slice, and done
                    int remainTime = job.getRemainingTime();

                    job.reduceTime(remainTime);  // End the job
                    counter += remainTime;  // Increase counter by correct time
                    job.setEndTime(counter);  // Mark the end time for job
                    completed.add(JobUtil.jobClone(job));
                    toRemove.add(job);
                } else if ((job.getRemainingTime() - this.slices) == 0) {  // Job is done
                    job.reduceTime(this.slices);
                    counter += slices;
                    job.setEndTime(counter);
                    completed.add(JobUtil.jobClone(job));
                    toRemove.add(job);
                } else {  // Job still has time left before done.
                    job.reduceTime(this.slices);
                    counter += slices;
                }
            }
            // Remove jobs that are done
            while (!toRemove.empty())
                this.jobs.remove(toRemove.pop());
        }
        return completed;
    }

    private ArrayList<Job> makeArrayList(LinkedList<Job> list) {
        ArrayList<Job> array = new ArrayList<>();
        list.forEach(job -> array.add(job));
        return array;
    }

    @Override
    public Type getType() {
        Type type = Type.ROUND_ROBIN;
        type.setSlice(this.slices);
        return type;
    }
}
