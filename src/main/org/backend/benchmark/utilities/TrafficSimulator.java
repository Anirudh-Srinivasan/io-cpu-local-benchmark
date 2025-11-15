package org.backend.benchmark.utilities;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TrafficSimulator {
    public String simulateIOLatency() throws InterruptedException {
        // ~[260-400]ms
        Random rand = new Random();
        long sleepTime = (long) ((0.26 + rand.nextDouble() * (0.42 - 0.26)) * 1000);
        Thread.sleep(sleepTime);
        return "Result";
    }
    public String simulateCPULatency(String input) throws InterruptedException {
        // ~[260-400]ms
        for(int i=0; i<6423; i++){
            long numberToHash = 123456789L + 1000*i;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                // Convert long to byte array
                ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
                buffer.putLong(numberToHash);
                byte[] numberBytes = buffer.array();

                byte[] hashBytes = md.digest(numberBytes);

                // Convert byte array to hexadecimal string
                BigInteger no = new BigInteger(1, hashBytes);
                String hashText = no.toString(16);
                while (hashText.length() < 64) { // SHA-256 is 64 hex characters
                    hashText = "0" + hashText;
                }

            } catch (NoSuchAlgorithmException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return "Hi";
    }
}
