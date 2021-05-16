package gr.codelearn.core.showcase.nestedclass.local.domain;

public class PasswordChecker {
	//Example adapted from: https://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html

	// Regular expression which checks if string contains:
	// Minimum 8, maximum 10 characters, at least 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character
	static String regularExpression = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$";

	public static boolean validatePassword(String inputPassword) {
		// Effectively final (not re-assigned in the following code) so no need to declare it as final
		int passwordLength = 10;

		// Inner "structure" (class) is used here
		// Such a class is mainly used to add logic that is tightly related with the enclosing method. Other uses may
		// be to "group" fields together, etc.
		class PasswordValidator {
			String formattedPassword = null;

			// Class constructor
			PasswordValidator(String password) {
				// Replaces all characters based on the regular expression provided
				String currentPassword = password.replaceAll(regularExpression, "");
				// If the length of the password is 0, then the password is valid
				if (currentPassword.isEmpty()) {
					formattedPassword = currentPassword;
				} else {
					formattedPassword = null;
				}
			}

			public String getPassword() {
				return formattedPassword;
			}
		}
		// Instantiating the local class and using the constructor to validate the password
		PasswordValidator passwordValidator = new PasswordValidator(inputPassword);

		// If password is null, then the password is not valid and this returns "false", else if valid, it returns true
		return passwordValidator.getPassword() != null;
	}
}

