/*
 *  Copyright Beijing 58 Information Technology Co.,Ltd.
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package com.jx.spat.gaea.server.util.tools;

import java.util.Map;

import com.jx.spat.gaea.server.util.config.PropertiesHelper;


public class UnityFactory {
	
	private static Map<String,Object> mapConfig = null;
	
	private UnityFactory(String configPath) {
		try {
			PropertiesHelper ph = new PropertiesHelper(configPath);
			mapConfig = ph.getAllKeyValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Object lockHelper = new Object();
	private static UnityFactory unityFactory = null;
	
	public static UnityFactory getIntrance(String configPath) {
		if(unityFactory == null){
			synchronized(lockHelper){
				if(unityFactory == null){
					System.out.println("UnityFactory:"+configPath);
					unityFactory = new UnityFactory(configPath);
				}
			}
		}
		return unityFactory;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T create(Class<?> clazz) throws Exception {
		String value = mapConfig.get(clazz.getName()).toString();
		System.out.println("create:"+value);
		return (T)Class.forName(value).newInstance();
	}
}