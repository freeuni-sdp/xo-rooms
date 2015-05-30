import static org.junit.Assert.*;
import ge.edu.freeuni.sdp.xo.rooms.PingService;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

public class TestPingService extends JerseyTest{

	@Override
	protected Application configure() {
		return new ResourceConfig(PingService.class);
	}
	
	@Test
	public void testGet() {
		Response actual = target("ping").request().get();
		assertEquals(
		        Response.Status.OK.getStatusCode(),
		        actual.getStatus());
	}

}
