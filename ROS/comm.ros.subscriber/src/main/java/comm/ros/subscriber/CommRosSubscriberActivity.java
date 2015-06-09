package comm.ros.subscriber;

import interactivespaces.activity.impl.BaseActivity;
import interactivespaces.activity.component.ros.RosActivityComponent;
import org.ros.message.MessageListener;
import org.ros.node.topic.Subscriber;
import std_msgs.String;
/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommRosSubscriberActivity extends BaseActivity {

    private RosActivityComponent rosActivityComponent;
    private java.lang.String topicName;
    private Subscriber<String> subs;
	@Override
    public void onActivitySetup() {
        getLog().info("Activity comm.ros.subscriber setup");
        rosActivityComponent = addActivityComponent(RosActivityComponent.COMPONENT_NAME);
        topicName=getConfiguration().getRequiredPropertyString("comm.ros.subscriber.topic");
        
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity comm.ros.subscriber startup");
        subs = rosActivityComponent.getNode().newSubscriber(topicName, String._TYPE);
        subs.addMessageListener(new MessageListener<String>() {
        	@Override
        	public void onNewMessage(String msg)
        	{
        		getLog().info("[TOPIC : " + topicName +"] Message : "+ msg);
        	}
		});
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity comm.ros.subscriber post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity comm.ros.subscriber activate");
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity comm.ros.subscriber deactivate");
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity comm.ros.subscriber pre shutdown");
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity comm.ros.subscriber shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity comm.ros.subscriber cleanup");
    }
}
