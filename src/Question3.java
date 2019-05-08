
public class Question3 {
	public static void main(String[] args) {
		Question3.checkTypo("pale","ple");
		Question3.checkTypo("pales","pale");
		Question3.checkTypo("pale","bale");
		Question3.checkTypo("pale","bake");
		Question3.checkTypo("pale","pale");
		//Time O(n)
		//Space O(n)
	}
	public static boolean checkTypo(String word1, String word2) {
		boolean result=false;
		if(word1.length()==word2.length()) {result=Question3.checkLetters(word1,word2)<=1;}
		else if(Math.abs(word1.length()-word2.length())>1) {result=false;}
		else if(word1.length()>word2.length()) {result=Question3.checkLetterDifferentSize(word1,word2);}
		else if(word1.length()<word2.length()) {result=Question3.checkLetterDifferentSize(word2,word1);}
		System.out.println(word1+", "+word2+" -> "+result);
		return result;		
	}
	public static int checkLetters(String word1, String word2) {
		if(word1.equalsIgnoreCase(word2)) {return 0;}
		int differentLetters=0;
		for (int i = 0; i < word1.length(); i++) {
			if(word1.charAt(i)!=word2.charAt(i)) {differentLetters+=1;}
		}
		return differentLetters;
	}
	private static boolean checkLetterDifferentSize(String word1, String word2) {
		if(word1.length()==1)return true;
		else if(word1.charAt(0)!=word2.charAt(0)) {
			return Question3.checkLetters(word1.substring(1), word2)==0;}
		else {
			return Question3.checkLetterDifferentSize(word1.substring(1), word2.substring(1));
		}
	}
}
