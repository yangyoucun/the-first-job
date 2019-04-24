package com.login;
 
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.database.*;
import com.management.StudentManagement;
 
import javax.swing.*;
 
/**
 * 登录
 * @author Administrator
 *
 */
public class Login extends Frame implements ActionListener{
	private static final long serialVersionUID = 1L;
	//记录登录次数
	private int flag = 0;
	//创建标签、文本框、密码框对象
	JPanel panel = new JPanel();
	JLabel JLUserName = new JLabel("用户名：");
	JLabel JLUserPaw = new JLabel("密    码：");
	JTextField JTUserName = new JTextField();
	JPasswordField JTUserPaw = new JPasswordField();
	
	//创建按钮对象
	JButton login = new JButton("登录");
	JButton cancle = new JButton("取消");
	JLabel identity = new JLabel("身    份：");
	//创建下拉列表框对象
	JComboBox<String> JC = new JComboBox<String>();
	
	public Login() {
		this.setTitle("管理系统示例1.0（增删改查）");
		this.setLayout(null);
		
		this.setResizable(false);    //禁止改变窗口大小
 
		//姓名
		JLUserName.setBounds(100, 50, 60, 20);
		JTUserName.setBounds(200, 50, 100, 20);
		this.add(JLUserName);
		this.add(JTUserName);
		
		//密码
		JLUserPaw.setBounds(100, 100, 60, 20);
		JTUserPaw.setBounds(200, 100, 100, 20);
		this.add(JLUserPaw);
		this.add(JTUserPaw);
		
		//身份
		identity.setBounds(100, 150, 60, 20);
		JC.setBounds(200, 150, 100, 20);
		JC.addItem(new String("学生"));
		JC.addItem(new String("教师"));
		this.add(identity);
		this.add(JC);
		
		//登录、取消
		login.setBounds(100, 200, 60, 20);
		cancle.setBounds(240, 200, 60, 20);
		login.addActionListener(this);
		cancle.addActionListener(this);
		this.add(login);
		this.add(cancle);
		
		this.setVisible(true);
		this.setSize(400, 250); //设置窗口的大小
		this.setLocationRelativeTo(null);//窗体居中显示
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
		});
		
	}
 
	//身份验证
	private boolean logindb(String password, String sql) {
		//查询数据库
		ResultSet rs = null;
 
		//验证用户名是否存在
		try {
			rs = DBConnection.getCon().createStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//验证密码是否存在
		try {
			if(rs.next()&&rs.getString(1).equals(password)) {
				rs.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//处理登录事件
		if(e.getSource() == login) {
			
			//将文本框中包含的文本传给字符串name
			String name = JTUserName.getText();
			String password = new String(JTUserPaw.getPassword());
			
			//将当前所选项传给字符串box
			String box = (String)JC.getSelectedItem();
			String loginsql = null;
			
			if(box.equals("学生")) {
				loginsql ="select password from students where name ='"+name+"'";
				
				//登录成功后进入，否则返回提示信息
				if(logindb(password,loginsql)) {
					this.setVisible(false);
					new StudentManagement();
					System.out.println("学生登录成功！");
				}else {
					flag++;
					if(flag >= 3) {
						JOptionPane.showMessageDialog(this, "输入三次错误，退出登录！","提示信息",JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					}
					
					JOptionPane.showMessageDialog(this, "输入有误，请重新输入！","提示信息",JOptionPane.WARNING_MESSAGE);
				}
					
			}
			else if(box.equals("教师")) 
			{
				loginsql ="select password from students where name ='"+name+"'";
				if(logindb(password,loginsql))
					System.out.println("教师登录成功！");
			}
			
		}
		
		//处理取消事件
		if(e.getSource() == cancle) {
			System.out.println("退出成功！");
			System.exit(0);
		}
		
	}
	
 
	public static void main(String[] args) {
		//new Login();
		new StudentManagement();
	}
	
}
