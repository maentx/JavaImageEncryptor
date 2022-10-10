import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.*;
import java.security.spec.InvalidKeySpecException;

class EncryptPictureAES
{
    public static void main(String args[])
    {
        try {
            IvParameterSpec iv = new IvParameterSpec("1234567812345678".getBytes());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            char[] pwd = "password".toCharArray(); // Use a secret manager in production
            PBEKeySpec pbeKeySpec = new PBEKeySpec(pwd, "saltandp".getBytes(), 65536, 256);
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
            SecretKeySpec secretKey = new SecretKeySpec(pbeKey.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            BufferedImage input = ImageIO.read(new File("decrypted.png"));
            FileOutputStream output = new FileOutputStream("encrypted.png");
            CipherOutputStream cos = new CipherOutputStream(output, cipher);
            ImageIO.write(input,"png",cos);
            cos.close();
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException: " + e);
        }
        catch (NoSuchPaddingException e) {
            System.out.println("NoSuchPaddingException: " + e);
        }
        catch (InvalidKeySpecException e) {
            System.out.println("InvalidKeySpecException: " + e);
        }
        catch (InvalidKeyException e) {
            System.out.println("InvalidKeyException: " + e);
        }
        catch (InvalidAlgorithmParameterException e) {
            System.out.println("InvalidAlgorithmParameterException: " + e);
        }
    }
}
