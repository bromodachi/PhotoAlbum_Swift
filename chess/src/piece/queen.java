package piece;
/*@author  Conrado Uraga*/
public class queen extends piece{
	public queen(String iro, String type, int x, int y) {
		super(iro, type, x, y);
		// TODO Auto-generated constructor stub
		setOutput("wp ");
	}


	@Override
	public boolean isValid(int sourceColumn, int sourceRow, int targetColumn,
			int targetRow) {
		// TODO Auto-generated method stub
		return false;
	}


}
