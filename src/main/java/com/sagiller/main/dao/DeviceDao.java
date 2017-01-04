package com.sagiller.main.dao;

import com.sagiller.main.entity.Daily;
import com.sagiller.main.entity.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceDao {
	 /**
     * 查询设备列表
     *
     * @param limit
     * @return
     */
    List<Device> queryAll(@Param("limit") int limit);


    int create(Device device);

    List<Device> queryBySerialNumber(@Param("serialNumber") String serialNumber);

    int update(Device device);

    List<Daily> getNewerDaylyList(@Param("startDate") String startDate, @Param("endDate")String endDate);

    int count();

    int getActiveDeviceCount(@Param("startDate") String startDate, @Param("endDate")String endDate);

    List<Device> queryActiveDevice(@Param("limit")int limit,@Param("startDate") String startDate, @Param("endDate")String endDate);
}
