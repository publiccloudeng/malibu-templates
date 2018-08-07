package block;

import controller.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Main
{
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int level = 0;

    public static void main(String[] args) throws Exception {

//        Block genesisBlock = new Block("Hi im the first block", "007");
//        System.out.println("Hash for block 1 : " + genesisBlock.hash);

//        blockchain.add(new Block("Hi im the first block", "0"));
//        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
//        blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));

//        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//        System.out.println(blockchainJson);

//        blockchain.add(new Block("Hi im the first block", "0"));
//        System.out.println("Trying to Mine block 1... ");
//        blockchain.get(0).mineBlock(level);
//
//        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
//        System.out.println("Trying to Mine block 2... ");
//        blockchain.get(1).mineBlock(level);

//        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
//        System.out.println("Trying to Mine block 3... ");
//        blockchain.get(2).mineBlock(level);
//
//        System.out.println("\nBlockchain is Valid: " + isChainValid());
//
//        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//        System.out.println("\nThe block chain: ");
//        System.out.println(blockchainJson);

    }

    public static Boolean isChainValid() throws Exception {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[level]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, level).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}