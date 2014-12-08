package piece;
/*@author  Conrado Uraga*/
public class king extends piece
{
//	boolean first =true;
	public king(String iro, String type, int x, int y) {
		super(iro, type, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(int sourceColumn, int sourceRow, int targetColumn,
			int targetRow) {
		if(sourceColumn==targetColumn&& sourceRow+1==targetRow){
	//		setFirst(false);
			return true;
		}
		else if(sourceColumn+1==targetColumn&& sourceRow+1==targetRow){
	//		setFirst(false);
			return true;
		}
		else if(sourceColumn+1==targetColumn&& sourceRow==targetRow){
	//		setFirst(false);
			return true;
		}
		else if(sourceColumn+1==targetColumn&& sourceRow-1==targetRow){
	//		setFirst(false);
			return true;
		}
		else if(sourceColumn==targetColumn&& sourceRow-1==targetRow){
		//	setFirst(false);
			return true;
		}
		else if(sourceColumn-1==targetColumn&& sourceRow-1==targetRow){
		//	setFirst(false);
			return true;
		}
		else if(sourceColumn-1==targetColumn&& sourceRow==targetRow){
		//	setFirst(false);
			return true;
		}
		else if(sourceColumn-1==targetColumn&& sourceRow+1==targetRow){
		//	setFirst(false);
			return true;
		}
		else{
			return false;
		}
	}



}
