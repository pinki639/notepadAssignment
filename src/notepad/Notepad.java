package notepad;
import javax.swing.*;//jframe jfilechooser
import java.awt.*;//image
        
import java.awt.event.*;//key event
import javax.swing.filechooser.*;//FileNameExtensionFilter 
import java.io.*;
public abstract class Notepad extends JFrame implements ActionListener{
    JTextArea area;
    String text;
    Notepad(){

        setTitle("Notepad");
        
        //ImageIcon notepadIcon=new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
        //Image icon=notepadIcon.getImage();
       //setImageIcon(icon);
         
                
        JMenuBar menubar=new JMenuBar();
        menubar.setBackground(Color.WHITE);
        JMenu file = new JMenu("File");
        file.setFont(new Font("ARIAL",Font.PLAIN,14));
        
        JMenuItem newdoc=new JMenuItem("NEW");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        //newdoc.setAccelerator(keyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));  both lines are similer but thisline will not work
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        JMenuItem save=new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        
        JMenuItem print=new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        
        JMenuItem exit =new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,ActionEvent.CTRL_MASK));
        
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
        
        menubar.add(file);
        
       
        JMenu edit=new JMenu("Edit");
        edit.setFont(new Font("ARIAL",Font.PLAIN,14));
        
        JMenuItem copy=new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        
        JMenuItem paste=new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        
        
        JMenuItem cut=new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        
        JMenuItem selectAll=new JMenuItem("Select All");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);
        
        menubar.add(edit);
        
        JMenu helpmenu=new JMenu("Help");
        helpmenu.setFont(new Font("ARIAL",Font.PLAIN,14));
        
        JMenuItem help=new JMenuItem("About");
        help.addActionListener(this);

        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
        
        helpmenu.add(help);

        menubar.add(helpmenu);
         
        setJMenuBar(menubar);//
        
        area=new JTextArea();
        area.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);//word ko break nhi kerega  
        
        add(area);
        
        JScrollPane pane=new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder()); //border ko manage kerega

        add(pane);
        setExtendedState(JFrame.MAXIMIZED_BOTH);//ye hmare frame ko laptop ki screen ke size ka open kerega
        

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("New")){
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open")){  //this is used to open txt file in text area
            JFileChooser chooser=new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);//chose only txt by restrict
            FileNameExtensionFilter restrict=new FileNameExtensionFilter("only.txt files","txt");
            chooser.addChoosableFileFilter(restrict);
             int action=chooser.showOpenDialog(this);
             if(action!=JFileChooser.APPROVE_OPTION){
                 return;
             }
             File file=chooser.getSelectedFile();
             try{
                 BufferedReader reader=new BufferedReader(new FileReader(file));//buffer reader is used for read the file
                 area.read(reader, null);
             }
             catch(Exception e){
                 e.printStackTrace();
             }
        }
        //save
        else if(ae.getActionCommand().equals("Save")){
            JFileChooser saveas=new JFileChooser();
            saveas.setApproveButtonText("Save");
            int action=saveas.showOpenDialog(this);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }
            File filename=new File(saveas.getSelectedFile()+".txt");
            BufferedWriter outFile=null;
            try{
                outFile=new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if (ae.getActionCommand().equals("Exit")){
            System.exit(0);
            
        }
        else if(ae.getActionCommand().equals("Copy")){
          text=area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste")){
            area.insert(text, area.getCaretPosition());
        }
        else if (ae.getActionCommand().equals("Cut")){
          text=area.getSelectedText();//copy
          area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
          
        }
        else if (ae.getActionCommand().equals("Select All")){
        area.selectAll();
          
    }
        //help
        else if(ae.getActionCommand().equals("About")){
            new About().setVisible(true);
        }

    }


    public static void main(String[] args) {
        new Notepad() {};
    

}
}

