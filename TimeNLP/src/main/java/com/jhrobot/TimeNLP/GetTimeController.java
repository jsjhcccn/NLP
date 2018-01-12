package com.jhrobot.TimeNLP;

import com.time.nlp.TimeUnit;
import com.time.util.DateUtil;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import com.time.nlp.TimeNormalizer;
/**
 * Created by 1 on 2018/1/12.
 */
@RestController
public class GetTimeController {
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }
    @PostMapping("/GetNLPTime")
    public String GetNLPTime(@RequestParam String word){
        URL url = TimeNormalizer.class.getResource("/TimeExp.m");

        TimeNormalizer normalizer = null;
        try {
            normalizer = new TimeNormalizer(url.toURI().toString());
            normalizer.setPreferFuture(true);
            normalizer.parse(word);
            TimeUnit[] unit = normalizer.getTimeUnit();
            return DateUtil.formatDateDefault(unit[0].getTime()) + "-" + unit[0].getIsAllDayTime();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "fail";
        }

    }
}
