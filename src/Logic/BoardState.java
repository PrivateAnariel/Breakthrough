package Logic;

import java.util.ArrayList;

import Server.Translator;
import Objects.Move;

public class BoardState {
	
	public BoardState(String serverBoardConfig) {
		
	}
	
	public static byte[][] loadBoard(String serverBoardConfig){
		byte[][] board = new byte[8][8];
		for(int x = 7; x>-1; x--){
			for(int y = 0; y<8; y++){
				switch (serverBoardConfig.charAt((x*8)+y)) {
		            case '2':  
		            	board[x][y] = 2;
		            	break;
		            case '0':  
		            	board[x][y] = 0;
		            	break;
		            case '4':  
		            	board[x][y] = 4;
		            	break;
		            default: 
		            	System.out.println("ERROR LOADING BOARD");
		                break;
		        }
			}
		}
		return board;
	}
	
	public static ArrayList<Move> discoverWhiteMoves(byte[][] board){
		ArrayList<Move> moves = new ArrayList<Move>();
		for(int x = 1; x<8; x++){
			for(int y = 0; y<8; y++){
				if(board[x][y] == 4){
					if(board[x-1][y]==0)
						moves.add(new Move(x,y,x-1,y));
					if(y>0 && board[x-1][y-1]!=4)
						moves.add(new Move(x,y,x-1,y-1));
					if(y<7 && board[x-1][y+1]!=4)
						moves.add(new Move(x,y,x-1,y+1));
				}
	        }
		}
		return moves;
	}
	
	public static ArrayList<Move> discoverBlackMoves(byte[][] board){
		ArrayList<Move> moves = new ArrayList<Move>();
		for(int x = 0; x<7; x++){
			for(int y = 0; y<8; y++){
				if(board[x][y] == 2){
					if(board[x+1][y]==0)
						moves.add(new Move(x,y,x+1,y));
					if(y>0 && board[x+1][y-1]!=2)
						moves.add(new Move(x,y,x+1,y-1));
					if(y<7 && board[x+1][y+1]!=2)
						moves.add(new Move(x,y,x+1,y+1));
				}
	        }
		}
		return moves;
	}
	
	public static long evaluate(byte[][] board){
		long score=0;
		
		for(int x = 0; x<8; x++){
			for(int y = 0; y<8; y++){
				if(board[x][y] == 2)
					if(x==7)
						return -99999999;
					else
						score-= x+1;
				else if (board[x][y] == 4)
					if(x==0)
						return 99999999;
					else
						score+= 8-x;
	        }
		}
		return score; 
	}

	public static byte[][] makeMove(byte[][] board, Move move){
		byte[][] nextBoard = board;
		int row = move.coordinates.charAt(1)-48;
		row = 8-row;
		int column = Translator.getColumnNumberValue(move.coordinates.charAt(0));
		byte pawnColor = nextBoard[row][column];
		nextBoard[row][column] = 0;
		row = move.coordinates.charAt(3)-48;
		row = 8-row;
		column = Translator.getColumnNumberValue(move.coordinates.charAt(2));
		nextBoard[row][column] = pawnColor;
		return nextBoard;
	}
}

