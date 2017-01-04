package com.sagiller.main.service.impl;

import com.sagiller.main.cache.RedisCache;
import com.sagiller.main.dao.ImageLoadingDao;
import com.sagiller.main.dao.LaunchDao;
import com.sagiller.main.entity.ImageLoadingAverage;
import com.sagiller.main.entity.Launch;
import com.sagiller.main.entity.UploadData;
import com.sagiller.main.service.AnalysisService;
import com.sagiller.main.entity.ImageLoading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalysisServiceImpl implements AnalysisService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ImageLoadingDao imageLoadingDao;
	@Autowired
	private LaunchDao launchDao;
	@Autowired
	private RedisCache cache;


	@Override
	public int uploadData(UploadData uploadData) {
        Launch launch = new Launch();
        List<Launch> launches = new ArrayList<>();
        launch.setSerialNumber(uploadData.getSerialNumber());
		for (Long time:uploadData.getTimes()) {
            launch.setLaunchTime(new Date(time));
            launches.add(launch);
        }
//        launchDao.createAll(launches);
        ImageLoading imageLoading = uploadData.getImageLoading();
        if (imageLoading == null) {
            return 0;
        }
        ImageLoading existImageLoading = imageLoadingDao.queryBySerialNumber(uploadData.getImageLoading().getSerialNumber());
        if (existImageLoading != null) {
            existImageLoading.setLoadingMax(existImageLoading.getLoadingMax() > imageLoading.getLoadingMax() ? existImageLoading.getLoadingMax() : imageLoading.getLoadingMax());
            existImageLoading.setLoadingMin(existImageLoading.getLoadingMin() > imageLoading.getLoadingMin() ? existImageLoading.getLoadingMin() : imageLoading.getLoadingMin());
            int existLoadingTimes = existImageLoading.getLoadingTimes();
            int loadingTimes = imageLoading.getLoadingTimes();
            float newLoadingAverage;
            if (existLoadingTimes == 0 && loadingTimes == 0) {
                newLoadingAverage = 0;
            } else {
                newLoadingAverage = (existImageLoading.getLoadingAverage() * existLoadingTimes + imageLoading.getLoadingAverage() * loadingTimes) / (existLoadingTimes + loadingTimes);

            }
            existImageLoading.setLoadingAverage(newLoadingAverage);
            existImageLoading.setLoadingTimes(existLoadingTimes + loadingTimes);

            existImageLoading.setDrawMax(existImageLoading.getDrawMax() > imageLoading.getDrawMax() ? existImageLoading.getDrawMax() : imageLoading.getDrawMax());
            existImageLoading.setDrawMin(existImageLoading.getDrawMin() > imageLoading.getDrawMin() ? existImageLoading.getDrawMin() : imageLoading.getDrawMin());
            int existDrawTimes = existImageLoading.getDrawTimes();
            int drawTimes = imageLoading.getDrawTimes();
            float newDrawAverage;
            if (existDrawTimes == 0 && drawTimes == 0) {
                newDrawAverage = 0;
            } else {
                newDrawAverage = (existImageLoading.getDrawAverage() * existDrawTimes + imageLoading.getDrawAverage() * drawTimes) / (existDrawTimes + drawTimes);
            }
            existImageLoading.setDrawAverage(newDrawAverage);
            existImageLoading.setDrawTimes(existDrawTimes + drawTimes);

            imageLoadingDao.update(existImageLoading);
        } else {
            imageLoadingDao.create(imageLoading);
        }
        return 0;
	}

    @Override
    public List<ImageLoadingAverage> getImageLoading(int osType, String start, String end) {
        return null;
    }

    public ImageLoadingAverage getImageLoadingAverage(int osType
    ) {
        return imageLoadingDao.averageOnPlatform(osType);
    }
}
