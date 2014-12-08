package outPut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import piece.piece;
/*@author  Conrado Uraga*/
public class ConsoleControl {
	board game;
	Player [] players;
	ArrayList<piece> checkMatePieces;
	boolean checkMate=false;
	boolean lolfirst=true;
	Player active;
	String gameOver="no";
	MoveValid valid;
	int draw=0;
	String choice="";
	ArrayList<piece> temp;
	Square kingSquare;
	ConsoleControl(board ready){
		this.game=ready;
		this.players=new Player[2];
		this.players[0]=new Player("black", this.game);
		this.players[1]=new Player("white", this.game);
		checkMatePieces=new ArrayList<piece>();
	//	game.printBoard();
		this.game.white=this.players[1];
		this.game.black=this.players[0];
		this.game.createPieces();
		this.valid=new MoveValid(this.game);
	//	startGame();
	}
	
	public void startGame(){
		boolean test=true;
		this.active=players[1];
		boolean underAttack=false;
	//	System.out.println("Size of array is: "+this.active.getColor()+" "+this.active.getArray().size());
		while(gameOver.equals("no")){
			if(lolfirst==true){
			this.game.printBoard();
			lolfirst=false;
			}
			else{
			System.out.println();
			this.game.printBoard();
			}
			underAttack=isKingChecked();
			if(underAttack==true){
			//	checkMate=checkMate();
				checkMate=checkMateMoved();
				if(checkMate){
					gameOver="yes";
					System.out.println("Checkmate");
					continue;
				}
				System.out.println("Check");
			}
		/*	if(test==true){
				System.out.println(this.game.getSquare(0, 6).getPiece().getType()+this.game.getSquare(0, 6).getPiece().getX()+this.game.getSquare(0, 6).getPiece().getY());
				test=false;
			}*/
			getMove();
			//System.out.println("last moved: "+this.game.lastMoved.getX()+this.game.lastMoved.getY());
			
			swapPlayers();
			/*System.out.println("Size of array is: "+this.active.getColor()+" "+this.active.getArray().size());
			for(int i=0; i<active.getArray().size();i++){
				System.out.println(active.getArray().get(i).getType());
			}*/
			/*for(int i=0; i<active.getArray().size();i++){
				if(active.getArray().get(i).getType().equals("king")){
					System.out.println("King position: "+active.getArray().get(i).getX()+" "+active.getArray().get(i).getY());
				}
			}*/
		/*	underAttack=isKingChecked();
			if(underAttack==true){
				System.out.println("Check: Move your king to a safe position");
			}*/
			
		}
		if(active.getColor().equals("white")){
			System.out.println("\nBlack wins");
			return;
		}
		else{
			System.out.println("\nWhite wins");
			return;
		}
	}
	public void swapPlayers(){
		if(this.active.getColor().equals("white")){
			this.active=players[0];
		}
		else{
			this.active=players[1];
		}
	}
	public boolean noPiecesStopCheck(int xKing, int yKing){
		int x=xKing;
		int y=yKing;
		int sourceX;
		int sourceY;
		int targetX;
		int targetY;
		int step;
		int stepy;
		Move test=null;
		boolean nope=false;
		boolean anotherCheck=false;
		for(int i=0; i<active.getArray().size();i++){
			if(active.getArray().get(i).getType().equals("king")){
				continue;
			}
			if(active.getArray().get(i).getFirst()==true){
				sourceX=active.getArray().get(i).getY();
				sourceY=active.getArray().get(i).getX();
			}
			else{
			sourceX=active.getArray().get(i).getX();
			sourceY=active.getArray().get(i).getY();
			}
		//	sourceX=active.getArray().get(i).getX();
		//	sourceY=active.getArray().get(i).getY();
		//	System.out.println("Source current piece: "+active.getArray().get(i).getType());
		//	System.out.println("its x: "+sourceX+"its y: "+sourceY);
				targetX=this.game.lastMoved.getX();
				targetY=this.game.lastMoved.getY();
		//		test=new Move(sourceY, sourceX, targetY, targetX);
				int xDelta=yKing-targetY;
				int yDelta=xKing-targetX;
				/*FINISH THIS LATER*/
		/*		System.out.println("Checkmate current piece: "+this.game.lastMoved.getType());
				System.out.println("xDelta is :"+xDelta+ "Ydelta: "+yDelta);
				System.out.println("King's Y is :"+yKing + "King X is "+ xKing);
				System.out.println("targetX is"+ targetX+"targetY is"+ targetY);*/
				/*if it's in the same row*/
				if(xDelta==0){
					/*if it's to the right:*/
					
					if(yDelta<0){
						step=targetX;
						while(step!=xKing){
							/*(int sourceR, int sourceC, int targeR, int targetC)*/
						//	System.out.println("Step to right: "+step);
							test=new Move(sourceY, sourceX, targetY, step);
							anotherCheck=this.valid.isValidMove(test, this.active.getColor());
							if(anotherCheck==true){
								return false;
							}
							step--;
						}
					}
					/*to the left:*/
					else{
						step=targetX;
						while(step!=xKing){
					//		System.out.println("Step to left: "+step);
							test=new Move(sourceY, sourceX, targetY, step);
							anotherCheck=this.valid.isValidMove(test, this.active.getColor());
							if(anotherCheck==true){
								return false;
							}
							step++;
						}
						
					}
				}
				/*if it's in the same column*/
				else if(yDelta==0){
					if(xDelta>0){
						step=targetY;
						while(step!=yKing){
				//			System.out.println("Step to down?: "+step);
							test=new Move(sourceY, sourceX, step, targetX);
							anotherCheck=this.valid.isValidMove(test, this.active.getColor());
							if(anotherCheck==true){
								return false;
							}
							step++;
						}
					}
					/*to the left:*/
					else{
						step=targetY;
						while(step!=yKing){
				//			System.out.println("Step to up?: "+step);
							test=new Move(sourceY, sourceX, step,targetX);
							anotherCheck=this.valid.isValidMove(test, this.active.getColor());
							if(anotherCheck==true){
								return false;
							}
							step--;
						}
						
					}
					
				}
				else if(Math.abs(xDelta)==Math.abs(yDelta)){
			//		System.out.println("came here");
					if(yDelta<0){
						/*int sourceColumn, int sourceRow, int targetColumn, int targetRow, int rowStep, int columnStep*/
						if(xDelta>0){
							/*we're going down left*/
							/* int rowStep, int columnStep*/
							step=targetX;
							stepy=targetY;
							/*DON'T CHANGE CORRECT*/
				//			System.out.println("WHERE AM I GOING: "+step+"  "+stepy);
							while(!(step==xKing)){
				//				System.out.println("Step to downleft?: "+step+"  "+stepy);
								test=new Move(sourceY, sourceX, stepy, step);
								anotherCheck=this.valid.isValidMove(test, this.active.getColor());
								if(anotherCheck==true){
									return false;
								}
								stepy++;
								step--;
								if(validNumbers(step, stepy)){
									continue;
								}
								else{
									break;
								}
							}
						}
						else{
							/*we're going up left DON'T CHANGE*/
							step=targetX;
							stepy=targetY;
				//			System.out.println("wait here? Step to up left? : "+step+"Y  "+stepy);
							while(!(step==xKing)){
								test=new Move(sourceY, sourceX, stepy, step);
								anotherCheck=this.valid.isValidMove(test, this.active.getColor());
								if(anotherCheck==true){
									return false;
								}
								stepy--;
								step--;
								if(validNumbers(step, stepy)){
									continue;
								}
								else{
									break;
								}
							}
						}
						
					}
					/*moving downwards*/
					else{
						if(xDelta<0){
							/*we're going up right*/
							step=targetX;
							stepy=targetY;
					//		System.out.println("Step to up right? : "+step+"Y  "+stepy);
							while(!(stepy==yKing)){
								test=new Move(sourceY, sourceX, stepy, step);
								anotherCheck=this.valid.isValidMove(test, this.active.getColor());
								if(anotherCheck==true){
									return false;
								}
								stepy--;
								step++;
								if(validNumbers(step, stepy)){
									continue;
								}
								else{
									break;
								}
							}
						}
						else{
							/*we're going downRight*/
							step=targetX;
							stepy=targetY;
					//		System.out.println("WHAAAA : "+step+"Y  "+stepy);
							while(!(stepy==yKing)){
								test=new Move(sourceY, sourceX, stepy, step);
								anotherCheck=this.valid.isValidMove(test, this.active.getColor());
								if(anotherCheck==true){
									return false;
								}
								stepy++;
								step++;
								if(validNumbers(step, stepy)){
									continue;
								}
								else{
									break;
								}
							}
						}
					}
				}
				else if(this.game.lastMoved.getType().equals("knight")){
					test=new Move(sourceY, sourceX, targetY, targetX);
					anotherCheck=this.valid.isValidMove(test, this.active.getColor());
					if(anotherCheck==true){
						return false;
					}
				}
			
		}
		/*boolean checked=false;
		for(int i=0; i<temp.size();i++){
			sourceX=temp.get(i).getX();
			sourceY=temp.get(i).getY();
			Move test=new Move(sourceY, sourceX, x, y);
			checked=this.valid.isValidMove(test, color);
			if(checked==true){
				checkMatePieces.add(temp.get(i));
				return true;
			}
		}
		return checked;*/
		return true;
	}
	public boolean checkMateMoved(){
		/*clear checkMates*/
		checkMatePieces.clear();
		boolean checked=true;
		piece king=null;
		String color="";
		if(this.active.getColor().equals("white")){
			temp=players[0].getArray();
			color="black";
		}
		else{
			temp=players[1].getArray();
			color="white";
		}
		/*get King*/
		for(int i=0; i<active.getArray().size();i++){
			if(active.getArray().get(i).getType().equals("king")){
				king=active.getArray().get(i);
				//System.out.println("King position: "+active.getArray().get(i).getX()+" "+active.getArray().get(i).getY());
			}
		}
		//return board[y][x];
//		kingSquare=this.game.getSquare(king.getX(),king.getY());
		int targetY;
		int targetX;
//	kingSquare.setPiece(null);
		if(king.getFirst()==true){
			targetY=king.getY();
			targetX=king.getX();
			kingSquare=this.game.getSquare(king.getY(),king.getX());
			kingSquare.setPiece(null);
		}
		else{
		targetY=king.getX();
		targetX=king.getY();
		kingSquare=this.game.getSquare(king.getX(),king.getY());
		kingSquare.setPiece(null);
		}
	//	targetY=king.getX();
	//	targetX=king.getY();
		int temp1=targetX;
		int temp2=targetY-1;
	/*	System.out.println("kingGetX "+king.getX()+" "+king.getY());
		System.out.println("reverse, TargetY: "+targetY+" Target X: "+targetX);
		System.out.println("targetX: "+targetX+" Target Y: "+targetY);
		System.out.println("temp1: "+temp1+" temp2 "+temp2);*/
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp2, temp1).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
				//	System.out.println("broke here 1");
					kingSquare.setPiece(king);
					return false;
				}
			}
		}
		temp1=targetX+1;
		temp2=targetY-1;
	//	System.out.println("targetX: "+targetX+" Target Y: "+targetY);
	//	System.out.println("X moved over, down one temp1: "+temp1+" temp2 "+temp2);
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp2, temp1).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
		//			System.out.println("broke here 2");
					kingSquare.setPiece(king);
					return false;
					
				}
			}
		}
		temp1=targetX+1;
		temp2=targetY;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp2, temp1).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
		//			System.out.println("broke here 3");
					kingSquare.setPiece(king);
					return false;
				}
			}
		}
		temp1=targetX+1;
		temp2=targetY+1;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp2, temp1).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
			//		System.out.println("broke here 4");
					kingSquare.setPiece(king);
					return false;
				}
			}
		}
		temp1=targetX;
		temp2=targetY+1;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp2, temp1).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
			//		System.out.println("broke here 5");
					kingSquare.setPiece(king);
					return false;
				}
			}
		}
		temp1=targetX-1;
		temp2=targetY+1;
	//	System.out.println("temp1: "+temp1+" temp2 "+temp2);
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp2, temp1).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
			//		System.out.println("temp1: "+temp1+" temp2 "+temp2);
			//		System.out.println("broke here 6");
					kingSquare.setPiece(king);
					return false;
				}
			}
		}
		temp1=targetX-1;
		temp2=targetY;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp2, temp1).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
		//			System.out.println("broke here 7");
					kingSquare.setPiece(king);
					return false;
				}
			}
		}
		temp1=targetX-1;
		temp2=targetY-1;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp2, temp1).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
		//			System.out.println("broke here 8");
					kingSquare.setPiece(king);
					return false;
				}
			}
		}
			/*while(targetX!=4){
							
							Move test=new Move(sourceX, sourceY, targetY, targetX);
							System.out.println("X is: "+sourceY+"Y is"+sourceX+"target X: "+targetY+ targetX);
							this.valid=new MoveValid(this);
							checked=this.valid.isValidMove(test, "white");
							if(checked==true){
								System.out.println("I broke out");
								return false;
							}
							targetX++;
						}*/
		if(checked==true){
			
			checked=noPiecesStopCheck(targetY, targetX);
			if(checked==false){
				kingSquare.setPiece(king);
				return false;
			}
		}
		/*clear checkMates*/
		checkMatePieces.clear();
		kingSquare.setPiece(king);
		return true;
		
	}
	public boolean checkMate(){
	//	ArrayList<piece> temp;
		
		checkMatePieces.clear();
		piece king=null;
		String color="";
		boolean checked=true;
		if(this.active.getColor().equals("white")){
			temp=players[0].getArray();
			color="black";
		}
		else{
			temp=players[1].getArray();
			color="white";
		}
		/*get King*/
		for(int i=0; i<active.getArray().size();i++){
			if(active.getArray().get(i).getType().equals("king")){
				king=active.getArray().get(i);
				//System.out.println("King position: "+active.getArray().get(i).getX()+" "+active.getArray().get(i).getY());
			}
		}
		if(king.getFirst()==false){
			return  checkMateMoved();
			
		}
		int targetX=king.getX();
		int targetY=king.getY();
	//	System.out.println("kingGetX, not reverse: "+targetX+" "+targetY);
		int temp1=targetX;
		int temp2=targetY-1;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp1, temp2).getPiece()==null){
			checked=checkSurroundings(temp1, temp2, color);
			if(checked==false){
				return false;
			}
			}
		}
		temp1=targetX+1;
		temp2=targetY-1;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp1, temp2).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
					return false;
				}
			}
		}
		temp1=targetX+1;
		temp2=targetY;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp1, temp2).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
					return false;
				}
			}
		}
		temp1=targetX+1;
		temp2=targetY+1;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp1, temp2).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
					return false;
				}
			}
		}
		temp1=targetX;
		temp2=targetY+1;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp1, temp2).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
					return false;
				}
			}
		}
		temp1=targetX-1;
		temp2=targetY+1;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp1, temp2).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
					return false;
				}
			}
		}
		temp1=targetX-1;
		temp2=targetY;
		if(validNumbers(temp1, temp2)){
			if(this.game.getSquare(temp1, temp2).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
					return false;
				}
			}
		}
		temp1=targetX-1;
		temp2=targetY-1;
		if(validNumbers(temp1, temp2)){
				if(this.game.getSquare(temp1, temp2).getPiece()==null){
				checked=checkSurroundings(temp1, temp2, color);
				if(checked==false){
					return false;
				}
			}
		}
			/*while(targetX!=4){
							
							Move test=new Move(sourceX, sourceY, targetY, targetX);
							System.out.println("X is: "+sourceY+"Y is"+sourceX+"target X: "+targetY+ targetX);
							this.valid=new MoveValid(this);
							checked=this.valid.isValidMove(test, "white");
							if(checked==true){
								System.out.println("I broke out");
								return false;
							}
							targetX++;
						}*/
		if(checked==true){
			
		}
		/*clear checkMatePieces*/
		//checkMatePieces.clear();
		return true;
	}
	
	public boolean checkSurroundings(int x, int y, String color){
		int sourceX;
		int sourceY;
		boolean checked=false;
		for(int i=0; i<temp.size();i++){
		//	System.out.println("Source X"+sourceX+"source Y: "+sourceY);
			sourceX=temp.get(i).getX();
			sourceY=temp.get(i).getY();
			/*if(temp.get(i).getType().equals("queen")){
				System.out.println("Queen Source X"+sourceX+"source Y: "+sourceY);
			}*/
		//	System.out.println("Source X"+sourceX+"source Y: "+sourceY);
		//	System.out.println("X"+x+"Y: "+y);
			if(temp.get(i).getFirst()==true){
				sourceX=temp.get(i).getY();
				sourceY=temp.get(i).getX();
			}
		//	System.out.println(" OUTSIDE Source X"+sourceX+"source Y: "+sourceY);
		//	System.out.println("X"+x+"Y: "+y);
			if(temp.get(i).getType().equals("king")&& temp.get(i).getFirst()==true){
				sourceX=temp.get(i).getY();
				sourceY=temp.get(i).getX();
			}
			Move test=new Move(sourceY, sourceX, x, y);
			checked=this.valid.isValidMove(test, color);
			if(checked==true){
	//			System.out.println(temp.get(i).getType());
				checkMatePieces.add(temp.get(i));
				return true;
			}
		}
		return checked;
			
		
	}
	public boolean validNumbers(int i, int j){
		if(i<0|| i>7||j<0||j>7){
			return false;
		}
		else{
			return true;
		}
		
	}
	public boolean isKingChecked(){
		int sourceX;
		int sourceY;
		piece king=null;
		boolean checked=false;
		Move test=null;
		for(int i=0; i<active.getArray().size();i++){
			if(active.getArray().get(i).getType().equals("king")){
				king=active.getArray().get(i);
				//System.out.println("King position: "+active.getArray().get(i).getX()+" "+active.getArray().get(i).getY());
			}
		}
		int targetX=king.getX();
		int targetY=king.getY();
		
		if(this.game.lastMoved!=null){
			piece temp1=this.game.lastMoved;
			/*sourceX=temp.getY();
			sourceY=temp.getX();*/
			sourceX=temp1.getX();
			sourceY=temp1.getY();
	//		System.out.println("king position: "+targetX+" "+ targetY+"color "+king.getColor());
			//return new Move(sourceY,sourceX, targetY, targetX);
			if(king.getFirst()==false){
				test=new Move(sourceY,sourceX, targetY, targetX);
			}
			else{
			test=new Move(sourceY,sourceX, targetX, targetY);}
			checked=this.valid.isValidMove(test, temp1.getColor());
			king.setCheck(checked);
			return checked;
			/*switch(temp.getType()){
				case "pawn":case "king":
					sourceX=temp.getY();
					sourceY=temp.getX();
					if(temp.getColor().equals("white")){
						if()
						if(sourceX+1==targetX && sourceY==source)
					}
				case "knight":
				
				case "bishop":
					
				case "rook":
					
				case "queen":
				default:
						return false;
			
			}*/
		}
		return false;
	}
	public void getMove(){
		boolean validInput=false;
		boolean success=false;
		Move temp=null;
		while(validInput==false){
			System.out.print("\n"+active.color+"'s move: ");
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			String output="";
			try {
				output = input.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(output.equals("resign")){
				gameOver="yes";
				if(active.getColor().equals("white")){
					System.out.println("Black wins");
					System.exit(0); 
					return;
				}
				else{
					System.out.println("White wins");
					System.exit(0); 
					return;
				}
			}
			if(draw==1){
				if(output.equals("draw")){
					gameOver="yes";
					System.out.println("\nDraw");
					System.exit(0); 
					return;
				}
				draw=0;
			}
	//		System.out.println("testing: "+output);
			temp=convertString(output);
			if(temp==null){
				System.out.println("Illegal move, try again");
			}
			else{
				validInput=this.valid.isValidMove(temp, active.getColor());
				
				if(validInput==false){
					System.out.println("Illegal move, try again");
				}
			}
		}
		if(validInput==true){
	//		System.out.println(choice);
			success=game.movePiece(temp, choice);
			if(success==false){
				System.out.println("Illegal move, try again");
				getMove();
				choice="";
			}
			else{
				choice="";
				return;
			}
		}
	}
	public Move convertString(String input){
		/*Move's constructor (int sourceR, int sourceC, int targeR, int targetC)*/
		/*input.length()!=5||*/
		if(input==null||!(input.contains(" "))){
			return null;
		}
		int sourceY;
		int targetY;
		String splitMe[]=input.split(" ");
		if(splitMe.length!=3 && splitMe.length!=2){
			return null;
		}
		if(splitMe.length==2){
			if(input.length()!=5){
				return null;
			}
		}
		if(splitMe.length==3){
			switch(splitMe[2].toLowerCase()){
				case "draw?":
					draw=1;
					break; 
				case "n":
				case "q":
				case "r":
				case "b":
					choice=splitMe[2].toLowerCase();
		//			System.out.println(choice);
					break;
				default:
					return null;
					
			}
		}
		
		int sourceX=alhpaToNumeric(splitMe[0].substring(0,1));
		int targetX=alhpaToNumeric(splitMe[1].substring(0,1));
		if(sourceX==-1||targetX==-1){
			return null;
		}
		try {
			sourceY=Integer.parseInt(splitMe[0].substring(1,2));
			targetY=Integer.parseInt(splitMe[1].substring(1,2));
			
		}
		catch (NumberFormatException e) {
			return null;
		}
		sourceY=convertToRealNum(sourceY);
		targetY=convertToRealNum(targetY);
		if(sourceX==-1||sourceY==-1){
			return null;
		}
		/*(int sourceR, int sourceC, int targeR, int targetC)*/
		return new Move(sourceY,sourceX, targetY, targetX);
		
		
	}
	public int convertToRealNum(int num) {
		if(num==8){
			return 0;
		}
		else if(num==7){
			return 1;
		}
		else if(num==6){
			return 2;
		}
		else if(num==5){
			return 3;
		}
		else if(num==4){
			return 4;
		}
		else if(num==3){
			return 5;
		}
		else if(num==2){
			return 6;
		}
		else if(num==1){
			return 7;
		}
		else{
			return -1;
		}
	}

	public int alhpaToNumeric(String input){
		switch(input.toLowerCase()){
			case "a":
				return 0;
			case "b":
				return 1;
			case "c":
				return 2;
			case "d":
				return 3;
			case "e":
				return 4;
			case "f":
				return 5;
			case "g":
				return 6;
			case "h":
				return 7;
			default:
				return -1;
				
		
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
		//		System.out.println("fucking up: "+currCol+" "+currRow);
				return true;
			}
			currRow=currRow+rowStep;
			currCol=currCol+columnStep;
		}
		return false;
	}
	

}
