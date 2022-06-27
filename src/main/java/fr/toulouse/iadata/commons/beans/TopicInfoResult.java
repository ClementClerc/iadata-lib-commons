package fr.toulouse.iadata.commons.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class TopicInfoResult {
    private Long nbDataTopicIn;
    private Long nbDataTopicOut;
    private Long nbDataTopicDLQ;
}
