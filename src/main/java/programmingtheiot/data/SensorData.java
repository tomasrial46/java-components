package programmingtheiot.data;

import java.io.Serializable;

import programmingtheiot.common.ConfigConst;

public class SensorData extends BaseIotData implements Serializable
{
// NOTE: You should create your own unique serialVersionUID

// private var's

private float value =ConfigConst.DEFAULT_VAL;

// constructors

public SensorData()
	{
super();
	}


// public methods

public float getValue()
	{
return this.value;
	}

public void setValue(float val)
	{
super.updateTimeStamp();
this.value =val;
	}

// protected methods

protected void handleUpdateData(BaseIotData data)
	{
if (data instanceof SensorData) {
SensorData sData = (SensorData) data;
this.setValue(sData.getValue());
		}
	}
}