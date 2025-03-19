package programmingtheiot.data;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import programmingtheiot.common.ConfigConst;

public class DataUtil {
    // static

    private static final Logger _Logger = Logger.getLogger(DataUtil.class.getName());
    private static final DataUtil _Instance = new DataUtil();

    public static final DataUtil getInstance() {
        return _Instance;
    }

    // constructors

    private DataUtil() {
        super();
    }

    // public methods

    public String actuatorDataToJson(ActuatorData data) {
        String jsonData = null;

        if (data != null) {
            Gson gson = new Gson();
            jsonData = gson.toJson(data);
        }

        return jsonData;
    }

    public ActuatorData jsonToActuatorData(String jsonData) {
        ActuatorData data = null;

        if (jsonData != null && jsonData.trim().length() > 0) {
            Gson gson = new Gson();
            data = gson.fromJson(jsonData, ActuatorData.class);
        }

        return data;
    }

    public String sensorDataToJson(SensorData data) {
        String jsonData = null;

        if (data != null) {
            Gson gson = new Gson();
            jsonData = gson.toJson(data);
        }

        return jsonData;
    }

    public SensorData jsonToSensorData(String jsonData) {
        SensorData data = null;

        if (jsonData != null && jsonData.trim().length() > 0) {
            Gson gson = new Gson();
            data = gson.fromJson(jsonData, SensorData.class);
        }

        return data;
    }

    public String systemPerformanceDataToJson(SystemPerformanceData data) {
        String jsonData = null;

        if (data != null) {
            Gson gson = new Gson();
            jsonData = gson.toJson(data);
        }

        return jsonData;
    }

    public SystemPerformanceData jsonToSystemPerformanceData(String jsonData) {
        SystemPerformanceData data = null;

        if (jsonData != null && jsonData.trim().length() > 0) {
            Gson gson = new Gson();
            data = gson.fromJson(jsonData, SystemPerformanceData.class);
        }

        return data;
    }
	public String systemStateDataToJson(SystemStateData data) {
		String jsonData = null;

		if (data != null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(data);
		}

		return jsonData;
	}

	public SystemStateData jsonToSystemStateData(String jsonData) {
		SystemStateData data = null;

		if (jsonData != null && jsonData.trim().length() > 0) {
			Gson gson = new Gson();
			data = gson.fromJson(jsonData, SystemStateData.class);
		}

		return data;
	}

}
