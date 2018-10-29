package com.shiyan1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



/** LifeGameService������.
 * 
 */
public class LifeGameServiceTest {
  /** ��Ա����service.
  * 
  */
  public static LifeGameService service = new LifeGameService();
  /** ��Ա����array.
   * 
   */
  public static CellArray array = new CellArray(10,10);
  /** ����ǰ������.
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
  /** �����������.
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
  /** �հ׵�����.
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
  /** ���Գ�ʼ��.
   * 
   */
  public void setUp() throws Exception {
    array.setCells(Map);
  }


  @Test
  /** countNeighbor��������.
   * 
   */
  public void testcountNeighbor() { 
    final int num = service.countNeighbor(array, 1, 8);
    assertEquals("���߲����",6,num);
  }
  
  @SuppressWarnings("deprecation")
  @Test
  /** generate��������.
   * 
   */
  public void testGenerate() {
    assertEquals("���߲����",MapNext,service.generate(array));
  }



  @SuppressWarnings("deprecation")
  @Test
  /** ��ʼ�����麯������.
   * 
   */
  public void testEmptyInit() {
    array = service.emptyInit(array);
    assertEquals("���߲����",MapEmpty,array.getCells());
  }

}
