package sam.struts.crud.dao;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sam.struts.crud.model.Country;

public class InMemoryPersonSupportDao implements PersonSupportDao {
	private static final Logger LOG = LogManager.getLogger(InMemoryPersonSupportDao.class.getSimpleName());
	
    private static final Country[] countries = {new Country("ES", "Spain"),
            new Country("IE", "Ireland"),
            new Country("IT", "Italy"),
            new Country("PL", "Poland"),
            new Country("US", "Usa") };
    private static final Map<String, Country> countriesMap = Arrays.stream(countries).collect(Collectors.toMap(Country::getCountryId, c -> c));
    private static final String[] genders = {"male", "female"};
    private static final String[] sports = {"football", "baseball", "basketball", "mtb" };
    private static final String [] carModelsAvailable = {"Ford", "Chrysler", "Toyota", "Nissan", "Seat"};
	

	@Override
	public Country[] getCountries() {
		return countries;
	}

	@Override
	public String[] getCarModels() {
		return carModelsAvailable;
	}
	@Override
	public String[] getSports() {
		return sports;
	}
	@Override
	public String[] getGenders() {
		return genders;
	}

	public static Country getCountry(String countryId) {
		return countriesMap.get(countryId);
	}
	
}
