package com.zds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private Long sId;
    private Long cId;
    private String score;
}
