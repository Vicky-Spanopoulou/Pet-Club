package cb13.project.repository;

import cb13.project.entities.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails ,Long> {

    InvoiceDetails findInvoiceDetailsByUserId(Long userid);
}
