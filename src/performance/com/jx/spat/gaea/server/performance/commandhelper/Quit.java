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
package com.jx.spat.gaea.server.performance.commandhelper;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.MessageEvent;

import com.jx.spat.gaea.server.contract.context.GaeaContext;
import com.jx.spat.gaea.server.performance.Command;
import com.jx.spat.gaea.server.performance.CommandType;

/**
 * 
 * @author Service Platform Architecture Team (spat@58.com)
 */
public class Quit extends CommandHelperBase {

	@Override
	public Command createCommand(String commandStr) {
		if(commandStr != null && !commandStr.equalsIgnoreCase("")) {
			if(commandStr.equalsIgnoreCase("quit")) {
				Command entity = new Command();
				entity.setCommandType(CommandType.Quit);
				return entity;
			}
		}
		return null;
	}
	
	
	@Override
	public void execCommand(Command command, MessageEvent event) throws Exception {
		if(command.getCommandType() == CommandType.Quit) {
			logger.info("quit monitor");
			event.getChannel().close();
		}
	}
	
	@Override
	public void messageReceived(GaeaContext context) {
		// do nothing
	}
	
	@Override
	public void removeChannel(Command command, Channel channel) {
		// do nothing
	}
	
	@Override
	public int getChannelCount() {
		return 0;
	}
}