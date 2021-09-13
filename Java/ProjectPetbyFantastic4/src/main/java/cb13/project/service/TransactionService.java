package cb13.project.service;



import cb13.project.entities.Transaction;

import java.util.List;

public interface TransactionService {
    
    Transaction findById(Long transactionId);
    
    List<Transaction> findAllTransactions();
    
    Transaction saveTransaction (Transaction transaction);
    
}
