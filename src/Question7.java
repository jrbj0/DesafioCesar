
public class Question7 {
	private Node head;
	Question7(String item){
		this.head=new Node(item);
	}
	public static void main(String[] args) {
		Question7 list1 = new Question7("C").add("A").add("E").add("H").add("J").add("B").add("A");
		Question7 list2 = new Question7("D").add("F").add(list1.head.prox.prox.prox.prox);
		System.out.print("List1 -> ");
		list1.show();
		System.out.print("List2 -> ");
		list2.show();
		System.out.print("Intersection -> ");
		showIntersection(list1.getIntersection(list2));
		
		
	}
	public Node getIntersection(Question7 anotherList) {
		Node result;
		int size1 = this.getSize();
		int size2 = anotherList.getSize();
		if(size1>size2) {result=Question7.searchIntersection(size1-size2, this.head, anotherList.head);}
		else if(size2>size1) {result=Question7.searchIntersection(size2-size1, anotherList.head,this.head);}
		else if(size1==size2 && this==anotherList ) {result=this.head;}
		else if(size1==size2 && this!=anotherList) {result=Question7.searchIntersection(size1-size2+1, this.head, anotherList.head.prox);}
		else {result=null;}
		return result;
		}
	public static Node searchIntersection(int dif,Node list1,Node list2) {
		Node aux1 = list1;
		Node aux2 = list2;
		for (int i=0;i<dif;i++){
	        if (aux1 == null) return null;
	        aux1 = aux1.prox;
	    }
	    while (aux1 != null && aux2 != null) {
	        if (aux1.item == aux2.item) return aux1;
	        aux1 = aux1.prox;
	        aux2 = aux2.prox;
	    }

		return null;
	}
	public int getSize() {
		Node aux = this.head;
		int length=0;
		while(aux!=null) {
			length+=1;
			aux=aux.prox;
		}
		return length;
	}
	public void show() {
		Node aux=this.head;
		while(aux!=null) {
			if(aux.prox==null) {System.out.println(aux.item);}
			else {System.out.print(aux.item+" - ");}
			aux=aux.prox;
		}
	}
	public static void showIntersection(Node node) {
		if(node==null) {System.out.println("Don't have intersection");return;}
		while(node!=null) {
			if(node.prox==null) {System.out.println(node.item);}
			else{System.out.print(node.item+" - ");}
			node=node.prox;
		}
	}
	public Question7 add(Node node) {
		Node aux = this.head;
		while(aux.prox!=null) {
			aux=aux.prox;
		}
		aux.prox = node;
		return this;
	}
	public Question7 add(String item) {
		Node aux = this.head;
		while(aux.prox!=null) {
			aux=aux.prox;
		}
		aux.prox = new Node(item);
		return this;
	}
}
