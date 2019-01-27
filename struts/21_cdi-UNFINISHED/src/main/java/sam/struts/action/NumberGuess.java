package sam.struts.action;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import sam.struts.model.Game;

public class NumberGuess extends ActionSupport {

	@Inject
	private Game game;

	public Game getGame() { return game; }
	public void setGame(Game game) { this.game = game; }

	public String check(){
		addActionError(game.check() ? "Correct" : ("Incorrect value. Correct value is: "+game.supposedToBe()));
		return SUCCESS;
	} 
}
