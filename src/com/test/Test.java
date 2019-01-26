package com.test;

import java.util.Arrays;

import com.beans.AdminInfo;
import com.util.HibUtil;

public class Test {
	public static void main(String[] args) {
		/*AdminInfo admin = new AdminInfo();
		admin.setPassword("123456");
		admin.setUserName("aaa");
		// System.out.println(HibUtil.add(admin));
		// System.out.println(HibUtil.select(3));
		AdminInfo obj =(AdminInfo) HibUtil.select(3);
		obj.setPassword("123");
		// HibUtil.delete(obj);
		HibUtil.update(obj);*/
		
		float [] a={2,1.3f,5,4,3,6,9,100,-1,0,-100.5f};
		Arrays.sort(a);
		System.out.println(a[6]);
	}
	
	public static void min(int [] array,int k){
		
	}
}
