package cb13.project.service;

import cb13.project.entities.InvoiceDetails;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceDetailsService {

    InvoiceDetails saveInvoiceDetails(InvoiceDetails invoiceDetails);

    List<InvoiceDetails> findAllInvoiceDetails();
    
    InvoiceDetails findById(Long id);


    InvoiceDetails findInvoiceByUserId(Long id);

    InvoiceDetails updateInvoiceDetails(InvoiceDetails invoiceDetails);

    void deleteInvoiceDetailsById(Long invoiceDetailsId);
}
