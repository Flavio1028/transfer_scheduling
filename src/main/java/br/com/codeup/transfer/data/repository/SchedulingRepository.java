package br.com.codeup.transfer.data.repository;

import br.com.codeup.transfer.data.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

}