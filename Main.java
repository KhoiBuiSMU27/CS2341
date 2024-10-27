import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // User enter the type of task they want to handle
        int task = Integer.parseInt(args[0]);

        switch (task) {
            case 1 -> handleJobs(false, false);
            case 2 -> handleJobs(true, false);
            case 3 -> handleJobs(false, true);
            default -> StdOut.println("Invalid task");
        }
    }

    private static void handleJobs(boolean withPriority, boolean dynamicArrival) {
        String[] lines = StdIn.readAllLines();
        int n = lines.length;

        MinPQ pq = new MinPQ(n);
        ArrayList<Job> jobs = new ArrayList<>();

        for (String line : lines) {
            String[] data = line.split(" ");
            Job job = getJob(withPriority, dynamicArrival, data);
            jobs.add(job);
        }

        if (!dynamicArrival)
            processTask1or2(pq, jobs, n);
        else
            processTask3(pq, jobs, n);
    }

    private static Job getJob(boolean withPriority, boolean dynamicArrival, String[] data) {
        int jobId = Integer.parseInt(data[0]);
        int processingTime = Integer.parseInt(data[1]);

        Job job;
        if (withPriority) { // get jobs in task 2
            int priority = Integer.parseInt(data[2]);
            job = new Job(jobId, processingTime, priority);
        }
        else if (dynamicArrival) { // get jobs in task 3
            int arrivalTime = Integer.parseInt(data[2]);
            job = new Job(jobId, processingTime, arrivalTime, true);
        }
        else { // get jobs in task 1
            job = new Job(jobId, processingTime);
        }
        return job;
    }

    private static void processTask1or2(MinPQ pq, ArrayList<Job> jobs, int n) {
        for (Job job : jobs)
            pq.insert(job);

        int currentTime = 0;
        int totalCompletionTime = 0;
        StringBuilder executionOrder = new StringBuilder("Execution order: [");

        /*
         Append the job with the highest priority to executionOrder
         and calculate the total completion time
        */
        while (!pq.isEmpty()) {
            Job job = pq.delMin();
            currentTime += job.getProcessingTime();
            totalCompletionTime += currentTime;
            executionOrder.append(job.getJobId()).append(", ");
        }

        executionOrder.delete(executionOrder.length() - 2, executionOrder.length());
        executionOrder.append("]");
        StdOut.println(executionOrder);

        // Output the average completion time
        double averageCompletionTime = (double) totalCompletionTime / n;
        StdOut.println("Average completion time: " + averageCompletionTime);
    }

    private static void processTask3(MinPQ pq, ArrayList<Job> jobs, int n) {
        int currentTime = 0;
        int totalCompletionTime = 0;
        int index = 0;

        StringBuilder executionOrder = new StringBuilder();
        executionOrder.append("Execution order: [");

        while (index < jobs.size() || !pq.isEmpty()) {
            // Insert jobs that have arrived by the current time
            while (index < jobs.size() && jobs.get(index).getArrivalTime() <= currentTime) {
                pq.insert(jobs.get(index));
                index++;
            }

            // If the priority queue is not empty, execute the job with the least processing time
            if (!pq.isEmpty()) {
                Job job = pq.delMin();
                currentTime += job.getProcessingTime();
                totalCompletionTime += currentTime;
                executionOrder.append(job.getJobId()).append(", ");
            } else {
                // If no jobs are available to process, advance the current time
                if (index < jobs.size()) {
                    currentTime = jobs.get(index).getArrivalTime();
                }
            }
        }

        executionOrder.delete(executionOrder.length() - 2, executionOrder.length());
        executionOrder.append("]");
        StdOut.println(executionOrder);

        // Output the average completion time
        double avgCompletionTime = (double) totalCompletionTime / n;
        StdOut.println("Average completion time: " + avgCompletionTime);
    }
}