package com.management;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.*;
 
import com.database.DBConnection;
import com.login.Students;
 
public class SearchGrade extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private Object [][]rowData;
	private Object []column = {"学号","姓名","语文","数学","班级","学院"};  //表头
	JLabel JLNumber = new JLabel("学号");
	JTextField JTNumber = new JTextField(16);
	
	JPanel epanel = new JPanel();
	JPanel wpanel = new JPanel();
	
	JButton searchBtn = new JButton("查询个人");
	JButton searchAllBtn = new JButton("查询全部");
	JButton nextBtn = new JButton("重置");
	JButton cancelBtn = new JButton("取消");
	
	public SearchGrade() {
		this.setTitle("查询成绩");
		this.setLayout(new BorderLayout());
		this.setLocation(400, 400);
		//标签和输入框
		wpanel.add(searchAllBtn);
		wpanel.add(JLNumber);
		wpanel.add(JTNumber);
		
		//三个按钮添加事件
		searchAllBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		
		epanel.add(searchBtn);
		epanel.add(nextBtn);
		epanel.add(cancelBtn);
			
		
		this.add(epanel, BorderLayout.EAST);
		this.add(wpanel, BorderLayout.WEST);
		
		this.pack();
		this.setLocationRelativeTo(null);//窗体居中显示
		this.setVisible(true);
	}
	
	
	
	/**
	 * 设置表格的样式
	 * @param num
	 */
	public void setTable(int num) {
		
		this.setVisible(false);
		
		JFrame jf =new JFrame("查询结果");
		jf.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		
		//表格所有行
		Object [][]rowData = queryData(num);
		
		JTable table = new JTable(rowData,column);
		//设置表格内容颜色
		table.setForeground(Color.BLACK);	//字体颜色
		table.setFont(new Font(null, Font.PLAIN, 14));	//字体样式
		table.setSelectionBackground(Color.DARK_GRAY);	//选中后字体背景
		table.setSelectionForeground(Color.LIGHT_GRAY);	//选中后字体颜色
		table.setGridColor(Color.GRAY);	//网格颜色
 
		//设置表头
		//设置表头字体样式
		table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
		//表头名称字体颜色
		table.getTableHeader().setForeground(Color.RED);
		//不允许手动改变列宽
		table.getTableHeader().setResizingAllowed(false);
		//不允许拖动重新排列各列
		table.getTableHeader().setReorderingAllowed(false);
 
		//设置行高30
		table.setRowHeight(30);
		//列宽设置为100
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
 
		//设置滚动面板视口大小
		table.setPreferredScrollableViewportSize(new Dimension(550,400));
		//把表格放到滚动面板中（自动添加到顶部）
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		jf.add(panel);
		
		jf.pack();
		jf.setContentPane(panel);
		jf.setVisible(true);
				
	}
	
	/**
	 * 查询所有用户数据
	 * @return	查找到的数据生成的list集合
	 */
	public List<Students> queryAllUser(){
 
		String sql="select * from students,grades where grades.sid="+"students.id";
		List<Students> list=new ArrayList<Students>();
		try {
			Connection conn=DBConnection.getCon();
			//获取结果集
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				Students student=new Students();
				//跨表列号相加
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setSex(rs.getString(3));
				student.setClasses(rs.getString(5));
				student.setAcademy(rs.getString(6));
				
				student.setMath(rs.getFloat(9));
				student.setChniese(rs.getFloat(10));
				
				list.add(student);
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
 
	/**
	 * 查询一个用户数据(用户id)
	 * @return	查找到的用户数据返回
	 */
	public Students queryUser(Integer snumber){
 
		Students student = new Students();
		//关联两张表查询
		String sql="select * from students,grades where grades.sid="+"students.id and students.id="+snumber;
 
		try {
			Statement stm = DBConnection.getCon().createStatement();
			//结果集
			ResultSet rs=stm.executeQuery(sql);
			
			if(rs.next()){
					//跨表列号相加
					student.setId(rs.getInt(1));
					student.setName(rs.getString(2));
					student.setSex(rs.getString(3));
					student.setClasses(rs.getString(5));
					student.setAcademy(rs.getString(6));
					
					student.setMath(rs.getFloat(9));
					student.setChniese(rs.getFloat(10));				
			}
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchBtn) {
			int num = Integer.parseInt(JTNumber.getText());
			if(num >0) 
				setTable(num);
			else
				JOptionPane.showMessageDialog(null, "输入数据错误！");
		}
		if(e.getSource() == searchAllBtn) {
			setTable(0);
		}
		
		if(e.getSource() == nextBtn) {
			JTNumber.setText(null);
		}
		
		if(e.getSource() == cancelBtn) {
			this.setVisible(false);
		}
		
	}
		
 
	 /**
     * 生成表格数据
     * @return 表格数据二维数组
     */
    public Object[][] queryData(int num){
               
		if(!(queryUser(num)==null)) {
			Students student = new Students();
			student = queryUser(num);
			//行设置为1,防止数组越界  "学号","姓名","班级","学院","语文","数学"
			rowData=new Object[1][column.length];
			rowData[0][0]=student.getId();
			rowData[0][1]=student.getName();
			rowData[0][2]=student.getChniese();
			rowData[0][3]=student.getMath();
			rowData[0][4]=student.getClasses();
			rowData[0][5]=student.getAcademy();
			
		}
		
		if(num == 0) {
			List<Students> list=queryAllUser();
			rowData=new Object[list.size()][column.length];
			System.out.println("查询全部信息");
			for(int i=0;i<list.size();i++){
				for(int j=0;j<column.length;j++) {
					rowData[i][0]=list.get(i).getId();
					rowData[i][1]=list.get(i).getName();
					rowData[i][2]=list.get(i).getChniese();
					rowData[i][3]=list.get(i).getMath();
					rowData[i][4]=list.get(i).getClasses();
					rowData[i][5]=list.get(i).getAcademy();
					System.out.print(rowData[i][j]+"    ");
				}
				System.out.println();
			}
        }
		return rowData;
    }
 
 
 
}
 
 
