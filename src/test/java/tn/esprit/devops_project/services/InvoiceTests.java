package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.*;

public class InvoiceTests {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository; // Corrected the variable name to match the @Mock annotation

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        when(invoiceRepository.findAll()).thenReturn(invoices);

        List<Invoice> retrievedInvoices = invoiceService.retrieveAllInvoices();

        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    public void testCancelInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        invoiceService.cancelInvoice(invoiceId);

        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    public void testRetrieveInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        Invoice retrievedInvoice = invoiceService.retrieveInvoice(invoiceId);

        verify(invoiceRepository, times(1)).findById(invoiceId);
    }

    @Test
    public void testGetInvoicesBySupplier() {
        Long idSupplier = 1L;
        Supplier supplier = mock(Supplier.class);

        Set<Invoice> invoices = new HashSet<>();

        when(supplierRepository.findById(idSupplier)).thenReturn(Optional.of(supplier));
        when(supplier.getInvoices()).thenReturn((invoices));

        Set<Invoice> retrievedInvoices =  invoiceService.getInvoicesBySupplier(idSupplier);

        verify(supplierRepository, times(1)).findById(idSupplier);
    }

}