import java.util.*;
import java.io.*;

public class Main {

    static class Job {
        int id;
        int time;
        int enterTime;

        Job(int id, int time, int enterTime){
            this.id = id;
            this.time = time;
            this.enterTime = enterTime;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        Deque<Job> queue = new ArrayDeque<>();

        int id = 0;
        int time = 0;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            id = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());

            queue.addLast(new Job(id, time, 0));
        }

        int M = Integer.parseInt(br.readLine());
        PriorityQueue<Job> pq = new PriorityQueue<>((j1, j2) -> j1.enterTime - j2.enterTime);
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            id = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
            int enterTime = Integer.parseInt(st.nextToken());

            pq.offer(new Job(id, time, enterTime));
        }

        int jobTime = 0;
        StringBuilder sb = new StringBuilder();
        loop1 : while(jobTime < W){
            Job currentJob = queue.pollFirst();

            int processTime;
            if (currentJob.time <= T){
                processTime = currentJob.time;
            } else {
                processTime = T;
            }

            for (int i = 0; i < processTime; i++){
                jobTime++;

                if (jobTime > W){
                    break loop1;
                }
                currentJob.time--;

                if (!pq.isEmpty() && pq.peek().enterTime == jobTime){
                    queue.addLast(pq.poll());
                }
                sb.append(currentJob.id).append("\n");
            }

            if (currentJob.time > 0){
                queue.addLast(currentJob);
            }
        }

        System.out.println(sb);
    }
}