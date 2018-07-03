package com.xuanyu.sw.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xuanyu.sw.entity.DataRealTimeCj;
import com.xuanyu.sw.model.JsonModel;

public interface DataRealTimeCjMapper {
	public void insertByBatch(List<DataRealTimeCj> swDatas);
	public void insert(DataRealTimeCj dataRealTime);
	public void deleteData();
	public void insertLog(DataRealTimeCj dataRealTime);
	public void insertLogByBatch(List<DataRealTimeCj> swDatas);
	
}
