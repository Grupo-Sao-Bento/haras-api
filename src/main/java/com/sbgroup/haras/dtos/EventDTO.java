package com.sbgroup.haras.dtos;

import java.sql.Timestamp;

public record EventDTO(String title, Timestamp start, Timestamp end, boolean allDay) {
}
