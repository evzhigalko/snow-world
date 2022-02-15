package by.zhigalko.snow.world.dao.user;

import by.zhigalko.snow.world.dao.BaseDaoSaveEntity;
import by.zhigalko.snow.world.entity.User;
import jakarta.persistence.NoResultException;
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
   boolean findByUsernameAndEmail(String username, String email);

    /**
     * Find all users
     * @return List of users {@link List<User>}
     */
   List<User> findAllUsers();
}
