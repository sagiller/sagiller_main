package com.sagiller.main.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sagiller.main.dto.BaseResult;
import com.sagiller.main.entity.Daily;
import com.sagiller.main.entity.Device;
import com.sagiller.main.entity.DeviceGlobalInfo;
import com.sagiller.main.enums.ResultEnum;
import com.sagiller.main.exception.BizException;
import com.sagiller.main.service.DeviceService;
import com.sagiller.main.util.TimeUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceService deviceService;

    private OkHttpClient client;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Integer offset, Integer limit) {
        LOG.info("invoke----------/device/list");
        //offset = offset == null ? 0 : offset;//默认便宜0
        limit = limit == null ? 50 : limit;//默认展示50条
        List<Device> list = deviceService.getDeviceList(limit);
        DeviceGlobalInfo global = deviceService.getDeviceGlobalInfo();
        model.addAttribute("devicelist", list);
        model.addAttribute("global", global);
        return "device/list";
    }

    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public String active(Model model, Integer offset, Integer limit, String start, String end) {
        LOG.info("invoke----------/device/active");
        //offset = offset == null ? 0 : offset;//默认便宜0
        if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
            //其中一个为空，则默认为距今为止30天的数据。
            start = TimeUtils.getTime(TimeUtils.getDate(-7),TimeUtils.DATE_FORMAT_DATE_D);
            end = TimeUtils.getTime(System.currentTimeMillis(),TimeUtils.DATE_FORMAT_DATE_D);
        }
        limit = limit == null ? 50 : limit;//默认展示50条
        List<Device> list = deviceService.getDeviceActive(limit,start,end);
        model.addAttribute("devicelist", list);
        return "device/active";
    }

    @RequestMapping(value = "/newer", method = RequestMethod.GET)
    public String newer(Model model, String start, String end) {


        LOG.info("invoke----------/device/newer");
        //offset = offset == null ? 0 : offset;//默认便宜0
        if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
            //其中一个为空，则默认为距今为止30天的数据。
            start = TimeUtils.getTime(TimeUtils.getDate(-30),TimeUtils.DATE_FORMAT_DATE_D);
            end = TimeUtils.getTime(System.currentTimeMillis(),TimeUtils.DATE_FORMAT_DATE_D);
        }
        List<Daily> newerDailyList = deviceService.getNewerDaylyList(start,end);
        model.addAttribute("newerdailylist", newerDailyList);
        return "device/newer";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public BaseResult<Object> register(HttpServletRequest request, @RequestBody @Valid Device device, BindingResult result) {
        LOG.info("invoke----------/" + device.getSerialNumber() + "/create");
        //Valid 参数验证(这里注释掉，采用AOP的方式验证,见BindingResultAop.java)
        //if (result.hasErrors()) {
        //    String errorInfo = "[" + result.getFieldError().getField() + "]" + result.getFieldError().getDefaultMessage();
        //    return new BaseResult<Object>(false, errorInfo);
        //}
        String ip = request.getRemoteAddr().toString();
        if (StringUtils.isEmpty(ip)) {
            //call by proxy
            ip = request.getHeader("X-FORWARDED-FOR");
        }
        device.setIp(ip);
        try {
            Device oldDevice = deviceService.getDeviceBySerialNumber(device.getSerialNumber());
            if (oldDevice == null ||StringUtils.isEmpty(oldDevice.getAddress()) || !device.getIp().equals(oldDevice.getIp())) {
                String getAddressByIpUrl = "http://freeapi.ipip.net/" + device.getIp();
                if (client == null) {
                    client = new OkHttpClient();
                }
                Request okHttpRequest = new Request.Builder()
                        .url(getAddressByIpUrl)
                        .build();
                String address = "";
                Response response = client.newCall(okHttpRequest).execute();
                String addressResult = response.body().string();
                JSONArray jp = JSON.parseArray(addressResult);
                for (int i= 0; i < jp.size(); i++) {
                    String item = jp.getString(i);
                    if (!StringUtils.isEmpty(item)) {
                        address = address + item;
                    }
                }
                device.setAddress(address);

            } else {
                device.setAddress(oldDevice.getAddress());
            }
            if (oldDevice != null) {
                device.setId(oldDevice.getId());

                deviceService.update(device);
            } else {
                deviceService.create(device);
            }

        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        Device item = deviceService.getDeviceBySerialNumber(device.getSerialNumber());
        return new BaseResult<Object>(true, item);
    }
}
