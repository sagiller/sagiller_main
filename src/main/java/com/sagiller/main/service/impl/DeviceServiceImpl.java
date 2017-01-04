package com.sagiller.main.service.impl;

import com.sagiller.main.entity.Daily;
import com.sagiller.main.entity.Device;
import com.sagiller.main.entity.DeviceGlobalInfo;
import com.sagiller.main.service.DeviceService;
import com.sagiller.main.cache.RedisCache;
import com.sagiller.main.dao.DeviceDao;
import com.sagiller.main.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class DeviceServiceImpl implements DeviceService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private RedisCache cache;

	@Override
	public List<Device> getDeviceList(int limit) {
		String cache_key = RedisCache.CAHCENAME + "|getDeviceList|" + limit;
		List<Device> result_cache = cache.getListCache(cache_key, Device.class);
		if (result_cache != null) {
			LOG.info("get cache with key:" + cache_key);
		} else {
			// 缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
			result_cache = deviceDao.queryAll(limit);
			cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
			LOG.info("put cache with key:" + cache_key);
			return result_cache;
		}
		return result_cache;
	}

    @Override
    public List<Device> getDeviceActive(int limit,String start, String end) {
        String cache_key = RedisCache.CAHCENAME + "|getDeviceActive|" + limit;
        List<Device> result_cache = cache.getListCache(cache_key, Device.class);
        if (result_cache != null) {
            LOG.info("get cache with key:" + cache_key);
        } else {
            // 缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
            result_cache = deviceDao.queryActiveDevice(limit,start,end);
            cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
            LOG.info("put cache with key:" + cache_key);
            return result_cache;
        }
        return result_cache;
    }

	@Override
	public List<Daily> getNewerDaylyList(String start, String end) {
		String cache_key = RedisCache.CAHCENAME + "|getNewerDaylyList|" + "start|" + start + "|end|" + end;
        List<Daily> dailyListWithEmpty = cache.getListCache(cache_key, Daily.class);
		if (dailyListWithEmpty != null) {
			LOG.info("get cache with key:" + cache_key);
		} else {
			// 缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
            List<Daily> dailyList = deviceDao.getNewerDaylyList(start, end);

            List<Daily> emptyDailyList = new ArrayList<>();
            for (Daily daily : dailyList) {
                daily.setDateDisplay(TimeUtils.getTime(daily.getDate(),TimeUtils.DATE_FORMAT_DATE_SHORT));
            }
			try {

				List<Date> dates = TimeUtils.getRangeDailyDate(start, end);
				for (Date date : dates) {
                    Daily daily = new Daily();
                    daily.setDate(date);
                    daily.setNumber(0);
                    daily.setDateDisplay(TimeUtils.getTime(date,TimeUtils.DATE_FORMAT_DATE_SHORT));
                    emptyDailyList.add(daily);
                }

                HashSet hashSet=new HashSet();
                hashSet.addAll(dailyList);
                hashSet.addAll(emptyDailyList);
                dailyListWithEmpty = new ArrayList<>(hashSet);
                Collections.sort(dailyListWithEmpty);
                Collections.reverse(dailyListWithEmpty);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			cache.putListCacheWithExpireTime(cache_key, dailyListWithEmpty, RedisCache.CAHCETIME);
			LOG.info("put cache with key:" + cache_key);
			return dailyListWithEmpty;
		}
		return dailyListWithEmpty;
	}

	@Override
	public int create(Device device) {
		return deviceDao.create(device);
	}

	@Override
	public Device getDeviceBySerialNumber(String serialNumber) {
		List<Device> devices = deviceDao.queryBySerialNumber(serialNumber);
		if (devices != null && devices.size() > 0) {
			return devices.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int update(Device device) {
		return deviceDao.update(device);
	}

	@Override
	public DeviceGlobalInfo getDeviceGlobalInfo() {
		DeviceGlobalInfo global = new DeviceGlobalInfo();
        global.setTotal(deviceDao.count());
        String beforeYesterday = TimeUtils.getTime(TimeUtils.getDate(-2),TimeUtils.DATE_FORMAT_DATE_D);
        String today = TimeUtils.getTime(TimeUtils.getDate(0),TimeUtils.DATE_FORMAT_DATE_D);
        List<Daily> list = deviceDao.getNewerDaylyList(beforeYesterday,today);
        if (list != null && list.size() > 0) {
            global.setYesterdayNew(list.get(0).getNumber());
        } else {
            global.setYesterdayNew(0);
        }
        String start = TimeUtils.getTime(TimeUtils.getDate(-7),TimeUtils.DATE_FORMAT_DATE_D);
		//TODO ,end should not be today.
		//global.setActiveDevice(deviceDao.getActiveDeviceCount(start,today));
        global.setActiveDevice(5);
		return global;
	}

}
