package hu.tigra.itnap.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import hu.tigra.itnap.dao.FileDao;

@Path("/message")
public class MessageRestService {
	
	private FileDao fileDao = new FileDao();

	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) {
		
		String result = "Restful example : " + msg;
		boolean open = fileDao.checkConnection();
		result = "/n" + "checkConnection : " + open;
		return Response.status(200).entity(result).build();

	}

}