package com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

/**
 * Base class for deserializers that handle types that are serialized
 * as JSON scalars (non-structured, i.e. non-Object, non-Array, values).
 */
public abstract class StdScalarDeserializer<T> extends StdDeserializer<T>
{
    private static final long serialVersionUID = 1L;

    protected StdScalarDeserializer(Class<?> vc) { super(vc); }
    protected StdScalarDeserializer(JavaType valueType) { super(valueType); }

    // since 2.5
    protected StdScalarDeserializer(StdScalarDeserializer<?> src) { super(src); }
    
    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromScalar(p, ctxt);
    }

    /**
     * Overridden to simply call <code>deserialize()</code> method that does not take value
     * to update, since scalar values are usually non-mergeable.
     */
    @Override // since 2.9
    public T deserialize(JsonParser p, DeserializationContext ctxt, T intoValue) throws IOException {
        return deserialize(p, ctxt);
    }

    /**
     * By default assumption is that scalar types can not be updated: many are immutable
     * values (such as primitives and wrappers)
     */
    @Override // since 2.9
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.FALSE;
    }

}
