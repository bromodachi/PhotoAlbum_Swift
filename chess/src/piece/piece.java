package piece;

import outPut.Square;
/*@author  Conrado Uraga*/
public abstract class piece {
	
	protected String color;
	private String outPut;
	private int positionX;
	private int positionY;
	private String kind;//don't know if I should keep this one yet
	private Square square;
	private boolean targetFree;
	private boolean targetCapturable;
	private boolean first;
	private boolean kingCastle;
	private boolean finalKC=false;
	private boolean check;
	private boolean enPassant=false;
	
	piece(String iro, String type, int x, int y){
		this.color=iro;
		this.kind=type;
		this.positionX=x;
		this.positionY=y;
	}
	public int getX(){
		return this.positionX;
	}
	public int getY(){
		return this.positionY;
	}
	public void setX(int x){
		this.positionX=x;
	}
	public void setY(int y){
		this.positionY=y;
	}
	public void setFree(boolean yes){
		this.targetFree=yes;
	}
	public boolean getFree(){
		return this.targetFree;
	}
	public void setCapture(boolean yes){
		this.targetCapturable=yes;
	}
	public void setFirst(boolean noMas){
		first=noMas;
	}
	public boolean getFirst(){
		return first;
	}
	public void setkingCastle(boolean no){
		this.kingCastle=no;
	}
	public boolean getkingCastle(){
		return this.kingCastle;
	}
	public void setCheck(boolean yes){
		this.check=yes;
	}
	public boolean getCheck(){
		return this.check;
	}
	/*set final castling*/
	public void setFinal(boolean yes){
		this.finalKC=yes;
	}
	public boolean getFinal(){
		return this.finalKC;
	}

	public boolean getCapture(){
		return this.targetCapturable;
	}
	
	public String getColor(){
		return this.color;
	}
	public String getType(){
		return this.kind;
	}
	public void setOutput(String set){
		this.outPut=set;
	}
	public String returnOutput(){
		return this.outPut;
	}
	public void setPassant(boolean yes){
		this.enPassant=yes;
	}
	public boolean getPassant(){
		return this.enPassant;
	}

	public abstract boolean isValid(int sourceColumn, int sourceRow, int targetColumn, int targetRow);
	
	

}
