package com.xuanyu.sw.parse;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.xuanyu.sw.model.JsonModel;

@Component
public class SwParse {
	static final Log logger = LogFactory.getLog(SwParse.class);
	static DecimalFormat df2 = new DecimalFormat("#.##");
	static DecimalFormat df0 = new DecimalFormat("#");
//	private static final ThreadLocal<DecimalFormat> dff = new ThreadLocal<DecimalFormat>() {
//		protected DecimalFormat initialValue() {
//			return new DecimalFormat();
//		};
//	}
	static String Starturl = "http://nweb.cjh.com.cn/sssqold.html";

	public List<JsonModel> getData() {
//		DecimalFormat df = dff.get();
//		df.applyPattern("#.##");
		Document doc;
		try {
			doc = Jsoup.connect(Starturl).userAgent("bbb").timeout(120000).get();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<JsonModel>();
		}
		// fastJson测试
		// 获取待解析的html文件
		String html = doc.html();
		List<JsonModel> swJsonData = new ArrayList<JsonModel>();
		// Pattern data1 = Pattern.compile("预告片\":(.*?)\\,(\"拍摄花絮|\"精彩片段)");
		Pattern data1 = Pattern.compile("\\[\\{(.*?)\\}\\]");
		Matcher dataMatcher1 = data1.matcher(html);
		String da1 = "";
		while (dataMatcher1.find()) {
			// 待解析的json字符串
			da1 = dataMatcher1.group(1);
		}
		if (da1.length() != 0) {
			da1 = "[{" + da1 + "}]";
			List<JsonModel> jsonmodel1 = JSON.parseArray(da1, JsonModel.class);
			for (JsonModel jso : jsonmodel1) {
				JsonModel model = new JsonModel();
				String stnm = jso.getStnm();
				String z = "-";
				String q = "-";
				String oq = "-";

				float tempZ = 0, tempQ = 0, tempOq = 0;
				if (jso.getZ() != null && jso.getZ() != "") {
					tempZ = Float.parseFloat(jso.getZ());
					z = df2.format(tempZ * 1).toString();
				}
				tempQ = Float.parseFloat(jso.getQ());
				if (tempQ > 0) {
					q = df0.format(tempQ * 1).toString();
				}
				tempOq = Float.parseFloat(jso.getOq());
				if (tempOq > 0) {
					oq = df0.format(tempOq * 1).toString();
				}
				String ioqTd = q;
				if (oq != "-") {
					ioqTd = q + "(入)<br/>" + oq + "(出)";
				}
				model.setStnm(stnm);//站名
				model.setTm(getTime(jso.getTm()));//时间
				model.setZ(z);//水位
				model.setOq(ioqTd);//流量
				if(jso.getWptn().equals("4")) {
					model.setDesc("落");
					model.setSw_type(0);
				}else if(jso.getWptn().equals("5")) {
					model.setDesc("涨");
					model.setSw_type(2);
				}else {
					model.setDesc("平");
					model.setSw_type(1);
				}
				swJsonData.add(model);
				logger.info("stnm: " + stnm);
			}
		}

		return swJsonData;
	}

	String getTime(String ntime) {
		Date now = new Date(Long.parseLong(ntime));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sf.format(now);
	}

}
