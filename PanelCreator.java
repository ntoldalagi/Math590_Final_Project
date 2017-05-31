import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Color;

public class PanelCreator {
  public static void main(String[] args) {
  //   JFrame frame = new JFrame("Plot Line Creator");
  //   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //   JLabel label = new JLabel("Click the button to select data and build line!");
  //   label.setHorizontalAlignment(JLabel.CENTER);
  //   label.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
  //   frame.getContentPane().add(label);
  //   frame.pack();
  //   frame.setLocationRelativeTo(null); //From stack overFlow
  //   frame.setVisible(true);
  // }
  PanelCreator p = new PanelCreator();
  System.out.println(p);
  System.out.println(p.hashCode());
  PanelCreator c = new PanelCreator();
  System.out.println(c);
  System.out.println(c.hashCode());
  }
}
