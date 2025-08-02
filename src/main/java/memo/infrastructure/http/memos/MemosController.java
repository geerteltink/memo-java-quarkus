package memo.infrastructure.http.memos;

import memo.application.MemoResponse;
import memo.application.creatememo.CreateMemoCommand;
import memo.application.creatememo.CreateMemoHandler;
import memo.application.deletememo.DeleteMemoCommand;
import memo.application.deletememo.DeleteMemoHandler;
import memo.application.findmemo.FindMemoHandler;
import memo.application.findmemo.FindMemoQuery;
import memo.application.findmemos.FindMemosHandler;
import memo.application.findmemos.FindMemosQuery;
import memo.application.updatememo.UpdateMemoCommand;
import memo.application.updatememo.UpdateMemoHandler;

import java.util.UUID;


import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/memos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemosController {
    @Inject
    private FindMemosHandler findMemosHandler;
    @Inject
    private CreateMemoHandler createMemoHandler;
    @Inject
    private FindMemoHandler findMemoHandler;
    @Inject
    private UpdateMemoHandler updateMemoHandler;
    @Inject
    private DeleteMemoHandler deleteMemoHandler;

    @GET
    public Response findMemos() {
        MemoResponse[] memos = findMemosHandler.handle(new FindMemosQuery(1, 1)).toArray(new MemoResponse[0]);
        return Response.ok().entity(memos).build();
    }

    @GET
    @Path("/{id}")
    public Response findMemo(@PathParam("id") UUID id) {
        MemoResponse memo = findMemoHandler.handle(new FindMemoQuery(id));
        return Response.ok().entity(memo).build();
    }

    @POST
    public Response createMemo(@Valid CreateMemoRequest request) {
        MemoResponse memo = createMemoHandler.handle(new CreateMemoCommand(request.id(), request.content()));
        return Response.status(Response.Status.CREATED).entity(memo).build();
    }

    @PATCH
    @Path("/{id}")
    public Response updateMemo(@PathParam("id") UUID id, @Valid UpdateMemoRequest request) {
        MemoResponse memo = updateMemoHandler.handle(new UpdateMemoCommand(id, request.content()));
        return Response.ok().entity(memo).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMemo(@PathParam("id") UUID id) {
        deleteMemoHandler.handle(new DeleteMemoCommand(id));
        return Response.noContent().build();
    }
}
