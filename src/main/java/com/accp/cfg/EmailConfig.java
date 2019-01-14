package com.accp.cfg;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class EmailConfig {
	@Scheduled(cron = "0 0/1 * * * ?")
	public void setTimeOutEmail() {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
//		if (ListVo.emailList != null) {
//			for (String item : ListVo.emailList.keySet()) {
//				long diff = new Date().getTime() - ListVo.emailList.get(item).getTime().getTime();
//				if (diff % nd % nh / nm >= 5) {
//					ListVo.emailList.remove(item);
//				}
//			}
//		}
	}
}