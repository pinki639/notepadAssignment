
package notepad;
import javax.swing.*;
import java.awt.*;//image
import java.awt.event.*;

public class About extends JFrame implements ActionListener{
    About(){
       setBounds(400,100,600,500);//size btata h frame ka
        setLayout(null);
//        
      ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("notepad/icons/windows.png"));
       Image i2=i1.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
           JLabel headerIcon=new JLabel(i3); //awt ka object swing m 
           headerIcon.setBounds(70, 40, 400, 80);
          add(headerIcon);
          
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
       Image i5=i4.getImage().getScaledInstance(70,70, Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
           JLabel icon=new JLabel(i6); //awt ka object swing m 
           icon.setBounds(50,180 , 70, 70);
          add(icon);
          
          JLabel text=new JLabel("<html> code with me</html>");
          text.setBounds(150,100,500,200);
          text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
          add(text);
          
          JButton b1=new JButton("ok");
          b1.setBounds(150,350,120,25);
          b1.addActionListener(this);
          add(b1);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    public static void main(String[] args){
        new About();
    }
}
