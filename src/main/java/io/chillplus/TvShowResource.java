package io.chillplus;

import lombok.NoArgsConstructor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import org.jboss.logging.Logger;

@NoArgsConstructor
@Path("/api/tv")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TvShowResource {
    private static long nextId = 0;
    private static List<TvShow> tvShows = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(tvShows).build();
    }

    @POST
    public Response create(CreateTvShowRequest createTvShowRequest) {
        if (createTvShowRequest.getTitle() == null
                || createTvShowRequest.getTitle().isEmpty()) {
            throw new WebApplicationException("No title specified. A title is required", 400);
        }
        TvShow tvShow = new TvShow(nextId++, createTvShowRequest.title, createTvShowRequest.category);
        tvShows.add(tvShow);
        return Response.ok(tvShow).status(201).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneById(@PathParam("id") long id) {
        TvShow tvShow = tvShows.stream().filter(t -> t.id == id).findFirst().orElse(null);
        return tvShow != null ? Response.ok(tvShow).build() : Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    public Response deleteAll() {
        tvShows.clear();
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") long id) {
        tvShows = tvShows.stream().filter(t -> t.id != id).collect(Collectors.toList());
        return Response.ok().build();
    }
}
