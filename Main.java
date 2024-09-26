public class Main {

    public static void main(String[] args) {

        Stack<String> errorLogEntries = new Stack<>();
        Stack<String> recentErrors = new Stack<>();
        Queue<String> queue = new Queue<>();

        // Skip the first line of the file
        StdIn.readLine();
        while (!StdIn.isEmpty()) {
            // Read the log file into program
            String logEntry = StdIn.readLine();

            // Enqueue all log entries into a queue
            queue.enqueue(logEntry);
        }

        int infoCount = 0, warnCount = 0, errorCount = 0, memoryWarnCount = 0;
        int dequeueCount = 0;

        while (!queue.isEmpty()) {
            // Dequeue entries one by one until the queue is empty
            String dequeuedLogEntry = queue.dequeue();

            // Implement a stack to store error log entries
            if (dequeuedLogEntry.contains("ERROR")){ // count ERROR log level
                errorCount++;
                errorLogEntries.push(dequeuedLogEntry);
            }
            else if (dequeuedLogEntry.contains("WARN")) { // count WARN log level
                warnCount++;
                if (dequeuedLogEntry.contains("Memory")) // count Memory warnings
                    memoryWarnCount++;
            }
            else if (dequeuedLogEntry.contains("INFO")) infoCount++; // count INFO log level

            dequeueCount++;
            // A list of the last 100 errors that occurred
            if ((queue.size() - dequeueCount) < 100)
                recentErrors.push(dequeuedLogEntry);
        }

        // Print out the result
        StdOut.println("INFO(s) count: " + infoCount);
        StdOut.println("WARN(s) count: " + warnCount);
        StdOut.println("ERROR(s) count: " + errorCount);
        StdOut.println("Memory warning(s) count: " + memoryWarnCount);

        StdOut.println("---------------------------------------------------");
        StdOut.println("Recent errors:");
        for (String log : recentErrors)
            StdOut.println(log);

    }

}