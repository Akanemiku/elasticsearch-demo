package github.akanemiku.elasticsearchdemo.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
}
