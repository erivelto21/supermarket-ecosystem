package br.com.product.utils;

import com.google.common.base.CaseFormat;

public enum LogKey {
  TOPIC, PARTITION, KEY, MSG_RECEIVED_TIMESTAMP, OFFSET,
  TRADER_ID, TRADER_CODE;

  @Override
  public String toString() {
    return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, this.name().toLowerCase());
  }
}
