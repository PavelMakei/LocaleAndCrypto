package by.makei.locale.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public enum CryptoUtil {
    INSTANCE;
    private ResourceManager manager;

    {
        manager = ResourceManager.INSTANCE;
    }

    public void PBKDF2(String lineForEncryption) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
        byte[] salt = {23,34};
        KeySpec spec = new PBEKeySpec(lineForEncryption.toCharArray(), salt, 65536, 128);//The third parameter (65536) is effectively the strength parameter. It indicates how many iterations that this algorithm run for, increasing the time it takes to produce the hash.
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] digest = factory.generateSecret(spec).getEncoded();
        BigInteger bigInt = new BigInteger(1, digest); //1(sign+) or -1(sign-)
        System.out.println(lineForEncryption + " " + manager.getString("menu2.enteredLine"));
        System.out.println(bigInt + " " + manager.getString("menu2.encryptedLine"));
    }

    public void encryptionSha(String lineForEncryption, String shaVariant) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance(shaVariant);
        byte[] data1 = lineForEncryption.getBytes("UTF-8");
        messageDigest.update(data1);//if we need to add blocks of bytes separately
        byte[] salt = {23,34};
        messageDigest.update(salt);// add additional security
        byte[] digest = messageDigest.digest();
        //  byte[] digest = messageDigest.digest(data1);//simple using
        BigInteger bigInt = new BigInteger(1, digest); //1(sign+) or -1(sign-)
        System.out.println(lineForEncryption + " " + manager.getString("menu2.enteredLine"));
        System.out.println(bigInt + " " + manager.getString("menu2.encryptedLine"));
    }
}
