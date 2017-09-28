package com.zr.util;

import javax.websocket.OnClose;
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
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Session id: "+session.getId()+" on close");
		DanMuRoom.room.remove(session);
	}

	@OnMessage
	public void message(String message,Session session) {
		System.out.println("message: "+message);
		DanMuRoom.sendMsgToOthers(message);
	}
}
