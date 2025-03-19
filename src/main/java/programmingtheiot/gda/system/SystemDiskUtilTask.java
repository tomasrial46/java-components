package programmingtheiot.gda.system;

import java.io.File;
import java.util.logging.Logger;

import programmingtheiot.common.ConfigConst;

/**
 * Class to monitor disk utilization.
 */
public class SystemDiskUtilTask extends BaseSystemUtilTask {
	private static final Logger _Logger = Logger.getLogger(SystemDiskUtilTask.class.getName());

	/**
	 * Default constructor.
	 */
	public SystemDiskUtilTask() {
		super(ConfigConst.NOT_SET, ConfigConst.DEFAULT_TYPE_ID);
	}

	@Override
	public float getTelemetryValue() {
		File root = new File("/");
		long totalSpace = root.getTotalSpace();
		long freeSpace = root.getFreeSpace();
		long usedSpace = totalSpace - freeSpace;
		
		float diskUtil = ((float) usedSpace / totalSpace) * 100.0f;
		
		_Logger.fine("Disk used: " + usedSpace + "; Disk total: " + totalSpace + "; Utilization: " + diskUtil + "%");
		return diskUtil;
	}
}
