package com.omnia.infrastructure.eventstore.geteventstore;

import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import eventstore.Content;
import eventstore.Event;
import eventstore.EventData;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.serializer.*;
import org.axonframework.serializer.json.JacksonSerializer;
import org.axonframework.upcasting.UpcasterChain;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import static com.omnia.common.util.JsonUtil.*;
import static org.axonframework.serializer.MessageSerializer.serializeMetaData;
import static org.axonframework.serializer.MessageSerializer.serializePayload;
import static org.axonframework.upcasting.UpcastUtils.upcastAndDeserialize;

/**
 * Created by Administrator on 2015/5/8.
 */
public class GetEventStoreEventEntry implements SerializedDomainEventData {

    private static final Logger LOG = LoggerFactory.getLogger(GetEventStoreEventEntry.class);

    private String aggregateIdentifier;
    private String eventIdentifier;
    private long sequenceNumber;
    private String timeStamp;
    private String aggregateType;
    private Object serializedPayload;
    private String payloadType;
    private String payloadRevision;
    private Object serializedMetaData;
    private Serializer serializer;

    public GetEventStoreEventEntry(String aggregateType, DomainEventMessage event, Serializer serializer) {
        this.serializer = serializer;
        this.aggregateType = aggregateType;
        this.aggregateIdentifier = event.getAggregateIdentifier().toString();
        this.sequenceNumber = event.getSequenceNumber();
        this.eventIdentifier = event.getIdentifier();
        Class<?> serializationTarget = String.class;
        if (serializer.canSerializeTo(Event.class)) {
            serializationTarget = Event.class;
        }
        SerializedObject serializedPayloadObject = serializePayload(event, serializer, serializationTarget);
        SerializedObject serializedMetaDataObject = serializeMetaData(event, serializer, serializationTarget);

        this.serializedPayload = serializedPayloadObject.getData();
        this.payloadType = serializedPayloadObject.getType().getName();
        this.payloadRevision = serializedPayloadObject.getType().getRevision();
        this.serializedMetaData = serializedMetaDataObject.getData();
        this.timeStamp = event.getTimestamp().toString();
    }

    public GetEventStoreEventEntry(Event event) {
        this.serializer = new JacksonSerializer();
        EventData eventData = event.data();
        Content content = eventData.data();
        ByteString byteString = (ByteString) content.productElement(0);
        InputStream is = byteString.iterator().asInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            LOG.error("read event error", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LOG.error("close io stream error", e);
            }
        }
        JsonNode map = null;
        try {
            map = parseJsonToJsonNode(sb.toString());
        } catch (IOException e) {
            LOG.error("parse Json To JsonNode error", e);
        }
        assert map != null;
        this.aggregateIdentifier = map.get("aggregateIdentifier") != null ? map.get("aggregateIdentifier").textValue() : null;
        this.eventIdentifier = map.get("eventIdentifier") != null ? map.get("eventIdentifier").textValue() : null;
//        this.serializedMetaData = map.get("metaData") != null ? parseObjectFromJson(map.get("metaData"), "data", "type/name") : null;
//        this.serializedPayload = map.get("payload") != null ? parseObjectFromJson(map.get("payload"), "data", "type/name") : null;
        this.serializedMetaData = map.get("metaData") != null ? findJsonString(map.get("metaData"), "data") : null;
        this.serializedPayload = map.get("payload") != null ? findJsonString(map.get("payload"), "data") : null;
        this.payloadType = map.get("payload") != null ? findJsonString(map.get("payload"), "type/name") : null;
        this.payloadRevision = map.get("payload") != null ? findJsonString(map.get("payload"), "type/revision") : null;
        if("null".equals(payloadRevision)){
            payloadRevision = null;
        }
        this.sequenceNumber = map.get("sequenceNumber") != null ? map.get("sequenceNumber").asLong() : 0L;
        this.timeStamp = map.get("timestamp") != null ? map.get("timestamp").textValue() : null;
    }


    @Override
    public String getEventIdentifier() {
        return this.eventIdentifier;
    }

    @Override
    public Object getAggregateIdentifier() {
        return this.aggregateIdentifier;
    }

    @Override
    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    @Override
    public DateTime getTimestamp() {
        return new DateTime(this.timeStamp);
    }

    @Override
    public SerializedObject getMetaData() {
        return new SerializedMetaData(serializedMetaData, getRepresentationType());
    }

    @Override
    public SerializedObject getPayload() {
        return new SimpleSerializedObject(serializedPayload, getRepresentationType(), payloadType, payloadRevision);
    }

    public List<DomainEventMessage> getDomainEvents(Object aggregateIdentifier,
                                                    UpcasterChain upcasterChain, boolean skipUnknownTypes) {
        return getDomainEvents(aggregateIdentifier, this.serializer,
                upcasterChain, skipUnknownTypes);
    }

    public List<DomainEventMessage> getDomainEvents(Object aggregateIdentifier, Serializer serializer,
                                                    UpcasterChain upcasterChain, boolean skipUnknownTypes) {
        return upcastAndDeserialize(this, aggregateIdentifier, serializer,
                upcasterChain, skipUnknownTypes);
    }


    private Class<?> getRepresentationType() {
        Class<?> representationType = String.class;
        if (serializedPayload instanceof Event) {
            representationType = Event.class;
        }
        return representationType;
    }

}
