package com.xuanyu.sw.route;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.xuanyu.sw.service.IDataRealTimeCjService;

@Configuration
@Component
@EnableScheduling
public class ScheduleTask {
	Logger logger=LoggerFactory.getLogger(ScheduleTask.class);
	@Autowired
	private IDataRealTimeCjService dataRealTimeService;
	
	public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(String.format("job1 run %s", sdf.format(new Date())));
		
		logger.info("开始插入数据:"+String.format("job1 run %s", sdf.format(new Date())));
		dataRealTimeService.addData();
		logger.info("数据插入完毕:"+String.format("job1 run %s", sdf.format(new Date())));
	}
	

}
