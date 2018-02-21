package cortes.luis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class RR implements Scheduler {
    private int slices;
    private ArrayList<Job> jobs;
    private LinkedList<Integer> turnAroundList = new LinkedList<>();
    private double avgProcessingTime;
    private double avgTurnAroundTime;

    public RR(LinkedList<Job> jobs, int slices) {
        this.jobs = this.makeArrayList(jobs);
        this.slices = slices;
        this.avgProcessingTime = this.calcAvgProcessingTime(jobs);
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
        LinkedList<Job> processed = new LinkedList<>();

        int counter = 0;
        while (this.jobs.size() > 0) {
            Stack<Job> toRemove = new Stack<>();
            for (int i = 0; i < this.jobs.size(); i++) {
                Job job = this.jobs.get(i);
                int beginTime = counter;

                if (job.getRemainingTime() < this.slices) {  // Job time is shorter than slice, and done
                    int remainTime = job.getRemainingTime();

                    job.reduceTime(remainTime);  // End the job
                    counter += remainTime;  // Increase counter by correct time
                    turnAroundList.add(counter);
                    toRemove.add(job);
                } else if ((job.getRemainingTime() - this.slices) == 0) {  // Job is done
                    job.reduceTime(this.slices);
                    counter += slices;
                    turnAroundList.add(counter);
                    toRemove.add(job);
                } else {  // Job still has time left before done.
                    job.reduceTime(this.slices);
                    counter += slices;
                }
                Job marker = new Job(job.getName(), job.getRemainingTime());
                marker.setBeginTime(beginTime);
                marker.setEndTime(counter);
                processed.add(marker);
            }
            // Remove jobs that are done
            while (!toRemove.empty())
                this.jobs.remove(toRemove.pop());
        }
        this.avgTurnAroundTime = this.calcAvgTurnAroundTime(this.turnAroundList);
        return processed;
    }

    private ArrayList<Job> makeArrayList(LinkedList<Job> list) {
        ArrayList<Job> array = new ArrayList<>();
        list.forEach(job -> array.add(job));
        return array;
    }

    private double calcAvgProcessingTime(LinkedList<Job> jobs) {
        double amount  = 0;
        int count = 0;
        for (Job job: jobs) {
            amount += job.getProcessingTime();
            count++;
        }
        return amount / count;
    }

    private double calcAvgTurnAroundTime(LinkedList<Integer> list) {
        double amount  = 0;
        int count = 0;
        for (Integer num : list) {
            amount += num;
            count++;
        }
        return amount / count;
    }

    @Override
    public Type getType() {
        Type type = Type.ROUND_ROBIN;
        type.setSlice(this.slices);
        return type;
    }
}
