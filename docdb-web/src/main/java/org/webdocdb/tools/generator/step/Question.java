package org.webdocdb.tools.generator.step;

public interface Question {

	public String getText();
	
	public ValidationResult validate(String value);
	
	public void process(String value);

	public Question next();
	
	public boolean hasNext();
	
	public static class ValidationResult {
		private boolean result;
		private String invalidAnswer;
		
		public ValidationResult(boolean result) {
			this.result = result;
		}
		public ValidationResult(boolean result, String invalidAnswer) {
			this.result = result;
			if (!result) {
				this.invalidAnswer = invalidAnswer;
			}
		}
		public boolean isResult() {
			return result;
		}
		public String getInvalidAnswer() {
			return invalidAnswer;
		}
		
	}
}
