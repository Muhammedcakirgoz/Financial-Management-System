package com.ProjeDeneme.Project.repository;

import com.ProjeDeneme.Project.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository, User entity'si için MongoDB repository'sidir.
 * Kullanıcı verilerine erişim sağlamak için gerekli CRUD işlemlerini içerir.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Kullanıcı adını kullanarak kullanıcıyı arar.
     *
     * @param username Kullanıcı adı
     * @return Eşleşen kullanıcı varsa Optional<User>, yoksa Optional.empty()
     */
    Optional<User> findByUsername(String username);
}
