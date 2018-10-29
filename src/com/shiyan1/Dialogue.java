package com.shiyan1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/** 界面框架类.
 * 
 */
public class Dialogue extends JFrame {
  private static final long serialVersionUID = -1660060555234651445L;  
  /** 成员变量array.
  * 
  */
  private transient CellArray array;

  /**主函數.
   * 
   */
  public static void main(final String[] args) {
    final Dialogue dialogue = new Dialogue();
    dialogue.array = new CellArray(20,43);
    new Thread(dialogue.array).start();
    dialogue.add(dialogue.array);
    
    final JMenuBar menu = new JMenuBar();
    dialogue.setJMenuBar(menu);
    
    final JMenu options = new JMenu("OPTION");
    menu.add(options);
    
    final JMenuItem random = options.add("随机模式");
    random.addActionListener(dialogue.new RandomActionListener());
    
    
    final JMenuItem fixed = options.add("固定模式（姓名）");
    fixed.addActionListener(dialogue.new FixedActionListener());
    
   
    final JMenuItem square = options.add("退出");
    square.addActionListener(dialogue.new ExidActionListener());        
    


    dialogue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    dialogue.setSize(1290, 700);
    dialogue.setTitle("Life Game");
    dialogue.setLocationRelativeTo(null);   //将窗口放置在屏幕中央
    dialogue.setVisible(true);
    dialogue.setResizable(false);   //使窗口可改变大小



  }




  /** 固定模式下设置地图.
   * 
   */
  class FixedActionListener implements ActionListener {
    /** 响应事件.
    * 
    */
    public void actionPerformed(final ActionEvent event) {
      array.setMap();
    }
  }

  /** 随机模式下设置地图.
   * 
   */
  class RandomActionListener implements ActionListener {
    /** 响应事件.
    * 
    */
    public void actionPerformed(final ActionEvent event) {
      array.ranMap();
    }
  }

  /** 关闭窗口.
  * 
  */
  class ExidActionListener implements ActionListener {
    /** 响应事件.
    * 
    */
    public void actionPerformed(final ActionEvent event) {
      dispose();
      System.exit(0);
    }
  }
}

