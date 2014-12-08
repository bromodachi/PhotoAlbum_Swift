package outPut;

import piece.piece;
/*@author  Conrado Uraga*/
public class MoveValid {
	Square source;
	Square target;
	piece pSource;
	piece pTarget;
	board game;
	String type;
	
	MoveValid(board thisGame){
		this.game=thisGame;
	}
	
	public boolean isValidMove(Move move, String color){
//		System.out.println(move.sColumn+" "+ move.sRow);
		source=this.game.getSquare(move.sColumn,move.sRow);
		target=this.game.getSquare(move.cTarget,move.rTarget);
	//	System.out.println(move.cTarget+" "+ move.rTarget);
		/*if either the input are invalid, return null*/
		this.pTarget=target.getPiece();
		this.pSource=source.getPiece();
	//	System.out.println(pTarget);
	//	System.out.println(pSource);
		/*if no source piece, return false*/
		if(pSource==null){
	//		System.out.println("I came here again");
			return false;
		}
		/*if wrong color, return false immediately*/
		if(!(pSource.getColor().equals(color))){
			return false;
		}
	//	System.out.println(pSource.getType()+pSource.getColor());
			switch(pSource.getType()){
				case "pawn":
				//	System.out.println("I came here");
					/*int sourceColumn, int sourceRow, int targetColumn,
			int targetRow)*/
					/*isValidPawn(int sourceColumn, int sourceRow, int targetColumn, int targetRow)*/
					return isValidPawn(move.sColumn, move.sRow, move.cTarget, move.rTarget);
				case "knight":
					return isValidKnight(move.sColumn, move.sRow, move.cTarget, move.rTarget);
				case "king":
					return isValidKing(move.sColumn, move.sRow, move.cTarget, move.rTarget);
				case "bishop":
					return isValidBishop(move.sColumn, move.sRow, move.cTarget, move.rTarget);
				case "rook":
					return isValidRook(move.sColumn, move.sRow, move.cTarget, move.rTarget);
				case "queen":
					return isValidQueen(move.sColumn, move.sRow, move.cTarget, move.rTarget);
				default:
						return false;
			}
	}
	
	private boolean isValidQueen(int sColumn, int sRow, int cTarget, int rTarget) {
		boolean answer;
		/*queen is just a combination of the bishop and rook so just call their methods
		 * and return valid or not*/
		answer=isValidBishop(sColumn, sRow, cTarget, rTarget);
		answer|=isValidRook(sColumn, sRow, cTarget, rTarget);
		return answer;
	}

	private boolean isValidRook(int sColumn, int sRow, int cTarget, int rTarget) {
		// TODO Auto-generated method stub
		boolean answer=false;
		int yDelta=rTarget-sRow;
		int xDelta=cTarget-sColumn;
		if(locationFree()||targetCapture()){
			/*columns*/
			/*downwards*/
			if(xDelta==0 && yDelta>0){
				answer=!anyObstructions(sColumn, sRow, cTarget, rTarget, +1, 0);
			}
			/*upwards*/
			else if(xDelta==0 && yDelta<0){
				answer=!anyObstructions(sColumn, sRow, cTarget, rTarget, -1, 0);
			}
			/*rows*/
			/*right*/
			else if(xDelta>0 && yDelta==0){
				answer=!anyObstructions(sColumn, sRow, cTarget, rTarget, 0,+1);
			}
			/*left*/
			else if(xDelta<0 && yDelta==0){
				answer=!anyObstructions(sColumn, sRow, cTarget, rTarget, 0, -1);
			}
			else{
				answer=false;
			}
		}
		return answer;
	}

	private boolean isValidBishop(int sColumn, int sRow, int cTarget,
			int rTarget) {
		boolean answer=false;
		int yDelta=rTarget-sRow;//y should be the rows
		int xDelta=cTarget-sColumn;//x should be the columns to correspond to my matrix
		if(locationFree()||targetCapture()){
			if(Math.abs(xDelta)==Math.abs(yDelta)){
				/*moving upwards*/
				if(yDelta<0){
					/*int sourceColumn, int sourceRow, int targetColumn, int targetRow, int rowStep, int columnStep*/
					if(xDelta>0){
						/*we're going upLeft*/
						/* int rowStep, int columnStep*/
						answer=!anyObstructions(sColumn, sRow, cTarget, rTarget, -1, +1);}
					else{
						/*we're going upRight*/
						answer=!anyObstructions(sColumn, sRow, cTarget, rTarget, -1, -1);
					}
					
				}
				/*moving downwards*/
				else{
					if(xDelta<0){
						/*we're going downLeft*/
						answer=!anyObstructions(sColumn, sRow, cTarget, rTarget, +1, -1);}
					else{
						/*we're going downRight*/
						answer=!anyObstructions(sColumn, sRow, cTarget, rTarget, +1, +1);
					}
				}
				
				
			}
		}
		return answer;
	}

	private boolean isValidKing(int sColumn, int sRow, int cTarget, int rTarget) {
		boolean answer;
		boolean ableCastling;
		if(pSource.getFirst()==true && pSource.getCheck()==false){
		//	System.out.println(cTarget+" "+rTarget);
			/*e1 g1*/
			if(cTarget==6 && rTarget==7){
				if(this.game.getSquare(cTarget+1,rTarget).getPiece()!=null){
					/*is it a rook?*/
					if((this.game.getSquare(cTarget+1,rTarget).getPiece().getType().equals("rook")) &&(this.game.getSquare(cTarget+1,rTarget).getPiece().getFirst()==true)
							&&(this.game.getSquare(cTarget+1,rTarget).getPiece().getColor().equals(pSource.getColor()))){
						ableCastling=!anyObstructions(sColumn, sRow, cTarget, rTarget, 0, +1);
						if(ableCastling){
							pSource.setkingCastle(true);
							answer=true;
			//				System.out.println("yes");
							return answer;
						}
						else{
							answer=false;
							return answer;
						}
					}
				}
			}
			else if(cTarget==2 && rTarget==7){
				if(this.game.getSquare(cTarget-2,rTarget).getPiece()!=null){
					/*is it a rook?*/
					if((this.game.getSquare(cTarget-2,rTarget).getPiece().getType().equals("rook")) &&(this.game.getSquare(cTarget-2,rTarget).getPiece().getFirst()==true)
							&&(this.game.getSquare(cTarget-2,rTarget).getPiece().getColor().equals(pSource.getColor()))){
						ableCastling=!anyObstructions(sColumn, sRow, cTarget-1, rTarget, 0, -1);
						if(ableCastling){
							pSource.setkingCastle(true);
							answer=true;
		//					System.out.println("yes");
							return answer;
						}
						else{
							answer=false;
							return answer;
						}
					}
				}
				
			}
			/*black*/
			else if(cTarget==2 && rTarget==0){
				if(this.game.getSquare(cTarget-2,rTarget).getPiece()!=null){
					/*is it a rook?*/
					if((this.game.getSquare(cTarget-2,rTarget).getPiece().getType().equals("rook")) &&(this.game.getSquare(cTarget-2,rTarget).getPiece().getFirst()==true)
							&&(this.game.getSquare(cTarget-2,rTarget).getPiece().getColor().equals(pSource.getColor()))){
						ableCastling=!anyObstructions(sColumn, sRow, cTarget-1, rTarget, 0, -1);
						if(ableCastling){
							pSource.setkingCastle(true);
							answer=true;
		//					System.out.println("yes");
							return answer;
						}
						else{
							answer=false;
							return answer;
						}
					}
				}
				
			}
			else if(cTarget==6 && rTarget==0){
				if(this.game.getSquare(cTarget+1,rTarget).getPiece()!=null){
					/*is it a rook?*/
					if((this.game.getSquare(cTarget+1,rTarget).getPiece().getType().equals("rook")) &&(this.game.getSquare(cTarget+1,rTarget).getPiece().getFirst()==true)
							&&(this.game.getSquare(cTarget+1,rTarget).getPiece().getColor().equals(pSource.getColor()))){
						ableCastling=!anyObstructions(sColumn, sRow, cTarget, rTarget, 0, +1);
						if(ableCastling){
							pSource.setkingCastle(true);
							answer=true;
		//					System.out.println("yes");
							return answer;
						}
						else{
							answer=false;
							return answer;
						}
					}
				}
			}
			/*be sure target has a piece of +1*/
		//	System.out.println("checksdfsdfing"+" target "+pTarget.getType());
		/*	if(!(cTarget-1<0)){
			if(this.game.getSquare(cTarget+1,rTarget).getPiece()!=null ||this.game.getSquare(cTarget-1,rTarget).getPiece()!=null){
			//	System.out.println("testing things out"+(pTarget.getFirst()==true));
				/*is a rook and it hasn't been touched?*/
		/*		if((this.game.getSquare(cTarget+1,rTarget).getPiece().getType().equals("rook")) &&(this.game.getSquare(cTarget+1,rTarget).getPiece().getFirst()==true)
						&&(this.game.getSquare(cTarget+1,rTarget).getPiece().getColor().equals(pSource.getColor()))){
					
						int yDelta=rTarget-sRow;
						int xDelta=cTarget-sColumn;
						System.out.println("wtf checking");
						if(xDelta<0 && yDelta==0){
							//check cTarget
				//			System.out.println("checking");
							ableCastling=!anyObstructions(sColumn, sRow, cTarget, rTarget, 0, -1);
							if(ableCastling){
								pSource.setkingCastle(true);
								answer=true;
								System.out.println("yes");
								return answer;
							}
							else{
								answer=false;
								return answer;
							}
						}
						else if(xDelta>0 && yDelta==0){
							System.out.println("did I come here? "+ cTarget+ rTarget);
							ableCastling=!anyObstructions(sColumn, sRow, cTarget, rTarget, 0, +1);
							if(ableCastling){
								pSource.setkingCastle(true);
								answer=true;
								return answer;
							}
							else{
								answer=false;
								return answer;
							}
						}
						
					
				}
			}
		}*/
		}
		if(locationFree()||targetCapture()){
			pSource.setFree(true);
			answer=pSource.isValid(sColumn, sRow, cTarget, rTarget);
		/*	if(answer==true){
				pSource.setFirst(false);
			}*/
			/*if we get a false and it's still the first move of the king,
			 * is it a Castling?*/
			return answer;
		}
		else{
			return false;
		}
	}

	public boolean isValidPawn(int sourceColumn, int sourceRow, int targetColumn, int targetRow){
		boolean answer;
		if(locationFree()){
		//	System.out.println("came here"+sourceColumn+"soruce row"+sourceRow+"targetC"+targetColumn);
			pSource.setFree(true);
			pSource.setCapture(false);
			/*int sourceColumn, int sourceRow, int targetColumn,
			int targetRow)*/
			answer=pSource.isValid(sourceColumn, sourceRow, targetColumn, targetRow);
			/*if(answer==true){
				pSource.setFirst(false);
			}*/
			return answer;
		}
		else if(targetCapture()){
		//	System.out.println("test");
			pSource.setFree(false);
			pSource.setCapture(true);
			answer=pSource.isValid(sourceColumn, sourceRow, targetColumn, targetRow);
			/*if(answer==true){
				pSource.setFirst(false);
			}*/
			return answer;
		}
		else{
			return false;
		}
		
	}
	public boolean isValidKnight(int sourceColumn, int sourceRow, int targetColumn, int targetRow){
		boolean answer;
		if(locationFree()||targetCapture()){
			pSource.setFree(true);
			answer=pSource.isValid(sourceColumn, sourceRow, targetColumn, targetRow);
			return answer;
		}
		else{
			return false;
		}
		
	}
	
	public boolean anyObstructions(int sourceColumn, int sourceRow, int targetColumn, int targetRow, int rowStep, int columnStep){
		int currRow=sourceRow+rowStep;
		int currCol=sourceColumn+columnStep;
		while(true){
			if(currRow==targetRow && currCol==targetColumn){
				/*no obstructons*/
				break;
			}
			if(currRow<0|| currRow>7||currCol<0||currCol>7){
				/*we reached the outerboundaries, we're good, let's break*/
				break;
			}
			if(this.game.getSquare(currCol,currRow).getPiece()!=null){
				/*found a piece interfering, return true*/
				//System.out.println("fucking up: "+currCol+" "+currRow);
				return true;
			}
			currRow=currRow+rowStep;
			currCol=currCol+columnStep;
		}
		return false;
	}
	
	public boolean locationFree(){
		/*location square has no pieces, let's move*/
		if(pTarget==null){
			return true;
		}
		/*location square has pieces, let's not move*/
		else{
			return false;
		}
	}
	
	public boolean targetCapture(){
		/*no targets in square*/
		if(pTarget==null){
			return false;
		}
		/*different color, let's capture*/
		else if(!(pSource.getColor().equals(pTarget.getColor()))){
			return true;
		}
		else{
			/*same color, invalid move*/
			return false;
		}
	}
	
	
	
	
}
