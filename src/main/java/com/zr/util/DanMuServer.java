package com.zr.util;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/DanMuServer")
public class DanMuServer {
	@OnOpen
	public void open(Session session) {
		System.out.println("Session id: "+session.getId()+" on  open");
		
		DanMuRoom.room.add(session);
		DanMuRoom.info.put("num", String.valueOf(DanMuRoom.room.size()));
		
		DanMuRoom.sendMsgToOthers("有新朋友进来，你们可以发弹幕交流");
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Session id: "+session.getId()+" on close");
		
		DanMuRoom.room.remove(session);
		DanMuRoom.info.put("num", String.valueOf(DanMuRoom.room.size()));
		
		System.out.println("close size: "+DanMuRoom.room.size());
	}

	@OnMessage
	public void message(String message,Session session) {
		System.out.println("message: "+message);
		
		DanMuRoom.sendMsgToOthers(message);
	}
	
	@OnError
	public void error(Session session, Throwable error){
		System.out.println("Session id: "+session.getId()+" on error");
		System.out.println("error size: "+DanMuRoom.room.size());
	}
}
