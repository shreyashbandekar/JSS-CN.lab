import java.util.Scanner;

public class SimpleCRC {
    private static final int POLYNOMIAL = 0x1021;
    private static final int INITIAL_CRC = 0xFFFF;

    public static String calculateCRC(String data) {
        int crc = INITIAL_CRC;
        for (char c : data.toCharArray()) {
            int ascii = (int) c;
            crc ^= (ascii << 8) & 0xFFFF;
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0)
                    crc = (crc << 1) ^ POLYNOMIAL;
                else
                    crc <<= 1;
                crc &= 0xFFFF; // Ensure it's a 16-bit value
            }
        }
        return Integer.toHexString(crc).toUpperCase();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the data for CRC calculation: ");
        String inputData = scanner.nextLine().trim();
        String calculatedCRC = calculateCRC(inputData);
        System.out.println("Calculated CRC: " + calculatedCRC);
        System.out.print("Enter the received data (message + CRC): ");
        String receivedData = scanner.nextLine().trim();
        String receivedMessage = receivedData.substring(0, receivedData.length() - calculatedCRC.length());
        String receivedCRC = receivedData.substring(receivedData.length() - calculatedCRC.length());
        if (calculatedCRC.equals(receivedCRC))
            System.out.println("CRC Check: Data is intact. Received message: " + receivedMessage);
        else
            System.out.println("CRC Check: Data is corrupted. Discarding the message.");
            scanner.close();
    }
}
