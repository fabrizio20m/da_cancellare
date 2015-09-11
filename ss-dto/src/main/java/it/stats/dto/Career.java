/**
 * 
 */
package it.stats.dto;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author fabrizio
 *
 */
@XmlRootElement
//@Entity
//@Table(name="career", schema="sw")
public class Career implements Serializable
{
	private static final long serialVersionUID = 1L;
	
//	@Id	
//	@Column(name="career_id", nullable=false)	
//	@SequenceGenerator(name="career_seq", sequenceName="career_seq", schema="sw", allocationSize=1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="career_seq")
	private BigInteger careerId;
	
//	@Column(name="season_id", length=10)
	private String seasonId;
	
//	@Column(name="team_id", precision=0)
	private Integer teamId;
	
//	@Column(name="competition_id", length=10)
	private String competitionId;

//	@Column(name="minute", precision=0)
	private Integer minute;
	
//	@Column(name="appearence", precision=0)
	private Integer appearence;
	
//	@Column(name="lineups", precision=0)
	private Integer lineups;
	
//	@Column(name="subs_in", precision=0)
	private Integer subsIn;
	
//	@Column(name="subs_out", precision=0)
	private Integer subsOut;
	
//	@Column(name="subs_on_bench", precision=0)
	private Integer subsOnBench;
	
//	@Column(name="goals", precision=0)
	private Integer goals;
	
//	@Column(name="yellow_cards", precision=0)
	private Integer yellowCards;
	
//	@Column(name="second_yellow_cards", precision=0)
	private Integer secondYellowCards;
	
//	@Column(name="red_cards", precision=0)
	private Integer redCards;
	
//	@Column(name="type", nullable=false, length=10)
	private String type;
	
//	@ManyToOne
//    @JoinColumn(name="player_id")
	private Player player;
	
	public BigInteger getCareerId() {
		return careerId;
	}

	public void setCareerId(BigInteger careerId) {
		this.careerId = careerId;
	}

	public String getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getAppearence() {
		return appearence;
	}

	public void setAppearence(Integer appearence) {
		this.appearence = appearence;
	}

	public Integer getLineups() {
		return lineups;
	}

	public void setLineups(Integer lineups) {
		this.lineups = lineups;
	}

	public Integer getSubsIn() {
		return subsIn;
	}

	public void setSubsIn(Integer subsIn) {
		this.subsIn = subsIn;
	}

	public Integer getSubsOut() {
		return subsOut;
	}

	public void setSubsOut(Integer subsOut) {
		this.subsOut = subsOut;
	}

	public Integer getSubsOnBench() {
		return subsOnBench;
	}

	public void setSubsOnBench(Integer subsOnBench) {
		this.subsOnBench = subsOnBench;
	}

	public Integer getGoals() {
		return goals;
	}

	public void setGoals(Integer goals) {
		this.goals = goals;
	}

	public Integer getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(Integer yellowCards) {
		this.yellowCards = yellowCards;
	}

	public Integer getSecondYellowCards() {
		return secondYellowCards;
	}

	public void setSecondYellowCards(Integer secondYellowCards) {
		this.secondYellowCards = secondYellowCards;
	}

	public Integer getRedCards() {
		return redCards;
	}

	public void setRedCards(Integer redCards) {
		this.redCards = redCards;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
