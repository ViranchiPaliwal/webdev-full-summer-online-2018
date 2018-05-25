package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.User;
public interface UserRepository extends CrudRepository<User, Integer> {
	/***
	 * Query database and finds list of users with provided user name
	 * @param username	user name parameter for which query run
	 * @return list of user with provided username
	 */
	@Query("SELECT u FROM User u WHERE"
			+ " u.username=:username")
	Iterable<User> findUserByUsername(
			@Param("username") String username);
	
	
	/***
	 * Query database and finds list of users with provided credentials 
	 * @param username	user name parameter for which query run
	 * @param password password parameter corresponding to username
	 * @return list of user with provided credentials
	 */
	@Query("SELECT u FROM User u WHERE"
			+ " u.username=:username"
			+ " AND u.password=:password")
	Iterable<User> findUserByCredentials(
			@Param("username") String username,
			@Param("password") String password);
}

