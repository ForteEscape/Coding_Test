import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static class SegmentTree{
        public static int size;
        public static int[] tree;

        public SegmentTree(int arrSize){
            int height = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
            size = 1 << (height + 1);
            tree = new int[size];
        }

        public int init(int[] arr, int idx, int start, int end){
            if(start == end){
               return  tree[idx] = arr[start];
            }

            int leftMin = init(arr, idx * 2, start, (start + end) / 2);
            int rightMin = init(arr, idx * 2 + 1, (start + end) / 2 + 1, end);

            return tree[idx] = Math.min(leftMin, rightMin);
        }

        public int searchMin(int idx, int searchStart, int searchEnd, int left, int right){
            if (left > searchEnd || right < searchStart){
                return -1;
            }

            if (left <= searchStart && searchEnd <= right){
                return tree[idx];
            }

            int leftMin = searchMin(idx * 2, searchStart, (searchStart + searchEnd) / 2, left, right);
            int rightMin = searchMin(idx * 2 + 1, (searchStart + searchEnd) / 2 + 1, searchEnd, left, right);

            if (leftMin == -1){
                return rightMin;
            } else if (rightMin == -1){
                return leftMin;
            } else {
                return Math.min(leftMin, rightMin);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] data = new int[N + 1];

        SegmentTree stree = new SegmentTree(N);

        for (int i = 1; i <= N; i++){
            data[i] = Integer.parseInt(br.readLine());
        }

        stree.init(data, 1, 1, N);

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            System.out.println(stree.searchMin(1, 1, N, start, end));
        }
    }
}

