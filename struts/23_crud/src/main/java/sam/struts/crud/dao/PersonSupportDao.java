package sam.struts.crud.dao;

import sam.struts.crud.model.Country;

public interface PersonSupportDao {
	Country[] getCountries();
	String[] getCarModels();
	String[] getSports();
	String[] getGenders();
}
