package comm.udp.client;

import interactivespaces.activity.impl.BaseActivity;
import interactivespaces.service.comm.network.client.UdpClientNetworkCommunicationEndpoint;
import interactivespaces.service.comm.network.client.UdpClientNetworkCommunicationEndpointListener;
import interactivespaces.service.comm.network.client.UdpClientNetworkCommunicationEndpointService;
import interactivespaces.service.comm.network.server.UdpServerNetworkCommunicationEndpoint;
import interactivespaces.service.comm.network.server.UdpServerNetworkCommunicationEndpointListener;
import interactivespaces.service.comm.network.server.UdpServerNetworkCommunicationEndpointService;
import interactivespaces.service.comm.network.server.UdpServerRequest;

import java.net.InetSocketAddress;

/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommUdpClientActivity extends BaseActivity {

	private UdpClientNetworkCommunicationEndpoint udpClient;
	private InetSocketAddress udpServerAddress;
	
	UdpServerNetworkCommunicationEndpoint udpServer;
	
    @Override
    public void onActivitySetup() {
        getLog().info("Activity comm.udp.client setup");
        UdpClientNetworkCommunicationEndpointService udpClientService = getSpaceEnvironment().getServiceRegistry().getRequiredService(UdpClientNetworkCommunicationEndpointService.SERVICE_NAME);
        String UdpServerHost = getConfiguration().getPropertyString("space.comm.udp.server.host","192.168.107.128");
        int udpServerPort =getConfiguration().getRequiredPropertyInteger("space.comm.udp.server.port");
        udpClient = udpClientService.newClient(getLog());
        udpClient.addListener(new UdpClientNetworkCommunicationEndpointListener() {
			
			public void onUdpResponse(UdpClientNetworkCommunicationEndpoint client,
					byte[] data, InetSocketAddress address) {
				String message = new String(data);
				getLog().info("Client : "+"From : "+ address.toString()+" Client received : " + message);
				
			}
		});
        addManagedResource(udpClient);
        udpServerAddress = new InetSocketAddress(UdpServerHost, udpServerPort);
        
        UdpServerNetworkCommunicationEndpointService udpServerService = getSpaceEnvironment().getServiceRegistry().getRequiredService(UdpServerNetworkCommunicationEndpointService.SERVICE_NAME);
        
        udpServer = udpServerService.newServer(udpServerPort, getLog());
        udpServer.addListener(new UdpServerNetworkCommunicationEndpointListener() {
			
			public void onUdpRequest(UdpServerNetworkCommunicationEndpoint server,
					UdpServerRequest req) {
				String message = new String(req.getRequest());
				getLog().info(req.getRemoteAddress() + "<- client sent server -> " +message);
				req.writeResponse("Client : Server recieved your message and is replying".getBytes());
			}
		});
        addManagedResource(udpServer);
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity comm.udp.client startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity comm.udp.client post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity comm.udp.client activate");
        udpClient.write(udpServerAddress, "Client activated".getBytes());
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity comm.udp.client deactivate");
        udpClient.write(udpServerAddress, "Client deactivated".getBytes());
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity comm.udp.client pre shutdown");
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity comm.udp.client shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity comm.udp.client cleanup");
    }
}
