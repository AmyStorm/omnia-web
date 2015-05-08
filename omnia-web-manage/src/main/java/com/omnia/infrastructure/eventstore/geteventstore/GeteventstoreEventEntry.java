package com.omnia.infrastructure.eventstore.geteventstore;

import org.axonframework.domain.DomainEventMessage;
import org.axonframework.serializer.SerializedDomainEventData;
import org.axonframework.serializer.SerializedDomainEventMessage;
import org.axonframework.serializer.SerializedObject;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.UnknownSerializedTypeException;
import org.axonframework.serializer.json.JacksonSerializer;
import org.axonframework.upcasting.SerializedDomainEventUpcastingContext;
import org.axonframework.upcasting.SimpleUpcasterChain;
import org.axonframework.upcasting.UpcastSerializedDomainEventData;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/8.
 */
public class GetEventStoreEventEntry implements SerializedDomainEventData {

    private static final Logger LOG = LoggerFactory.getLogger(GetEventStoreEventEntry.class);

    @Override
    public String getEventIdentifier() {
        return null;
    }

    @Override
    public Object getAggregateIdentifier() {
        return null;
    }

    @Override
    public long getSequenceNumber() {
        return 0;
    }

    @Override
    public DateTime getTimestamp() {
        return null;
    }

    @Override
    public SerializedObject getMetaData() {
        return null;
    }

    @Override
    public SerializedObject getPayload() {
        return null;
    }

    public List<DomainEventMessage> getDomainEvents(Object aggregateIdentifier){
        Serializer serializer = new JacksonSerializer();
        SerializedDomainEventUpcastingContext context = new SerializedDomainEventUpcastingContext(this, serializer);
        List<SerializedObject> objects = SimpleUpcasterChain.EMPTY.upcast(this.getPayload(), context);
        List<DomainEventMessage> events = new ArrayList<DomainEventMessage>(objects.size());
        for (SerializedObject object : objects) {
            try {
                DomainEventMessage<Object> message = new SerializedDomainEventMessage<Object>(
                        new UpcastSerializedDomainEventData(this, aggregateIdentifier, object),
                        serializer);

                // prevents duplicate deserialization of meta data when it has already been access during upcasting
                if (context.getSerializedMetaData().isDeserialized()) {
                    message = message.withMetaData(context.getSerializedMetaData().getObject());
                }
                events.add(message);
            } catch (UnknownSerializedTypeException e) {
//                if (!skipUnknownTypes) {
//                    throw e;
//                }
                LOG.info("Ignoring event of unknown type {} (rev. {}), as it cannot be resolved to a Class",
                        object.getType().getName(), object.getType().getRevision());
                throw e;
            }
        }
        return events;
    }
}
