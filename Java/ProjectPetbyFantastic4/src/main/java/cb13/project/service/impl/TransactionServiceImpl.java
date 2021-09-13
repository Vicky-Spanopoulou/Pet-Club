package cb13.project.service.impl;


import cb13.project.entities.Transaction;
import cb13.project.repository.TransactionsRepository;
import cb13.project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import cb13.project.service.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;


    @Override
    public List<Transaction> findAllTransactions() {
        
        return transactionsRepository.findAll();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {

        return transactionsRepository.save(transaction);
    }

    @Override
    public Transaction findById(Long transactionId) {
        return transactionsRepository.getById(transactionId);
    }
    
   
}
