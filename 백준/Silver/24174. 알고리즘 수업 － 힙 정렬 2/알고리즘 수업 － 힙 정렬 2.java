import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int cnt;
    static boolean flag = false;
    static int[] data;

    static void heapSort(int[] arr){
        for (int i = arr.length / 2; i >= 1; i--){
            heapify(arr, i, N);
            if(flag){
                return;
            }
        }

        for (int i = N; i >= 2; i--){
            swap(arr, 1, i);
            heapify(arr, 1, i - 1);

            if(flag){
                return;
            }
        }
    }

    static void heapify(int[] arr, int idx, int size){
        int leftChild = idx * 2;
        int rightChild = idx * 2 + 1;
        int minIdx = 0;

        if (rightChild <= size){
            if (arr[leftChild] < arr[rightChild]){
                minIdx = leftChild;
            } else{
                minIdx = rightChild;
            }
        } else if (leftChild <= size){
            minIdx = leftChild;
        } else{
            return;
        }

        if (arr[minIdx] < arr[idx]){
            swap(arr, minIdx, idx);
            heapify(arr, minIdx, size);
        }
    }

    static void swap(int[] arr, int i, int j){
        cnt++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        if (cnt == K){
            flag = true;

            StringBuilder sb = new StringBuilder();
            for (int element: data){
                if (element == -1){
                    continue;
                }
                sb.append(element);
                sb.append(" ");
            }

            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        data = new int[N + 1];
        data[0] = -1;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        cnt = 0;
        heapSort(data);

        if (!flag){
            System.out.println("-1");
        }
    }
}