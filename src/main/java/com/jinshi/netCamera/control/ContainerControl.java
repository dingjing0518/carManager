package com.jinshi.netCamera.control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jinshi.netCamera.view.ContainerView;

public class ContainerControl {
	private ContainerView containerView;
	
	public ContainerControl(){
		containerView = new ContainerView();
		initListener();
	}

	
	private void initListener() {
		containerView.getBtn_clearScreen().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				containerView.getStatus().setText("");
			}
		});
	}

	public ContainerView getContainerView() {
		return containerView;
	}
	
	//增加选项卡
	public void addTabbed(String title,Component component){
		containerView.getTabbed().addTab(title, component);
	}
	//写状态栏
	public void message(String message){
		containerView.getStatus().append("\n\n>>>>>>>>>>>>>>>\n");
		containerView.getStatus().append(message);
		containerView.getStatus().setCaretPosition(containerView.getStatus().getText().length());
	}
}
