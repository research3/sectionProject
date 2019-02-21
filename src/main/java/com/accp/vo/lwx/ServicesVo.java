package com.accp.vo.lwx;

import com.accp.pojo.Servicetype;
import com.accp.pojo.User;

public class ServicesVo extends com.accp.pojo.Services{
		// 商家
		private User user;
		// 服务类型
		private Servicetype serviceType;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Servicetype getServiceType() {
			return serviceType;
		}

		public void setServiceType(Servicetype serviceType) {
			this.serviceType = serviceType;
		}
}
