package com.shiyan1;

/** ϸ��״̬ö����.
 * 
 */
public enum CellState {
    DEAD(0),
    LIVE(1);

  /** ϸ��״ֵ̬.
  * 
  */
  private int value;

  CellState(final int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}

