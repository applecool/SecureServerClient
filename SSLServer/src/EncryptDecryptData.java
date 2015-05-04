import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class EncryptDecryptData {
	static Cipher cipher;
	SecretKey secretKey;

	public EncryptDecryptData() {
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			secretKey = keyGenerator.generateKey();
			cipher = Cipher.getInstance("AES");
		} catch (Exception e) {
		}

	}

	public String encrypt(String plainText) throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		String encryptedText = new BASE64Encoder().encode(encryptedByte);
		return encryptedText;
	}

	public String decrypt(String encryptedText) throws Exception {

		byte[] encryptedTextByte = new BASE64Decoder()
				.decodeBuffer(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}

}
