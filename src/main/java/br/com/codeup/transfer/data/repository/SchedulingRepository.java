package br.com.codeup.transfer.data.repository;

import br.com.codeup.transfer.data.entity.Scheduling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    @Query("SELECT s FROM Scheduling s JOIN s.originAccount a WHERE a.accountNumber = :originAccount")
    Page<Scheduling> findBYOriginAccount(@Param("originAccount") String originAccount, Pageable page);

}