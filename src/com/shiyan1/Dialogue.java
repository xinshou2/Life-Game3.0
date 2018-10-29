package com.shiyan1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/** ��������.
 * 
 */
public class Dialogue extends JFrame {
  private static final long serialVersionUID = -1660060555234651445L;  
  /** ��Ա����array.
  * 
  */
  private transient CellArray array;

  /**������.
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
    
    final JMenuItem random = options.add("���ģʽ");
    random.addActionListener(dialogue.new RandomActionListener());
    
    
    final JMenuItem fixed = options.add("�̶�ģʽ��������");
    fixed.addActionListener(dialogue.new FixedActionListener());
    
   
    final JMenuItem square = options.add("�˳�");
    square.addActionListener(dialogue.new ExidActionListener());        
    


    dialogue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    dialogue.setSize(1290, 700);
    dialogue.setTitle("Life Game");
    dialogue.setLocationRelativeTo(null);   //�����ڷ�������Ļ����
    dialogue.setVisible(true);
    dialogue.setResizable(false);   //ʹ���ڿɸı��С



  }




  /** �̶�ģʽ�����õ�ͼ.
   * 
   */
  class FixedActionListener implements ActionListener {
    /** ��Ӧ�¼�.
    * 
    */
    public void actionPerformed(final ActionEvent event) {
      array.setMap();
    }
  }

  /** ���ģʽ�����õ�ͼ.
   * 
   */
  class RandomActionListener implements ActionListener {
    /** ��Ӧ�¼�.
    * 
    */
    public void actionPerformed(final ActionEvent event) {
      array.ranMap();
    }
  }

  /** �رմ���.
  * 
  */
  class ExidActionListener implements ActionListener {
    /** ��Ӧ�¼�.
    * 
    */
    public void actionPerformed(final ActionEvent event) {
      dispose();
      System.exit(0);
    }
  }
}

