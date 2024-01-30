
public class Wheel {

	public static String WheelScore(int rand) {
		switch(rand) {
		case 0: 
			return "10";
		case 1:
			return "50";
		case 2:
			return "100";
		case 3:
			return "250";
		case 4: 
			return "500";
		case 5:
			return "1000";
		case 6:
			return "Double Money";
		case 7:
			return "Banktrup";
		default:
			return "a problem occurred";
		}
		
		
		
	}
	public static int addScore(int rand, int money,int foundchar) {
		switch(rand) {
		case 0: 
			return money + (foundchar*10);
		case 1:
			return money + (foundchar*50);
		case 2:
			return money + (foundchar*100);
		case 3:
			return money + (foundchar*250);
		case 4: 
			return money + (foundchar*500);
		case 5:
			return money + (foundchar*1000);
		case 6:
			return money * 2;
		case 7:
			return 0;
		default:
			return 100000;
		}
	}
}
