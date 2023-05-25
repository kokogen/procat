package org.koko.procat.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufacturerDTO {
    private Long manufacturerId;
    private String manufacturerName;
    private String countryName;
}
