package ru.papont.library.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.papont.library.entity.Client;

import java.util.Optional;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    Optional<Client> findByEmailAndPhone(String email, String phone);

    @Query("select b from Client b where lower(b.firstName) like :query " +
            "or lower(b.lastName) like :query " +
            "or lower(b.email) like :query " +
            "or lower(b.phone) like :query")
    Page<Client> findByQuery(String query, Pageable pageable);
}
