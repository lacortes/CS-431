package cortes.luis;

import java.util.LinkedList;

public interface Scheduler {
    public LinkedList<Job> processJobs();
}
