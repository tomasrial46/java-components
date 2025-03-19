package programmingtheiot.data;

import java.io.Serializable;

import programmingtheiot.common.ConfigConst;

public class SystemPerformanceData extends BaseIotData implements Serializable
{
// NOTE: You should create your own unique serialVersionUID

// private var's

private float cpuUtil  =ConfigConst.DEFAULT_VAL;
private float diskUtil =ConfigConst.DEFAULT_VAL;
private float memUtil  =ConfigConst.DEFAULT_VAL;

// constructors

public SystemPerformanceData()
	{
super();

super.setName(ConfigConst.SYS_PERF_DATA);
	}

// public methods

// Implement the getter and setter methods for:
//  - cpuUtil
//  - diskUtil
//  - memUtil
//
public float getCpuUtilization()
	{
		return this.cpuUtil;
	}

public void setCpuUtilization(float val)
	{
		super.updateTimeStamp();
		this.cpuUtil = val;
	}

public float getDiskUtilization()
	{
		return this.diskUtil;
	}

public void setDiskUtilization(float val)
	{
		super.updateTimeStamp();
		this.diskUtil = val;
	}

public float getMemoryUtilization()
	{
		return this.memUtil;
	}

public void setMemoryUtilization(float val)
	{
		super.updateTimeStamp();
		this.memUtil = val;
	}


// protected methods

// Implement the handleUpdateData(BaseIotData data) method.
//
// Follow the same pattern shown in ActuatorData and SensorData,
// but be sure to implement this method specifically to support
// SystemPerformanceData with cpuUtil, diskUtil, and memUtil

protected void handleUpdateData(BaseIotData data)
	{
		if (data instanceof SystemPerformanceData){
			SystemPerformanceData spData = (SystemPerformanceData) data;
			this.setCpuUtilization(spData.getCpuUtilization());
			this.setDiskUtilization(spData.getDiskUtilization());
			this.setMemoryUtilization(spData.getMemoryUtilization());
		}
	}
}