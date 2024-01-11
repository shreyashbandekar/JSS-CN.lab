import java.util.Scanner;

public class LeakyBucketAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the bucket size: ");
        int bucketSize = scanner.nextInt();

        System.out.print("Enter the output rate (tokens/second): ");
        int outputRate = scanner.nextInt();

        System.out.print("Enter the number of packets to simulate: ");
        int numPackets = scanner.nextInt();

        int storedTokens = 0;

        for (int i = 1; i <= numPackets; i++) {
            System.out.print("Enter packet size for packet " + i + ": ");
            int packetSize = scanner.nextInt();

            storedTokens = Math.min(storedTokens + outputRate, bucketSize);

            if (packetSize <= storedTokens) {
                System.out.println("Packet " + i + " sent successfully!");
                storedTokens -= packetSize;
            } else {
                System.out.println("Packet " + i + " dropped due to congestion!");
            }

            System.out.println("Remaining tokens in the bucket: " + storedTokens);
            System.out.println("-----------------------------");
        }
    }
}

Enter the bucket size: 10
Enter the output rate (tokens/second): 3
Enter the number of packets to simulate: 5
Enter packet size for packet 1: 4
Enter packet size for packet 2: 7
Enter packet size for packet 3: 2
Enter packet size for packet 4: 6
Enter packet size for packet 5: 5

  Packet 1 sent successfully!
Remaining tokens in the bucket: 7
-----------------------------
Packet 2 sent successfully!
Remaining tokens in the bucket: 4
-----------------------------
Packet 3 sent successfully!
Remaining tokens in the bucket: 6
-----------------------------
Packet 4 dropped due to congestion!
Remaining tokens in the bucket: 6
-----------------------------
Packet 5 sent successfully!
Remaining tokens in the bucket: 3
-----------------------------

import java.util.Scanner;

public class TokenBucketAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the bucket size: ");
        int bucketSize = scanner.nextInt();

        System.out.print("Enter the input rate (tokens/second): ");
        int inputRate = scanner.nextInt();

        System.out.print("Enter the number of packets to simulate: ");
        int numPackets = scanner.nextInt();

        int storedTokens = 0;

        for (int i = 1; i <= numPackets; i++) {
            System.out.print("Enter packet size for packet " + i + ": ");
            int packetSize = scanner.nextInt();

            storedTokens = Math.min(storedTokens + inputRate, bucketSize);

            if (packetSize <= storedTokens) {
                System.out.println("Packet " + i + " sent successfully!");
                storedTokens -= packetSize;
            } else {
                System.out.println("Packet " + i + " dropped due to lack of tokens!");
            }

            System.out.println("Remaining tokens in the bucket: " + storedTokens);
            System.out.println("-----------------------------");
        }
    }
}

Enter the bucket size: 10
Enter the input rate (tokens/second): 2
Enter the number of packets to simulate: 5
Enter packet size for packet 1: 3
Enter packet size for packet 2: 6
Enter packet size for packet 3: 8
Enter packet size for packet 4: 2
Enter packet size for packet 5: 7

  Packet 1 sent successfully!
Remaining tokens in the bucket: 7
-----------------------------
Packet 2 sent successfully!
Remaining tokens in the bucket: 5
-----------------------------
Packet 3 dropped due to lack of tokens!
Remaining tokens in the bucket: 5
-----------------------------
Packet 4 sent successfully!
Remaining tokens in the bucket: 3
-----------------------------
Packet 5 sent successfully!
Remaining tokens in the bucket: 1
-----------------------------
