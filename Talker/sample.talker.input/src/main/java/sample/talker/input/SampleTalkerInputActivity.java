package sample.talker.input;

import interactivespaces.activity.impl.ros.BaseRoutableRosActivity;
import java.util.Map;

/**
 * A simple Interactive Spaces Java-based activity.
 */
public class SampleTalkerInputActivity extends BaseRoutableRosActivity {

    @Override
    public void onActivitySetup() {
        getLog().info("Activity sample.talker.input setup");
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity sample.talker.input startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity sample.talker.input post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity sample.talker.input activate");
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity sample.talker.input deactivate");
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity sample.talker.input pre shutdown");
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity sample.talker.input shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity sample.talker.input cleanup");
    }
    
    @Override
    public void onNewInputJson(String cName,Map<String, Object> message){
    	getLog().info("Input Channel" +cName);
    	getLog().info(message);
    }
}
