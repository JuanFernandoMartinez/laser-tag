package model;

public class Player {
	private String nickname;
	private double score;
	
	public Player(String nick) {
		nickname = nick;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	

}
