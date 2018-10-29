package com.shiyan1;

/** Ï¸°û×´Ì¬Ã¶¾ÙÀà.
 * 
 */
public enum CellState {
    DEAD(0),
    LIVE(1);

  /** Ï¸°û×´Ì¬Öµ.
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

