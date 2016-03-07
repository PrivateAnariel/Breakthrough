import java.util.ArrayList;

import Logic.BoardState;
import Objects.Move;
import Server.Translator;
public class appMain {

	private static byte[][] currentBoard;
	public static void main(String[] args) {
		currentBoard = BoardState.loadBoard("2222222222222222000000000000000000000000000000004444444444444444");
		Translator.print(currentBoard);
		int x = 0;
		Move terribleWhite = new Move(-99999999);
		Move terribleBlack = new Move(99999999);
		while(x<20){
			Move move = playAsWhite(currentBoard, terribleWhite, terribleBlack, 5);
			currentBoard = BoardState.makeMove(currentBoard, move);
			Translator.print(currentBoard);

			move = playAsBlack(currentBoard, terribleWhite, terribleBlack, 5);
			currentBoard = BoardState.makeMove(currentBoard, move);
			
			Translator.print(currentBoard);
		}
		
	}
	
	public static Move playAsWhite(byte[][] board, Move whiteBest, Move blackBest, int exploreLevels ) {
		Translator.print(board);
		ArrayList<Move> moves = BoardState.discoverWhiteMoves(board);
		if ( exploreLevels == 0 || moves.isEmpty()) {
			whiteBest.score = BoardState.evaluate(board);
			return whiteBest;
		}
		for(Move move: moves){
			System.out.println(move.coordinates);
			move.score = playAsBlack(BoardState.makeMove(board, move), whiteBest, blackBest, exploreLevels - 1 ).score;
			if( move.score >= blackBest.score )
				return blackBest;
			if( move.score > whiteBest.score )
				whiteBest = move;
		}
		return whiteBest;
	}
	 
	public static Move playAsBlack(byte[][] board, Move whiteBest, Move blackBest, int exploreLevels ) {
		Translator.print(board);
		
		ArrayList<Move> moves = BoardState.discoverBlackMoves(board);
		if ( exploreLevels == 0 || moves.isEmpty()){
			blackBest.score = BoardState.evaluate(board);
			return blackBest;
		}
		for(Move move: moves){
			System.out.println(move.coordinates + " " + move.score);
			move.score = playAsWhite(BoardState.makeMove(board, move), whiteBest, blackBest, exploreLevels - 1 ).score;
			if( move.score <= whiteBest.score )
				return whiteBest;
			if( move.score < blackBest.score )
				blackBest = move; 
		}
		return blackBest;
	}
}