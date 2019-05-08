
public class Question2 {
	public static void main(String[] args) {
		checkIsJumbled("you","yuo");
		checkIsJumbled("probably","porbalby");
		checkIsJumbled("despite","desptie");
		checkIsJumbled("moon","nmoo");
		checkIsJumbled("misspellings","mpeissngslli");	
		//Space O(n)
		//Time	O(n)
	}
	public static boolean checkIsJumbled(String rightWord,String wrongWord) {
		boolean isFirstLetterEqual = Question2.checkSameLetter(rightWord, wrongWord,0);
		boolean isPercentageAccepted = Question2.checkPercentage(rightWord, wrongWord);
		boolean result = isFirstLetterEqual && isPercentageAccepted;
		System.out.println(rightWord+", "+wrongWord+"->"+result);
		return result;
	}
	public static boolean checkSameLetter(String rightWord,String wrongWord,int i) {
		return rightWord.charAt(i)==wrongWord.charAt(i);
	}
	public static boolean checkPercentage(String rightWord,String wrongWord) {
		boolean hasSameLength = Question2.checkLength(rightWord, wrongWord);
		if(hasSameLength==false) {return false;}
		else if(rightWord.length()<=3) {return true;}
		else {
			double percentage=0;
			for (int i = 0; i < rightWord.length(); i++) {
				boolean sameLetter = Question2.checkSameLetter(rightWord, wrongWord, i);
				if(sameLetter==false) {
					percentage+=100f/rightWord.length();
				}
			}
			return percentage<(2f/3f)*100;
		}
	}
	public static boolean checkLength(String rightWord,String wrongWord) {
		return rightWord.length()==wrongWord.length();
	}
}
