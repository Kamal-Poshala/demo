package trees;

public class Node<K extends Comparable<K>,V> {
	
	int height;
	K key;
	V value;
	Node<K,V> left,right;
	Node(K key,V value){
		this.key=key;
		this.value=value;
		height=1;
	}
	
}
