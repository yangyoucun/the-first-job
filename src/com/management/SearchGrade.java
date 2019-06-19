/**
 * qichen206102057���д������
 */
package com.management;
//package ��䲻���У������Ʋ��������� package ���

//import��䲻Ҫʹ��ͨ���,��Ҫ����
//import���ɷ�Ϊ���¼��飬�������˳��ÿ����һ�����зָ�
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
	private Object []column = {"ѧ��","����","����","��ѧ","�༶","ѧԺ"};  //��ͷ

	JLabel JLNumber = new JLabel("ѧ��");
	JTextField JTNumber = new JTextField(16);

	JPanel epanel = new JPanel();
	JPanel wpanel = new JPanel();

	//���ð���
	JButton searchBtn = new JButton("��ѯ����");
	JButton searchAllBtn = new JButton("��ѯȫ��");
	JButton nextBtn = new JButton("����");
	JButton cancelBtn = new JButton("ȡ��");

	public SearchGrade(){
		this.setTitle("��ѯ�ɼ�");
		this.setLayout(new BorderLayout());
		this.setLocation(400, 400);

		//��ǩ�������
		wpanel.add(searchAllBtn);
		wpanel.add(JLNumber);
		wpanel.add(JTNumber);

		//������ť����¼�
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
		this.setLocationRelativeTo(null);  //���������ʾ
		this.setVisible(true);
	}



	/**
	 * ���ñ�����ʽ
	 * @param num
	 */
	public void setTable(int num){
		this.setVisible(false);

		JFrame jf =new JFrame("��ѯ���");
		jf.setLocationRelativeTo(null);
		JPanel panel = new JPanel();

		//���������
		Object [][]rowData = queryData(num);

		JTable table = new JTable(rowData,column);

		//���ñ��������ɫ
		table.setForeground(Color.BLACK);	            //������ɫ
		table.setFont(new Font(null, Font.PLAIN, 14));	//������ʽ
		table.setSelectionBackground(Color.DARK_GRAY);	//ѡ�к����屳��
		table.setSelectionForeground(Color.LIGHT_GRAY);	//ѡ�к�������ɫ
		table.setGridColor(Color.GRAY);	                //������ɫ

		//���ñ�ͷ
		table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  //���ñ�ͷ������ʽ
		table.getTableHeader().setForeground(Color.RED);                //��ͷ����������ɫ
		table.getTableHeader().setResizingAllowed(false);               //�������ֶ��ı��п�
		table.getTableHeader().setReorderingAllowed(false);             //�������϶��������и���


		table.setRowHeight(30);                                      //�����и�30
		table.getColumnModel().getColumn(1).setPreferredWidth(80);   //�п�����Ϊ100


		table.setPreferredScrollableViewportSize(new Dimension(550,400));  //���ù�������ӿڴ�С

		//�ѱ��ŵ���������У��Զ���ӵ�������
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		jf.add(panel);

		jf.pack();
		jf.setContentPane(panel);
		jf.setVisible(true);

	}

	/**
	 * ��ѯ�����û�����
	 * @return	���ҵ����������ɵ�list����
	 */
	public List<Students> queryAllUser(){
		String sql="select * from students,grades where grades.sid="+"students.id";
		List<Students> list=new ArrayList<Students>();
		try {
			Connection conn=DBConnection.getCon();

			//��ȡ�����
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				Students student=new Students();

				//����к����
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

		} catch (SQLException e){
			e.printStackTrace();
		}

		return list;

	}


	/**
	 * ��ѯһ���û�����(�û�id)
	 * @return	���ҵ����û����ݷ���
	 */
	public Students queryUser(Integer snumber){
		Students student = new Students();

		//�������ű��ѯ
		String sql="select * from students,grades where grades.sid="+"students.id and students.id="+snumber;
		try {
			Statement stm = DBConnection.getCon().createStatement();

			//�����
			ResultSet rs=stm.executeQuery(sql);
			if(rs.next()){

					//����к����
					student.setId(rs.getInt(1));
					student.setName(rs.getString(2));
					student.setSex(rs.getString(3));
					student.setClasses(rs.getString(5));
					student.setAcademy(rs.getString(6));

					student.setMath(rs.getFloat(9));
					student.setChniese(rs.getFloat(10));
			}
			stm.close();

		} catch (SQLException e){
			e.printStackTrace();
		}

		return student;
	}


	@Override
	public void actionPerformed(ActionEvent e){

		//��ѯ����
		if(e.getSource() == searchBtn){
			int num = Integer.parseInt(JTNumber.getText());
			if(num >0)
				setTable(num);
			else
				JOptionPane.showMessageDialog(null, "�������ݴ���");
		}

		//��ѯȫ��
		if(e.getSource() == searchAllBtn){
			setTable(0);
		}

		//����
		if(e.getSource() == nextBtn){
			JTNumber.setText(null);
		}

		//ȡ��
		if(e.getSource() == cancelBtn){
			this.setVisible(false);
		}

	}


	 /**
     * ���ɱ������
     * @return ������ݶ�ά����
     */
    public Object[][] queryData(int num){

		if(!(queryUser(num)==null)) {
			Students student = new Students();
			student = queryUser(num)

			//������Ϊ1,��ֹ����Խ��  "ѧ��","����","�༶","ѧԺ","����","��ѧ"
			rowData=new Object[1][column.length];
			rowData[0][0]=student.getId();
			rowData[0][1]=student.getName();
			rowData[0][2]=student.getChniese();
			rowData[0][3]=student.getMath();
			rowData[0][4]=student.getClasses();
			rowData[0][5]=student.getAcademy();

		}

		if(num == 0){
			List<Students> list=queryAllUser();
			rowData=new Object[list.size()][column.length];
			System.out.println("��ѯȫ����Ϣ");

			for(int i=0;i<list.size();i++){
				for(int j=0;j<column.length;j++){

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


