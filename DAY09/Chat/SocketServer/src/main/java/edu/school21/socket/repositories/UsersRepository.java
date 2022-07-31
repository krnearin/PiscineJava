package edu.school21.socket.repositories;

import edu.school21.socket.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User>  {
    Optional<User> findByEmail(String email);
}
