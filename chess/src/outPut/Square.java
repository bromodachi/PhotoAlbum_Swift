package outPut;

import piece.piece;
/*@author  Conrado Uraga*/
public class Square {
	piece pi;
	int x;
	int y;
	String options;
	
	Square(String stuff,int yL, int xL, piece add){
		this.options=stuff;
		this.y=yL;
		this.x=xL;
		this.pi=add;
	}
	Square(piece add,int yL, int xL){
		this.pi=add;
		this.y=yL;
		this.x=xL;
	}
	public void setOutput(String setMe){
		this.options=setMe;
	}
	String outPut(){
		if(pi==null){
			return options;
		}
		else{
			return pi.returnOutput();
		}
	}
	public piece getPiece(){
		return this.pi;
	}
	public void setPiece(piece setMe){
		this.pi=setMe;
	}
	

}
