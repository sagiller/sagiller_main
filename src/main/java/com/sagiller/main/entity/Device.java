package com.sagiller.main.entity;

import com.sagiller.main.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class Device {

	private long id;

	private String address;

	//序列号
	private String serialNumber;
	
	private String ip;
	
	private String appVersion;
	
	private String vendor;

	private String model;

	private int osType; //1:ios  2:android

	private String osVersion;
	
	//这里展示了jackson封装好的以及自定义的对时间格式的转换方式
	//后续对于一些复杂的转换可以自定义转换方式
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getOsType() {
		return osType;
	}

	public void setOsType(int osType) {
		this.osType = osType;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", serialNumber=" + serialNumber + ", ip=" + ip + ", address=" + address + ", appVersion=" + appVersion + ", vendor=" + vendor+ ", model=" + model+ ", osType=" + osType+ ", osVersion=" + osVersion + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
	
}
