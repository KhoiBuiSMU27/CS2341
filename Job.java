public class Job implements Comparable<Job> {

    private final int jobId;
    private final int processingTime;
    private int priority;
    private int arrivalTime;

    // Constructor for Task 1
    public Job(int jobId, int processingTime) {
        this.jobId = jobId;
        this.processingTime = processingTime;
    }

    // Constructor for Task 2
    public Job(int jobId, int processingTime, int priority) {
        this.jobId = jobId;
        this.processingTime = processingTime;
        this.priority = priority;
    }

    // Constructor for Task 3
    public Job(int jobId, int processingTime, int arrivalTime, boolean dynamicArrival) {
        this.jobId = jobId;
        this.processingTime = processingTime;
        this.arrivalTime = arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getJobId() {
        return jobId;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public int compareTo(Job other) {
        if (this.priority == other.priority) {
            return Integer.compare(this.processingTime, other.processingTime);
        }
        return Integer.compare(this.priority, other.priority);
    }

}
