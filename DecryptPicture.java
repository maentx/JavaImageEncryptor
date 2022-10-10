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

class DecryptPicture
{
    public static void main(String args[])
    {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            PBEKeySpec pbeKeySpec = new PBEKeySpec("bar".toCharArray());
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
            PBEParameterSpec pbeParamSpec = new PBEParameterSpec("saltandp".getBytes(), 20);
            Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);

            File inputFile = new File("encrypted.png");
            FileInputStream fis = new FileInputStream(inputFile);
            CipherInputStream cis = new CipherInputStream(fis, pbeCipher);
            img = ImageIO.read(cis);
            cis.close();
            FileOutputStream output = new FileOutputStream("decrypted.png");
            ImageIO.write(img,"png",output);
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
