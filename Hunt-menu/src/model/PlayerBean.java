package model;

import java.io.Serializable;

public class PlayerBean implements Serializable {
	private static final long serialVersionUID = -218845840700379740L;
	private int playerId;
	private String playerName;
	
	
	public PlayerBean() {
		
	}
	
	public PlayerBean(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(int id) {
		this.playerId = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
