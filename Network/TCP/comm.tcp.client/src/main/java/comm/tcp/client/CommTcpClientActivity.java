package comm.tcp.client;

import interactivespaces.activity.impl.BaseActivity;
import interactivespaces.SimpleInteractiveSpacesException;
import interactivespaces.service.comm.network.client.TcpClientNetworkCommunicationEndpoint;
import interactivespaces.service.comm.network.client.TcpClientNetworkCommunicationEndpointListener;
import interactivespaces.service.comm.network.client.TcpClientNetworkCommunicationEndpointService;
import com.google.common.base.Charsets;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommTcpClientActivity extends BaseActivity {
	
	public static final byte[][] MESSAGE_TERMINATORS = new byte[][] { new byte[] { '\n' } };
	private TcpClientNetworkCommunicationEndpoint<String> tcpClient;
	
    @Override
    public void onActivitySetup() {
        getLog().info("Activity comm.tcp.client setup");
        TcpClientNetworkCommunicationEndpointService commTcpClientService=getSpaceEnvironment().getServiceRegistry().getRequiredService(TcpClientNetworkCommunicationEndpointService.SERVICE_NAME);
        String remoteTcpHost = getConfiguration().getPropertyString("space.comm.tcp.server.host","127.0.0.1");
        int remoteTcpPort = getConfiguration().getRequiredPropertyInteger("space.comm.tcp.server.port");
        
        try 
        {
        	InetAddress remoteTcpHostAdress = InetAddress.getByName(remoteTcpHost);
        	tcpClient = commTcpClientService.newStringClient(MESSAGE_TERMINATORS, Charsets.UTF_8, remoteTcpHostAdress, remoteTcpPort, getLog());
        	tcpClient.addListener(new TcpClientNetworkCommunicationEndpointListener<String>() {
				@Override
				public void onTcpResponse(
						TcpClientNetworkCommunicationEndpoint<String> client, String msg) {
						getLog().info(String.format("Server Responded %s" , msg));
					
				}
			});
        	 addManagedResource(tcpClient);
        }
        catch(UnknownHostException e)
        {
        	throw new SimpleInteractiveSpacesException(String.format("Could not get host %s", remoteTcpHost), e);
        }
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity comm.tcp.client startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity comm.tcp.client post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity comm.tcp.client activate");
        tcpClient.write("Tcp Client Activated\n");
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity comm.tcp.client deactivate");
        tcpClient.write("Tcp Client Deactivated\n");
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity comm.tcp.client pre shutdown");
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity comm.tcp.client shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity comm.tcp.client cleanup");
    }
}
