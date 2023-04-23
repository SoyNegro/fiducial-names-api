package dev.coffeecult.Names.repository;

import dev.coffeecult.Names.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NamesRepository extends JpaRepository<Name,Long> {
    boolean existsByFirstName(String firstName);

    void deleteNameByFirstName(String firstName);
}
