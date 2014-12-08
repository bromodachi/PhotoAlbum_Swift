package piece;

import outPut.board;
/*@author  Conrado Uraga*/
public class bishop extends piece{
	public bishop(String iro, String type, int x, int y) {
		super(iro, type, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	

	public boolean isValid(int sourceColumn, int sourceRow, int targetColumn,
			int targetRow) {
		if(sourceColumn==targetColumn&& sourceRow+1==targetRow){
			return true;
		}
		else if(sourceColumn+1==targetColumn&& sourceRow+1==targetRow){
			return true;
		}
		else if(sourceColumn+1==targetColumn&& sourceRow==targetRow){
			return true;
		}
		else if(sourceColumn+1==targetColumn&& sourceRow-1==targetRow){
			return true;
		}
		else if(sourceColumn==targetColumn&& sourceRow-1==targetRow){
			return true;
		}
		else if(sourceColumn-1==targetColumn&& sourceRow-1==targetRow){
			return true;
		}
		else if(sourceColumn-1==targetColumn&& sourceRow==targetRow){
			return true;
		}
		else if(sourceColumn-1==targetColumn&& sourceRow+1==targetRow){
			return true;
		}
		else{
			return false;
		}
	}
	/*public boolean anyObstructions(int sourceColumn, int sourceRow, int targetColumn, int targetRow, int rowStep, int columnStep, board game){
		int currRow=sourceRow+rowStep;
		int currCol=sourceColumn+columnStep;
		while(true){
			if(currRow==targetRow && currCol==targetColumn){
				/*no obstructons
				break;
			}
			if(currRow<0|| currRow>8||currCol<0||currCol>8){
				break;
			}
			if(this.game.getSquare(currRow, currCol).getPiece()!=null){
				return true;
			}
			currRow=currRow+rowStep;
			currCol=currCol+columnStep;
		}
		return false;
	}*/
}
