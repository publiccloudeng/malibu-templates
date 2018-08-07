package block;

import java.security.MessageDigest;
import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data; //our data will be a simple message.
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;

    //Block Constructor.
    public Block(String data, String previousHash) throws Exception {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() throws Exception {
        String calculatedhash = applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
        return calculatedhash;
    }

    public void mineBlock(int level) throws Exception {
        String target = new String(new char[level]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, level).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public static String applySha256(String input) throws Exception {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input,
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw e;
        }
    }

}