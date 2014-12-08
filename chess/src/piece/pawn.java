package piece;

import outPut.Square;
/*@author  Conrado Uraga*/
public class pawn extends piece {

	boolean first=true;
	public pawn(String iro, String type, int x, int y) {
		super(iro, type, x, y);
		// TODO Auto-generated constructor stub
		setOutput("wp ");
	}

	@Override
	public boolean isValid(int sourceColumn, int sourceRow, int targetColumn,
			int targetRow) {
	//	System.out.println(getFree());
	//	System.out.println(getPassant());
		boolean check;
		if(getPassant()){
			if(sourceColumn+1==targetColumn||sourceColumn-1==targetColumn){
				if(color.equals("white")){
					if(sourceRow-1==targetRow)
					{
						return true;
					}				
			/*		else{
				
					}*/
				}
				else{
					if(sourceRow+1==targetRow)
					{
						return true;
					}				
				/*	else{
			//			check= false;
					}*/
				}
		}
		else{
			if(getFree()){
		//		System.out.println("fucking you up");
				if(sourceColumn==targetColumn){
				//	System.out.println("fucking you up");
					if(color.equals("white")){
				//		System.out.println("fucking you up");
						if(sourceRow-1==targetRow)
						{
							first=false;
							setPassant(false);
							return true;
						}	
						else if(sourceRow-2==targetRow && first==true)
						{
							first=false;
							setPassant(false);
							return true;
						}				
						else{
							return false;
						}
					}
					else{
						if(sourceRow+1==targetRow)
						{
							first=false;
							setPassant(false);
							return true;
						}	
						else if(sourceRow+2==targetRow && first==true)
						{
							first=false;
							setPassant(false);
							return true;
						}	
						else{
							return false;
						}
					}
				}
				else{
					return false;
				}
			}
			}
		}
		else if(getFree()){
		//	System.out.println("fucking you up");
			if(sourceColumn==targetColumn){
			//	System.out.println("fucking you up");
				if(color.equals("white")){
			//		System.out.println("fucking you up");
					if(sourceRow-1==targetRow)
					{
						first=false;
						return true;
					}	
					else if(sourceRow-2==targetRow && first==true)
					{
						first=false;
						return true;
					}				
					else{
						return false;
					}
				}
				else{
					if(sourceRow+1==targetRow)
					{
						first=false;
						return true;
					}	
					else if(sourceRow+2==targetRow && first==true)
					{
						first=false;
						return true;
					}	
					else{
						return false;
					}
				}
			}
			else{
				return false;
			}
		}
		else if(getCapture()){
		//	System.out.println("asdasd yes");
			if(sourceColumn+1==targetColumn||sourceColumn-1==targetColumn){
				if(color.equals("white")){
					if(sourceRow-1==targetRow)
					{
						return true;
					}				
					else{
						return false;
					}
				}
				else{
					if(sourceRow+1==targetRow)
					{
						return true;
					}				
					else{
						return false;
					}
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		return false;
	}


	
	
	

}
