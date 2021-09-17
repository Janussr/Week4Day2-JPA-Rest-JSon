package facades;

import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFacadeTest {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);

    @Test
    public void getEmployeeByIdTest(){

        String expected = "janus";
        String actual = facade.getById(1).getName();
        assertEquals(expected, actual);


    }

}