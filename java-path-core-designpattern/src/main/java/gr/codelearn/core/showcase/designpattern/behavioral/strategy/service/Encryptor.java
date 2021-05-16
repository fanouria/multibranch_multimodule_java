package gr.codelearn.core.showcase.designpattern.behavioral.strategy.service;

//context
public class Encryptor {

	private EncryptionStrategy strategy;

	public Encryptor(EncryptionStrategy strategy) {
		this.strategy = strategy;
	}

	public String encrypt(String plainText) {
		return strategy.encryptData(plainText);
	}
}
