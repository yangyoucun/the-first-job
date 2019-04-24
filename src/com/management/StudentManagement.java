package com.management;
 
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.*;
 
public class StudentManagement extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JMenuBar menubar = new JMenuBar(); //创建菜单栏对象
	//一级菜单
	JMenu informationMenu = new JMenu("信息");		
	JMenu searchMenu = new JMenu("查询");	
	JMenu otherMenu = new JMenu("其他");	
	
	JMenuItem addMenu = new JMenuItem("增加信息");
	JMenuItem deleteMenu = new JMenuItem("删除信息");
	JMenuItem alterMenu = new JMenuItem("修改信息");
	
	JMenuItem searchInforMenu = new JMenuItem("信息查询");
	JMenuItem searchGradeMenu = new JMenuItem("成绩查询");
	
	JMenuItem quitMenu = new JMenuItem("退出");
	JLabel label = new JLabel();
	
	
	public StudentManagement() {
		this.setTitle("学生基本信息");
		this.setLayout(new CardLayout());
		this.setJMenuBar(menubar);	//将菜单栏组件添加到容器
		this.setResizable(false);	//设置窗口大小不可变
		//this.setUndecorated(true);   //设置frame边框不可见
		//一级菜单添加到菜单栏及事件监听
		menubar.add(informationMenu);
		menubar.add(searchMenu);
		menubar.add(otherMenu);
		informationMenu.addActionListener(this);
		searchMenu.addActionListener(this);
		otherMenu.addActionListener(this);
		
		
		//二级菜单添加及事件监听
		informationMenu.add(addMenu);
		informationMenu.add(deleteMenu);
		informationMenu.add(alterMenu);
		addMenu.addActionListener(this);
		deleteMenu.addActionListener(this);
		alterMenu.addActionListener(this);
		
		searchMenu.add(searchInforMenu);
		searchMenu.add(searchGradeMenu);
		searchInforMenu.addActionListener(this);
		searchGradeMenu.addActionListener(this);
		
		otherMenu.add(quitMenu);
		quitMenu.addActionListener(this);
		
		label.setIcon(new ImageIcon("images/主界面.jpg"));
		this.add(label);
		
		this.setVisible(true);
		this.setSize(580, 400); //设置窗口的大小
		this.setLocationRelativeTo(null);//窗体居中显示
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
		});
		
	}
	
	@Override
	/**
	 * 事件监听
	 */
	public void actionPerformed(ActionEvent e) {
		
		//增加信息
		if(e.getSource() == addMenu) {
			new AddManagement();
		}
		//删除信息
		if(e.getSource() == deleteMenu) {
			new DeleteManagement();
		}
		//修改信息
		if(e.getSource() == alterMenu) {
			new AlterManagement();
		}
		//查询基本信息
		if(e.getSource() == searchInforMenu) {
			new SearchManagement();
		}
		
		//查询成绩
		if(e.getSource() == searchGradeMenu) {
			new SearchGrade();
		}
		
		//退出
		if(e.getSource() == quitMenu) {
			System.exit(0);
			//new UsingExit().setVisible(true);
		}
	}
	
}
