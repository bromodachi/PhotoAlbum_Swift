package outPut;
/*@author  Conrado Uraga*/
public class Chess {
	public static void main(String []args){
		board chessGame=new board();
		ConsoleControl go=new ConsoleControl(chessGame);
		go.startGame();
	}

}
