import java.util.ArrayList;
import java.util.Date;
public class Block {
    public String hash;
    public String previousHash;
    public String merkelRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    // Data in the block
    //public String blockData;

    // Timestamp of block creation
    public long timeStamp;
    public int nonce;

    public Block(String previousHash){
        //this.blockData = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        String calculatedHash = StringUtil.applySha256(
                previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + merkelRoot
        );
        return calculatedHash;
    }

    public void mineBlock(int difficulty) {
        merkelRoot = StringUtil.getMerkleRoot(transactions);
        //String target = new String(new char[difficulty]).replace('\0', '0');
        String target = StringUtil.getDifficultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!!, Hash of new block : " + hash);
    }

    public boolean addTransaction(Transaction transaction){
        if(transaction==null) return false;
        if((previousHash!="0")){
            if((transaction.processTransaction() != true)){
                System.out.println("Transaction failed to process");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction successfully added to the chain");
        return true;
    }
}
