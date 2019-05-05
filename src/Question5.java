import java.util.HashSet;

public class Question5 {
	private static Node head;
	Question5(String item){
		head = new Node(item);	
	}
	
	public static void main(String[] args) {
		Question5 message = new Question5("m1");
		message.add("m2").add("m1").add("m3").add("m3").add("m4");
		message.show();
		message.removeDuplicates();
		message.show();
	}
	public void show(){
		Node aux = head;
		while(aux!=null) {
			if(aux.prox==null) {System.out.println(aux.item);}
			else {System.out.print(aux.item+" - ");}
			aux=aux.prox;
		}
		
	}
	public Question5 add(String item) {
		Node aux = head;
		while(aux.prox!=null) {
			aux=aux.prox;
		}
		aux.prox = new Node(item);
		return this;
	}
	public void removeDuplicates() {
		HashSet<String> hashSet = new HashSet<String>();
		Node aux = head;
		Node prev = null;
		while(aux!=null) {
			if(hashSet.contains(aux.item)) {
				prev.prox=aux.prox;
			}
			else {
				prev = aux;
				hashSet.add(aux.item);
			}
			aux=aux.prox;
		}
		
	}


}
class Node{
	Node prox;
	String item;
	Node(String item){
		this.item=item;
	}
}
