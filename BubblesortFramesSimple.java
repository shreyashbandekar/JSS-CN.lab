import java.util.Scanner;
import java.util.Random;

public class BubblesortFramesSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Enter the number of frames: ");
        int n = sc.nextInt();
        int[][] frames = new int[n][2]; // Array to store frames [value, sequence number]
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the value of Frame " + (i + 1) + ": ");
            int value = sc.nextInt();
            int seqNo = random.nextInt(1000);
            frames[i][0] = value;
            frames[i][1] = seqNo;
        }
        System.out.println("Frames before sorting are:");
        printFrames(frames);
        bubbleSortFrames(frames);
        System.out.println("Frames after sorting are:");
        printFrames(frames);
    }
    public static void printFrames(int[][] frames) {
        for (int[] frame : frames) {
            System.out.println(frame[0] + "->" + frame[1]);
        }
    }
    public static void bubbleSortFrames(int[][] frames) {
        int n = frames.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (frames[j][1] > frames[j + 1][1]) {
                    int[] temp = frames[j];
                    frames[j] = frames[j + 1];
                    frames[j + 1] = temp;
                }
            }
        }
    }
}
