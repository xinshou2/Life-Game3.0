package com.shiyan1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/** cellarray测试类.
 * 
 */ 
public class CellarrayTest {

  /** 成员变量cellarray.
  * 
  */   
  private static final CellArray cellarray = new CellArray(10, 10);
  
  
  /** cells数组.
   * 
   */ 
  public static final int[][]cells = {
    {0,0,0,0,0,0,0,0,1,1},
    {0,0,1,1,0,0,1,1,1,1},
    {0,0,1,1,0,0,0,0,1,1},
    {0,0,0,0,0,0,0,0,0,0},
    {1,1,1,1,1,1,1,1,1,1},
    {1,0,1,0,1,0,1,0,1,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {1,0,1,0,1,0,1,0,1,0},
    {0,1,0,1,0,1,0,1,0,1},
  };
    
  /** cellsnext数组.
   * 
   */
  public static final int[][]cellsnext = {
    {0,0,0,0,0,0,0,0,1,1},
    {0,0,1,1,0,0,1,1,1,1},
    {0,0,1,1,0,0,0,0,1,1},
    {0,0,0,0,0,0,0,0,0,0},
    {1,1,1,1,1,1,1,1,1,1},
    {1,0,1,0,1,0,1,0,1,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {1,0,1,0,1,0,1,0,1,0},
    {0,1,0,1,0,1,0,1,0,1},
  };
  
  /** 初始化数组.
   * 
   */
  public static final int[][]cellsempty = {
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
  };

  @Before
  /** 测试前初始化.
   * 
   */
  public void setUp() throws Exception {
    cellarray.setCells(cellsempty);
  }

  @Test
  /** 检测GetCol函数.
   * 
   */
  public void testGetCol() {
    cellarray.setCol(20);
    assertEquals("列数不等于20",20, cellarray.getCol());
  }

  @Test
  /** 检测GetRow函数.
   * 
   */
  public void testGetRow() {
    cellarray.setRow(20);
    assertEquals("行数不等于20",20, cellarray.getRow());
  }
  
  @SuppressWarnings("deprecation")
  @Test
  /** 检测GetCells函数.
   * 
   */
  public void testGetCells() {
    cellarray.setCells(cells);
    assertEquals("两个数组不相等",cells, cellarray.getCells());
  }

  @Test
  /** 检测GetCell函数.
   * 
   */
  public void testGetCell() {
    cellarray.setCell(8, 2, 1);
    assertEquals("这个细胞未存活",1,cellarray.getCell(8, 2));
  }

  @Test
  /** 检测EqualsObject函数.
   * 
   */
  public void testEqualsObject() {
    final CellArray array = new CellArray(10,10);
    final boolean judge = cellarray.equals(array);
    cellarray.setCells(cells);
    array.setCells(cellsnext);
    assertTrue("这是对的",judge);
  }

}
