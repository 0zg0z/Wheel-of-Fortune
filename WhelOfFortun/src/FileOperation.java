
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.Scanner;

	public class FileOperation {
		
		
		public static Stack HighscoreTable(String player, int pscore,int opr) {   //reads highscore table, shorts, and puts in a stack 
			  try {
				  int linecount = 0;
			      File myObj = new File("./src/HighScoreTable.txt");
			      //counts line to find out how much line there is
			      Scanner lineReader = new Scanner(myObj);
			      while (lineReader.hasNextLine()) {
			    	  lineReader.nextLine();
			    	  linecount++;
			      }
			      lineReader.close();
			      //2 stack is created for names and scores
			      Scanner Reader = new Scanner(myObj);
			      Stack S1 = new Stack(linecount + 5);
			      Stack S2 = new Stack(linecount + 5);
			      //players name and score is pushed first
			      S1.push(player);
			      S2.push(pscore);
			      while (Reader.hasNextLine()) {
			    	  
			    	//line is read and splits as names and score
			        String contestant = Reader.nextLine();
			        String[] cont = contestant.split("#");
			        String person = cont[0];
			        int score;
			        try {
			        	   score = Integer.parseInt(cont[1]);
			        	}
			        	catch (NumberFormatException e) {
			        	   score = 400;
			        	}
			        //temporary Stacks are created
			        Stack tempS = new Stack(linecount + 5);
			        Stack tempS2 = new Stack(linecount + 5);
			        boolean entered = false;
			 
			        	int len = S1.size();
			        	for (int i = 0; i < len;i++) { //iterates throught Stacks to find lower score to push the person and score before it
			        		if(score > (int)S2.peek()  && entered == false) {
			        		tempS.push(person);
			        		tempS2.push(score);
			        		entered = true;
			        		}
			        		tempS.push(S1.pop());
			        		tempS2.push(S2.pop());
			        	}
			        	if (entered == false) { //if there is no lower score existed, pushes to lower end
			        		tempS.push(person);
			        		tempS2.push(score);
			        	}
			        	len = tempS.size();
			        	for(int i = 0; i < len; i++) {  //return to original Stacks 
			        		S1.push(tempS.pop());
			        		S2.push(tempS2.pop());
			        	}
			        
			      }
			      Reader.close();
			      if (opr==0)
			    	  return S1;  //if opr is 0 it returns names
			      else 
			    	  return S2;  //if it is not returns score 
		
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			      Stack S = new Stack(10);
			      return S;
			    }
		}
		public static Object CountryRead(int opr) {   //reads Countries, shorts, and puts in a stack 
			
			  try {
				  int linecount = 0;
			      File myObj = new File("./src/countries.txt");
			      //count lines
			      Scanner Reader = new Scanner(myObj);
			      while (Reader.hasNextLine()) {
			    	  Reader.nextLine();
			    	  linecount++;
			      }
			      Reader.close();
			      
			      Scanner Reader2 = new Scanner(myObj);
			      Stack S1 = new Stack(linecount);
			      Stack tempStack = new Stack(linecount);
			      
			      while (Reader2.hasNextLine()) {
			        String country = Reader2.nextLine();
			        boolean entered = false;
			        if (S1.isEmpty()) {   //if stack is empty puts the first country
			        	S1.push(country);
			        }
			        else{
			        	int len = S1.size();
			        	for (int i = 0; i < len;i++) {
			        		if(1 == (Comperisant(country,(String)S1.peek()))  && entered == false) {
			        		tempStack.push(country);
			        		entered = true;
			        		}
			        		tempStack.push(S1.pop());
			        	}
			        	if (entered == false) {
			        		tempStack.push(country);
			        	}
			        	len = tempStack.size();
			        	for(int i = 0; i < len; i++) {
			        		S1.push(tempStack.pop());
			        	}
			        }
			      }
			      Reader2.close();
			      if (opr == 0)
			    	  return S1;
			      else 
			    	  return linecount;
		
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			      Stack S = new Stack(10);
			      return S;
			    }
		}
		
		
		public static int Comperisant(String str1, String str2) {  //compare Strings
			char[] strarr1 = str1.toCharArray();
			char[] strarr2 = str2.toCharArray();
			int lngth;
			if (strarr1.length < strarr2.length) lngth = strarr1.length; 
			else if (strarr1.length > strarr2.length) lngth = strarr2.length; 
			else lngth = strarr1.length;
			
			for (int i = 0; i < lngth; i++) {
				if (strarr1[i] > strarr2[i]) {
					return 0;
				}
				else if (strarr1[i] < strarr2[i]) {
					return 1;
				}
			}
			return 2;
		}
	}


