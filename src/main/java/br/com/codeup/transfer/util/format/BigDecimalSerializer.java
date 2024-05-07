package br.com.codeup.transfer.util.format;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {


    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DecimalFormat df = new DecimalFormat("#,###.00");
        jsonGenerator.writeString(df.format(value));
    }
}