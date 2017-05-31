import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Color;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.awt.*;
import java.lang.StringBuffer;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;

public class PanelCreator extends JFrame{
  public static void main(String[] args) {
    PanelCreator p = new PanelCreator("Trend Line Maker");
  }

  public PanelCreator(String s) {
    super(s);
    int width = 720;
    int height = width/16*9;
    setSize(1000,1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel label = new JLabel("Click the button to select a data file of type 'txt' and build a line!");
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
    JPanel panel = new JPanel(new BorderLayout());
    panel.setSize(width,height);
    JButton button = new JButton ("Run Line Maker");
    button.addActionListener(new PlayButtonListener());
    panel.add(label, BorderLayout.NORTH);
    panel.add(button, BorderLayout.CENTER);
    add(panel);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private class PlayButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
      JButton source = (JButton) e.getSource();
      source.setVisible(false);
      File f = null;
      String title = "";
      while(true) {
        JFileChooser chooser = new JFileChooser();
        int choice = chooser.showOpenDialog(null);
        f = chooser.getSelectedFile();
        if(f.getName().indexOf(".txt") == -1) {
          System.out.println("CHOOSE A FILE OF TYPE '.txt'");
          continue;
        } else {
          title = f.getName().substring(0, f.getName().indexOf(".txt"));
          break;
        }
      }
      LineCreator lc = new LineCreator(f, title);
    }
  }

}
