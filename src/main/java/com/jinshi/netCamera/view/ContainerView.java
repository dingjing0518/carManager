package com.jinshi.netCamera.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ContainerView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbed;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JTextArea status;
	private JButton btn_clearScreen;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public ContainerView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(1002, 506));
			this.setLayout(null);
			this.setBackground(new java.awt.Color(238,238,238));
			{
				jPanel1 = new JPanel();
				this.add(jPanel1);
				jPanel1.setBounds(310, 4, 686, 494);
				jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel1.setLayout(null);
				jPanel1.setBackground(new java.awt.Color(230,230,230));
				{
					tabbed = new JTabbedPane();
					jPanel1.add(tabbed);
					tabbed.setBounds(7, 6, 671, 481);
					tabbed.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
					tabbed.setBackground(new java.awt.Color(230,230,230));
				}
			}
			{
				btn_clearScreen = new JButton();
				this.add(getBtn_clearScreen());
				btn_clearScreen.setText("清屏");
				btn_clearScreen.setBounds(6, 6, 298, 30);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(6, 42, 298, 458);
				{
					status = new JTextArea();
					jScrollPane1.setViewportView(status);
					status.setLineWrap(true);
					status.setText("...");
					status.setBounds(6, 42, 298, 458);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JTextArea getStatus() {
		return status;
	}

	public JTabbedPane getTabbed() {
		return tabbed;
	}
	
	public JButton getBtn_clearScreen() {
		return btn_clearScreen;
	}

}
