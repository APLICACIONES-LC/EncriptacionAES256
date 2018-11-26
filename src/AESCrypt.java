import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ivan
 */

public class AESCrypt {

    private static final String LLAVE_SIMETRICA = "123456789hola";

    public static void main(String[] args) {
        byte[] datosCifrados;
        datosCifrados = encDatos("que mas puedo meter ya no se que mas\nque mas puedo meter ya no se que mas");
        System.out.println("Datos Cifrados -> " + asHex(datosCifrados));
  
        System.out.println("Datos Descifrados -> " + dencDatos(datosCifrados));
        
    }

    public static byte[] encDatos(String cadena) {
//        byte[] aError = null;
        SecretKeySpec key = new SecretKeySpec(LLAVE_SIMETRICA.getBytes(), "AES");
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES");
            //Comienzo a encriptar
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] datosCifrados = cipher.doFinal(cadena.getBytes()); //cadena = a texto a cifrar
            /*
                 * TODO: Representar los bytes como string vía base64, así será
                 * humanamente leíble. La otra opción es expresar como hexadecimal
                 * 
                 * En este caso lo imprimo en pantalla como bytes.
             */
            return datosCifrados;
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            return null;
        }

    }

    public static String dencDatos(byte[] datosCifrados) {
        SecretKeySpec key = new SecretKeySpec(LLAVE_SIMETRICA.getBytes(), "AES");
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES");

            //Comienzo a desencriptar
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] datosDecifrados = cipher.doFinal(datosCifrados);
         
            return new String(datosDecifrados);
            /*
                 * TODO: Representar los bytes como string vía base64, así será
                 * humanamente leíble. La otra opción es expresar como hexadecimal
                 * 
                 * En este caso lo imprimo en pantalla como bytes.
             */
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println("desencriptar: "+e);
            return null;
        }
    }

    public static String asHex(byte buf[]) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }
}
