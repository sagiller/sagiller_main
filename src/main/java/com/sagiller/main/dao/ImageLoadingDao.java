package com.sagiller.main.dao;

import com.sagiller.main.entity.ImageLoading;
import com.sagiller.main.entity.ImageLoadingAverage;

public interface ImageLoadingDao {
	int create(ImageLoading imageLoading);

	ImageLoading queryBySerialNumber(String serialNumber);

	int update(ImageLoading imageLoading);

	ImageLoadingAverage averageOnPlatform(int osType);
}
