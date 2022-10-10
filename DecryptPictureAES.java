import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.crypto.*;
import javax.crypto.SecretKeyFactory;
import java.security.*;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.*;
import java.security.spec.InvalidKeySpecException;

class DecryptPictureAES
{
    public static void main(String args[])
    {
        try {
            IvParameterSpec iv = new IvParameterSpec("1234567812345678".getBytes());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec pbeKeySpec = new PBEKeySpec("bar".toCharArray(), "saltandp".getBytes(), 65536, 256);
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
            SecretKeySpec secretKey = new SecretKeySpec(pbeKey.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            File inputFile = new File("encrypted.png");
            FileInputStream fis = new FileInputStream(inputFile);
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            BufferedImage input = ImageIO.read(cis);
            cis.close();
            FileOutputStream output = new FileOutputStream("decrypted.png");
            ImageIO.write(input,"png",output);
        }
        catch (IOException e) {}
        catch (NoSuchAlgorithmException e) {}
        catch (NoSuchPaddingException e) {}
        catch (InvalidKeySpecException e) {
            System.out.println("InvalidKeySpecException");
        }
        catch (InvalidKeyException e) {
            System.out.println("InvalidKeyException");
        }
        catch (InvalidAlgorithmParameterException e) {
            System.out.println("InvalidAlgorithmParameterException: " + e);
        }
    }
}
