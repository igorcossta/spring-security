package io.igorcossta.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
            SELECT t FROM Token t INNER JOIN User u on t.user.id = u.id
            WHERE u.id = :userId AND (t.isExpired = false OR t.isRevoked = false)
                """)
    List<Token> findAllValidTokensByUser(Integer userId);
    Optional<Token> findByToken(String token);

}
