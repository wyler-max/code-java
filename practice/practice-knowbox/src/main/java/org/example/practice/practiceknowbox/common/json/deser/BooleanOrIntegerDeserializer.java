package org.example.practice.practiceknowbox.common.json.deser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

/**
 * 支持true/false to 0/1
 *
 * @author yijiu.chen
 * @date 2020/09/05
 */
public class BooleanOrIntegerDeserializer extends StdScalarDeserializer<Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BooleanOrIntegerDeserializer() {
        super(Integer.class);
    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_TRUE) {
            return 1;
        } else if (t == JsonToken.VALUE_FALSE) {
            return 0;
        }
        if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            return p.getIntValue();
        }

        // other copy
        switch (p.getCurrentTokenId()) {
            // NOTE: caller assumed to usually check VALUE_NUMBER_INT in fast path
            case JsonTokenId.ID_NUMBER_INT:
                return Integer.valueOf(p.getIntValue());
            case JsonTokenId.ID_NUMBER_FLOAT: // coercing may work too
                if (!ctxt.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    _failDoubleToIntCoercion(p, ctxt, "Integer");
                }
                return Integer.valueOf(p.getValueAsInt());
            case JsonTokenId.ID_STRING: // let's do implicit re-parse
                String text = p.getText().trim();
                int len = text.length();
                if (len == 0) {
                    return (Integer)_coerceEmptyString(ctxt, true);
                }
                if (_hasTextualNull(text)) {
                    return (Integer)_coerceTextualNull(ctxt, true);
                }
                _verifyStringForScalarCoercion(ctxt, text);
                try {
                    if (len > 9) {
                        long l = Long.parseLong(text);
                        if (_intOverflow(l)) {
                            return (Integer)ctxt.handleWeirdStringValue(_valueClass, text,
                                String.format("Overflow: numeric value (%s) out of range of Integer (%d - %d)", text,
                                    Integer.MIN_VALUE, Integer.MAX_VALUE));
                        }
                        return Integer.valueOf((int)l);
                    }
                    return Integer.valueOf(NumberInput.parseInt(text));
                } catch (IllegalArgumentException iae) {
                    return (Integer)ctxt.handleWeirdStringValue(_valueClass, text, "not a valid Integer value");
                }
            case JsonTokenId.ID_NULL:
                return (Integer)_coerceNullToken(ctxt, true);
            case JsonTokenId.ID_START_ARRAY:
                return _deserializeFromArray(p, ctxt);
        }
        // Otherwise, no can do:
        return (Integer)ctxt.handleUnexpectedToken(_valueClass, p);
    }

}
