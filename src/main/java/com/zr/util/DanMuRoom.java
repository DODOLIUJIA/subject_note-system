package com.zr.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.websocket.Session;

import net.sf.json.JSONObject;

public class DanMuRoom {
	public static ArrayList<Session> room = new ArrayList<Session>();
	public static Hashtable<String, String> info = new Hashtable<String, String>();

	public static void sendMsgToOthers(String msg) {
		DanMuRoom.info.put("message", msg);
		JSONObject js = JSONObject.fromObject(DanMuRoom.info);
		for (Session session : room) {
			try {
				if (session != null)
					session.getBasicRemote().sendText(js.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
