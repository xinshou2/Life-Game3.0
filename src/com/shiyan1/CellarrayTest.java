package com.shiyan1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/** cellarray������.
 * 
 */ 
public class CellarrayTest {

  /** ��Ա����cellarray.
  * 
  */   
  private static final CellArray cellarray = new CellArray(10, 10);
  
  
  /** cells����.
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
    
  /** cellsnext����.
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
  
  /** ��ʼ������.
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
  /** ����ǰ��ʼ��.
   * 
   */
  public void setUp() throws Exception {
    cellarray.setCells(cellsempty);
  }

  @Test
  /** ���GetCol����.
   * 
   */
  public void testGetCol() {
    cellarray.setCol(20);
    assertEquals("����������20",20, cellarray.getCol());
  }

  @Test
  /** ���GetRow����.
   * 
   */
  public void testGetRow() {
    cellarray.setRow(20);
    assertEquals("����������20",20, cellarray.getRow());
  }
  
  @SuppressWarnings("deprecation")
  @Test
  /** ���GetCells����.
   * 
   */
  public void testGetCells() {
    cellarray.setCells(cells);
    assertEquals("�������鲻���",cells, cellarray.getCells());
  }

  @Test
  /** ���GetCell����.
   * 
   */
  public void testGetCell() {
    cellarray.setCell(8, 2, 1);
    assertEquals("���ϸ��δ���",1,cellarray.getCell(8, 2));
  }

  @Test
  /** ���EqualsObject����.
   * 
   */
  public void testEqualsObject() {
    final CellArray array = new CellArray(10,10);
    final boolean judge = cellarray.equals(array);
    cellarray.setCells(cells);
    array.setCells(cellsnext);
    assertTrue("���ǶԵ�",judge);
  }

}
