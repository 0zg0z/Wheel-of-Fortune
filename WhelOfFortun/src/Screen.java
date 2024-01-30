
public class Screen {

	static void QueuePrinter(Queue Q) {  //prints Queue to console
		int size = Q.size();
		for (int i = 0; i < size; i++) {
			System.out.print(Q.peek());
			Q.enqueue(Q.dequeue());
		}
			
	}
	
	static void StackPrinter(Stack S) {     //prints stack to console
		int size = S.size();
		Stack Sc = new Stack(size);
		for (int i = 0; i < size; i++) {
			System.out.print(S.peek());
			Sc.push(S.pop());
		}
		for (int i = 0; i < size; i++) {
			S.push(Sc.pop());
		}
			
	}
	
}
