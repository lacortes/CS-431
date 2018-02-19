package cortes.luis;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Job> jobs  = JobUtil.readInJobs("testdata2.txt");

        Scheduler fcfs = new FCFS(JobUtil.getJobsCopy(jobs));
        LinkedList<Job> processed = fcfs.processJobs();
        System.out.println();
        for (Job job : processed) {
            System.out.println(job);
        }

        System.out.println();
        System.out.println();
        SJF sjf = new SJF(JobUtil.getJobsCopy(jobs));

        LinkedList<Job> check = sjf.processJobs();
        check.forEach(job -> System.out.println(job));
    }
}
