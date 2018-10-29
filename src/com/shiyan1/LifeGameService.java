package com.shiyan1;

import java.util.Random;

/**�߼���.
* 
*/
public class LifeGameService {
  /**����ϸ������.
  * 
  */
  private final transient int[] direct = {-1,0,1};
  /**����״̬.
  * 
  */
  private static final CellState cellstate0 = CellState.DEAD;
  /**����״̬.
  * 
  */
  private static final  CellState cellstate1 = CellState.LIVE;
  /**����״ֵ̬.
  * 
  */
  private final transient int dead;
  /**����״ֵ̬.
  * 
  */
  private final transient int live;
  /**���캯��.
   * 
   */
  
  public LifeGameService() {
    this.dead = cellstate0.getValue();
    this.live = cellstate1.getValue();
  }

  /**�����������Ӌ������.
   * 
   */
  public int countNeighbor(final CellArray now,final int xdirect,final int ydirect) { 
    int count = 0;
    for (int i = 0;i < 3;i++) {
      for (int j = 0;j < 3;j++) {
        final int rownum = xdirect + direct[i];
        final int colnum = ydirect + direct[j];
        if (live == now.getCell(rownum,
            colnum)) {
          ++count;
        }
      }
    }
    if (live == now.getCell(xdirect, ydirect)) {
      --count;
    }
    return count;
  }

  /**���®�ǰ�����.
   * 
   */
  public final int[][] generate(final CellArray now) {
    int liveCount;
    final CellArray next = new CellArray(now.getRow(), now.getCol());
    for (int i = 0; i < next.getRow(); ++i) {
      for (int j = 0; j < next.getCol(); ++j) {
        liveCount = this.countNeighbor(now, i, j);
        if (live == now.getCell(i, j) && (liveCount < 2 || liveCount > 3)) { 
          next.setCell(i, j, dead);
        } else if (live == now.getCell(i, j) 
            && 2 <= liveCount && liveCount <= 3) {
          next.setCell(i, j,live);
        } else if (dead == now.getCell(i, j) && 3 == liveCount) {
          next.setCell(i, j, live);
        }
      }
    }
    return next.getCells();
  }

  /**�S�Cһ�������.
   * 
   */
  public CellArray randInit(final CellArray cellularArray) {
    final Random rand = new Random();
    int value;
    for (int i = 0; i < cellularArray.getRow(); ++i) {
      for (int j = 0; j < cellularArray.getCol(); ++j) {
        value = rand.nextInt(2);
        cellularArray.setCell(i, j, value);
      }
    }
    return cellularArray;
  }

  /**��ʼ��һ�������.
   * 
   */
  public CellArray emptyInit(final CellArray cellularArray) {
    for (int i = 0; i < cellularArray.getRow(); ++i) {
      for (int j = 0; j < cellularArray.getCol(); ++j) {
        cellularArray.setCell(i, j, dead);
      }
    }
    return cellularArray;
  }



}
