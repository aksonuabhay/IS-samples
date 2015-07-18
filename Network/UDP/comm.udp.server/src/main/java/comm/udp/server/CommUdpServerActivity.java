package comm.udp.server;

import java.net.InetSocketAddress;

import interactivespaces.activity.impl.BaseActivity;
import interactivespaces.service.comm.network.client.UdpClientNetworkCommunicationEndpoint;
import interactivespaces.service.comm.network.client.UdpClientNetworkCommunicationEndpointListener;
import interactivespaces.service.comm.network.client.UdpClientNetworkCommunicationEndpointService;
import interactivespaces.service.comm.network.server.UdpServerNetworkCommunicationEndpoint;
import interactivespaces.service.comm.network.server.UdpServerNetworkCommunicationEndpointListener; 
import interactivespaces.service.comm.network.server.UdpServerNetworkCommunicationEndpointService;
import interactivespaces.service.comm.network.server.UdpServerRequest;

/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommUdpServerActivity extends BaseActivity {

	UdpServerNetworkCommunicationEndpoint udpServer;
	
	private UdpClientNetworkCommunicationEndpoint udpClient;
	private InetSocketAddress udpServerAddress;
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
				req.writeResponse("SERVER : Server recieved your message and is replying".getBytes());
			}
		});
        addManagedResource(udpServer);
        
        UdpClientNetworkCommunicationEndpointService udpClientService = getSpaceEnvironment().getServiceRegistry().getRequiredService(UdpClientNetworkCommunicationEndpointService.SERVICE_NAME);
        String UdpServerHost = getConfiguration().getPropertyString("space.comm.udp.server.host","192.168.107.1");
        int udpServerPort =getConfiguration().getRequiredPropertyInteger("space.comm.udp.server.port");
        udpClient = udpClientService.newClient(getLog());
        udpClient.addListener(new UdpClientNetworkCommunicationEndpointListener() {
			
			public void onUdpResponse(UdpClientNetworkCommunicationEndpoint client,
					byte[] data, InetSocketAddress address) {
				String message = new String(data);
				getLog().info("SERVER : Client : "+"From : "+ address.toString()+" Client received : " + message);
				
			}
		});
        addManagedResource(udpClient);
        udpServerAddress = new InetSocketAddress(UdpServerHost, udpServerPort);
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
        udpClient.write(udpServerAddress, "SERVER Client activated".getBytes());
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity comm.udp.server deactivate");
        udpClient.write(udpServerAddress, "SERVER Client deactivated".getBytes());
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
