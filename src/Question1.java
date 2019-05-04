
public class Question1 {
	public static void main(String[] args) {
		String word = "User is not allowed      ";
		int length = 19;
		Question1.alterChar(word.toCharArray(), length);
	}
	public static void alterChar(char[] array, int length) {
		int spaces = Question1.countSpaces(array,length);
		for (int i = length-1; i >= 0; i--) {
			int dif=i+(spaces*3)-spaces;
			if(array[i]!=' ') {array[dif]=array[i];}
			else {
				array[dif-2]='&';
				array[dif-1]='3';
				array[dif]='2';
				spaces--;
			}
		}
		System.out.println(array);
	}
	public static int countSpaces(char[] array,int length) {
		int spaces=0;
		for(int i=0;i<length;i++) {
			if(array[i]==' ') {spaces++;}
		}
		return spaces;
	}
}
