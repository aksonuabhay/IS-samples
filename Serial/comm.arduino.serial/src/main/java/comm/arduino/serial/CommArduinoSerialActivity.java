package comm.arduino.serial;

import interactivespaces.activity.impl.BaseActivity;

/**
 * A simple Interactive Spaces Java-based activity.
 */
public class CommArduinoSerialActivity extends BaseActivity {

    @Override
    public void onActivitySetup() {
        getLog().info("Activity comm.arduino.serial setup");
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
