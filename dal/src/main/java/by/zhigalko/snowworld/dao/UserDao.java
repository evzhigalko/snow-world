package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.entity.User;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Data Access Object for User.
 */
public interface UserDao extends BaseDaoSaveEntity<User> {
    /**
     * Find user by username and password .
     * @param username username
     * @param password password
     * @return user {@link User}
     * @throws NoResultException error when user wasn't found
     */
    User findByUsernameAndPassword(String username, String password) throws NoResultException;

    /**
     * Find user by username and email.
     * @param username username
     * @param email email
     * @return <ul>
     *     <li>{@code true} if found</li>
     *     <li>{@code false} if wasn't found</li>
     * </ul>
     */
   User findByUsernameAndEmail(String username, String email);

    /**
     * Find all users
     * @return List of users {@link List<User>}
     */
   List<User> findAllUsers();
}
