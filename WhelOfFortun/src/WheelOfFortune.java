

public class WheelOfFortune {
	
static void GameStart() throws InterruptedException {
		
	    //variables
		Stack S1 = (Stack)FileOperation.CountryRead(0);
		Stack S2 = StackLetters();
		int countrylinecount = (int)FileOperation.CountryRead(1);
		int randnumber = (int)(Math.random() * countrylinecount);
		String country = (String)findInStack(S1,randnumber);
		Queue Q1 = StringtoQueue(country);
		Queue Q2 = new Queue(Q1.size());
		int step = 1;
		int score = 0;
		int wheelnumber;
		char choosenchar;
		
		//for loop to fill the blank spots in Q2 with - for better visual 
		for (int i = 0; i < Q1.size(); i++)
		{
			Q2.enqueue('-');
		}
		
		System.out.println("Randomly generated number:" + randnumber);
		
		while(true) {
			Thread.sleep(400);
			// prints the screen
			System.out.print("Word:");
			Screen.QueuePrinter(Q2);
			System.out.print("         Step:   " + step + "         Score:  " + score + "        ");
			Screen.StackPrinter(S2);
			
			if (isFinished(Q2)) //checks if the game finished or not
				break;			
			step++;
			//spin the wheel
			wheelnumber = (int) (Math.random() * 8);
			System.out.println();
			System.out.println("Wheel:  " + Wheel.WheelScore(wheelnumber));
			//checks if the player is banktrupted or not
			if (wheelnumber == 7) {
				System.out.println();
				System.out.println("You bankrupted");
				System.out.println();
				score = 0;
				Thread.sleep(600);
			}
			else {
				//chooses a random character and search in Q2
				int foundchar = 0;
				choosenchar = (char)findInStack(S2,(int) (Math.random() * S2.size()));
				System.out.println("Guess:  " + choosenchar);
				Q2 = (Queue)findLetters(Q1,Q2,choosenchar,0);
				foundchar = (int)findLetters(Q1,Q2,choosenchar,1);
				if(foundchar > 0)
					score = Wheel.addScore(wheelnumber, score,foundchar); //if a character is found, adds score
			}
			System.out.println();
			
		}
		Thread.sleep(500);
		System.out.println();
		System.out.println();
		System.out.println("YOU WON  " + score + "TL  !!!");
		
		System.out.println();
		System.out.println("-----CONGRATULATIONS-----");
		System.out.println();
		System.out.println();
		
		//S3 and S4 is created for high score table
		Stack S3 = FileOperation.HighscoreTable("You", score, 0);
		Stack S4 = FileOperation.HighscoreTable("You", score, 1);
		Thread.sleep(600);
		
		//prints the high score table
		int snd = S3.size();
		for (int n = 0; n < snd; n++) 
			System.out.println(S3.pop() + "      " + S4.pop());
			
		
	}
	
	public static boolean isFinished(Queue Q) {      //search the Queue for "-", if its exists it means game is not finished yet
		int size = Q.size();
		boolean finished = true;
		for(int i = 0; i < size; i++) {
			if ('-' == (char) Q.peek()) 
				finished = false;
			Q.enqueue(Q.dequeue());
		}
		return finished;
	}
	
	public static Object findLetters(Queue Q1, Queue Q2,char guess,int operation) {    //find letter inside a Queue
		int count = 0;
		int size = Q1.size();
		for(int i = 0; i < size; i++) {
			if (guess == (char) Q1.peek()) {    //if a letter is found, it is replaced the '-' in Q2 
				Q2.dequeue();
				Q2.enqueue(Q1.peek());
				Q1.enqueue(Q1.dequeue());
				count++;
			}
			else {
				Q1.enqueue(Q1.dequeue());
				Q2.enqueue(Q2.dequeue());
			}
			
		}
		if (operation == 0)
			return Q2;               //if function is called with operation 1, it returns letters replaced Q2
		else
			return count;            //if function is called with operation 2, it returns the count of founded letters
	}
	
	
	
	public static Stack StackLetters() {        //stack letters in a Stack
		Stack S = new Stack(40);
		String alfa = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
		char[] a = alfa.toCharArray();
		for(int i = 0; i < a.length; i++) {
			S.push(a[i]);
		}
		return S;
	}
	
	public static Object findInStack(Stack S,int number) {   // founds item in the choosen numbernt place in a Stack
		Stack Sc = new Stack(S.size());
		Object choosen= "";
		int size = S.size();
		for(int i = 0;i < size; i++ ) {
			if (i == number) {
				choosen =  S.pop();
			}
			else {
			Sc.push(S.pop());}
		}
		for(int i = 0;i < size - 1; i++ ) {
			S.push(Sc.pop());
		}
		return choosen;
	}
	
	public static Queue StringtoQueue(String s) {   //converts String to Queue
		Queue Q = new Queue(s.length());
		for (int i = 0; i < s.length(); i++)
		{
			Q.enqueue(s.charAt(i));
		}
		return Q;
	}
}
