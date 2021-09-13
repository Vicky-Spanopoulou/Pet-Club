package cb13.project.service.impl;

import cb13.project.entities.InvoiceDetails;
import cb13.project.repository.InvoiceDetailsRepository;
import cb13.project.service.InvoiceDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    @Override
    public InvoiceDetails saveInvoiceDetails(InvoiceDetails invoiceDetails) {
        return invoiceDetailsRepository.save(invoiceDetails);
    }

    @Override
    public List<InvoiceDetails> findAllInvoiceDetails() {
        return invoiceDetailsRepository.findAll();
    }

    @Override
    public InvoiceDetails updateInvoiceDetails(InvoiceDetails invoiceDetails) {
        return invoiceDetailsRepository.save(invoiceDetails);
    }

    @Override
    public void deleteInvoiceDetailsById(Long invoiceDetailsId) {
        invoiceDetailsRepository.deleteById(invoiceDetailsId);
    }

    @Override
    public InvoiceDetails findById(Long id) {
        return invoiceDetailsRepository.getById(id);
    }

    @Override
    public InvoiceDetails findInvoiceByUserId(Long id) {
        return invoiceDetailsRepository.findInvoiceDetailsByUserId(id);
    }


}
