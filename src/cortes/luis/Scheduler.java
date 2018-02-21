package cortes.luis;

import java.util.LinkedList;

public interface Scheduler {
    enum Type {
        FIRST_COME_FIRST_SERVED, SHORTEST_JOB_FIRST, ROUND_ROBIN;

        int slice = 0;

        public void setSlice(int slice) {
            this.slice = slice;
        }

        @Override
        public String toString() {
            if (slice == 0)
                return super.toString();
            else {
                String type = super.toString();
                return type + "_" + slice;
            }
        }
    }

    public LinkedList<Job> processJobs();
    public Type getType();
    public double getATT();
    public double getAPT();
    default double getAWT() {
        return getATT() - getAPT();
    }
}
