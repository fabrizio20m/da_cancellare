/**
 * 
 */
package it.stats.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Eva
 *
 */
@XmlRootElement
//@Entity
//@Table(name="player", schema="sw")
public class Player implements Serializable
{
	private static final long serialVersionUID = 1L;
	
//	@Id
//	@Column(name="player_id", nullable=false, precision=0)
	private BigInteger playerId;
	
//	@Column(name="name", nullable=false, length=100)
	private String name;
	
//	@Column(name="surname", nullable=false, length=100)
	private String surname;
	
//	@Column(name="nationality", nullable=true, length=100)
	private String nationality;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="birth_date", nullable=true)
	private Calendar birthDate;
	
//	@Column(name="birth_country", nullable=true, length=100)
	private String birthCountry;
	
//	@Column(name="birth_place", nullable=true, length=100)
	private String birthPlace;
	
//	@Column(name="position", nullable=true, length=30)
	private String position;
	
//	@Column(name="height", nullable=true, length=10)
	private String height;
	
//	@Column(name="weight", nullable=true, length=10)
	private String weight;
	
//	@Column(name="foot", nullable=true, length=10)
	private String foot;
	
//	@Column(name="picture_url", nullable=true, length=200)
	private String pictureUrl;
	
//	@Column(name="picture", nullable=true)
	private byte[] picture;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "player", cascade = CascadeType.ALL)
	private List<Career> career;

	/**
	 * @return the playerId
	 */
	public BigInteger getPlayerId() {
		return playerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @return the birthDate
	 */
	public Calendar getBirthDate() {
		return birthDate;
	}

	/**
	 * @return the birthCountry
	 */
	public String getBirthCountry() {
		return birthCountry;
	}

	/**
	 * @return the birthPlace
	 */
	public String getBirthPlace() {
		return birthPlace;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @return the foot
	 */
	public String getFoot() {
		return foot;
	}

	/**
	 * @return the pictureUrl
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * @return the picture
	 */
	public byte[] getPicture() {
		return picture;
	}

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(BigInteger playerId) {
		this.playerId = playerId;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @param birthCountry the birthCountry to set
	 */
	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	/**
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @param foot the foot to set
	 */
	public void setFoot(String foot) {
		this.foot = foot;
	}

	/**
	 * @param pictureUrl the pictureUrl to set
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public List<Career> getCareer() {
		return career;
	}

	public void setCareer(List<Career> career) {
		this.career = career;
	}
}
