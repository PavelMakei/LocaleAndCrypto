package by.makei.locale.util;

import org.apache.commons.codec.digest.Crypt;

public enum ApacheCryptoUtil {
    INSTANCE;
    private ResourceManager manager;

    {
        manager = ResourceManager.INSTANCE;
    }

    /**
     * SHA-512 salts start with $6$ and are up to 16 chars long.
     * SHA-256 salts start with $5$ and are up to 16 chars long
     * MD5 salts start with $1$ and are up to 8 chars long
     * DES, the traditional UnixCrypt algorithm is used with only 2 chars
     * Only the first 8 chars of the passwords are used in the DES algorithm!
     */
    public void apacheCrypt(String lineForEncryption, String algorithmAndSold) {
        Crypt crypt = new Crypt();
        //String encryptedString = crypt("1"); //sha512 and random salt
        String encryptedString = Crypt.crypt(lineForEncryption, algorithmAndSold); //

        System.out.println(lineForEncryption + " " + manager.getString("menu2.enteredLine"));
        System.out.println(encryptedString + " " + manager.getString("menu2.encryptedLine"));
    }

    /**
     * SHA-512 salts start with $6$ and are up to 16 chars long.
     * SHA-256 salts start with $5$ and are up to 16 chars long
     * MD5 salts start with $1$ and are up to 8 chars long
     * DES, the traditional UnixCrypt algorithm is used with only 2 chars
     * Only the first 8 chars of the passwords are used in the DES algorithm!
     */
    public void apacheCrypt(String lineForEncryption) {
        Crypt crypt = new Crypt();
        //String encryptedString = crypt("1"); //sha512 and random salt
        String encryptedString = Crypt.crypt(lineForEncryption); //

        System.out.println(lineForEncryption + " " + manager.getString("menu2.enteredLine"));
        System.out.println(encryptedString + " " + manager.getString("menu2.encryptedLine"));
    }
}
