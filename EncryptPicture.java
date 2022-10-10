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

class EncryptPicture
{
    public static void main(String args[])
    {
        try {
            IvParameterSpec iv = new IvParameterSpec("1234567812345678".getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_128");
            PBEKeySpec pbeKeySpec = new PBEKeySpec("bar".toCharArray());
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
            PBEParameterSpec pbeParamSpec = new PBEParameterSpec("saltandp".getBytes(), 20, iv);
            Cipher pbeCipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_128");
            pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

            BufferedImage input = ImageIO.read(new File("decrypted.png"));
            FileOutputStream output = new FileOutputStream("encrypted.png");
            CipherOutputStream cos = new CipherOutputStream(output, pbeCipher);
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
