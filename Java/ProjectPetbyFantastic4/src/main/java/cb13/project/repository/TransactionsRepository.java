package cb13.project.repository;

import cb13.project.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transaction,Long> {
   
}
