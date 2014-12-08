package piece;
/*@author  Conrado Uraga*/
public class knight extends piece{
	public knight(String iro, String type, int x, int y) {
		super(iro, type, x, y);
		// TODO Auto-generated constructor stub
		setOutput("wN ");
	}


	@Override
	public boolean isValid(int sourceColumn, int sourceRow, int targetColumn,
			int targetRow) {
		if(sourceColumn+1==targetColumn&& sourceRow+2==targetRow){
			return true;
		}
		else if(sourceColumn+2==targetColumn&& sourceRow+1==targetRow){
			return true;
		}
		else if(sourceColumn+2==targetColumn&& sourceRow-1==targetRow){
			return true;
		}
		else if(sourceColumn+1==targetColumn&& sourceRow-2==targetRow){
			return true;
		}
		else if(sourceColumn-2==targetColumn&& sourceRow-1==targetRow){
			return true;
		}
		else if(sourceColumn-2==targetColumn&& sourceRow+1==targetRow){
			return true;
		}
		else if(sourceColumn-1==targetColumn&& sourceRow+2==targetRow){
			return true;
		}
		else if(sourceColumn-1==targetColumn&& sourceRow-2==targetRow){
			return true;
		}
		else{
			return false;
		}
	}

}
