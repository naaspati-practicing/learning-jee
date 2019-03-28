package sam.learn.ejb.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="WINE_ITEM")
@NamedQueries({
	@NamedQuery(name = Wine.FIND_ALL, query = "select o from Wine o"),
	@NamedQuery(name = Wine.FIND_BY_YEAR, query = "select wine from Wine wine where wine.year = :year"),
	@NamedQuery(name = Wine.FIND_BY_COUNTRY, query = "select wine from Wine wine where wine.country = :country"),
	@NamedQuery(name = Wine.FIND_BY_VARIETAL, query = "select wine from Wine wine where wine.varietal = :varietal")
})
public class Wine extends Item {
	private static final long serialVersionUID = -590541255916852777L;

	public static final String FIND_ALL = "Wine.findAll";
	public static final String FIND_BY_YEAR = "Wine.findByYear";
	public static final String FIND_BY_COUNTRY = "Wine.findByCountry";
	public static final String FIND_BY_VARIETAL = "Wine.findByVarietal";

	@Column(length = 4000) private String varietal;
	@Column(length = 4000) private String country;
	@Column(length = 4000) private String region;

	@Column(name= "YEAR_") private int year;

	private int rating;

	public String getVarietal() {
		return varietal;
	}

	public void setVarietal(String varietal) {
		this.varietal = varietal;
	}

	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }

	public String getRegion() { return region; }
	public void setRegion(String region) { this.region = region; }

	public int getYear() { return year; }
	public void setYear(int year) { this.year = year; }

	public int getRating() { return rating; }
	public void setRating(int rating) { this.rating = rating; }
}
