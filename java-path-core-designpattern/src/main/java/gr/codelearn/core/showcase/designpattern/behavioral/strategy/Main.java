package gr.codelearn.core.showcase.designpattern.behavioral.strategy;

import gr.codelearn.core.showcase.designpattern.behavioral.strategy.service.AESEncryptionStrategy;
import gr.codelearn.core.showcase.designpattern.behavioral.strategy.service.EncryptionStrategy;
import gr.codelearn.core.showcase.designpattern.behavioral.strategy.service.Encryptor;
import gr.codelearn.core.showcase.designpattern.behavioral.strategy.service.RSAEncryptionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//client
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		//encrypts text based on RSA strategy
		EncryptionStrategy rsaStrategy = new RSAEncryptionStrategy();
		Encryptor rsaEncryptor = new Encryptor(rsaStrategy);
		String encryptedText1 = rsaEncryptor.encrypt("This is plain text");
		logger.info("{}", encryptedText1);

		//encrypts text based on AES strategy
		EncryptionStrategy aesStrategy = new AESEncryptionStrategy();
		Encryptor aesEncryptor = new Encryptor(aesStrategy);
		String encryptedText2 = aesEncryptor.encrypt("This is plain text");
		logger.info("{}", encryptedText2);
	}
}
