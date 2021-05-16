package gr.codelearn.core.showcase.designpattern.behavioral.strategy.service;

//concrete strategy
public class AESEncryptionStrategy implements EncryptionStrategy {

	@Override
	public String encryptData(String text) {
		/*Text is hypothetically encrypted based on the AES strategy*/
		return text.hashCode() + "" + "AES".hashCode();
	}
}
