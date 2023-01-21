package BlockChain;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
    // A wallet stores transactions in blockchain

    // Public Key will be our wallet address
    // You can share this with other people
    public PrivateKey privateKey;

    // Private Key will used to sign transactions
    public PublicKey publicKey;

    public Wallet(){
        generateKeyPair();
    }

    public void generateKeyPair(){
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            keyGen.initialize(ecSpec, random);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
