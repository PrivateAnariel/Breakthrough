package Server;
public class Translator {
	
	
	
	public static int getColumnNumberValue(char column){
		switch (column) {
		    case 'A':  
		    	return 0;
		    case 'B':  
		    	return 1;
			case 'C':  
		    	return 2;
			case 'D':  
		    	return 3;
			case 'E':  
		    	return 4;
			case 'F':  
		    	return 5;
			case 'G':  
		    	return 6;
			case 'H':  
		    	return 7;
			default: 
		    	System.out.println("ERROR: INVALID COLUMN Letter");
		        break;
		}
		return -1;
	}
	
	public static char getColumnLetterValue(int column){
		switch (column) {
		    case 0:  
		    	return 'A';
		    case 1:  
		    	return 'B';
			case 2:  
		    	return 'C';
			case 3:  
		    	return 'D';
			case 4:  
		    	return 'E';
			case 5:  
		    	return 'F';
			case 6:  
		    	return 'G';
			case 7:  
		    	return 'H';
			default: 
		    	System.out.println("ERROR: INVALID COLUMN number");
		        break;
		}
		return 'Z';
	}
	
	public static void print(byte[][] board){
		for(int x = 0; x<8; x++){
			System.out.print(8-x + "- ");
			for(int y = 0; y<8; y++){
					System.out.print(board[x][y] + " ");
	        }
			System.out.println();
		}
		System.out.println("   ^ ^ ^ ^ ^ ^ ^ ^");
		System.out.println("   A B C D E F G H");
	}

	public static String generateMoveCoordinates(int xOrigin, int yOrigin, int xDest, int yDest) {
		String coordinates = "";
		coordinates += getColumnLetterValue((byte)yOrigin);
		coordinates += 8-xOrigin;
		coordinates += getColumnLetterValue((byte)yDest);
		coordinates += 8-xDest;
		
		return coordinates;
	}
}
