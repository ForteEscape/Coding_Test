import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int testCase = 0; testCase < T; testCase++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      int teamId = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      int[][] result = new int[N][K]; // team id, problem number, [seq, score]
      int[] submitCount = new int[N];
      int[] lastSubmitIdx = new int[N];

      for (int i = 0; i < M; i++){
        st = new StringTokenizer(br.readLine());

        int team = Integer.parseInt(st.nextToken());
        int problemNo = Integer.parseInt(st.nextToken());
        int score = Integer.parseInt(st.nextToken());

        if (result[team - 1][problemNo - 1] < score){
          result[team - 1][problemNo - 1] = score;
        }

        submitCount[team - 1]++;
        lastSubmitIdx[team - 1] = i;
      }

      int[][] scoreSum = new int[N][2]; // [team, scoreSum]

      for (int team = 0; team < N; team++){
        scoreSum[team][0] = team;

        for (int problemNo = 0; problemNo < K; problemNo++){
          scoreSum[team][1] += result[team][problemNo];
        }
      }

      Arrays.sort(scoreSum, (x, y) -> {
        if (x[1] == y[1]){
          if (submitCount[x[0]] == submitCount[y[0]]){
            return lastSubmitIdx[x[0]] - lastSubmitIdx[y[0]];
          } else{
            return submitCount[x[0]] - submitCount[y[0]];
          }
        } else{
          return y[1] - x[1];
        }
      });

      int rank = 1;
      
      for (int i = 0; i < N; i++){
        if (scoreSum[i][0] != teamId - 1){
          rank++;
        } else{
          System.out.println(rank);
          break;
        }
      }
    }
  }
}
