package comm.rpc.server;

import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import interactivespaces.activity.impl.BaseActivity;

/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommRpcServerActivity extends BaseActivity {
	WebServer webServer;
	XmlRpcServer rpcServer;
	PropertyHandlerMapping phm;
	XmlRpcServerConfigImpl config;
    @Override
    public void onActivitySetup() {
        getLog().info("Activity comm.rpc.server setup");
        webServer = new WebServer(8999);
        rpcServer =  webServer.getXmlRpcServer();
        phm = new PropertyHandlerMapping();
        getLog().info("1");
        try {
			phm.addHandler("Test", comm.rpc.server.Test.class);
			getLog().info("2");
		} catch (XmlRpcException e) {
			getLog().error(e);
		}
        rpcServer.setHandlerMapping(phm);
        getLog().info("3");
        config = (XmlRpcServerConfigImpl) rpcServer.getConfig();
        config.setEnabledForExceptions(true);
        config.setContentLengthOptional(false);
        getLog().info("4");
        try {
			webServer.start();
			getLog().info("5");
		} catch (IOException e) {
			getLog().error(e);
		}
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity comm.rpc.server startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity comm.rpc.server post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity comm.rpc.server activate");
        //getLog().info(webServer.);
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity comm.rpc.server deactivate");
        webServer.shutdown();
        
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity comm.rpc.server pre shutdown");
        webServer.shutdown();
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity comm.rpc.server shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity comm.rpc.server cleanup");
    }
    

}
