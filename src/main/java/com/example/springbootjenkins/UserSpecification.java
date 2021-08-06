package com.example.springbootjenkins;

import static org.springframework.data.jpa.domain.Specification.where;
import javax.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import net.opentrends.reservation.model.Location;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.opentrends.reservation.model.User;

/**
 * @author sujith
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSpecification {

	private static final String WILD_CARD = "%";

	// Below values need to update with Hibernate modeljen
	// TODO
	private static final String FIRST_NAME = "firstName";

	private static final String LAST_NAME = "lastName";

	private static final String USERNAME = "username";

	private static final String EMAIL = "email";

	private static final String LOCATION = "location";

	private static final String LOCATION_ID = "id";

	private static final String EMPLOYEE_ID = "employeeId";

	public static Specification<User> getUsers(String searchParam) {
		return (root, query, criteriaBuilder) -> where(usernameContains(searchParam)).or(firstNameContains(searchParam))
				.or(lastNameContains(searchParam)).or(emailContains(searchParam)).or(employeeIdContains(searchParam))
				.toPredicate(root, query, criteriaBuilder);

	}

	public static Specification<User> getUsersByLocation(String searchParam, Integer locationId) {
		return (root, query, criteriaBuilder) -> where(searchByLocation(locationId))
				.and(usernameContains(searchParam).or(firstNameContains(searchParam)).or(lastNameContains(searchParam))
						.or(emailContains(searchParam)).or(employeeIdContains(searchParam)))
				.toPredicate(root, query, criteriaBuilder);

	}

	private static Specification<User> lastNameContains(String lastName) {
		return userAttributeContains(LAST_NAME, lastName);
	}

	private static Specification<User> firstNameContains(String firstName) {
		return userAttributeContains(FIRST_NAME, firstName);
	}

	private static Specification<User> usernameContains(String username) {
		return userAttributeContains(USERNAME, username);
	}

	private static Specification<User> emailContains(String email) {
		return userAttributeContains(EMAIL, email);
	}

	private static Specification<User> employeeIdContains(String employeeId) {
		return userAttributeContains(EMPLOYEE_ID, employeeId);
	}

	// For Location
	private static Specification<User> searchByLocation(Integer locationId) {
		return (root, query, criteriaBuilder) -> {

			Join<User, Location> userJoin = root.join(LOCATION);
			return criteriaBuilder.equal(userJoin.get(LOCATION_ID), locationId);
		};
	}

	private static Specification<User> userAttributeContains(String attribute, String value) {
		return (root, query, cb) -> {
			if (value == null) {
				return null;
			}
			return cb.like(cb.lower(root.get(attribute)), containsLowerCase(value));
		};

	}

	private static String containsLowerCase(String searchField) {
		return WILD_CARD + searchField + WILD_CARD;
	}
}