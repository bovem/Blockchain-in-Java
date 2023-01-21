import java.security.*;
import java.util.Base64;

public class StringUtil {
    public static String applySha256(String input){
        try{
            // Using SHA-256 algorithm for calculating hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Calculating the hash from the input
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for(int i=0;i<hash.length;i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length()==1){
                        hexString.append('0');
                }
                hexString.append(hex);
            }

            // Returning hash as string
            return hexString.toString();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static byte[] applyEDSASig(PrivateKey privateKey, String input){
        // Returns an array of bytes form the senders private key and input

        Signature dsa;
        byte[] output = new byte[0];
        try{
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            output = realSig;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return output;
    }

    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature){
        // Verifies that the data is signed with publicKey or not
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromKey(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
