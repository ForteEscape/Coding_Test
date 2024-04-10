import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, Q, k;
    static Deque<Integer> deque;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        deque = new ArrayDeque<>();
        int[][] query = new int[Q][];
        int dir = 0;
        int lastSortQuery = -1;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int opt = Integer.parseInt(st.nextToken());

            if (opt == 0) {
                int num = Integer.parseInt(st.nextToken());
                query[i] = new int[]{opt, num};
                continue;
            } else if (opt == 1) {
                lastSortQuery = i;
            }

            query[i] = new int[]{opt};
        }

        for(int i = 0; i < Q; i++) {
            int opt = query[i][0];

            if (i <= lastSortQuery) {
                if(opt == 0) {
                    if(dir == 0) {
                        deque.addFirst(query[i][1]);
                    } else {
                        deque.addLast(query[i][1]);
                    }
                } else if(opt == 1) {
                    if(i == lastSortQuery) {
                        List<Integer> tempList = new ArrayList<>(deque);

                        if (dir == 1) {
                            Collections.sort(tempList, Comparator.comparingInt(e -> -e));
                        } else {
                            Collections.sort(tempList, Comparator.comparingInt(e -> e));
                        }

                        deque.clear();
                        deque.addAll(tempList);
                    }
                } else {
                    dir ^= 1;
                }
                continue;
            }

            if (opt == 0) {
                if (dir == 0) {
                    deque.addFirst(query[i][1]);
                } else {
                    deque.addLast(query[i][1]);
                }
            } else if(opt == 2) {
                dir ^= 1;
            }
        }
        
        List<Integer> ansList = new ArrayList<>(deque);
        System.out.println(dir == 0 ? ansList.get(k - 1) : ansList.get(ansList.size() - k));
    }
}