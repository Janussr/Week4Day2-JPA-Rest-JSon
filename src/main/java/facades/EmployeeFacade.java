package facades;

import dtos.EmployeeDTO;
import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import utils.EMF_Creator;

public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeeDTO getById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {

        Employee employee = em.find(Employee.class, id);
        return new EmployeeDTO(employee);

        }finally {
            em.close();
            emf.close();
        }

    }

    public void create(EmployeeDTO e) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee(e.getName(), e.getAddress(), e.getSalary()));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    /**
     * since im casting a wildcard
     * I have to tell the compiler that I believe the code to be safe and won't cause unexpected exceptions
     */
    @SuppressWarnings("unchecked")
    public List<EmployeeDTO> getEmployeeByName() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            TypedQuery<Employee> typedQuery = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            List<Employee> employee = typedQuery.getResultList();
            em.getTransaction().commit();
            return (List<EmployeeDTO>) (List<?>) employee;
        } finally {
            em.close();
        }

    }

    @SuppressWarnings("unchecked")
    public List<EmployeeDTO> getAllEmployees() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Employee> typedQuery = em.createQuery(" SELECT e FROM Employee e ", Employee.class);
            List<Employee> employee = typedQuery.getResultList();
            em.getTransaction().commit();
            return (List<EmployeeDTO>) (List<?>) employee;
        } finally {
            em.close();
            emf.close();
        }
    }


    @SuppressWarnings("unchecked")
    public EmployeeDTO getEmployeeWithHighestSalary() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> typedQuery = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary)FROM Employee e) ", Employee.class);
            Employee employee = typedQuery.getSingleResult();
            return new EmployeeDTO(employee);

        } finally {
            em.close();
            emf.close();
        }

    }

}


//ALREADY HAVE A BETTER METHOD ABOVE CALLED "getById"
//getEmployeeById
// public EmployeeDTO getEmployeeById() {
//     EntityManager em = emf.createEntityManager();
//     try {
//         em.getTransaction().begin();
//         TypedQuery<Employee> typedQuery = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
//         Employee e = typedQuery.getSingleResult();
//         System.out.println(e);
//     } finally {
//         em.close();
//     }
//     return null;
// }