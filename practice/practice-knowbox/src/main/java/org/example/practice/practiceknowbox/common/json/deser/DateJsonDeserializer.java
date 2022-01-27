package org.example.practice.practiceknowbox.common.json.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import org.example.practice.practiceknowbox.common.util.DateUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * 支持毫秒或字符串
 *
 * @author yijiu.chen
 * @date 2020/04/17
 */
public class DateJsonDeserializer extends DateDeserializer {
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            String date = jsonParser.getText().trim();
            try {
                if (date.length() == 19) {
                    return DateUtil.DATETIME.parse(date);
                } else if (date.length() == 10) {
                    return DateUtil.DATE.parse(date);
                } else if (date.length() == 16){
                    return DateUtil.DATETIME_WITHOUT_SECOND.parse(date);
                }
            } catch (ParseException e) {
                // do nothing use default
            }
        } else if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            return new Date(jsonParser.getLongValue());
        }
        return super.deserialize(jsonParser, deserializationContext);
    }

}
