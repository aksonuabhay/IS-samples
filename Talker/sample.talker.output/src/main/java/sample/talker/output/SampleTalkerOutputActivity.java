package sample.talker.output;

import interactivespaces.activity.impl.ros.BaseRoutableRosActivity;
import java.util.Map;

import com.google.common.collect.Maps;
/**
 * A simple Interactive Spaces Java-based activity.
 */
public class SampleTalkerOutputActivity extends BaseRoutableRosActivity  {

    @Override
    public void onActivitySetup() {
        getLog().info("Activity sample.talker.output setup");
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity sample.talker.output startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity sample.talker.output post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity sample.talker.output activate");
        Map<String,Object> temp=Maps.newHashMap();
        temp.put("message", "OnActivityActivate");
        sendOutputJson("output1", temp);
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity sample.talker.output deactivate");
        Map<String,Object> temp=Maps.newHashMap();
        temp.put("message", "OnActivityDeactivate");
        sendOutputJson("output1", temp);
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity sample.talker.output pre shutdown");
        Map<String,Object> temp=Maps.newHashMap();
        temp.put("message", "OnActivityPreShutdown");
        sendOutputJson("output1", temp);
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity sample.talker.output shutdown");
        
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity sample.talker.output cleanup");
    }
}
