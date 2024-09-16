package com.automation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode

public class ObjectData {
    String year;
    double price;
    @JsonProperty("CPU model")
    String CPU_model;
    @JsonProperty("Hard disk size")
    String Hard_disk_size;
}
