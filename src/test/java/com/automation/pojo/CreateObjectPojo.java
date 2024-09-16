package com.automation.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)   //it ignores the property of id
@EqualsAndHashCode //here we override the equals method of the parent object class


public class CreateObjectPojo {
    String name;
    ObjectData data;
}
