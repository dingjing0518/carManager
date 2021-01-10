package com.jinshi.netCamera.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import net.sdk.function.main.NET;
import com.jinshi.netCamera.control.ContainerControl;
import com.jinshi.netCamera.control.BasicConfControl;
import com.jinshi.netCamera.control.InitControl;
import com.jinshi.netCamera.control.PlateDeviceControl;
import com.jinshi.netCamera.control.ServiceConfControl;
import com.jinshi.netCamera.utils.StatusCode;
import com.jinshi.netCamera.view.MainJFrame;

public class MianPro
{
  public static void main(String[] args)
  {

    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        final NET net = NET.INSTANCE;

        MainJFrame mainJFrame = new MainJFrame();

        ContainerControl containerControl = new ContainerControl();


        int init = net.Net_Init();
        containerControl.message(StatusCode.getStatusCode(init, "Net_Init()"));

        InitControl initControl = new InitControl(net, containerControl);
        BasicConfControl basicConfControl = new BasicConfControl(net, containerControl);
        ServiceConfControl serviceConfControl = new ServiceConfControl(net, containerControl);
        PlateDeviceControl plateDeviceControl = new PlateDeviceControl(net, containerControl);


        containerControl.addTabbed("初始化", initControl.getInitView());
        containerControl.addTabbed("设备配置&基本配置+设备维护", basicConfControl.getBasicConfView());
        containerControl.addTabbed("设备配置&业务配置", serviceConfControl.getServiceConfView());
        containerControl.addTabbed("停车场设备接口", plateDeviceControl.getPlateDeviceView());

        mainJFrame.add(containerControl.getContainerView());

        mainJFrame.setTitle("相机测试程序");



        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        mainJFrame.setLocation((screen.width - mainJFrame.getWidth()) / 2, (screen.height - mainJFrame.getHeight()) / 2);


        mainJFrame.addWindowListener(new WindowAdapter()
        {
          public void windowClosing(WindowEvent e)
          {
            net.Net_UNinit();
          }
        });
        mainJFrame.setDefaultCloseOperation(3);
        mainJFrame.setVisible(true);
      }
    });
  }
}