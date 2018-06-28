package block;

public class Main
{
    public static void main(String[] args) throws Exception {
        Block genesisBlock = new Block("Hi im the first block", "007");
        System.out.println("Hash for block 1 : " + genesisBlock.hash);
    }
}