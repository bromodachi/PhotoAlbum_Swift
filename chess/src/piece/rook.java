package piece;
/*@author  Conrado Uraga*/
public class rook extends piece{
	private boolean first=false;
	public rook(String iro, String type, int x, int y) {
		super(iro, type, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(int sourceColumn, int sourceRow, int targetColumn,
			int targetRow) {
		// TODO Auto-generated method stub
		return false;
	}
	public void setFirst(boolean noMas){
		first=noMas;
	}
	public boolean getFirst(){
		return first;
	}


}
