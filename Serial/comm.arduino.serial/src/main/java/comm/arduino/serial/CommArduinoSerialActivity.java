package comm.arduino.serial;


import interactivespaces.activity.impl.BaseActivity;
import interactivespaces.service.comm.serial.SerialCommunicationEndpoint;
import interactivespaces.service.comm.serial.SerialCommunicationEndpointService;
import interactivespaces.util.concurrency.CancellableLoop;
import interactivespaces.util.resource.ManagedResourceWithTask;
/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommArduinoSerialActivity extends BaseActivity {
	
	private SerialCommunicationEndpoint serial;

    @Override
    public void onActivitySetup() {
        getLog().info("Activity comm.arduino.serial setup");
        SerialCommunicationEndpointService commSerialService=getSpaceEnvironment().getServiceRegistry().getRequiredService(SerialCommunicationEndpointService.SERVICE_NAME );
        String ports=commSerialService.getSerialPorts().toString();
        getLog().info("Ports available : " + ports);
        String portName=getConfiguration().getRequiredPropertyString("space.hardware.serial.port");
        serial =commSerialService.newSerialEndpoint(portName);
        ManagedResourceWithTask temp= new ManagedResourceWithTask(serial, new CancellableLoop() {
			
        	
			@Override
			protected void loop() throws InterruptedException {
				// TODO Auto-generated method stub
				getLog().info("Received :  " + serial.read() );
			}
			@Override
        	protected void handleException(Exception e)
			{
				getLog().error("Error " +e);
			}
        	
		}, getSpaceEnvironment());
        addManagedResource(temp);
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity comm.arduino.serial startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity comm.arduino.serial post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity comm.arduino.serial activate");
        String temp="Hello Arduino!";
        serial.write(temp.getBytes());
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity comm.arduino.serial deactivate");
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity comm.arduino.serial pre shutdown");
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity comm.arduino.serial shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity comm.arduino.serial cleanup");
    }
}
