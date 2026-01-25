package br.com.sgcore.sgcore_cloud.modules.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalApiErrorPayload implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long occurredAt;
    private Integer httpStatus;
    private String title;
    private String detail;
    private String requestUri;
}
