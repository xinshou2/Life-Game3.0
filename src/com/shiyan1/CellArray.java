package com.shiyan1;

import java.awt.Graphics;
import javax.swing.JPanel;




/**.
*ϸ������
*/

public class CellArray extends JPanel implements Runnable {
  private static final long serialVersionUID = -1660060555234651445L;  
  /**.
  *ϸ��
  */
  private int row;
  /**.
  *ϸ����
  */
  private int col;
  /**.
  *ϸ������
  */
  private int[][] cells;
  /**.
  *ÿ����
  */
  private static final int width = 25;
  /**.
  *ÿ��߶�
  */
  private static final transient int height = 25; 
  /**.
  *�ж��Ƿ����ڱ仯
  */
  private boolean isChanging = false; 
  
  /**���캯��.
 * 
 */
  public CellArray(final int row,final int col) {   //�̳�panel�൫û�й��캯���ϵ���ϵ

    this.row = row;
    this.col = col;
    this.cells = new int[row][col];


  }

  public void setRow(final int row) {   //������
    this.row = row;
  }

  public int getRow() {          //�õ���
    return row;
  }

  public void setCol(final int col) {  //������
    this.col = col;
  }

  public int getCol() {         //�õ���
    return col;
  }

  /**�O�ü������M.
   * 
   */
  public void setCells(final int[][] cells) {  
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        this.cells[i][j] = cells[i][j];
      }
    }
  }

  public int[][] getCells() {
    return this.cells;
  }

  /**����ĳ��ϸ����״̬����ֵ��ʾ����.
   * 
   */
  public boolean setCell(final int xdirect,final int ydirect,final int cell) {
    if (xdirect < 0 || this.row <= xdirect || ydirect < 0 || this.col <= ydirect) {
      //���Ҫ���õ�ϸ�����ڱ����ڣ��л���Խ�磩
      return false;
    } else {
      this.cells[xdirect][ydirect] = cell;
      return true;
    }
  }

  /**�õ�ĳ�������Ġ�B.
   * 
   */
  public int getCell(final int xdirect,final int ydirect) {
    if (xdirect < 0 || row <= xdirect || ydirect < 0 || col <= ydirect) {
      return -1;    //���ڱ����ڵ�����ֵͨͨ����Ϊ-1
    }    
    return cells[xdirect][ydirect];
    
  }

  /**�ж���һ����ͼ�͵�ǰ���ǲ�����ȫһ���������ȫһ������Ҫ���Ķ���.
   * 
   */
  public boolean game_equals(final Object obj) {
    if (this == obj) {
      return true;
    }    //ֱ�ӱȽ϶���
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }    //�Ƚ��������Ƿ���ͬ
    final CellArray other = (CellArray)obj;
    if (this.row != other.getRow() || this.col != other.getCol()) {
      //�Ƚ�����������������Ƿ���ͬ
      return false;
    }
    for (int i = 0;i < this.row;++i) {
      for (int j = 0;j < this.col;++j) {
        if (this.cells[i][j] != other.getCell(i, j)) {
          return false;
        }
      }  
    }
    return true;
  }

  /**��Q�����.
   * 
   */
  public void run() {
    final LifeGameService service = new LifeGameService();
    int [][]cell;
    while (true) {
      synchronized (this) {
        while (isChanging) {
          try {
            this.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        repaint();
        try {
          wait(1000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        cell = service.generate(this);

        for (int i = 0; i < this.row; i++) {
          for (int j = 0; j < this.col; j++) {
            this.cells[i][j] = cell[i][j];
          }
        }
      }
    }
  }

  /**.
  *��ͼ����
  */
  protected void paintComponent(final Graphics graph) {     //һ��С��һ��С��
    final int live = 1;
    super.paintComponent(graph);
    for (int i = 0; i < row; i++) {  
      for (int j = 0; j < col; j++) {  
        if (cells[i][j] == live) {  
          graph.fillRect(j * width, i * height, width, height);                     
        } else {  
          graph.drawRect(j * width, i * height, width, height); 
        }  
      }  
    }  
  }  

  /**�̶�ģʽ��ͼ.
  * 
  */
  public static final int[][] Map = {  
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
    {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  };  



  /**�O�ù̶��؈D.
   * 
   */
  public void setMap() {
    isChanging = true;
    synchronized (this) {
      for (int i = 0;i < this.row;i++) {
        for (int j = 0;j < this.col;j++) {
          this.cells[i][j] = Map[i][j];
        }
      }
      isChanging = false;
      this.notifyAll();
    }
  }

  /**�O���S�C�؈D.
   * 
   */
  public void ranMap() {
    final LifeGameService service = new LifeGameService();
    isChanging = true;
    synchronized (this) {
      service.randInit(this);
      isChanging = false;
      this.notifyAll();
    }
  }


}
