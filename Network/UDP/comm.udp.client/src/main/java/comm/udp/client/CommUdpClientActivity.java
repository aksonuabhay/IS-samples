package comm.udp.client;

import interactivespaces.activity.impl.BaseActivity;
import interactivespaces.service.comm.network.client.UdpClientNetworkCommunicationEndpoint;
import interactivespaces.service.comm.network.client.UdpClientNetworkCommunicationEndpointListener;
import interactivespaces.service.comm.network.client.UdpClientNetworkCommunicationEndpointService;
import java.net.InetSocketAddress;

/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommUdpClientActivity extends BaseActivity {

	private UdpClientNetworkCommunicationEndpoint udpClient;
	private InetSocketAddress udpServerAddress;
	
	
    @Override
    public void onActivitySetup() {
        getLog().info("Activity comm.udp.client setup");
        UdpClientNetworkCommunicationEndpointService udpClientService = getSpaceEnvironment().getServiceRegistry().getRequiredService(UdpClientNetworkCommunicationEndpointService.SERVICE_NAME);
        String UdpServerHost = getConfiguration().getPropertyString("space.comm.udp.server.host","127.0.0.1");
        int udpServerPort =getConfiguration().getRequiredPropertyInteger("space.comm.udp.server.port");
        udpClient = udpClientService.newClient(getLog());
        udpClient.addListener(new UdpClientNetworkCommunicationEndpointListener() {
			
			public void onUdpResponse(UdpClientNetworkCommunicationEndpoint client,
					byte[] data, InetSocketAddress address) {
				getLog().info("From : "+ address.toString()+" Client received : " + data.toString());
				
			}
		});
        addManagedResource(udpClient);
        udpServerAddress = new InetSocketAddress(UdpServerHost, udpServerPort);
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
