package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;
import tn.esprit.devops_project.entities.Invoice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
@SpringBootTest(classes = DevOps_ProjectSpringBootApplication.class)
public class OperatorServiceImplTest {

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllOperators() {
        List<Operator> operators = new ArrayList<>();
        // Add some test data to the list
        Operator operator1 = new Operator();
        operator1.setIdOperateur(1L);
        operator1.setFname("Operator1");
        operator1.setLname("Lastname1");
        operator1.setPassword("password1");

        Operator operator2 = new Operator();
        operator2.setIdOperateur(2L);
        operator2.setFname("Operator2");
        operator2.setLname("Lastname2");
        operator2.setPassword("password2");

        operators.add(operator1);
        operators.add(operator2);

        when(operatorRepository.findAll()).thenReturn(operators);

        List<Operator> retrievedOperators = operatorService.retrieveAllOperators();

        // Verify that the service method returns the expected data
        assertEquals(2, retrievedOperators.size());
    }

    @Test
    public void testAddOperator() {
        Operator newOperator = new Operator();
        newOperator.setIdOperateur(1L);
        newOperator.setFname("NewOperator");
        newOperator.setLname("NewLastname");
        newOperator.setPassword("newpassword");

        when(operatorRepository.save(newOperator)).thenReturn(newOperator);

        Operator addedOperator = operatorService.addOperator(newOperator);

        // Verify that the service method returns the added operator
        assertEquals(newOperator, addedOperator);
    }

    @Test
    public void testDeleteOperator() {
        Long operatorIdToDelete = 1L;

        // Mock the deleteById method
        doNothing().when(operatorRepository).deleteById(operatorIdToDelete);

        operatorService.deleteOperator(operatorIdToDelete);

        // Verify that the deleteById method was called with the correct operator ID
        verify(operatorRepository).deleteById(operatorIdToDelete);
    }

    @Test
    public void testUpdateOperator() {
        Operator updatedOperator = new Operator();
        updatedOperator.setIdOperateur(1L);
        updatedOperator.setFname("UpdatedOperator");
        updatedOperator.setLname("UpdatedLastname");
        updatedOperator.setPassword("updatedpassword");

        when(operatorRepository.save(updatedOperator)).thenReturn(updatedOperator);

        Operator result = operatorService.updateOperator(updatedOperator);

        // Verify that the service method returns the updated operator
        assertEquals(updatedOperator, result);
    }

    @Test
    public void testRetrieveOperator() {
        Long operatorIdToRetrieve = 1L;
        Operator operator = new Operator();
        operator.setIdOperateur(operatorIdToRetrieve);
        operator.setFname("RetrievedOperator");
        operator.setLname("RetrievedLastname");
        operator.setPassword("retrievedpassword");

        when(operatorRepository.findById(operatorIdToRetrieve)).thenReturn(Optional.of(operator));

        Operator retrievedOperator = operatorService.retrieveOperator(operatorIdToRetrieve);

        // Verify that the service method returns the correct operator
        assertEquals(operator, retrievedOperator);
    }

    @Test
    public void testRetrieveOperator_NotFound() {
        Long operatorIdToRetrieve = 1L;

        when(operatorRepository.findById(operatorIdToRetrieve)).thenReturn(Optional.empty());

        // Verify that the service method throws a NullPointerException when the operator is not found
        assertThrows(NullPointerException.class, () -> operatorService.retrieveOperator(operatorIdToRetrieve));
    }

    // The rest of the test methods remain the same
}