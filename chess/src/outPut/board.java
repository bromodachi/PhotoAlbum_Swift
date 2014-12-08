package outPut;
import java.util.ArrayList;

import piece.bishop;
import piece.king;
import piece.knight;
import piece.pawn;
import piece.piece;
import piece.queen;
import piece.rook;

/*@author  Conrado Uraga*/
public class board {
	Square [][] board;
	piece lastMoved;
	piece enPassantPiece;
	Player white;
	boolean wasSet;
	Player black;
	MoveValid valid; //for special moves only
	//ArrayList<piece> pieceSet;
	public board(){
		board=new Square [9][9];
		int des=8;
		for(int i=0; i<board.length;i++){
			for(int j=0; j<board.length;j++){
				if(i==8){
					break;
				}
				if(j%8==0 && j!=0){
					board[i][j]=new Square(Integer.toString(des), 8, i, null);
					des--;
				}
				else{
					if(i%2==0){
						if(j%2!=0){
						board[i][j]=new Square("## ", j, i, null);
						}
						else{
							board[i][j]=new Square("   ", j, i,null);
						}
					}
					else{
						if(j%2==0){
							board[i][j]=new Square("## ", j, i,null);
							}
						else{
								board[i][j]=new Square("   ", j, i,null);
							}
					}
				}
			}
		}
		board[8][0]=new Square(" a ", 8, 0, null);
		board[8][1]=new Square(" b ", 8, 1, null);
		board[8][2]=new Square(" c ", 8, 2, null);
		board[8][3]=new Square(" d ", 8, 3, null);
		board[8][4]=new Square(" e ", 8, 4, null);
		board[8][5]=new Square(" f ", 8, 5, null);
		board[8][6]=new Square(" g ", 8, 6, null);
		board[8][7]=new Square(" h ", 8, 7, null);
		board[8][8]=new Square("   ", 8, 8, null);
		/*creating white pieces*/
//		printBoard();
		lastMoved=null;
	}
	public Square getSquare(int x, int y){
		if(x<0 ||y>8){
			return null;
		}
		return board[y][x];
	}
	
	public void createPieces(){
		/*white pieces*/
		createPieceRook("white", "rook", 0, 7);
		createPieceRook("white", "rook", 7, 7);
	//	createPieceRook("white", "rook", 2, 4);//<-testing
		createPieceBishop("white", "bishop", 2, 7);
		createPieceBishop("white", "bishop", 5, 7);
		createPieceKnight("white", "knight", 1, 7);
		createPieceKnight("white", "knight", 6, 7);
		createPieceQueen("white", "queen", 3, 7); //<-original
	//	createPieceQueen("white", "queen", 1, 7); //<-testing
	//	createPieceRook("white", "rook", 6, 5); //<-testing
		createPieceKing("white", "king", 4, 7);//<-original
	//	createPieceKing("white", "king", 7, 7);//<-testing
		/*en passant test with white*/
		/*this.board[1][2]=new Square(new pawn("white", "pawn", 2,1),2,1);
		this.board[1][2].getPiece().setOutput("wp ");
		this.black.getArray().add(this.board[1][2].getPiece());*/
		//was 8
		for(int j=0; j<8;j++){
			this.board[6][j]=new Square(new pawn("white", "pawn", j,6),j,6);
			this.board[6][j].getPiece().setOutput("wp ");
			this.white.pieceSet.add(board[6][j].getPiece());
		}
		/*black pieces*/
		createPieceRook("black", "rook", 0, 0);
		createPieceRook("black", "rook", 7, 0);
	//	createPieceRook("black", "rook", 3, 4);
		createPieceBishop("black", "bishop", 2, 0);
		createPieceBishop("black", "bishop", 5, 0);
		createPieceKnight("black", "knight", 1, 0);
		createPieceKnight("black", "knight", 6, 0);
		createPieceQueen("black", "queen", 3, 0);
	//	createPieceQueen("black", "queen", 5, 3); //<-testing
	//	createPieceRook("black", "rook", 2, 5);
		createPieceKing("black", "king", 4, 0);
	//	createPieceKing("black", "king", 0, 0); //<-testing
	//	createPieceKnight("white", "knight", 1, 0);
	//	createPieceKnight("white", "knight", 1, 1);
	//	createPieceBishop("black", "bishop", 6, 6);
	//	createPieceBishop("black", "bishop", 7, 6);
		/*testing en passant*/
		/*this.board[4][1]=new Square(new pawn("black", "pawn", 1,4),1,4);
		this.board[4][1].getPiece().setOutput("bp ");
		this.black.getArray().add(this.board[4][1].getPiece());
		/*ATTENTION: put j back to 0*/
		for(int j=0; j<8;j++){
			this.board[1][j]=new Square(new pawn("black", "pawn", j,1),j,1);
			this.board[1][j].getPiece().setOutput("bp ");
			this.black.getArray().add(this.board[1][j].getPiece());
		}
	}
	public void createPieceKnight(String color, String type, int x, int y){
		this.board[y][x]=new Square(new knight(color, type, y,x),y,x);
		this.board[y][x].getPiece().setFirst(true);
		if(color.equals("white")){
			this.board[y][x].getPiece().setOutput("wN ");
			this.white.getArray().add(this.board[y][x].getPiece());
		}
		else{
			this.board[y][x].getPiece().setOutput("bN ");
			this.black.getArray().add(this.board[y][x].getPiece());
		}
	}
	public void createPieceRook(String color, String type, int x, int y){
		this.board[y][x]=new Square(new rook(color, type, y,x),y,x);
		this.board[y][x].getPiece().setFirst(true);
		if(color.equals("white")){
			this.board[y][x].getPiece().setOutput("wR ");
			this.white.getArray().add(this.board[y][x].getPiece());
		}
		else{
			this.board[y][x].getPiece().setOutput("bR ");
			this.black.getArray().add(this.board[y][x].getPiece());
		}
	}
	public void createPieceBishop(String color, String type, int x, int y){
		this.board[y][x]=new Square(new bishop(color, type, y,x),y,x);
		this.board[y][x].getPiece().setFirst(true);
		if(color.equals("white")){
			this.board[y][x].getPiece().setOutput("wB ");
			this.white.getArray().add(this.board[y][x].getPiece());
		}
		else{
			this.board[y][x].getPiece().setOutput("bB ");
			this.black.getArray().add(this.board[y][x].getPiece());
		}
	}
	public void createPieceQueen(String color, String type, int x, int y){
		this.board[y][x]=new Square(new queen(color, type, y,x),y,x);
		this.board[y][x].getPiece().setFirst(true);
		if(color.equals("white")){
			this.board[y][x].getPiece().setOutput("wQ ");
			this.white.getArray().add(this.board[y][x].getPiece());
		}
		else{
			this.board[y][x].getPiece().setOutput("bQ ");
			this.black.getArray().add(this.board[y][x].getPiece());
		}
	}
	public void createPieceKing(String color, String type, int x, int y){
		this.board[y][x]=new Square(new king(color, type, y,x),y,x);
		this.board[y][x].getPiece().setFirst(true);
		if(color.equals("white")){
			this.board[y][x].getPiece().setOutput("wK ");
			this.white.getArray().add(this.board[y][x].getPiece());
		}
		else{
			this.board[y][x].getPiece().setOutput("bK ");
			this.black.getArray().add(this.board[y][x].getPiece());
		}
	}
	public void printBoard(){
		for(int i=0; i<board.length;i++){
			for(int j=0; j<board.length;j++){
				if(board[i][j]!=null){
				System.out.print(board[i][j].outPut());
				if(j==8){
					System.out.println();
				}
			}
			}
		}
	}
	/*public boolean moveWithPromotion(Move me, String kind){
		Square source=getSquare(me.sColumn,me.sRow);
		piece pSource=source.getPiece();
		Square target=getSquare(me.cTarget,me.rTarget);
		Square getRook = null;
		piece pTarget=target.getPiece();
		ArrayList<piece> temp;
		boolean checked=false;
		int sourceX;
		int sourceY;
		if(me.sColumn%2==0){
			if(me.sRow%2!=0){
				source.setOutput("## ");
			}
			else{
				source.setOutput("   ");
			}
		}
		else{
			if(me.sRow%2==0){
				source.setOutput("## ");
			}
			else{
				source.setOutput("   ");
			}
		}
		if(target.getPiece()!=null){
			piece sayonara=target.getPiece();
			if(sayonara.getColor().equals("white")){
				for(int i=0; i<this.white.getArray().size();i++){
					if(this.white.getArray().get(i).getType()==target.getPiece().getType()&&
							this.white.getArray().get(i).getX()==target.getPiece().getX() &&
							this.white.getArray().get(i).getY()==target.getPiece().getY()){
						this.white.getArray().remove(i);
					}
				}
			}
			else{
				for(int i=0; i<this.black.getArray().size();i++){
					if(this.black.getArray().get(i).getType()==target.getPiece().getType()&&
							this.black.getArray().get(i).getX()==target.getPiece().getX() &&
							this.black.getArray().get(i).getY()==target.getPiece().getY()){
						this.black.getArray().remove(i);
					}
				}
				
			}
			/*destroy reference to the piece*/
		/*	target.setPiece(null);
		}*/
		/*if(pSource.getFirst()==true){
			pSource.setFirst(false);
		}*/
	/*	source.setPiece(null);
		target.setPiece(pSource);
		pSource.setX(me.cTarget);
		pSource.setY(me.rTarget);
		System.out.println("Before returning true: testing. X: "+pSource.getX()+ "Y : "+pSource.getY());
		
		pSource.setFirst(false);//if piece has been moved, set it to false
		System.out.println("target column:"+me.cTarget+"row: "+me.rTarget);
		lastMoved=pSource;
		return true;
	}*/
	/*long method below*/
	public boolean movePiece(Move me, String kind){
		/*System.out.println(move.sColumn+" "+ move.sRow);*/
		Square source=getSquare(me.sColumn,me.sRow);
		piece pSource=source.getPiece();
		Square target=getSquare(me.cTarget,me.rTarget);
		Square getRook = null;
		if((enPassantPiece!=null)  &&(pSource.getColor().equals(enPassantPiece.getColor()))){
			/*sourceX=temp.get(i).getX();
					sourceY=temp.get(i).getY();
					if(temp.get(i).getFirst()==true){
						sourceX=temp.get(i).getY();
						sourceY=temp.get(i).getX();
					}*/
			int x;
			int y;
			if(pSource.getFirst()==true){
				x=pSource.getY();
				y=pSource.getX();
			}
			else{
				x=pSource.getX();
				y=pSource.getY();
			}
			if(x!=enPassantPiece.getX() &&y!=enPassantPiece.getY())
			enPassantPiece.setPassant(false);
			enPassantPiece=null;
		}
		piece pTarget=target.getPiece();
		ArrayList<piece> temp;
		boolean checked=false;
		int sourceX;
		int sourceY;
		if(me.sColumn%2==0){
			if(me.sRow%2!=0){
				source.setOutput("## ");
			}
			else{
				source.setOutput("   ");
			}
		}
		else{
			if(me.sRow%2==0){
				source.setOutput("## ");
			}
			else{
				source.setOutput("   ");
			}
		}
		/*BEGINNING OF CASTLING*/
		/*if it's a king and we got permission to castle*/
	//	System.out.println("testing should come here"+pSource.getkingCastle());
		if(pSource.getType().equals("king") && pSource.getkingCastle()==true &&
				pSource.getFinal()==false){
			//System.out.println("testing should come here"+ pSource.getColor());
			int yDelta=me.rTarget-me.sRow;
			int xDelta=me.cTarget-me.sColumn;
			if(pSource.getColor().equals("white")){
				/*get the opponent's array*/
				temp=black.getArray();
		
				for(int i=0; i<temp.size();i++){
					sourceX=temp.get(i).getX();
					sourceY=temp.get(i).getY();
					if(temp.get(i).getFirst()==true){
						sourceX=temp.get(i).getY();
						sourceY=temp.get(i).getX();
					}
		//			System.out.println("DETLA IS : "+xDelta);
					if(xDelta<0 && yDelta==0){
						getRook=getSquare(me.cTarget-2,me.rTarget);
						pTarget=getRook.getPiece();
						int targetY=7;
						int targetX=1;
						while(targetX!=4){
							/*checks all the pieces of black, if even one is on
							 * the path of possible castling, we can't do it
							 * Tested: with queen and knight*/
							Move test=new Move(sourceY, sourceX, targetY, targetX);
		//					System.out.println("X is: "+sourceY+"Y is"+sourceX+"target X: "+targetY+ targetX);
							this.valid=new MoveValid(this);
							checked=this.valid.isValidMove(test, "black");
							if(checked==true){
		//						System.out.println("I broke out");
								return false;
							}
							targetX++;
						}
						/*if checked remained true, we're good to go*/
					}
					else{
						int targetY=7;
						int targetX=5;
						getRook=getSquare(me.cTarget+1,me.rTarget);
						pTarget=getRook.getPiece();
						while(targetX!=7){
							Move test=new Move(sourceY, sourceX, targetY, targetX);
			//				System.out.println("X is: "+sourceY+"Y is"+sourceX+"target X: "+targetY+ targetX);
							this.valid=new MoveValid(this);
							checked=this.valid.isValidMove(test, "black");
							if(checked==true){
			//					System.out.println("I broke out");
								return false;
							}
							targetX++;
						}
						
					}
					
				}
				if(checked==false){
					if(pTarget.getY()==0){
			//		System.out.println("white left mwuahah fucking");
					source.setPiece(null);
					getRook.setPiece(null);
					board[7][2].setPiece(pSource);
					board[7][3].setPiece(pTarget);
					pSource.setX(2);
					pSource.setY(7);
					pTarget.setX(3);
					pTarget.setY(7);
					pSource.setFirst(false);
					pSource.setFinal(true);
					pTarget.setFirst(false);
					pTarget.setFinal(true);
					getRook.setOutput("## ");
		//			System.out.println("target column:"+me.cTarget+"row: "+me.rTarget);
					lastMoved=pSource;
					return true;
					}
					else{
	//					System.out.println("white right mwuahah fucking");
						source.setPiece(null);
						getRook.setPiece(null);
						board[7][6].setPiece(pSource);
						board[7][5].setPiece(pTarget);
						pSource.setX(6);
						pSource.setY(7);
						pTarget.setX(5);
						pTarget.setY(7);
						pSource.setFirst(false);
						pSource.setFinal(true);
						pTarget.setFirst(false);
						pTarget.setFinal(true);
						source.setOutput("## ");
						getRook.setOutput("   ");
		//				System.out.println("target column:"+me.cTarget+"row: "+me.rTarget);
						lastMoved=pSource;
						return true;
					}
				}
			}
			/*is it a black castling?*/
			else{
				temp=white.getArray();
				for(int i=0; i<temp.size();i++){
					sourceX=temp.get(i).getX();
					sourceY=temp.get(i).getY();
					if(temp.get(i).getFirst()==true){
						sourceX=temp.get(i).getY();
						sourceY=temp.get(i).getX();
					}
					if(xDelta<0 && yDelta==0){
						getRook=getSquare(me.cTarget-2,me.rTarget);
						pTarget=getRook.getPiece();
						int targetY=0;
						int targetX=1;
						while(targetX!=4){
							/*checks all the pieces of black, if even one is on
							 * the path of possible castling, we can't do it
							 * Tested: with queen and knight*/
							Move test=new Move(sourceY, sourceX, targetY, targetX);
					//		System.out.println("X is: "+sourceY+"Y is"+sourceX+"target X: "+targetY+ targetX);
							this.valid=new MoveValid(this);
							checked=this.valid.isValidMove(test, "white");
							if(checked==true){
				//				System.out.println("I broke out");
								return false;
							}
							targetX++;
						}
						/*if checked remained true, we're good to go*/
					}
					else{
						int targetY=0;
						int targetX=5;
						getRook=getSquare(me.cTarget+1,me.rTarget);
						pTarget=getRook.getPiece();
						while(targetX!=7){
							Move test=new Move(sourceY, sourceX, targetY, targetX);
			//				System.out.println("X is: "+sourceY+"Y is"+sourceX+"target X: "+targetY+ targetX);
							this.valid=new MoveValid(this);
							checked=this.valid.isValidMove(test, "white");
							if(checked==true){
					//			System.out.println("I broke out");
								return false;
							}
							targetX++;
						}
						
					}
					
				}
				if(checked==false){
					if(pTarget.getY()==0){
		//			System.out.println("mwuahah fucking or here");
					source.setPiece(null);
					getRook.setPiece(null);
					board[0][2].setPiece(pSource);
					board[0][3].setPiece(pTarget);
					pSource.setX(2);
					pSource.setY(0);
					pTarget.setX(3);
					pTarget.setY(0);
					pSource.setFirst(false);
					pSource.setFinal(true);
					pTarget.setFirst(false);
					pTarget.setFinal(true);
					getRook.setOutput("   ");
		//			System.out.println("target column:"+me.cTarget+"row: "+me.rTarget);
					lastMoved=pSource;
					return true;
					}
					else{
		//				System.out.println("mwuahah fucking did I come here");
						source.setPiece(null);
						getRook.setPiece(null);
						board[0][6].setPiece(pSource);
						board[0][5].setPiece(pTarget);
						pSource.setX(6);
						pSource.setY(0);
						pTarget.setX(5);
						pTarget.setY(0);
						pSource.setFirst(false);
						pSource.setFinal(true);
						pTarget.setFirst(false);
						pTarget.setFinal(true);
						source.setOutput("   ");
						getRook.setOutput("## ");
			//			System.out.println("target column:"+me.cTarget+"row: "+me.rTarget);
						lastMoved=pSource;
						return true;
					}
				}
			}
				
			
		}
		/*END OF CASTLING*/
		/*BEGIN OF using en passant*/
		if(pSource.getType().equals("pawn") && pSource.getPassant()==true){
			Square goodbye=getSquare(lastMoved.getX(), lastMoved.getY());
		//	System.out.println("testing attackage: "+goodbye.getPiece().getType());
			piece goodMan=goodbye.getPiece();
			/*if it's a pawn, goodbye dear friend*/
			if(goodMan.getType().equals("pawn")){
				if(goodMan.getColor().equals("white")){
					for(int i=0; i<this.white.getArray().size();i++){
						if(this.white.getArray().get(i).getType()==goodMan.getType()&&
								this.white.getArray().get(i).getX()==goodMan.getX() &&
								this.white.getArray().get(i).getY()==goodMan.getY()){
							this.white.getArray().remove(i);
						}
					}
				}
				else{
					for(int i=0; i<this.black.getArray().size();i++){
						if(this.black.getArray().get(i).getType()==goodMan.getType()&&
								this.black.getArray().get(i).getX()==goodMan.getX() &&
								this.black.getArray().get(i).getY()==goodMan.getY()){
							this.black.getArray().remove(i);
						}
					}
					
				}
				//goodbye.setOutput("  ");
				goodMan=null;
				goodbye.setPiece(null);
				source.setPiece(null);
				target.setPiece(pSource);
				pSource.setX(me.cTarget);
				pSource.setY(me.rTarget);
		//		System.out.println("IN passant: Before returning true: testing. X: "+pSource.getX()+ "Y : "+pSource.getY());
				
				pSource.setFirst(false);//if piece has been moved, set it to false
		//		System.out.println("target column:"+me.cTarget+"row: "+me.rTarget);
				lastMoved=pSource;
				return true;
			}
		}
		/*End of using en Passnt*/
		/*BEGIN OF setting opponent En Passant*/
		/*isValid already hanldes if it's the pawn starting position*/
		if(pSource.getType().equals("pawn") && Math.abs(me.rTarget-me.sRow)==2){
			if(pSource.getColor().equals("white")){
			if(pSource.getX()!=0){
				//for white
				int counter=0;
		//		while(counter!=2){
		//		System.out.println("testing. X: "+pSource.getX()+ "Y : "+pSource.getY());
					int test=pSource.getX()+1;
					int test1=pSource.getY()-2;
			//		System.out.println("testing. X+1: "+test+ "Y-2 : "+test1);
					Square passant=getSquare(pSource.getX()+1,pSource.getY()-2);
		//			System.out.println("testing. Passant piece is: "+passant.getPiece().getType());
					/*square has a piece?*/
					if(passant.getPiece()!=null){
						/*is it a pawn?*/
						if(passant.getPiece().getType().equals("pawn") &&!(pSource.getColor().equals(passant.getPiece().getColor()))){
							passant.getPiece().setPassant(true);
							enPassantPiece=passant.getPiece();
							wasSet=true;
						}
						/*check other side*/
						else{
							passant=getSquare(pSource.getX()-1,pSource.getY()-2);
							if(passant.getPiece()!=null){
								if(passant.getPiece().getType().equals("pawn") &&!(pSource.getColor().equals(passant.getPiece().getColor()))){
									passant.getPiece().setPassant(true);
									enPassantPiece=passant.getPiece();
									wasSet=true;
								}
							}
							
						}
					}
					else{
						/*equals null, we should still check the other side*/
						passant=getSquare(pSource.getX()-1,pSource.getY()-2);
						test=pSource.getX()-1;
						test1=pSource.getY()-2;
			//			System.out.println("testing. X+1: "+test+ "Y-2 : "+test1+passant.getPiece());
						if(passant.getPiece()!=null){
							if(passant.getPiece().getType().equals("pawn") &&!(pSource.getColor().equals(passant.getPiece().getColor()))){
								passant.getPiece().setPassant(true);
								enPassantPiece=passant.getPiece();
								wasSet=true;
							}
						}
						
					}
				//}
			}
			if(pSource.getX()==0){
				/*check only one side*/
				Square passant=getSquare(pSource.getX()+1,pSource.getY()-2);
				if(passant.getPiece()!=null){
								/*is it a pawn?*/
					if(passant.getPiece().getType().equals("pawn") &&!(pSource.getColor().equals(passant.getPiece().getColor()))){
									passant.getPiece().setPassant(true);
									enPassantPiece=passant.getPiece();
									wasSet=true;
							}
						}
				
			}
		}/*end of white passant*/
			else{
				if(pSource.getX()!=0){
					//for white
			//		while(counter!=2){
			//		System.out.println("testing. X: "+pSource.getX()+ "Y : "+pSource.getY());
				//		System.out.println("testing. X+1: "+test+ "Y-2 : "+test1);
						Square passant=getSquare(pSource.getX()+1,pSource.getY()+2);
			//			System.out.println("testing. Passant piece is: "+passant.getPiece().getType());
						/*square has a piece?*/
						if(passant.getPiece()!=null){
							/*is it a pawn?*/
							if(passant.getPiece().getType().equals("pawn") &&!(pSource.getColor().equals(passant.getPiece().getColor()))){
								passant.getPiece().setPassant(true);
								enPassantPiece=passant.getPiece();
								wasSet=true;
							}
							/*check other side*/
							else{
								passant=getSquare(pSource.getX()-1,pSource.getY()+2);
								if(passant.getPiece()!=null){
									if(passant.getPiece().getType().equals("pawn") &&!(pSource.getColor().equals(passant.getPiece().getColor()))){
										passant.getPiece().setPassant(true);
										enPassantPiece=passant.getPiece();
										wasSet=true;
									}
								}
								
							}
						}
						else{
							/*equals null, we should still check the other side*/
							passant=getSquare(pSource.getX()-1,pSource.getY()+2);
						//	System.out.println("testing. X+1: "+test+ "Y-2 : "+test1+passant.getPiece());
							if(passant.getPiece()!=null){
								if(passant.getPiece().getType().equals("pawn") &&!(pSource.getColor().equals(passant.getPiece().getColor()))){
									passant.getPiece().setPassant(true);
									enPassantPiece=passant.getPiece();
									wasSet=true;
								}
							}
							
						}
					//}
				}
				if(pSource.getX()==0){
					/*check only one side*/
					Square passant=getSquare(pSource.getX()+1,pSource.getY()+2);
					if(passant.getPiece()!=null){
									/*is it a pawn?*/
						if(passant.getPiece().getType().equals("pawn") &&!(pSource.getColor().equals(passant.getPiece().getColor()))){
										passant.getPiece().setPassant(true);
										enPassantPiece=passant.getPiece();
										wasSet=true;
								}
							}
					
				}
			}
			
		}
		/*END OF En Passant*/
		if(target.getPiece()!=null){
			piece sayonara=target.getPiece();
			if(sayonara.getColor().equals("white")){
				for(int i=0; i<this.white.getArray().size();i++){
					if(this.white.getArray().get(i).getType()==target.getPiece().getType()&&
							this.white.getArray().get(i).getX()==target.getPiece().getX() &&
							this.white.getArray().get(i).getY()==target.getPiece().getY()){
						this.white.getArray().remove(i);
					}
				}
			}
			else{
				for(int i=0; i<this.black.getArray().size();i++){
					if(this.black.getArray().get(i).getType()==target.getPiece().getType()&&
							this.black.getArray().get(i).getX()==target.getPiece().getX() &&
							this.black.getArray().get(i).getY()==target.getPiece().getY()){
						this.black.getArray().remove(i);
					}
				}
				
			}
			/*destroy reference to the piece*/
			target.setPiece(null);
		}
		/*if(pSource.getFirst()==true){
			pSource.setFirst(false);
		}*/
	//	System.out.println("Before returning true: testing. X: "+pSource.getX()+ "Y : "+pSource.getY());
		
		pSource.setFirst(false);//if piece has been moved, set it to false
		/*BEGIN OF promotion checker*/
		/*was the source that was moved a pawn?*/
		pSource.setX(me.cTarget);
		pSource.setY(me.rTarget);
	//	System.out.println("before entering");
		if(pSource.getType().equals("pawn")){
	//		System.out.println("yes, pawn");
			if(pSource.getColor().equals("white")){
	//			System.out.println("it's white");
				if(pSource.getY()==0){
		//			System.out.println("it's 0");
					/*pawn has reached the top, promotion time*/
					if(!kind.isEmpty()){
						switch(kind){
						case "n":
							removePiece(pSource);
							pSource=null;
							/*"white", "pawn", j,6)*/
							pSource=new knight("white", "knight",me.rTarget, me.cTarget);
							pSource.setOutput("wN ");
							this.white.getArray().add(pSource);
							break;
						case "q":
							removePiece(pSource);
							pSource=null;
							/*"white", "pawn", j,6)*/
							pSource=new queen("white", "queen",me.rTarget, me.cTarget);
							pSource.setOutput("wQ ");
							this.white.getArray().add(pSource);
							break;
						case "r":
							removePiece(pSource);
							/*"white", "pawn", j,6)*/
							pSource=null;
							pSource=new rook("white", "rook",me.rTarget, me.cTarget);
							pSource.setOutput("wR ");
							this.white.getArray().add(pSource);
							break;
						case "b":
							removePiece(pSource);
							pSource=null;
							/*"white", "pawn", j,6)*/
							pSource=new bishop("white", "bishop",me.rTarget, me.cTarget);
							pSource.setOutput("wB ");
							this.white.getArray().add(pSource);
							break;
						default:
						}
						
					}
					else{
						removePiece(pSource);
						/*"white", "pawn", j,6)*/
						pSource=null;
						pSource=new queen("white", "queen",me.rTarget, me.cTarget);
						pSource.setOutput("wQ ");
						this.white.getArray().add(pSource);
		//				System.out.println("in else"+pSource.getType());
					}
					
				}
			}
			else{
				if(pSource.getY()==7){
					if(!kind.isEmpty()){
						switch(kind){
						case "n":
							removePiece(pSource);
							pSource=null;
							/*"white", "pawn", j,6)*/
							pSource=new knight("black", "knight",me.rTarget, me.cTarget);
							pSource.setOutput("bN ");
							this.black.getArray().add(pSource);
							break;
						case "q":
							removePiece(pSource);
							pSource=null;
							/*"white", "pawn", j,6)*/
							pSource=new queen("black", "queen",me.rTarget, me.cTarget);
							pSource.setOutput("bQ ");
							this.black.getArray().add(pSource);
							break;
						case "r":
							removePiece(pSource);
							/*"white", "pawn", j,6)*/
							pSource=null;
							pSource=new rook("black", "rook",me.rTarget, me.cTarget);
							pSource.setOutput("bR ");
							this.black.getArray().add(pSource);
							break;
						case "b":
							removePiece(pSource);
							pSource=null;
							/*"white", "pawn", j,6)*/
							pSource=new bishop("black", "bishop",me.rTarget, me.cTarget);
							pSource.setOutput("bB ");
							this.black.getArray().add(pSource);
							break;
						default:
						}
						
					}
					else{
						removePiece(pSource);
						/*"white", "pawn", j,6)*/
						pSource=null;
						pSource=new queen("black", "queen",me.rTarget, me.cTarget);
						pSource.setOutput("bQ ");
						this.black.getArray().add(pSource);
			//			System.out.println("in else"+pSource.getType());
					}
					
				
					
				}
			}
			
		}
			/*END OF promotion checker*/
		if(enPassantPiece!=null &&enPassantPiece.getPassant()==true &&wasSet==false){
			enPassantPiece.setPassant(false);
		}
		wasSet=false;
		source.setPiece(null);
		target.setPiece(pSource);
		pSource.setX(me.cTarget);
		pSource.setY(me.rTarget);
//		System.out.println("before returning type is "+pSource.getType());
//		System.out.println("target column:"+me.cTarget+"row: "+me.rTarget);
		lastMoved=pSource;
		return true;
	}
	
	public void removePiece(piece sayonara){
			if(sayonara.getColor().equals("white")){
				for(int i=0; i<this.white.getArray().size();i++){
					if(this.white.getArray().get(i).getType()==sayonara.getType()&&
							this.white.getArray().get(i).getX()==sayonara.getX() &&
							this.white.getArray().get(i).getY()==sayonara.getY()){
	//					System.out.println("remove was successful");
						this.white.getArray().remove(i);
					}
				}
			}
			else{
				for(int i=0; i<this.black.getArray().size();i++){
					if(this.black.getArray().get(i).getType()==sayonara.getType()&&
							this.black.getArray().get(i).getX()==sayonara.getX() &&
							this.black.getArray().get(i).getY()==sayonara.getY()){
						this.black.getArray().remove(i);
					}
				}
				
			}
	}
	/*public static void main(String []args){
		board test=new board();
		}*/
	}

