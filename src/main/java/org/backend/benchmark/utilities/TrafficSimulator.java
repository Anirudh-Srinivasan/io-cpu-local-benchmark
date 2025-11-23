package org.backend.benchmark.utilities;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TrafficSimulator {
    public void simulateIOLatency() throws InterruptedException {
        // ~[260-400]ms
        Random rand = new Random();
        long sleepTime = (long) ((0.26 + rand.nextDouble() * (0.42 - 0.26)) * 1000);
        Thread.sleep(sleepTime);
    }
    public String simulateCPULatency(String input) throws InterruptedException {
        // ~[260-400]ms
        String hashedContent = "";
        for(int i=0; i<6423; i++){
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                // Convert long to byte array
                ByteBuffer buffer = StandardCharsets.UTF_8.encode(input);
                byte[] numberBytes = buffer.array();

                byte[] hashBytes = md.digest(numberBytes);

                // Convert byte array to hexadecimal string
                BigInteger no = new BigInteger(1, hashBytes);
                String hashText = no.toString(16);
                while (hashText.length() < 64) { // SHA-256 is 64 hex characters
                    hashText = "0" + hashText;
                }
                hashedContent = hashText;
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return hashedContent;
    }
}
