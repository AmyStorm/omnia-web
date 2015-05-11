package com.omnia.infrastructure.eventstore.geteventstore;

import eventstore.Event;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.serializer.SerializedDomainEventData;
import org.axonframework.serializer.SerializedDomainEventMessage;
import org.axonframework.serializer.SerializedMetaData;
import org.axonframework.serializer.SerializedObject;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.SimpleSerializedObject;
import org.axonframework.serializer.UnknownSerializedTypeException;
import org.axonframework.serializer.json.JacksonSerializer;
import org.axonframework.upcasting.SerializedDomainEventUpcastingContext;
import org.axonframework.upcasting.SimpleUpcasterChain;
import org.axonframework.upcasting.UpcastSerializedDomainEventData;
import org.axonframework.upcasting.UpcasterChain;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.serializer.MessageSerializer.serializeMetaData;
import static org.axonframework.serializer.MessageSerializer.serializePayload;
import static org.axonframework.upcasting.UpcastUtils.upcastAndDeserialize;

/**
 * Created by Administrator on 2015/5/8.
 */
public class GetEventStoreEventEntry implements SerializedDomainEventData {

    private static final Logger LOG = LoggerFactory.getLogger(GetEventStoreEventEntry.class);

    private String aggregateIdentifier;
    private long sequenceNumber;
    private String timeStamp;
    private String aggregateType;
    private Object serializedPayload;
    private String payloadType;
    private String payloadRevision;
    private Object serializedMetaData;
    private String eventIdentifier;
    private Serializer eventSerializer;

    public GetEventStoreEventEntry(String aggregateType, DomainEventMessage event, Serializer serializer){
        this.eventSerializer = serializer;
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
                                                    UpcasterChain upcasterChain, boolean skipUnknownTypes){
        return upcastAndDeserialize(this, aggregateIdentifier, this.eventSerializer,
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
