package Utilitary;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import service.QueryService;

public class Metodo {
	//date format
	public static String dateFormat(String date) {
		try {
			String dateFormat = date.substring(0, 10);
			String[] dateSort = dateFormat.split("-");
			dateFormat = dateSort[2] + "/" + dateSort[1] + "/" + dateSort[0];
			
			return dateFormat;
		}
		catch(Exception e) {
			return date;
		}
	}
	
	//alert
	public static void invalidDocumentError() {
		JOptionPane.showMessageDialog(null, "Documento inválido!", "Atenção!", JOptionPane.WARNING_MESSAGE);
	}
	
	//background color
	public static void setStyle(JPanel[] panels, int red, int green, int blue) {
		for(int i = 0; i < panels.length; i++) {
			panels[i].setBackground(new Color(red, green, blue));
		}
	}
	
	public static void setStyle(JTextField[] fields, int red, int green, int blue) {
		try {
			for(int i = 0; i < fields.length; i++) {
				fields[i].setBackground(new Color(red, green, blue));
				fields[i].setBorder(BorderFactory.createLineBorder(new Color(51,51,51)));
				fields[i].setForeground(new Color(10,10,80));
				fields[i].setEditable(false);
			}
		}
		catch(NullPointerException ne) {
			ne.printStackTrace();
		}
	}
	
	//foreground color
	public static void setForegroundColor(JLabel[] labels, int red, int green, int blue) {
		for(int i = 0; i < labels.length; i++) {
			labels[i].setForeground(new Color(red, green, blue));
		}
	}
	
	
	//tabbed pane
	public static void setBorderStyle(JTabbedPane tp) {
		for(int i = 0; i <= 2; i++) {
			tp.setBackgroundAt(i, new Color(64,64,64));
			tp.setForegroundAt(i, Color.WHITE);
		}
		tp.setUI(new BasicTabbedPaneUI() {
			   @Override
			   protected void installDefaults() {
			       super.installDefaults();
			       highlight = new Color(64,64,64);
			       lightHighlight = new Color(51,51,51);
			       shadow = new Color(51,51,51);
			       darkShadow = Color.black;
			       focus = null;
			       
			   }
			});
	}
	
	//button
	public static void setButtonLayout(JButton[] btn) {
		for(int i = 0; i < btn.length; i++) {
			btn[i].setBackground(new Color(77, 77, 77));
			btn[i].setForeground(new Color(204,204,204));
			//btn[i].setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
		}
	}
	
	//limpa campos
	public static void clearFields(JTextField[] field) {
		for(int i = 0; i < field.length; i++) {
			field[i].setText("");
		}
	}
	
	//cocatena vetor
	public static JTextField[] sumVector(JTextField[] arg1, JTextField[] arg2) {
		int size1 = 0, size2 = 0;
		
		try {
			size1 = arg1.length;
		}
		catch(NullPointerException ne) {
			ne.printStackTrace();
		}
		
		try {
			size2 = arg2.length;
		}
		catch(NullPointerException ne) {
			ne.printStackTrace();
		}
		
		JTextField[] aux = new JTextField[size1 + size2];
		int i = 0;
		
		for(int j = 0; j < size1; j++) {
			aux[i] = arg1[j];
			i++;
		}
		
		for(int k = 0; k < size2; k++) {
			aux[i] = arg2[k];
			i++;
		}
		
		return aux;
	}
	
	public static JLabel[] sumVector(JLabel[] arg1, JLabel[] arg2) {
		int size1 = 0, size2 = 0;
		
		try {
			size1 = arg1.length;
		}
		catch(NullPointerException ne) {
			ne.printStackTrace();
		}
		
		try {
			size2 = arg2.length;
		}
		catch(NullPointerException ne) {
			ne.printStackTrace();
		}
		
		JLabel[] aux = new JLabel[size1 + size2];
		int i = 0;
		
		for(int j = 0; j < size1; j++) {
			aux[i] = arg1[j];
			i++;
		}
		
		for(int k = 0; k < size2; k++) {
			aux[i] = arg2[k];
			i++;
		}
		
		return aux;
	}
	
	//exception para documentos invalidos
	public static void exceptionFillDocument(JTextField[] fields) {
		clearFields(fields);

		QueryService qs = new QueryService();
		qs.create();

		Metodo.invalidDocumentError();
	}
	
	//set gridbaglayout
	public static void setGridBagLayout(JPanel pnl, GridBagConstraints gbc, JTextField[] fields, JLabel[] labels) {
		
		for(int row = 0; row < fields.length; row++) {
			gbc.gridy = row;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.weighty = 1.0;
			gbc.fill = GridBagConstraints.BOTH;
			
			for(int column = 0; column <= 1; column++) {
				gbc.gridx = column;
				
				if(column == 0) {
					gbc.weightx = 1.0;
					pnl.add(labels[row], gbc);
				}
				else {
					gbc.weightx = 9.0;
					pnl.add(fields[row], gbc);
				}
			}
			
		}
	}
}
