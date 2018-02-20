package cortes.luis;

public class Job {
    private int processingTime;
    private int remainingTime;
    private int beginTime;
    private int endTime;
    private String name;

    public Job(String name, int processingTime) {
        this.name = name;
        this.processingTime = processingTime;
        this.remainingTime = processingTime;
        this.beginTime = -1;
        this.endTime = -1;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void reduceTime(int minusTime) {
        if (remainingTime > 0)
            this.remainingTime -= minusTime;
    }

    public int getRemainingTime() {
        return this.remainingTime;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Job{" +
                "Name=" + name +
                ", Time=" + processingTime +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
