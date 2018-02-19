package cortes.luis;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        LinkedList<Job> jobs  = JobUtil.readInJobs("testdata1.txt");
        for (Job job : jobs) {
            System.out.println(job);
        }

        Scheduler fcfs = new FCFS(jobs);
        jobs = fcfs.processJobs();
        System.out.println();
        for (Job job : jobs) {
            System.out.println(job);
        }
    }
}
