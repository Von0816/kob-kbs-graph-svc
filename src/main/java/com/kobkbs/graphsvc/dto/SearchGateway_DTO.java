package com.kobkbs.graphsvc.dto;

import java.io.Serializable;

public record SearchGateway_DTO(
    String label,
    String requestMapping,
    String id,
    String name
    ) implements Serializable{
}
