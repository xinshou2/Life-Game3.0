package com.shiyan1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



/** LifeGameService测试类.
 * 
 */
public class LifeGameServiceTest {
  /** 成员变量service.
  * 
  */
  public static LifeGameService service = new LifeGameService();
  /** 成员变量array.
   * 
   */
  public static CellArray array = new CellArray(10,10);
  /** 迭代前的数组.
   * 
   */
  public static final int[][] Map = {
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
  /** 迭代后的数组.
   * 
   */
  public static final int[][] MapNext = {
    {0,0,0,0,0,0,0,0,0,1},
    {0,0,1,1,0,0,0,0,0,0},
    {0,0,1,1,0,0,0,0,0,1},
    {0,0,0,0,0,1,1,0,0,0},
    {1,0,1,0,1,0,1,0,1,1},
    {1,0,1,0,1,0,1,0,1,1},
    {0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0},
    {0,1,1,1,1,1,1,1,1,0},
    {0,1,1,1,1,1,1,1,1,0},
  };
  /** 空白的数组.
   * 
   */
  public static final int[][] MapEmpty = {
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
  /** 测试初始化.
   * 
   */
  public void setUp() throws Exception {
    array.setCells(Map);
  }


  @Test
  /** countNeighbor函数测试.
   * 
   */
  public void testcountNeighbor() { 
    final int num = service.countNeighbor(array, 1, 8);
    assertEquals("二者不相等",6,num);
  }
  
  @SuppressWarnings("deprecation")
  @Test
  /** generate函数测试.
   * 
   */
  public void testGenerate() {
    assertEquals("二者不相等",MapNext,service.generate(array));
  }



  @SuppressWarnings("deprecation")
  @Test
  /** 初始化数组函数测试.
   * 
   */
  public void testEmptyInit() {
    array = service.emptyInit(array);
    assertEquals("二者不相等",MapEmpty,array.getCells());
  }

}
