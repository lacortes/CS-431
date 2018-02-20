package cortes.luis;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        // Number of slices for Round Robing
        final int SlICE_1 = 2;
        final int SlICE_2 = 5;

        String[] testData = {"testdata1.txt", "testdata2.txt", "testdata3.txt"};

        for (String file : testData) {

            LinkedList<Job> jobs  = JobUtil.readInJobs(file);
            Scheduler[] schedulers = {
                    new FCFS(JobUtil.getJobsCopy(jobs)),
                    new SJF(JobUtil.getJobsCopy(jobs)),
                    new RR(JobUtil.getJobsCopy(jobs), SlICE_1),
                    new RR(JobUtil.getJobsCopy(jobs), SlICE_2)
            };

            System.out.println("+----------------------------------------------------------+");
            System.out.println("|###################-----Begin test-----###################|");
            System.out.println("+----------------------------------------------------------+");
            System.out.println("TESTING: " + file);
            for (Scheduler scheduler : schedulers) {
                System.out.println("Type: " + scheduler.getType());
                LinkedList<Job> completed = scheduler.processJobs();
                completed.forEach(job -> System.out.println("\t"+job));
            }
            System.out.println("+----------------------------------------------------------+");
            System.out.println("|###################-----End Of test-----##################|");
            System.out.println("+----------------------------------------------------------+");
            System.out.println();
            System.out.println();
        }
    }
}
