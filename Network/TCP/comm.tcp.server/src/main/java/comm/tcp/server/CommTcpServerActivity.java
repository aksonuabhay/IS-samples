package comm.tcp.server;

import interactivespaces.activity.impl.BaseActivity;
import interactivespaces.service.comm.network.server.TcpServerNetworkCommunicationEndpoint;
import interactivespaces.service.comm.network.server.TcpServerNetworkCommunicationEndpointListener;
import interactivespaces.service.comm.network.server.TcpServerNetworkCommunicationEndpointService;
import interactivespaces.service.comm.network.server.TcpServerRequest;

import com.google.common.base.Charsets;
/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommTcpServerActivity extends BaseActivity {
	
	public static final byte[][] MESSAGE_TERMINATORS = new byte[][] { new byte[] { '\n' } };

    @Override
    public void onActivitySetup() {
        getLog().info("Activity comm.tcp.server setup");
        TcpServerNetworkCommunicationEndpointService commTcpService = getSpaceEnvironment().getServiceRegistry().getRequiredService(TcpServerNetworkCommunicationEndpointService.SERVICE_NAME);
        int serverPort= getConfiguration().getRequiredPropertyInteger("space.comm.tcp.server.port");
        TcpServerNetworkCommunicationEndpoint<String> tcpServer = commTcpService.newStringServer(MESSAGE_TERMINATORS, Charsets.UTF_8, serverPort, getLog());
        tcpServer.addListener(new TcpServerNetworkCommunicationEndpointListener<String>() {
			
			public void onTcpRequest(
					TcpServerNetworkCommunicationEndpoint<String> arg0,
					TcpServerRequest<String> arg1) {
					getLog().info(String.format("%s client requested : %s" ,arg1.getRemoteAddress(),arg1.getMessage() ));
					arg1.writeMessage(String.format("Server received %s",arg1.getMessage()));
			}
		});
        addManagedResource(tcpServer);
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity comm.tcp.server startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity comm.tcp.server post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity comm.tcp.server activate");
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity comm.tcp.server deactivate");
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity comm.tcp.server pre shutdown");
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity comm.tcp.server shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity comm.tcp.server cleanup");
    }
}
