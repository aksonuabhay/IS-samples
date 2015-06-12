package util.activity.nativerunner;


import interactivespaces.activity.impl.BaseActivity;
import interactivespaces.util.process.NativeApplicationRunner;
import interactivespaces.util.process.NativeApplicationRunnerCollection;
import interactivespaces.util.process.StandardNativeApplicationRunnerCollection;
import interactivespaces.util.process.NativeApplicationRunnerListener;
import interactivespaces.util.process.restart.RestartStrategy;


/**
 * A simple Interactive Spaces Java-based activity.
 */
public class UtilActivityNativerunnerActivity extends BaseActivity {

	private NativeApplicationRunnerCollection nativeRunnerCollection;
	NativeApplicationRunner runner;
    @Override
    public void onActivitySetup() {
        getLog().info("Activity util.activity.nativerunner setup");
        nativeRunnerCollection = new StandardNativeApplicationRunnerCollection(getSpaceEnvironment(), getLog());
		addManagedResource(nativeRunnerCollection);
    }

    @Override
    public void onActivityStartup() {
        getLog().info("Activity util.activity.nativerunner startup");
    }

    @Override
    public void onActivityPostStartup() {
        getLog().info("Activity util.activity.nativerunner post startup");
    }

    @Override
    public void onActivityActivate() {
        getLog().info("Activity util.activity.nativerunner activate");
        runner = nativeRunnerCollection.newNativeApplicationRunner();
		runner.setExecutablePath(getConfiguration().getRequiredPropertyString("space.nativeapplication.executable"));
		runner.parseCommandArguments(getConfiguration().getPropertyString("space.nativeapplication.executable.flags"));
		runner.addNativeApplicationRunnerListener(new NativeApplicationRunnerListener() {
			
			public void onRestartSuccess(RestartStrategy<NativeApplicationRunner> arg0,
					NativeApplicationRunner runner) {
				// TODO Auto-generated method stub
				getLog().info("Native application restart Successful");
			}
			
			public void onRestartFailure(RestartStrategy<NativeApplicationRunner> arg0,
					NativeApplicationRunner runner) {
				// TODO Auto-generated method stub
				getLog().info("Restarting the native application has failed");
				
			}
			
			public boolean onRestartAttempt(
					RestartStrategy<NativeApplicationRunner> arg0,
					NativeApplicationRunner runner, boolean arg2) {
				// TODO Auto-generated method stub
				getLog().info("Native application has crashed and is trying to restart");
				return true;
			}
			
			public void onNativeApplicationRunnerStartupFailed(
					NativeApplicationRunner runner) {
				// TODO Auto-generated method stub
				getLog().info("Native application failed to start");
			}
			
			public void onNativeApplicationRunnerStarting(NativeApplicationRunner runner) {
				// TODO Auto-generated method stub
				getLog().info("Native application starting");
			}
			
			public boolean onNativeApplicationRunnerShutdownRequested(
					NativeApplicationRunner runner) {
				// TODO Auto-generated method stub
				getLog().info("Native application shutdown requested");
				return false;
			}
			
			public void onNativeApplicationRunnerShutdown(NativeApplicationRunner runner) {
				// TODO Auto-generated method stub
				getLog().info("Native application shut down");
			}
			
			public void onNativeApplicationRunnerRunning(NativeApplicationRunner runner) {
				// TODO Auto-generated method stub
				getLog().info("Native application running");
			}
		});
        nativeRunnerCollection.addNativeApplicationRunner(runner);
    }

    @Override
    public void onActivityDeactivate() {
        getLog().info("Activity util.activity.nativerunner deactivate");
    }

    @Override
    public void onActivityPreShutdown() {
        getLog().info("Activity util.activity.nativerunner pre shutdown");
    }

    @Override
    public void onActivityShutdown() {
        getLog().info("Activity util.activity.nativerunner shutdown");
    }

    @Override
    public void onActivityCleanup() {
        getLog().info("Activity util.activity.nativerunner cleanup");
    }
}
