import java.io.*;
import java.util.*;

public class Main {
    static int[][] my_pic;
    static int[][] teacher_pic;
    static int[][] tmp;
    static int[][] teacherHash;
    static final int MAX_SIZE = 2000;
    static final int HASH_SIZE = (1 << 30);
    static final int MOD = HASH_SIZE - 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //int T = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();

        for (int i = 0; i < H; i++){
            String line = br.readLine();
            for (int j = 0; j < W; j++){
                my_pic[i][j] = line.charAt(j) == 'o' ? 1 : 0;
            }
        }

        for (int i = 0; i < N; i++){
            String line = br.readLine();
            for (int j = 0; j < M; j++){
                teacher_pic[i][j] = line.charAt(j) == 'o' ? 1 : 0;
            }
        }

        // 내 사진의 해시값을 구한다.
        for (int i = 0; i < H; i++){
            // 가로 해시값을 먼저 구한다.
            tmp[0][i] = getHash(my_pic[i], W, 4);
        }
        // 이후 세로 해시값을 구한다.
        int myHash = getHash(tmp[0], H, 5);


        // 선생님의 해시값을 구한다.
        int mulC = calcMul(W, 4);
        int mulR = calcMul(H, 5);

        for (int i = 0; i < N; i++){
            tmp[0][i] = getHash(teacher_pic[i], W, 4);
            for (int j = 1; j < M - W + 1; j++){
                tmp[j][i] = getNext(tmp[j - 1][i], teacher_pic[i][j - 1], mulC, teacher_pic[i][j + W - 1], 4);
            }
        }

        for (int i = 0; i < M - W + 1; i++){
            teacherHash[0][i] = getHash(tmp[i], H, 5);
            for (int j = 1; j < N - H + 1; j++){
                teacherHash[j][i] = getNext(teacherHash[j - 1][i], tmp[i][j - 1], mulR, tmp[i][j + H - 1], 5);
            }
        }

        int ans = 0;
        for (int i = 0; i < N - H + 1; i++){
            for (int j = 0; j < M - W + 1; j++){
                if (teacherHash[i][j] == myHash) ans++;
            }
        }

        bw.write(ans + "\n");
        bw.flush();

        bw.close();
        br.close();
    }

    static void init(){
        my_pic = new int[MAX_SIZE][MAX_SIZE];
        teacher_pic = new int[MAX_SIZE][MAX_SIZE];
        tmp = new int[MAX_SIZE][MAX_SIZE];
        teacherHash = new int[MAX_SIZE][MAX_SIZE];
    }

    static int getHash(int[] line, int width, int shift){
        long hash = 0;

        for (int i = 0; i < width; i++){
            hash = (hash << shift) + hash + line[i];
        }
        return (int)(hash & MOD);
    }

    static int calcMul(int num, int shift){
        long rev = 1;

        for (int i = 1; i < num; i++){
            rev = (rev << shift) + rev;
        }

        return (int)(rev & MOD);
    }

    static int getNext(int prev, int sub, int mul, int add, int shift){
        long hash = prev - (sub * mul);
        hash = (hash << shift) + hash + add;
        return (int)(hash & MOD);
    }
}