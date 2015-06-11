package comm.udp.server;

import interactivespaces.activity.impl.BaseActivity;
import interactivespaces.service.comm.network.server.UdpServerNetworkCommunicationEndpoint;
import interactivespaces.service.comm.network.server.UdpServerNetworkCommunicationEndpointListener; 
import interactivespaces.service.comm.network.server.UdpServerNetworkCommunicationEndpointService;
import interactivespaces.service.comm.network.server.UdpServerRequest;

/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommUdpServerActivity extends BaseActivity {

	UdpServerNetworkCommunicationEndpoint udpServer;
    @Override
    public void onActivitySetup() {
        getLog().info("Activity comm.udp.server setup");
        UdpServerNetworkCommunicationEndpointService udpServerService = getSpaceEnvironment().getServiceRegistry().getRequiredService(UdpServerNetworkCommunicationEndpointService.SERVICE_NAME);
        int port = getConfiguration().getRequiredPropertyInteger("space.comm.udp.server.port");
        udpServer = udpServerService.newServer(port, getLog());
        udpServer.addListener(new UdpServerNetworkCommunicationEndpointListener() {
			
			public void onUdpRequest(UdpServerNetworkCommunicationEndpoint server,
					UdpServerRequest req) {
				String message = new String(req.getRequest());
				getLog().info(req.getRemoteAddress() + "<- client sent server -> " +message);
				req.writeResponse("Server recieved your message and is replying".getBytes());
			}
		});
        addManagedResource(udpServer);
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity comm.udp.server startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity comm.udp.server post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity comm.udp.server activate");
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity comm.udp.server deactivate");
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity comm.udp.server pre shutdown");
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity comm.udp.server shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity comm.udp.server cleanup");
    }
}
