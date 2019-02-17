package org.glassfish.jersey.examples.entityfiltering.selectable.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.examples.entityfiltering.selectable.domain.Address;
import org.glassfish.jersey.examples.entityfiltering.selectable.domain.Person;
import org.glassfish.jersey.examples.entityfiltering.selectable.domain.PhoneNumber;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

	@GET
	@Path("{id}")
	public Person getPerson(@PathParam("id") int id) {
		final Person person = new Person();
        person.setGivenName("Andrew");
        person.setFamilyName("Dowd");
        person.setHonorificPrefix("Mr.");
        person.setHonorificSuffix("PhD");
        person.setRegion("1st Level Region");

        final ArrayList<Address> addresses = new ArrayList<>();
        person.setAddresses(addresses);

        final Address address = new Address();
        addresses.add(address);
        address.setRegion("2nd Level Region");
        address.setStreet("1234 fake st.");
        address.setPhoneNumber(new PhoneNumber());
        address.getPhoneNumber().setNumber("867-5309");
        address.getPhoneNumber().setAreaCode("540");

        person.setPhoneNumbers(new HashMap<String, PhoneNumber>());
        final PhoneNumber number = new PhoneNumber();
        number.setAreaCode("804");
        number.setNumber("867-5309");
        person.getPhoneNumbers().put("HOME", number);

        return person;
	}
	@GET
	@Path("{id}/address")
	public Address getAddress(@PathParam("id") int id) {
		return Optional.of(getPerson(id).getAddresses())
				.filter(s -> !s.isEmpty())
				.map(s -> s.get(0))
				.orElse(null);
	}

}
