 
 package programmingtheiot.gda.app;


 import programmingtheiot.gda.system.SystemPerformanceManager;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import programmingtheiot.common.ConfigConst;
 import programmingtheiot.common.ConfigUtil;
 

public class GatewayDeviceApp 
{
    // Static variables
    private static final Logger _Logger = Logger.getLogger(GatewayDeviceApp.class.getName());
    public static final long DEFAULT_TEST_RUNTIME = 600000L; // 10 minutes

    // Private variables
    private DeviceDataManager dataMgr = null;
	private SystemPerformanceManager sysPerfMgr =null;
    // Constructors
    public GatewayDeviceApp(String[] args) 
    {
        super();
        _Logger.info("Initializing GDA...");
		 this.sysPerfMgr =new SystemPerformanceManager();
    }

    // Main application entry point
    public static void main(String[] args) 
    {
        GatewayDeviceApp gwApp = new GatewayDeviceApp(args);
        gwApp.startApp();

        // Custom configuration for running indefinitely
        boolean runForever = ConfigUtil.getInstance().getBoolean(ConfigConst.GATEWAY_DEVICE, ConfigConst.ENABLE_RUN_FOREVER_KEY);

        if (runForever) {
            try {
                // TODO: Make the 2000L configurable
                while (true) {
                    Thread.sleep(2000L);
                }
            } catch (InterruptedException e) {
                // Ignore exception
            }

            gwApp.stopApp(0);
        } else {
            try {
                Thread.sleep(DEFAULT_TEST_RUNTIME);
            } catch (InterruptedException e) {
                // Ignore exception
            }

            gwApp.stopApp(0);
        }
    }

    // Public methods
    public void startApp() 
    {
        _Logger.info("Starting GDA...");

        try {
            if (!ConfigUtil.getInstance().getBoolean(ConfigConst.GATEWAY_DEVICE, ConfigConst.TEST_EMPTY_APP_KEY)) {
				this.dataMgr = new DeviceDataManager();
            }

            if (this.dataMgr != null) {
                this.dataMgr.startManager();
            }

            _Logger.info("GDA started successfully.");
        } catch (Exception e) {
            _Logger.log(Level.SEVERE, "Failed to start GDA. Exiting.", e);
            stopApp(-1);
        }
    }

    public void stopApp(int code) 
    {
        _Logger.info("Stopping GDA...");

        try {
            if (this.dataMgr != null) {
                this.dataMgr.stopManager();
            }

            _Logger.log(Level.INFO, "GDA stopped successfully with exit code {0}.", code);
        } catch (Exception e) {
            _Logger.log(Level.SEVERE, "Failed to cleanly stop GDA. Exiting.", e);
        }

    }
}
