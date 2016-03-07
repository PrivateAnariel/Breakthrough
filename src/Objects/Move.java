package Objects;

import Server.Translator;

public class Move {
	public String coordinates;
	public long score;

	public Move(String moveData) {
		coordinates = moveData;
	}
	
	public Move(long score) {
		this.score = score;
	}
	
	public Move() {
	}

	public Move(int xOrigin, int yOrigin, int xDest, int yDest) {
		coordinates = Translator.generateMoveCoordinates(xOrigin,yOrigin,xDest,yDest);
	}
}
