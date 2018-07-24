package com.hottae.controller;

import com.hottae.bdaction.Action;
import com.hottae.bdaction.BoardAction;
import com.hottae.bdaction.BoardDeleteAction;
import com.hottae.bdaction.BoardMyListAction;
import com.hottae.bdaction.BoardReplyAction;
import com.hottae.bdaction.BoardReplyForm;
import com.hottae.bdaction.BoardSearchAction;
import com.hottae.bdaction.BoardUpdateAction;
import com.hottae.bdaction.BoardUpdateForm;
import com.hottae.bdaction.BoardViewForm;
import com.hottae.bdaction.BoardWriteAction;
import com.hottae.bdaction.BoardWriteForm;
import com.hottae.bdaction.MainForm;
import com.hottae.mbaction.MemberInfoUpdateForm;
import com.hottae.mbaction.MemberDeleteAction;
import com.hottae.mbaction.MemberDeleteForm;
import com.hottae.mbaction.MemberInfoForm;
import com.hottae.mbaction.MemberInfoUpdateAction;
import com.hottae.mbaction.MemberJoinAction;
import com.hottae.mbaction.MemberJoinForm;
import com.hottae.mbaction.MemberLoginAction;
import com.hottae.mbaction.MemberLoginForm;
import com.hottae.mbaction.MemberLogoutAction;
import com.hottae.naveraction.NaverSearchAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	private ActionFactory() {}
	
	public Action getAction(String cmd) { 
		Action action = null;
		if(cmd.equals("main")) {
			action = new MainForm();//-------------------------------------------------main
		} else if(cmd.equals("boardList")) {
			action = new BoardAction();
		} else if(cmd.equals("boardSearch")) {
			action = new BoardSearchAction();
		} else if(cmd.equals("selfBoard")){
			action = new BoardMyListAction();
		} else if(cmd.equals("write")) {
			action = new BoardWriteForm();
		} else if(cmd.equals("writeAction")) {
			action = new BoardWriteAction();
		} else if(cmd.equals("boardView")) {
			action = new BoardViewForm();
		} else if(cmd.equals("delete")) {
			action = new BoardDeleteAction();
		} else if(cmd.equals("reply")) {
			action = new BoardReplyForm();
		} else if(cmd.equals("replyAction")) {
			action = new BoardReplyAction();
		} else if(cmd.equals("update")) {
			action = new BoardUpdateForm();
		} else if(cmd.equals("updateAction")) {//-------------------------------------------------board
			action = new BoardUpdateAction();
		} else if(cmd.equals("nSearch")) {//-------------------------------------------------naverAPI
			action = new NaverSearchAction();
		} else if(cmd.equals("login")) {
			action = new MemberLoginForm();
		} else if(cmd.equals("loginAction")) {
			action = new MemberLoginAction();
		} else if(cmd.equals("logout")) {
			action = new MemberLogoutAction();
		} else if(cmd.equals("join")) {
			action = new MemberJoinForm();
		} else if(cmd.equals("joinAction")) {
			action = new MemberJoinAction();
		} else if(cmd.equals("info")) {
			action = new MemberInfoForm();
		} else if(cmd.equals("infoUpdate")) {
			action = new MemberInfoUpdateForm();
		} else if(cmd.equals("infoUpdateAction")) {
			action = new MemberInfoUpdateAction();
		} else if(cmd.equals("withdrawal")) {
			action = new MemberDeleteForm();
		} else if(cmd.equals("withdrawalAction")) {
			action = new MemberDeleteAction();
		}
		return action;
	}
}
