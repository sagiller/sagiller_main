package com.sagiller.main.service;

import com.sagiller.main.entity.ImageLoadingAverage;
import com.sagiller.main.entity.UploadData;

import java.util.List;

public interface AnalysisService {
	int uploadData(UploadData uploadData);

    List<ImageLoadingAverage> getImageLoading(int osType, String start, String end);
    ImageLoadingAverage getImageLoadingAverage(int osType);
}
