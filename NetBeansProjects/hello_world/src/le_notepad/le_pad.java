/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
**https://github.com/six519/Java-Notepad/blob/master/Notepad.java
 */
package le_notepad;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
// import java.awt.datatransfer.*;
/*
add JTextPane
*/


public class le_pad extends JFrame implements ActionListener, WindowListener {
	
	JTextArea jta=new JTextArea();
	File fnameContainer;
	
	public le_pad(){

		Font fnt=new Font("Arial",Font.PLAIN,36);
		Container con=getContentPane();
		
		JMenuBar jmb=new JMenuBar();
		JMenu jmfile = new JMenu("File");
		JMenu jmedit = new JMenu("Edit");
		JMenu jmformat = new JMenu("Format");
		
		con.setLayout(new BorderLayout());
		//trying to add scrollbar
		JScrollPane sbrText = new JScrollPane(jta);
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sbrText.setVisible(true);
		
		
		jta.setFont(fnt);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		
		con.add(sbrText);

		createMenuItem(jmfile,"New");
		createMenuItem(jmfile,"Open");
		createMenuItem(jmfile,"Save");
		jmfile.addSeparator();
		createMenuItem(jmfile,"Exit");
		
		createMenuItem(jmedit,"Replace");
		createMenuItem(jmedit,"Go to...");
		
		createMenuItem(jmformat,"Green");
                createMenuItem(jmformat,"Red");
		
		jmb.add(jmfile);
		jmb.add(jmedit);
		jmb.add(jmformat);
		
		setJMenuBar(jmb);
		setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
		addWindowListener(this);
		setSize(500,500);
		setTitle("Untitled.txt - Notepad");
				
		setVisible(true);
	
	}

	public void createMenuItem(JMenu jm,String txt){
		JMenuItem jmi=new JMenuItem(txt);
		jmi.addActionListener(this);
		jm.add(jmi);
	}
	
	public void actionPerformed(ActionEvent e){	
		JFileChooser jfc=new JFileChooser();
		
		if(e.getActionCommand().equals("New")){ 
			//new
			this.setTitle("Untitled.txt - Notepad");
			jta.setText("");
			fnameContainer=null;
		}else if(e.getActionCommand().equals("Open")){
			//open
			int ret=jfc.showDialog(null,"Open");
			
			if(ret == JFileChooser.APPROVE_OPTION)
			{
				try{
					File fyl=jfc.getSelectedFile();
					OpenFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName()+ " - Notepad");
					fnameContainer=fyl;
				}catch(IOException ers){}
			}
			
		}else if(e.getActionCommand().equals("Save")){
			//save
			if(fnameContainer != null){
				jfc.setCurrentDirectory(fnameContainer);		
				jfc.setSelectedFile(fnameContainer);
			}
			else {
				//jfc.setCurrentDirectory(new File("."));
				jfc.setSelectedFile(new File("Untitled.txt"));
			}
			
			int ret=jfc.showSaveDialog(null);
				
			if(ret == JFileChooser.APPROVE_OPTION){
				try{
					
					File fyl=jfc.getSelectedFile();
					SaveFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName()+ " - Notepad");
					fnameContainer=fyl;
					
				}catch(Exception ers2){}
			}
			
		}else if(e.getActionCommand().equals("Exit")){
			//exit
			Exiting();
		}else if(e.getActionCommand().equals("Replace")){
			//replace
		}else if(e.getActionCommand().equals("Go to...")){
			//Go to...
                }else if(e.getActionCommand().equals("Green")){
			//Go to...
                }else if(e.getActionCommand().equals("Red")){
			//Go to...
                }
	}
	
	public void OpenFile(String fname) throws IOException {	
		//open file code here
		BufferedReader d=new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
		String l;
		//clear the textbox
		jta.setText("");
	
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
		while((l=d.readLine())!= null) {
			jta.setText(jta.getText()+l+"\r\n");
		}
		
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		d.close();
	}
        
	
	public void SaveFile(String fname) throws IOException {
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		DataOutputStream o=new DataOutputStream(new FileOutputStream(fname));
		o.writeBytes(jta.getText());
		o.close();		
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void windowDeactivated(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	
	public void windowClosing(WindowEvent e) {
		Exiting();
	}
	
	public void windowOpened(WindowEvent e){}
	
	public void Exiting() {
		System.exit(0);
	}
	
	public static void main (String[] args) {
		le_pad notp=new le_pad();	
	}
			
}
