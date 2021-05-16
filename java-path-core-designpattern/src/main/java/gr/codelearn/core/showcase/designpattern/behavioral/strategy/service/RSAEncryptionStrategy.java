package gr.codelearn.core.showcase.designpattern.behavioral.strategy.service;

//concrete strategy
public class RSAEncryptionStrategy implements EncryptionStrategy {

	@Override
	public String encryptData(String text) {
		/*Text is hypothetically encrypted based on the RSA strategy*/
		return text.hashCode() + "" + "RSA".hashCode();
	}
}
