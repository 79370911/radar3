package radar.UI.Content;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import radar.SpringUtil;
import radar.ServiceImpl.AnalysisServiceImpl;
import radar.Tools.Init;
import radar.UI.AcuteForecast.ABody1;
import radar.UI.AcuteForecast.ABody2;
import radar.UI.AcuteForecast.ABody3;
import radar.UI.AcuteForecast.ATop1;
import radar.UI.AcuteForecast.ATop2;
import radar.UI.AcuteForecast.ATop3;

public class Radartype extends ContentPanel implements Init{

	private static final long serialVersionUID = -6029898662509471669L;
	AnalysisServiceImpl analysisServiceImpl = (AnalysisServiceImpl) SpringUtil.getBean("AnalysisServiceImpl");
	private ATop1 top1;
	private ATop2 top2;
	private ATop3 top3;
	private ABody1 body1;
	private ABody2 body2;
	private ABody3 body3;
	public Radartype() {		
		remove(contentFoot);
		initUI();
		Action();	

	}

	@Override
	public void initUI() {
		contentTop.setLayout(new BorderLayout(0, 0));
		contentBody.setLayout(new BorderLayout(0, 0));
		set1();
	}

	private void set1() {
		top1 = new ATop1();	
		top1.getRadarType().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value1 = (String) e.getItem();
					String value2 = (String) top1.getLocationType().getSelectedItem();
					int columnIndex1 = 1;
					int columnIndex2 = 2;
					if(value1.equals("All"))
							columnIndex1 = -1;
					if(value2.equals("All"))
						columnIndex2 = -1;
					body1.getTable().selectDataByColumnIndexsAndValues(columnIndex1,value1,columnIndex2,value2);
				}
			}
		});
		top1.getLocationType().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value1 = (String) top1.getRadarType().getSelectedItem();
					String value2 = (String) e.getItem();
					int columnIndex1 = 1;
					int columnIndex2 = 2;
					if(value1.equals("All"))
							columnIndex1 = -1;
					if(value2.equals("All"))
						columnIndex2 = -1;
					body1.getTable().selectDataByColumnIndexsAndValues(columnIndex1,value1,columnIndex2,value2);
				}
				
			}
		});
		
		body1 = new ABody1();
		contentTop.add(top1);
		contentBody.add(body1);
		body1.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(body1.getTable().getValueAt(body1.getTable().getSelectedRow(), 0) != null) {
					int row = body1.getTable().getSelectedRow();		//行数
					int[] TL=analysisServiceImpl.getTL(row);
					contentTop.remove(top1);
					contentBody.remove(body1);
					set2(TL[0],TL[1]);
					validate();
					repaint();	
				}
							
			}
		});
	}

	private void set2(int typeid, int location) {
		top2 = new ATop2(typeid,location);		
		//页面切换
		top2.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top2);
				contentBody.remove(body2);
				contentTop.add(top1);
				contentBody.add(body1);
				validate();
				repaint();
			}
		});
		top2.getRadioButton_1().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top2);
				contentBody.remove(body2);
				set3(typeid,location);
				validate();
				repaint();
			}			
		});
		
		body2 = new ABody2(typeid,location);		
		contentTop.add(top2);
		contentBody.add(body2);
		
	}
	
	private void set3(int typeid, int location) {
		top3 = new ATop3(typeid,location);		
		top3.getTitle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top3);
				contentBody.remove(body3);
				contentTop.add(top1);
				contentBody.add(body1);
				validate();
				repaint();
			}
		});
		top3.getRadioButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentTop.remove(top3);
				contentBody.remove(body3);
				set2(typeid,location);
				validate();
				repaint();
			}			
		});
		top3.getTimeButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(body3 != null) {
					contentBody.remove(body3);
					body3 = new ABody3(typeid,location,top3.getSDate().getText(),top3.getEDate().getText());
					contentBody.add(body3);
					validate();
					repaint();
				}
			}
		});
		
		body3 = new ABody3(typeid,location,top3.getSDate().getText(), top3.getEDate().getText());		
		contentTop.add(top3);
		contentBody.add(body3);
		
	}
	
	@Override
	public void Action() {
		// TODO Auto-generated method stub
		
	}


}
