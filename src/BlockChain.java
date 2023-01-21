import java.util.ArrayList;
import com.google.gson.GsonBuilder;
public class BlockChain {

    public static int difficulty=5;

    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    //    public static void main(String[] args) {
    //        Block genesisBlock = new Block("First block on the chain", "0");
    //        System.out.println("Hash for the block 1: "+ genesisBlock.hash);
    //
    //        Block secondBlock = new Block("Second block on the chain", genesisBlock.hash);
    //        System.out.println("Hash for the block 2: "+ secondBlock.hash);
    //
    //        Block thirdBlock = new Block("Third block on the chain", secondBlock.hash);
    //        System.out.println("Hash for the block 3: "+ thirdBlock.hash);
    //    }

    public static boolean isValidChain(){
        // Checks if all the hashes are correctly calculated from the previous block
        Block currentBlock;
        Block previousBlock;

        for(int i=1;i<blockChain.size();i++){
            currentBlock=blockChain.get(i);
            previousBlock=blockChain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println((i+1)+" Block's current hash is invalid");
                return false;
            }
            if(!previousBlock.hash.equals((currentBlock.previousHash))){
                System.out.println((i+1)+" Block's previous hash is invalid");
                return false;
            }
        }
        return true;
    }

    //    public static void main(String[] args) {
    //        blockChain.add(new Block("First block on the chain", "0"));
    //        blockChain.add(new Block("Second block on the chain", blockChain.get(blockChain.size()-1).hash));
    //        blockChain.add(new Block("Third block on the chain", blockChain.get(blockChain.size()-1).hash));
    //        blockChain.add(new Block("Fourth block on the chain", blockChain.get(blockChain.size()-1).hash));
    //        blockChain.add(new Block("Fifth block on the chain", blockChain.get(blockChain.size()-1).hash));
    //    }

    public static void main(String[] args) {
        blockChain.add(new Block("First block on the chain", "0"));
        System.out.println("Mining block....");
        blockChain.get(0).mineBlock(difficulty);

        blockChain.add(new Block("Second block on the chain", blockChain.get(blockChain.size()-1).hash));
        System.out.println("Mining block....");
        blockChain.get(1).mineBlock(difficulty);

        blockChain.add(new Block("Third block on the chain", blockChain.get(blockChain.size()-1).hash));
        System.out.println("Mining block....");
        blockChain.get(2).mineBlock(difficulty);

        blockChain.add(new Block("Fourth block on the chain", blockChain.get(blockChain.size()-1).hash));
        System.out.println("Mining block....");
        blockChain.get(3).mineBlock(difficulty);

        blockChain.add(new Block("Fifth block on the chain", blockChain.get(blockChain.size()-1).hash));
        System.out.println("Mining block....");
        blockChain.get(4).mineBlock(difficulty);

        System.out.println("Validity of blockchain: "+isValidChain());

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println("Block Chain: ");
        System.out.println(blockChainJson);
    }
}
