/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.EmployeeDTO;
import dtos.RenameMeDTO;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

import java.util.List;

/**
 *
 * @author tha
 */
public class EmployeePopulator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
      //  facade.create(new EmployeeDTO("janus", "adress", 123));
       List<EmployeeDTO> employeeDTOS = facade.getAllEmployees();

        for ( EmployeeDTO e:employeeDTOS  ) {
            System.out.println(e.getName());

        }
    }

    public static void main(String[] args) {
       populate();

    }
}
