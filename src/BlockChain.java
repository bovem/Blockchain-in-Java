import java.security.SecureRandom;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.security.Security;
import java.util.Base64;
import java.util.HashMap;

public class BlockChain {

    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static float minimumTransaction = 0.1f;
    public static int difficulty=5;
    public static Wallet walletA;
    public static Wallet walletB;

    public static Transaction genesisTransaction;
//    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();

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

    //    public static void main(String[] args) {
    //        blockChain.add(new Block("First block on the chain", "0"));
    //        System.out.println("Mining block....");
    //        blockChain.get(0).mineBlock(difficulty);
    //
    //        blockChain.add(new Block("Second block on the chain", blockChain.get(blockChain.size()-1).hash));
    //        System.out.println("Mining block....");
    //        blockChain.get(1).mineBlock(difficulty);
    //
    //        blockChain.add(new Block("Third block on the chain", blockChain.get(blockChain.size()-1).hash));
    //        System.out.println("Mining block....");
    //        blockChain.get(2).mineBlock(difficulty);
    //
    //        blockChain.add(new Block("Fourth block on the chain", blockChain.get(blockChain.size()-1).hash));
    //        System.out.println("Mining block....");
    //        blockChain.get(3).mineBlock(difficulty);
    //
    //        blockChain.add(new Block("Fifth block on the chain", blockChain.get(blockChain.size()-1).hash));
    //        System.out.println("Mining block....");
    //        blockChain.get(4).mineBlock(difficulty);
    //
    //        System.out.println("Validity of blockchain: "+isValidChain());
    //
    //        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
    //        System.out.println("Block Chain: ");
    //        System.out.println(blockChainJson);
    //    }

    //    public static void main(String[] args) {
    //        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    //
    //        walletA = new Wallet();
    //        walletB = new Wallet();
    //
    //        System.out.println("Private and public keys: ");
    //        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
    //        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
    //
    //        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
    //        transaction.generateSignature(walletA.privateKey);
    //
    //        System.out.println("Signature verified: "+transaction.verifySignature());

    public static void main(String[] args) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        walletA = new Wallet();
        walletB = new Wallet();

        Wallet coinbase = new Wallet();

        genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f, null);
        genesisTransaction.generateSignature(coinbase.privateKey);

        genesisTransaction.transactionId = "0";
        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.recipient,
                genesisTransaction.value,
                genesisTransaction.transactionId));

        UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

        System.out.println("Creating and Mining Genesis Block");
        Block genesis = new Block("0");
        genesis.addTransaction(genesisTransaction);
        addBlock(genesis);

        Block block1 = new Block(genesis.hash);
        System.out.println("\nWalletA's balance is: "+walletA.getBalance());
        float sendFundToB = 40;
        System.out.println("\nWalletA is trying to send funds "+Float.toString(sendFundToB)+" to wallet B....");
        block1.addTransaction(walletA.sendFunds(walletB.publicKey, sendFundToB));
        addBlock(block1);

        System.out.println("\nWallet A's balance is: "+walletA.getBalance());
        System.out.println("Wallet B's balance is: "+walletB.getBalance());

        Block block2 = new Block(block1.hash);
        float sendFundToB2 = 1000;
        System.out.println("\nWalletA is trying to send funds "+Float.toString(sendFundToB2)+" to wallet B....");
        System.out.println("Which it dosen't have");
        block2.addTransaction(walletA.sendFunds(walletB.publicKey, sendFundToB2));
        addBlock(block2);

        System.out.println("\nWallet A's balance is: "+walletA.getBalance());
        System.out.println("Wallet B's balance is: "+walletB.getBalance());

        Block block3 = new Block(block2.hash);
        float sendFundToB3 = 20;
        System.out.println("\nWalletA is trying to send funds "+Float.toString(sendFundToB3)+" to wallet B....");
        block3.addTransaction(walletA.sendFunds(walletB.publicKey, sendFundToB3));
        addBlock(block3);

        System.out.println("\nWallet A's balance is: "+walletA.getBalance());
        System.out.println("Wallet B's balance is: "+walletB.getBalance());
    }

    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;

        String hashTarget = new String(new char[difficulty]).replace("\0", "0");

        HashMap<String, TransactionOutput> tempUTXOs = new HashMap<String, TransactionOutput>();

        tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

        for(int i=1;i<blockChain.size();i++){

            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hashes are not equal");
                return false;
            }

            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous hashes are not equal");
                return false;
            }

            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
                System.out.println("This block hasn't been mined");
                return false;
            }

            TransactionOutput tempOutput;

            for(int t=0;t< currentBlock.transactions.size();t++){
                Transaction currentTransaction = currentBlock.transactions.get(t);

                if(!currentTransaction.verifySignature()){
                    System.out.println("Signature on Transaction("+t+") is invalid");
                    return false;
                }
                if(currentTransaction.getInputsValue()!=currentTransaction.getOutputValue()){
                    System.out.println("Inputs are not equal to outputs on Transaction ("+t+")");
                }
                for(TransactionInput input: currentTransaction.inputs){
                    tempOutput = tempUTXOs.get(input.transactionOutputId);

                    if(tempOutput == null){
                        System.out.println("Reference input on Transaction ("+t+") is missing");
                        return false;
                    }
                    if(input.UTXO.value != tempOutput.value){
                        System.out.println("Reference input Transaction ("+t+") value is invalid");
                    }
                    tempUTXOs.remove(input.transactionOutputId);
                }
                for(TransactionOutput output: currentTransaction.outputs){
                    tempUTXOs.put(output.id, output);
                }
                if(currentTransaction.outputs.get(0).reciepient != currentTransaction.recipient){
                    System.out.println("Transaction ("+t+") output reciepient is not valid");
                    return false;
                }
                if(currentTransaction.outputs.get(1).reciepient != currentTransaction.sender){
                    System.out.println("Transaction ("+t+") output change is not sender");
                    return false;
                }
            }
        }
        System.out.println("Blockchain is valid");
        return true;
    }

    public static void addBlock(Block newBlock){
        newBlock.mineBlock(difficulty);
        blockChain.add(newBlock);
    }
}
