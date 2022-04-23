package utilities;

import java.io.Serializable;

public class Node implements Serializable{
		private static final long serialVersionUID = 1L;


		private Node left;
		private Node right;
		String word;
		
		public Node() {
			
		}
		
		public Node(String word) {
			this.word = word;
		}
		
		public void insertNode(String newWord) {
			
			if((newWord.compareTo(this.word)) > 0) {
				if(right == null) {
					right = new Node(newWord);
				}else {
					right.insertNode(newWord);
				}
			}else {
				if(left == null) {
					left = new Node(newWord);
				}else {
					left.insertNode(newWord);
				}
			}
			
		}
		public void AscendingSort() {
			if(left != null) {
				left.AscendingSort();
				System.out.println(word);
			}
			
			if(right != null) {
				right.AscendingSort();
				System.out.println(word);
			}
		}
}
