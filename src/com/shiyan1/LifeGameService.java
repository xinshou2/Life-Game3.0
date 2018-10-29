package com.shiyan1;

import java.util.Random;

/**逻辑类.
* 
*/
public class LifeGameService {
  /**相邻细胞方向.
  * 
  */
  private final transient int[] direct = {-1,0,1};
  /**生存状态.
  * 
  */
  private static final CellState cellstate0 = CellState.DEAD;
  /**死亡状态.
  * 
  */
  private static final  CellState cellstate1 = CellState.LIVE;
  /**死亡状态值.
  * 
  */
  private final transient int dead;
  /**生存状态值.
  * 
  */
  private final transient int live;
  /**构造函数.
   * 
   */
  
  public LifeGameService() {
    this.dead = cellstate0.getValue();
    this.live = cellstate1.getValue();
  }

  /**相鄰細胞存活計數函數.
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

  /**更新當前細胞陣.
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

  /**隨機一個細胞陣.
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

  /**初始化一個細胞陣.
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
