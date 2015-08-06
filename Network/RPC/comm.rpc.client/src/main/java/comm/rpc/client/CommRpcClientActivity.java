package comm.rpc.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import interactivespaces.activity.impl.BaseActivity;

/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommRpcClientActivity extends BaseActivity {
	
	XmlRpcClientConfigImpl config;
	XmlRpcClient client;
    @Override
    public void onActivitySetup() {
        getLog().info("Activity comm.rpc.client setup");
        client = new XmlRpcClient();
        config = new XmlRpcClientConfigImpl();
        getLog().info("C1");
        try {
			config.setServerURL(new URL("http://192.168.172.1:8999/xmlrpc"));
			getLog().info("C2");
		} catch (MalformedURLException e) {
			getLog().error(e);
		}
        config.setEnabledForExtensions(true);
        config.setConnectionTimeout(60*1000);
        config.setReplyTimeout(60*1000);
        client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
        client.setConfig(config);
        getLog().info("C3");
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity comm.rpc.client startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity comm.rpc.client post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity comm.rpc.client activate");
        Integer[] params = new Integer[1];
        params[0]=4;
        Object [] par = null;
            try {
            	Integer result = (Integer) client.execute("Test.printYe", par);
				getLog().info("After number call");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 //params = new Object[]{};
				Integer  result1 = (Integer) client.execute("Test.printNo", par);
				getLog().info("After print yes call");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Integer result2 = (Integer) client.execute("Test.printNumber", params);
				 getLog().info("After print no call"+result);

				  
			} catch (XmlRpcException e) {
				getLog().error(e);
			}
            
            getLog().info("Finished everything");
            
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity comm.rpc.client deactivate");
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity comm.rpc.client pre shutdown");
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity comm.rpc.client shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity comm.rpc.client cleanup");
    }
}
