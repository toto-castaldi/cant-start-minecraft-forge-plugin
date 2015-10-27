package it.toto.services.awsJenkins.resource;

import it.toto.services.awsJenkins.ApiPath;
import it.toto.services.awsJenkins.Semaphore;
import it.toto.services.awsJenkins.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by toto on 05/12/14.
 */
@Path(ApiPath.SEMAPHORE)
@Slf4j
public class SemaphoreResource {

    private final ApiResponse apiResponse;
    private final Semaphore semaphore;

    @Inject
    public SemaphoreResource(
            ApiResponse apiResponse,
            Semaphore semaphore
    ) {
        this.apiResponse = apiResponse;
        this.semaphore = semaphore;
    }

    @POST
    @Path(ApiPath.ON)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response on(@Context HttpServletRequest httpServletRequest) {
        semaphore.on();
        return apiResponse.createdReturns(httpServletRequest, ApiPath.SEMAPHORE);
    }

    @POST
    @Path(ApiPath.OFF)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response off(@Context HttpServletRequest httpServletRequest) {
        semaphore.off();
        return apiResponse.createdReturns(httpServletRequest, ApiPath.SEMAPHORE);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response status(@Context HttpServletRequest httpServletRequest) {
        return apiResponse.ok(semaphore.status());
    }

}