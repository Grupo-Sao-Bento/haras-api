package com.sbgroup.haras.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeUtil {

  public static Timestamp getCurrentTimestamp() {
    LocalDateTime currentTime = LocalDateTime.now();
    ZoneId countryZone = ZoneId.of("America/Sao_Paulo");
    Instant instant = currentTime.atZone(countryZone).toInstant();

    return Timestamp.from(instant);
  }
  
}
