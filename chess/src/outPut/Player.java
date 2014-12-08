package outPut;

import java.util.ArrayList;

import piece.piece;
/*@author  Conrado Uraga*/
public class Player {
	String color;
	ArrayList<piece> pieceSet;
	board game;
	Player(String colorMe, board playerBoard){
		this.color=colorMe;
		this.pieceSet=new ArrayList<piece>(16);
		this.game=playerBoard;
	}
	
	public String getColor(){
		return this.color;
	}
	public ArrayList<piece> getArray(){
		return this.pieceSet;
	}
	
}
