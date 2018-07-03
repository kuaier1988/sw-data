package com.xuanyu.sw.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanyu.sw.entity.DataRealTimeCj;
import com.xuanyu.sw.mapper.DataRealTimeCjMapper;
import com.xuanyu.sw.model.JsonModel;
import com.xuanyu.sw.parse.SwParse;
import com.xuanyu.sw.service.IDataRealTimeCjService;
import com.xuanyu.sw.util.DateUtils;

@Service
public class DataRealTimeCjService implements IDataRealTimeCjService {
	static Map<String,Object> codesMap=new HashMap<String,Object>();
	@Autowired
	private DataRealTimeCjMapper dataRealTimeMapper;
	@Autowired
	private SwParse swParse;
	
	static {
		codesMap.put("汉口", 1);
		codesMap.put("向家坝", 11);
		codesMap.put("寸滩", 12);
		codesMap.put("万县", 13);
		codesMap.put("宜昌", 14);
		codesMap.put("沙市", 15);
		codesMap.put("螺山", 16);
		codesMap.put("九江", 17);
		codesMap.put("大通", 18);
		codesMap.put("城陵矶", 19);
		codesMap.put("皇庄", 20);
		codesMap.put("湖口", 21);
	}
	
	@Transactional
	public void addData() {
		List<DataRealTimeCj> dataRealTime=new ArrayList<DataRealTimeCj>(); 
		
		List<JsonModel> swDatas=swParse.getData();
		if(swDatas==null ||swDatas.isEmpty())
			return;
		for(JsonModel model:swDatas) {
			String stnm=model.getStnm();
			if(codesMap.get(stnm)==null || codesMap.isEmpty()) {
				continue;
			}
			
			DataRealTimeCj entity=new DataRealTimeCj();
			entity.setZd_code(Integer.parseInt(codesMap.get(stnm).toString()));
			entity.setZd_date(DateUtils.stringtoDate(model.getTm(), DateUtils.FORMAT_ONE));
			entity.setZd_des(model.getDesc());
			entity.setZd_name(model.getStnm());
			entity.setZd_shuiwei_type(model.getSw_type());
			entity.setZd_shuiwei(new BigDecimal(model.getZ().toString()));
			dataRealTime.add(entity);
			
		}
		
		dataRealTimeMapper.deleteData();
		dataRealTimeMapper.insertByBatch(dataRealTime);
		dataRealTimeMapper.insertLogByBatch(dataRealTime);
		
		
	}
}
