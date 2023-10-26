import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int A = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    int blueGiftResult = 0;
    int redGiftResult = 0;

    Deque<int[]> blueGiftQueue = new ArrayDeque<>();
    Deque<int[]> redGiftQueue = new ArrayDeque<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int orderTime = Integer.parseInt(st.nextToken());
      String color = st.nextToken();
      int giftAmount = Integer.parseInt(st.nextToken());

      if (color.equals("B")) {
        blueGiftResult += giftAmount;
        blueGiftQueue.addLast(new int[]{orderTime, giftAmount});
      } else {
        redGiftResult += giftAmount;
        redGiftQueue.addLast(new int[]{orderTime, giftAmount});
      }
    }

    int time = 1;
    int giftNumber = 1;
    List<Integer> blueGiftSeq = new ArrayList<>();
    List<Integer> redGiftSeq = new ArrayList<>();

    int currentBlueGift = 0;
    int currentRedGift = 0;
    int nextRedPackageTime = 0;
    int nextBluePackageTime = 0;

    while (!blueGiftQueue.isEmpty() || !redGiftQueue.isEmpty()) {
      if (!blueGiftQueue.isEmpty() && blueGiftQueue.peekFirst()[0] == time) {
        int[] blueGiftData = blueGiftQueue.pollFirst();

        if (currentBlueGift == 0) {
          nextBluePackageTime = blueGiftData[0] + A;
          blueGiftSeq.add(giftNumber++);
        }

        currentBlueGift += blueGiftData[1];
      } else if (!redGiftQueue.isEmpty() && redGiftQueue.peekFirst()[0] == time) {
        int[] redGiftData = redGiftQueue.pollFirst();

        if (currentRedGift == 0) {
          nextRedPackageTime = redGiftData[0] + B;
          redGiftSeq.add(giftNumber++);
        }

        currentRedGift += redGiftData[1];
      }

      if (currentBlueGift != 0 && time == nextBluePackageTime) {
        currentBlueGift--;

        if (A == 0) {
          while (currentBlueGift > 0) {
            currentBlueGift--;
            blueGiftSeq.add(giftNumber++);
          }
        } else {
          nextBluePackageTime += A;

          if (currentBlueGift != 0){
            blueGiftSeq.add(giftNumber++);
          }
        }
      }

      if (currentRedGift != 0 && time == nextRedPackageTime) {
        currentRedGift--;

        if (B == 0) {
          while (currentRedGift > 0) {
            currentRedGift--;
            redGiftSeq.add(giftNumber++);
          }
        } else {
          nextRedPackageTime += B;

          if (currentRedGift != 0){
            redGiftSeq.add(giftNumber++);
          }
        }
      }

      time++;
    }

    System.out.println(blueGiftResult);
    for (int element: blueGiftSeq){
      System.out.print(element + " ");
    }

    System.out.println("\n" + redGiftResult);
    for (int element: redGiftSeq){
      System.out.print(element + " ");
    }
  }
}