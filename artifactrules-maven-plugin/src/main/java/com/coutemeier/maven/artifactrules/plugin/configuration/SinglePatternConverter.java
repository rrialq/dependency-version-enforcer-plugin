package com.coutemeier.maven.artifactrules.plugin.configuration;

import java.util.regex.Pattern;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;

import com.thoughtworks.xstream.converters.extended.RegexPatternConverter;

public class SinglePatternConverter implements Converter {

    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Pattern pattern = (Pattern) source;
        writer.setValue(pattern.pattern());
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return Pattern.compile(reader.getValue());
    }

    public boolean canConvert(Class type) {
        return type.equals(Pattern.class);
    }
}