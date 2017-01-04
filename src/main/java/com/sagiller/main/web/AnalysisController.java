package com.sagiller.main.web;

import com.sagiller.main.dto.BaseResult;
import com.sagiller.main.entity.ImageLoadingAverage;
import com.sagiller.main.entity.UploadData;
import com.sagiller.main.enums.ResultEnum;
import com.sagiller.main.exception.BizException;
import com.sagiller.main.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private static final int OS_TYPE_ANDROID = 2, OS_TYPE_IOS = 1;

    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public BaseResult<Object> register(HttpServletRequest request, @RequestBody @Valid UploadData data, BindingResult result) {
        LOG.info("invoke----------/" + data.getSerialNumber() + "/upload");
        int res = -1;
        try {
            res = analysisService.uploadData(data);

        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        return new BaseResult<Object>(true, data);
    }

    @RequestMapping(value = "/imageloading", method = RequestMethod.GET)
    public String imageloading(Model model) {
        LOG.info("invoke----------/analysis/imageloading");
//        List<ImageLoadingAverage> androidList = analysisService.getImageLoading(OS_TYPE_ANDROID, start, end);
//        List<ImageLoadingAverage> iosList = analysisService.getImageLoading(OS_TYPE_IOS, start, end);
        ImageLoadingAverage androidAverage = analysisService.getImageLoadingAverage(OS_TYPE_ANDROID);
        ImageLoadingAverage iosAverage = analysisService.getImageLoadingAverage(OS_TYPE_IOS);
        model.addAttribute("androidAverage", androidAverage);
        model.addAttribute("iosAverage", iosAverage);
        return "analysis/imageloading";
    }


}
