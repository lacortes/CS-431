package cortes.luis;

public class Job {
    private int processingTime;
    private int currentTime;
    private int beginTime;
    private int endTime;
    private String name;

    public Job(String name, int processingTime) {
        this.name = name;
        this.processingTime = processingTime;
        this.currentTime = processingTime;
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

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void reduceTime(int minusTime) {
        if (currentTime > 0)
            this.currentTime -= minusTime;
    }

    public int getProcessingTime() {
        return this.processingTime;
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
