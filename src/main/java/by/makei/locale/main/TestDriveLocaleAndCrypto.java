package by.makei.locale.main;

import by.makei.locale.util.ApacheCryptoUtil;
import by.makei.locale.util.CryptoUtil;
import by.makei.locale.util.ResourceManager;
import org.apache.commons.codec.digest.Crypt;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Locale;
import java.util.Scanner;


public class TestDriveLocaleAndCrypto {
    private ResourceManager manager;
    private ApacheCryptoUtil apacheCryptoUtil;
    private CryptoUtil cryptoUtil;


    public static void main(String[] args) {
        TestDriveLocaleAndCrypto instance = new TestDriveLocaleAndCrypto();
        try {
            instance.run();
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
            //TODO
            e.printStackTrace();
        }
    }

    public void run() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        manager = ResourceManager.INSTANCE;
        apacheCryptoUtil = ApacheCryptoUtil.INSTANCE;
        cryptoUtil = CryptoUtil.INSTANCE;

        System.out.println(manager.getString("menu1.welcome"));
        printMainMenu();
        mainMenu();
        System.out.println(manager.getString("menu2.crypto"));
        cryptoMenu();
    }

    private void cryptoMenu() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {
        boolean isInMenu = true;
        String line = null;
        String lineForEncryption = null;
        while (isInMenu) {
            if(lineForEncryption == null) {
                System.out.println(manager.getString("menu2.enterLine") + ":");
                lineForEncryption = readLineFromConsole();
            }
                printCryptoMenu();
                line = readLineFromConsole();

            switch (line) {
                case "1" -> {
                    cryptoUtil.encryptionSha(lineForEncryption, "SHA-256");
                    lineForEncryption = null;
                }
                case "2" -> {
                    cryptoUtil.encryptionSha(lineForEncryption,"SHA-512");
                    lineForEncryption = null;                }
                case "3" -> {
                    cryptoUtil.PBKDF2(lineForEncryption);
                    lineForEncryption = null;
                }
                case "4" -> {
                    String algorithmAndSold = "$6$2334";
                    apacheCryptoUtil.apacheCrypt(lineForEncryption, algorithmAndSold);
                    lineForEncryption = null;
                }
                case "5" -> {
                    String algorithmAndSold = "$5$2334";
                    apacheCryptoUtil.apacheCrypt(lineForEncryption, algorithmAndSold);
                    lineForEncryption = null;
                }
                case "6" -> {
                    String algorithmAndSold = "$1$2334";
                    apacheCryptoUtil.apacheCrypt(lineForEncryption, algorithmAndSold);
                    lineForEncryption = null;
                }
                case "7" -> {
                    apacheCryptoUtil.apacheCrypt(lineForEncryption);
                    lineForEncryption = null;
                }
                case "8" -> {
                    isInMenu = false;
                    System.out.println( manager.getString("menu2.exit"));
                }
                default -> {
                    System.out.println(line + " " + manager.getString("menu1.incorrectChoice"));
//                    printCryptoMenu();
                }
            }
        }
    }

    private void mainMenu() {
        boolean isInMenu = true;

        while (isInMenu) {
            String line = readLineFromConsole();
            switch (line) {
                case "1" -> {
                    manager.changeResource(new Locale("ru", "RU"));
                    printMainMenu();
                }
                case "2" -> {
                    manager.changeResource(new Locale("en", "EN"));
                    printMainMenu();
                }
                case "3" -> {
                    manager.changeResource(new Locale("be", "BY"));
                    printMainMenu();
                }
                case "4" -> {
                    isInMenu = false;
                }
                default -> {
                    System.out.println(line + " " + manager.getString("menu1.incorrectChoice"));
                    printMainMenu();
                }
            }
        }
    }

    private String readLineFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        line = line.strip();
        return line;
    }

    private void printCryptoMenu() {

        System.out.println(manager.getString("menu2.chooseMethod") + ":");
        System.out.println("1 - " + "SHA256");
        System.out.println("2 - " + "SHA512");
        System.out.println("3 - " + "PBKDF2");
        System.out.println("4 - " + "Apache SHA512 + 2334");
        System.out.println("5 - " + "Apache SHA256 + 2334");
        System.out.println("6 - " + "Apache MD5 + 2334");
        System.out.println("7 - " + "Apache SHA512 + " + manager.getString("menu2.randomGeneratedSalt"));

        System.out.println("8 - " + manager.getString("menu2.exit"));

    }

    private void printMainMenu() {

        System.out.println();
        System.out.println(manager.getString("menu1.chooseLanguage") + ":");
        System.out.println("1 - " + manager.getString("menu1.ru"));
        System.out.println("2 - " + manager.getString("menu1.en"));
        System.out.println("3 - " + manager.getString("menu1.be"));
        System.out.println();
        System.out.println("4 - " + manager.getString("menu1.goToCryptoMenu"));
    }


}
