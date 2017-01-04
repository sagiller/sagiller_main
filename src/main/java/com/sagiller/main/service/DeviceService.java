package com.sagiller.main.service;

import com.sagiller.main.entity.Daily;
import com.sagiller.main.entity.Device;
import com.sagiller.main.entity.DeviceGlobalInfo;

import java.util.List;

public interface DeviceService {

	/**
	 * 查询设备列表
	 *
	 * @param limit
	 * @return
	 */
	List<Device> getDeviceList(int limit);

	List<Device> getDeviceActive(int limit,String start, String end);

	List<Daily> getNewerDaylyList(String start, String end);

    int create(Device device);

	Device getDeviceBySerialNumber(String serialNumber);

	int update(Device device);

    DeviceGlobalInfo getDeviceGlobalInfo();
}
