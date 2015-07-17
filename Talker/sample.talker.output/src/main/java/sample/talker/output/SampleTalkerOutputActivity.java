package sample.talker.output;

import interactivespaces.activity.impl.ros.BaseRoutableRosActivity;

import java.util.Arrays;
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
        byte [] arr = {(byte) 254,123,(byte) 234,32,(byte) 156,(byte) 180,12,1,125, (byte) 150};
        int[] ret = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            ret[i] = arr[i] & 0xff; // Range 0 to 255, not -128 to 127
        }
        String tempStr = Arrays.toString(arr); 
        temp.put("message", tempStr);
        sendOutputJson("output1", temp);
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity sample.talker.output deactivate");
        Map<String,Object> temp=Maps.newHashMap();
        Byte [] arr = {(byte) 254,123,(byte) 234,32,(byte) 156,(byte) 180,12,1,125, (byte) 150};
        String tempStr = Arrays.toString(arr);  
        temp.put("message", tempStr);
        sendOutputJson("output1", temp);
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity sample.talker.output pre shutdown");
        Map<String,Object> temp=Maps.newHashMap();
        Byte [] arr = {(byte) 254,123,(byte) 234,32,(byte) 156,(byte) 180,12,1,125, (byte) 150};
        int[] ret = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            ret[i] = arr[i] & 0xff; // Range 0 to 255, not -128 to 127
        }
        String tempStr = Arrays.toString(arr);  
        temp.put("message", tempStr);
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
