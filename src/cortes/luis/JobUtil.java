package cortes.luis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JobUtil {
    public static LinkedList<Job> readInJobs(String fileName) {
        LinkedList<Job> jobs = new LinkedList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
            List<String> lines;
            lines = bufferedReader.lines().collect(Collectors.toList());

            for (int i = 0; i < lines.size(); i+=2) {
                String jobName = lines.get(i);
                int time = Integer.parseInt(lines.get(i+1));
                jobs.add(new Job(jobName, time));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jobs;
    }
}
