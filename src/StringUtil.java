import java.security.MessageDigest;
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
}
