package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(classes = DevOps_ProjectSpringBootApplication.class)
public class SupplierServiceImplTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllSuppliers() {
        // Create a list of suppliers for testing
        List<Supplier> suppliers = new ArrayList<>();
        List<Supplier> suppliers1 = new ArrayList<>();
        Supplier supplier1 = new Supplier();
        Supplier supplier2 = new Supplier();
        suppliers.add(supplier1);
        suppliers.add(supplier2);

        // Mock the behavior of supplierRepository.findAll() to return the list of suppliers
        when(supplierRepository.findAll()).thenReturn(suppliers);

        // Call the method under test
        List<Supplier> retrievedSuppliers = supplierService.retrieveAllSuppliers();

        // Verify that the method returned the expected list of suppliers
        assertEquals(2, retrievedSuppliers.size());
    }

    @Test
    public void testAddSupplier() {
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);


        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        assertEquals(1L, addedSupplier.getIdSupplier());
    }
}

