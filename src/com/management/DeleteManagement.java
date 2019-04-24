package com.management;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
 
import com.database.DBConnection;
 
/**
 * 删除信息
 * @author Administrator
 *
 */
public class DeleteManagement extends JFrame implements ActionListener {
 
	private static final long serialVersionUID = 1L;
 
	JLabel label = new JLabel("删除信息",JLabel.CENTER);
	
	JLabel JLNumber = new JLabel("学号");
	JTextField JTNumber = new JTextField();
	
	JLabel JLName = new JLabel("姓名");
	JTextField JTName = new JTextField();
	
	JButton ensureBtn = new JButton("确定");
	JButton nextBtn = new JButton("重置");
	JButton cancelBtn = new JButton("取消");
	
	public DeleteManagement() {
		this.setTitle("删除信息");
		this.setLayout(null);
		label.setBackground(Color.red);  //label前景色为红色
		label.setFont(new Font("宋体", Font.HANGING_BASELINE, 19));
		label.setBounds(170, 20, 100, 20);
		this.add(label);
		//学号
		JLNumber.setBounds(120, 60, 30, 25);
		JTNumber.setBounds(150, 60, 120, 25);
		this.add(JLNumber);
		this.add(JTNumber);
		
		//姓名
		JLName.setBounds(120, 100, 30, 20);
		JTName.setBounds(150, 100, 120, 25);
		this.add(JLName);
		this.add(JTName);
		
		
		ensureBtn.setBounds(100, 320, 60, 25);
		nextBtn.setBounds(170, 320, 60, 25);
		cancelBtn.setBounds(240, 320, 60, 25);
		//添加事件监听
		ensureBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		this.add(ensureBtn);
		this.add(nextBtn);
		this.add(cancelBtn);
		
		this.setVisible(true);	
		this.setSize(400, 400); //设置窗口的大小
		this.setLocationRelativeTo(null);//窗体居中显示
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ensureBtn) {
			
			Integer snumber = Integer.parseInt(JTNumber.getText());
			
			//检索学生
			String sql = "select * from students where id='"+snumber+"'";
			//打开数据库连接并创建Statement对象
			try {
				Statement stm = DBConnection.getCon().createStatement();
				ResultSet rs = stm.executeQuery(sql);
				if(rs.next()) {
					//删除一条记录
					sql = "delete from students where id ='"+snumber+"'";
					int i = stm.executeUpdate(sql);
					if(i>0) {
						JOptionPane.showMessageDialog(null, "删除成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "删除失败！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "该账号不存在！", "提示信息", JOptionPane.WARNING_MESSAGE);					
				}
				stm.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == nextBtn) {
			JTNumber.setText(null);
			JTName .setText(null);
		}
		
		if(e.getSource() == cancelBtn) {
			setVisible(false);
		}
		
	}
 
}
