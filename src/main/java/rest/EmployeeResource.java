package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.EmployeeFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;

@Path("/employee")
public class EmployeeResource {
    private static final EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    private static final EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllEmployees() {
        return gson.toJson(facade.getAllEmployees());
    }



    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String getMovieById(@PathParam("id") int id) {
        return gson.toJson(facade.getById(id));
    }



//:TODO NEEDS THESE TWO ENDPOINT BELOW + Test
//api/employee/highestpaid
///api/employee/name/{name}
}