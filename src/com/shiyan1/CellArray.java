package com.shiyan1;

import java.awt.Graphics;
import javax.swing.JPanel;




/**.
*细胞阵列
*/

public class CellArray extends JPanel implements Runnable {
  private static final long serialVersionUID = -1660060555234651445L;  
  /**.
  *细行
  */
  private int row;
  /**.
  *细胞列
  */
  private int col;
  /**.
  *细胞阵列
  */
  private int[][] cells;
  /**.
  *每格宽度
  */
  private static final int width = 25;
  /**.
  *每格高度
  */
  private static final transient int height = 25; 
  /**.
  *判断是否正在变化
  */
  private boolean isChanging = false; 
  
  /**構造函數.
 * 
 */
  public CellArray(final int row,final int col) {   //继承panel类但没有构造函数上的联系

    this.row = row;
    this.col = col;
    this.cells = new int[row][col];


  }

  public void setRow(final int row) {   //设置行
    this.row = row;
  }

  public int getRow() {          //得到行
    return row;
  }

  public void setCol(final int col) {  //设置列
    this.col = col;
  }

  public int getCol() {         //得到列
    return col;
  }

  /**設置細胞數組.
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

  /**设置某个细胞的状态，数值表示生死.
   * 
   */
  public boolean setCell(final int xdirect,final int ydirect,final int cell) {
    if (xdirect < 0 || this.row <= xdirect || ydirect < 0 || this.col <= ydirect) {
      //如果要设置的细胞不在背景内（行或列越界）
      return false;
    } else {
      this.cells[xdirect][ydirect] = cell;
      return true;
    }
  }

  /**得到某個細胞的狀態.
   * 
   */
  public int getCell(final int xdirect,final int ydirect) {
    if (xdirect < 0 || row <= xdirect || ydirect < 0 || col <= ydirect) {
      return -1;    //不在背景内的区域值通通设置为-1
    }    
    return cells[xdirect][ydirect];
    
  }

  /**判断上一代的图和当前代是不是完全一样，如果完全一样则不需要作改动了.
   * 
   */
  public boolean game_equals(final Object obj) {
    if (this == obj) {
      return true;
    }    //直接比较对象
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }    //比较两个类是否相同
    final CellArray other = (CellArray)obj;
    if (this.row != other.getRow() || this.col != other.getCol()) {
      //比较两个对象的行列数是否相同
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

  /**多綫程入口.
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
  *画图函数
  */
  protected void paintComponent(final Graphics graph) {     //一个小格一个小格画
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

  /**固定模式地图.
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



  /**設置固定地圖.
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

  /**設置隨機地圖.
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
